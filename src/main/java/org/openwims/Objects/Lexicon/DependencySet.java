/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openwims.Objects.Lexicon;

import java.util.LinkedList;

/**
 *
 * @author jesse
 */
public class DependencySet {
    public LinkedList<Dependency> dependencies;
    public LinkedList<Meaning> meanings;
    public boolean optional;
    public String label;

    public DependencySet(DependencySet toCopy) {
        this.dependencies = new LinkedList();
        this.meanings = new LinkedList();
        this.optional = toCopy.optional;
        this.label = toCopy.label;
        
        for (Dependency dependency : toCopy.dependencies) {
            this.dependencies.add(new Dependency(dependency));
        }
        for (Meaning meaning : toCopy.meanings) {
            this.meanings.add(new Meaning(meaning));
        }
    }
    
    public DependencySet(LinkedList<Dependency> dependencies, LinkedList<Meaning> meanings, boolean optional, String label) {
        this.dependencies = dependencies;
        this.meanings = meanings;
        this.optional = optional;
        this.label = label;
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();

        if (!optional) {
            out.append("*");
        }
        out.append(this.label);
        out.append(":");
        for (Dependency dependency : dependencies) {
            out.append("\n   ");
            out.append(dependency);
        }

        return out.toString();
    }
}
