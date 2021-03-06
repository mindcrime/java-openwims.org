/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openwims.Processors;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import org.openwims.Objects.Disambiguation.Interpretation;
import org.openwims.Objects.Lexicon.Dependency;
import org.openwims.Objects.Lexicon.DependencySet;
import org.openwims.Objects.Lexicon.Sense;
import org.openwims.Objects.Preprocessor.PPDependency;
import org.openwims.Objects.Preprocessor.PPDocument;
import org.openwims.Objects.Preprocessor.PPSentence;
import org.openwims.Objects.Preprocessor.PPToken;
import org.openwims.Processors.Iterators.NaivePossibilityIterator;
import org.openwims.Processors.Iterators.PossibilityIterator;
import org.openwims.Processors.WIMProcessor.WSDProcessor;
import org.openwims.Processors.WIMProcessor.WSEProcessor;
import org.openwims.Serialization.JSONPPDocumentSerializer;

/**
 *
 * @author jesseenglish
 */
public class LandGrabDisambiguation extends WIMProcessor implements WSEProcessor, WSDProcessor {
    
    private PPSentence sentence;
    private LinkedList<PPToken> flattenedTokens;
    private PossibilityIterator iter;
    
    public static void main(String[] args) throws Exception {
        
        PPDocument document = JSONPPDocumentSerializer.deserialize("/Users/jesseenglish/Desktop/test.pp");
        LandGrabDisambiguation d = new LandGrabDisambiguation();
        for (Interpretation interpretation : d.wse(new NaivePossibilityIterator(document))) {
            System.out.println(interpretation.wim());
        }
    }

    public LandGrabDisambiguation() {}
    
    public Interpretation wsd(LinkedList<Interpretation> interpretations) {
        Collections.sort(interpretations);
        Collections.reverse(interpretations);
        return interpretations.getFirst();
    }
    
    public LinkedList<Interpretation> wse(PossibilityIterator iter) {
        this.iter = iter;
        this.sentence = iter.getSentence();
        this.flattenedTokens = this.sentence.listFlattenedTokens();
        
        LinkedList<Interpretation> interpretations = new LinkedList();
        
        while (iter.hasNext()) {
            HashMap<PPToken, Sense> possibility = iter.next();
            try {
                HashMap<PPDependency, Dependency> mapping = landgrab(possibility);
                interpretations.add(new Interpretation(possibility, mapping));
            } catch (IncompleteMappingException e) {
                WIMProcessor.logPossibilityElimination(possibility, e);
            } catch (UnusedNonOptionalSetException e) {
                WIMProcessor.logPossibilityElimination(possibility, e);
            } catch (MutexedOptionalSetsException e) {
                WIMProcessor.logPossibilityElimination(possibility, e);
            }
        }
        
        return interpretations;
    }
    
    private HashMap<PPDependency, Dependency> landgrab(HashMap<PPToken, Sense> possibility) throws IncompleteMappingException, UnusedNonOptionalSetException, MutexedOptionalSetsException {
        HashMap<PPDependency, Dependency> claims = new HashMap();
        
        for (PPToken token : this.flattenedTokens) {
            claim(token, possibility, claims);
        }
        
        //Verify that all ppdeps have been mapped
        if (!claims.keySet().containsAll(this.sentence.listDependencies())) {
            throw new IncompleteMappingException(this.sentence.listDependencies(), claims.keySet());
        }
        
        //Verify that all senses have all non-optional sets used (allow for mutexes)
        for (PPToken token : possibility.keySet()) {
            Sense sense = possibility.get(token);
            
            // - an unused non-optional set is invalid unless it is mutexed with a used non-optional set
            // - a used optional set is invalid if it is mutex with a used set
            
            OUTER:
            for (DependencySet dependencySet : sense.listDependencySets()) {
                if (!dependencySet.optional && !claims.values().containsAll(dependencySet.dependencies)) { //unused non-optional
                    for (DependencySet innerSet : sense.listDependencySets()) {
                        if (!innerSet.optional && claims.values().containsAll(innerSet.dependencies) && sense.areMutexed(dependencySet, innerSet)) {
                            continue OUTER;
                        }
                    }
                    throw new UnusedNonOptionalSetException(token, sense, dependencySet, claims);
                }
                if (dependencySet.optional && claims.values().containsAll(dependencySet.dependencies)) {
                    for (DependencySet innerSet : sense.listDependencySets()) {
                        if (innerSet.optional && claims.values().containsAll(innerSet.dependencies) && sense.areMutexed(dependencySet, innerSet)) {
                            throw new MutexedOptionalSetsException(token, sense, dependencySet, innerSet);
                        }
                    }
                }
            }
            
            for (DependencySet dependencySet : sense.listDependencySets()) {
                if (!dependencySet.optional && !claims.values().containsAll(dependencySet.dependencies)) {
                    throw new UnusedNonOptionalSetException(token, sense, dependencySet, claims);
                }
            }
        }
        
        return claims;
    }
    
    private void claim(PPToken token, HashMap<PPToken, Sense> possibility, HashMap<PPDependency, Dependency> claims) {
        Sense sense = possibility.get(token);

        for (DependencySet dependencySet : sense.listDependencySets()) {
            boolean keepGoing = true;
            while (keepGoing) {
            
                HashMap<PPDependency, Dependency> consideredClaims = new HashMap();
                HashMap<String, PPToken> variables = new HashMap();
                variables.put("SELF", token);

                LinkedList<Dependency> dependencies = new LinkedList(dependencySet.dependencies);
                
                while (dependencies.size() > 0) {
                    Dependency dependency = popDependency(dependencies, variables);
                    
                    for (PPDependency ppDependency : sentence.listDependencies()) {
                        if (claims.containsKey(ppDependency)) {
                            continue;
                        }
                        if (doDependenciesMatch(dependency, ppDependency, possibility, variables)) {
                            consideredClaims.put(ppDependency, dependency);
                            variables.put(dependency.governor, ppDependency.getGovernor());
                            variables.put(dependency.dependent, ppDependency.getDependent());
                        }
                    }
                }

                //Add to official claims only if the dependency set is fully satisfied
                if (consideredClaims.values().containsAll(dependencySet.dependencies)) {
                    claims.putAll(consideredClaims);
                    keepGoing = true;
                } else {
                    keepGoing = false;
                }
            
            }
        }
    }
    
    private Dependency popDependency(LinkedList<Dependency> dependencies, HashMap<String, PPToken> variables) {
        Dependency next = null;
        for (Dependency dependency : dependencies) {
            if (variables.containsKey(dependency.governor)) {
                next = dependency;
                break;
            }
        }
        if (next == null) {
            next = dependencies.getFirst();
        }
        dependencies.remove(next);
        return next;
    }
    
    private boolean doDependenciesMatch(Dependency dependency, PPDependency ppDependency, HashMap<PPToken, Sense> possibility, HashMap<String, PPToken> variables) {
        //Verify types match
        if (!dependency.type.equalsIgnoreCase(ppDependency.getType())) {
            return false;
        }
        
        //if either variable (gov/dep) is spoken for, then the tokens must match
        if (variables.containsKey(dependency.governor) && variables.get(dependency.governor) != ppDependency.getGovernor()) {
            return false;
        }
        if (variables.containsKey(dependency.dependent) && variables.get(dependency.dependent) != ppDependency.getDependent()) {
            return false;
        }
        
        //Verify expectations match (excluding any variable already mapped)
        if (!variables.containsKey(dependency.governor)) {
            if (!doesPossibilitySatisfyExpectations(ppDependency.getGovernor(), possibility, dependency.expectations)) {
//                iter.doNotUseSenses(ppDependency.getGovernor(), possibility.get(ppDependency.getGovernor()), ppDependency.getDependent(), possibility.get(ppDependency.getDependent()));
                return false;
            }
        }
        if (!variables.containsKey(dependency.dependent)) {
            if (!doesPossibilitySatisfyExpectations(ppDependency.getDependent(), possibility, dependency.expectations)) {
//                iter.doNotUseSenses(ppDependency.getGovernor(), possibility.get(ppDependency.getGovernor()), ppDependency.getDependent(), possibility.get(ppDependency.getDependent()));
                return false;
            }
        }
        
        return true;
    }
    
    private class IncompleteMappingException extends Exception {
        
        private Collection<PPDependency> inputDependencies;
        private Collection<PPDependency> claimedDependencies;

        public IncompleteMappingException(Collection<PPDependency> inputDependencies, Collection<PPDependency> claimedDependencies) {
            this.inputDependencies = inputDependencies;
            this.claimedDependencies = claimedDependencies;
        }

        @Override
        public String toString() {
            HashSet<PPDependency> inputs = new HashSet(inputDependencies);
            inputs.removeAll(claimedDependencies);
            
            return "no matches found for inputs: " + inputs;
        }
        
    }
    
    private class UnusedNonOptionalSetException extends Exception {
        
        private PPToken token;
        private Sense sense;
        private DependencySet dependencySet;
        private HashMap<PPDependency, Dependency> claims;

        public UnusedNonOptionalSetException(PPToken token, Sense sense, DependencySet dependencySet, HashMap<PPDependency, Dependency> claims) {
            this.token = token;
            this.sense = sense;
            this.dependencySet = dependencySet;
            this.claims = claims;
        }

        public LinkedList<Dependency> missingDependencies() {
            LinkedList<Dependency> missing = new LinkedList(dependencySet.dependencies);
            missing.removeAll(claims.values());
            return missing;
        }
        
        @Override
        public String toString() {
            return token.anchor() + " (" + sense.getId() + ") found no match for NON-OPTIONAL dependency set " + dependencySet;
        }
        
    }
    
    private class MutexedOptionalSetsException extends Exception {
        
        private PPToken token;
        private Sense sense;
        private DependencySet dependencySetA;
        private DependencySet dependencySetB;

        public MutexedOptionalSetsException(PPToken token, Sense sense, DependencySet dependencySetA, DependencySet dependencySetB) {
            this.token = token;
            this.sense = sense;
            this.dependencySetA = dependencySetA;
            this.dependencySetB = dependencySetB;
        }

        @Override
        public String toString() {
            return token.anchor() + " (" + sense.getId() + ") found mutexed matches for OPTIONAL dependency set " + dependencySetA + " and " + dependencySetB;
        }
        
    }
    
}
