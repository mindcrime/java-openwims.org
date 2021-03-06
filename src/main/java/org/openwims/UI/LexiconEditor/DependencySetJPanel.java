/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openwims.UI.LexiconEditor;

import com.jesseenglish.swingftfy.extensions.FLabel;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import org.openwims.Objects.Lexicon.Dependency;
import org.openwims.Objects.Lexicon.DependencySet;
import org.openwims.Objects.Lexicon.Expectation;
import org.openwims.Objects.Lexicon.Meaning;
import org.openwims.Objects.Lexicon.Sense;
import org.openwims.WIMGlobals;

/**
 *
 * @author jesseenglish
 */
public class DependencySetJPanel extends javax.swing.JPanel {

    private static String[] validTypes = new String[] { "nsubj", "dobj", "prep", "pobj", "det", "cop", "nsubjpass", "advmod", "dep" };
    
    private static BufferedImage DELETE = null;
    private static BufferedImage DELETE_OVER = null;
    
    private static BufferedImage ADD = null;
    private static BufferedImage ADD_OVER = null;
    
    private DependencySet dependencySet;
    private HashMap<String, Color> variableColors;
    private Color[] colors = new Color[] { new Color(189, 76, 70), new Color(70, 94, 189), new Color(48, 128, 32), new Color(142, 58, 145), new Color(58, 145, 145), new Color(171, 167, 58) };
    
    /**
     * Creates new form DependencySetJPanel
     */
    public DependencySetJPanel(DependencySet dependencySet) {
        initComponents();
        this.dependencySet = dependencySet;
        
        if (DELETE == null) {
            try {
                DELETE = ImageIO.read(DependencySetJPanel.class.getResourceAsStream("/images/glyphicons_197_remove.png"));
                DELETE_OVER = ImageIO.read(DependencySetJPanel.class.getResourceAsStream("/images/glyphicons_197_remove_OVER.png"));
                
                ADD = ImageIO.read(DependencySetJPanel.class.getResourceAsStream("/images/glyphicons_190_circle_plus.png"));
                ADD_OVER = ImageIO.read(DependencySetJPanel.class.getResourceAsStream("/images/glyphicons_190_circle_plus_OVER.png"));
            } catch (IOException ex) {
                Logger.getLogger(DependencySetJPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        refresh();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        HeaderJPanel = new javax.swing.JPanel();
        OptionalJLabel = new javax.swing.JLabel();
        OptionalFLabel = new com.jesseenglish.swingftfy.extensions.FLabel();
        AddDependencyJLabel = new com.jesseenglish.swingftfy.extensions.FLabel();
        AddMeaningJLabel = new com.jesseenglish.swingftfy.extensions.FLabel();
        TitleJTextField = new javax.swing.JTextField();
        ContentsJPanel = new javax.swing.JPanel();

        setLayout(new java.awt.GridBagLayout());

        HeaderJPanel.setBackground(new java.awt.Color(204, 204, 204));
        HeaderJPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                HeaderJPanelMouseReleased(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                HeaderJPanelMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                HeaderJPanelMouseEntered(evt);
            }
        });
        HeaderJPanel.setLayout(new java.awt.GridBagLayout());

        OptionalJLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        OptionalJLabel.setText("+");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        HeaderJPanel.add(OptionalJLabel, gridBagConstraints);

        OptionalFLabel.setText("optional");
        OptionalFLabel.setBold(false);
        OptionalFLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                OptionalFLabelMouseReleased(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                OptionalFLabelMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                OptionalFLabelMouseEntered(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 5;
        HeaderJPanel.add(OptionalFLabel, gridBagConstraints);

        AddDependencyJLabel.setText("+syn");
        AddDependencyJLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                AddDependencyJLabelMouseReleased(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                AddDependencyJLabelMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                AddDependencyJLabelMouseEntered(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 5;
        HeaderJPanel.add(AddDependencyJLabel, gridBagConstraints);

        AddMeaningJLabel.setText("+sem");
        AddMeaningJLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                AddMeaningJLabelMouseReleased(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                AddMeaningJLabelMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                AddMeaningJLabelMouseEntered(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 5;
        HeaderJPanel.add(AddMeaningJLabel, gridBagConstraints);

        TitleJTextField.setBackground(new java.awt.Color(204, 204, 204));
        TitleJTextField.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        TitleJTextField.setText("WITH-INSTRUMENT (this is removed)");
        TitleJTextField.setBorder(null);
        TitleJTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TitleJTextFieldKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.weightx = 0.1;
        HeaderJPanel.add(TitleJTextField, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        add(HeaderJPanel, gridBagConstraints);

        ContentsJPanel.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 0.1;
        add(ContentsJPanel, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void OptionalFLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OptionalFLabelMouseEntered
        this.OptionalFLabel.setBold(true);
        this.validate();
        this.repaint();
    }//GEN-LAST:event_OptionalFLabelMouseEntered

    private void OptionalFLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OptionalFLabelMouseExited
        this.OptionalFLabel.setBold(false);
        this.validate();
        this.repaint();
    }//GEN-LAST:event_OptionalFLabelMouseExited

    private void OptionalFLabelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OptionalFLabelMouseReleased
        this.dependencySet.optional = !this.dependencySet.optional;
        if (this.dependencySet.optional) {
            this.OptionalFLabel.setText("optional");
            this.OptionalJLabel.setVisible(true);
        } else {
            this.OptionalFLabel.setText("required");
            this.OptionalJLabel.setVisible(false);
        }
        this.validate();
        this.repaint();
    }//GEN-LAST:event_OptionalFLabelMouseReleased

    private void HeaderJPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HeaderJPanelMouseEntered
        this.HeaderJPanel.setBackground(new Color(180, 180, 180));
        this.validate();
        this.repaint();
    }//GEN-LAST:event_HeaderJPanelMouseEntered

    private void HeaderJPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HeaderJPanelMouseExited
        this.HeaderJPanel.setBackground(new Color(204, 204, 204));
        this.validate();
        this.repaint();
    }//GEN-LAST:event_HeaderJPanelMouseExited

    private void HeaderJPanelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HeaderJPanelMouseReleased
        this.ContentsJPanel.setVisible(!this.ContentsJPanel.isVisible());
        this.validate();
        this.repaint();
    }//GEN-LAST:event_HeaderJPanelMouseReleased

    private void AddDependencyJLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddDependencyJLabelMouseEntered
        this.AddDependencyJLabel.setBold(true);
        this.validate();
        this.repaint();
    }//GEN-LAST:event_AddDependencyJLabelMouseEntered

    private void AddDependencyJLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddDependencyJLabelMouseExited
        this.AddDependencyJLabel.setBold(false);
        this.validate();
        this.repaint();
    }//GEN-LAST:event_AddDependencyJLabelMouseExited

    private void AddDependencyJLabelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddDependencyJLabelMouseReleased
        this.dependencySet.dependencies.add(new Dependency("   ", "   ", "   ", new LinkedList()));
        this.refresh();
    }//GEN-LAST:event_AddDependencyJLabelMouseReleased

    private void AddMeaningJLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddMeaningJLabelMouseEntered
        this.AddMeaningJLabel.setBold(true);
        this.validate();
        this.repaint();
    }//GEN-LAST:event_AddMeaningJLabelMouseEntered

    private void AddMeaningJLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddMeaningJLabelMouseExited
        this.AddMeaningJLabel.setBold(false);
        this.validate();
        this.repaint();
    }//GEN-LAST:event_AddMeaningJLabelMouseExited

    private void AddMeaningJLabelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddMeaningJLabelMouseReleased
        this.dependencySet.meanings.add(new Meaning("   ", "   ", "   "));
        this.refresh();
    }//GEN-LAST:event_AddMeaningJLabelMouseReleased

    private void TitleJTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TitleJTextFieldKeyReleased
        this.dependencySet.label = this.TitleJTextField.getText().trim();
    }//GEN-LAST:event_TitleJTextFieldKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.jesseenglish.swingftfy.extensions.FLabel AddDependencyJLabel;
    private com.jesseenglish.swingftfy.extensions.FLabel AddMeaningJLabel;
    private javax.swing.JPanel ContentsJPanel;
    private javax.swing.JPanel HeaderJPanel;
    private com.jesseenglish.swingftfy.extensions.FLabel OptionalFLabel;
    private javax.swing.JLabel OptionalJLabel;
    private javax.swing.JTextField TitleJTextField;
    // End of variables declaration//GEN-END:variables

    protected JPanel getHeader() {
        return this.HeaderJPanel;
    }
    
    private void refresh() {
        this.variableColors = new HashMap();
        
        this.TitleJTextField.setText(dependencySet.label);
        if (this.dependencySet.optional) {
            this.OptionalFLabel.setText("optional");
            this.OptionalJLabel.setVisible(true);
        } else {
            this.OptionalFLabel.setText("required");
            this.OptionalJLabel.setVisible(false);
        }
        
        this.ContentsJPanel.removeAll();
        this.ContentsJPanel.setBackground(Color.WHITE);
        
        {
            this.HeaderJPanel.remove(this.TitleJTextField);
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 1;
            c.gridy = 0;
            c.weightx = 0.1;
            c.anchor = GridBagConstraints.WEST;
            c.insets = new Insets(0, 5, 0, 0);
            this.HeaderJPanel.add(new DependencySetTitleJTextField(dependencySet), c);
        }
        
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = -1;
        
        for (Dependency dependency : dependencySet.dependencies) {
            c.gridx = 0;
            c.gridy++;
            c.weightx = 0.0;
            c.weighty = 0.0;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.insets = new Insets(1, 5, 0, 0);
            this.ContentsJPanel.add(new DependencyJPanel(dependency), c);
            
            for (Expectation expectation : dependency.expectations) {
                c.gridx = 0;
                c.gridy++;
                c.weightx = 0.0;
                c.weighty = 0.0;
                c.fill = GridBagConstraints.HORIZONTAL;
                c.insets = new Insets(1, 21, 0, 0);
                this.ContentsJPanel.add(new ExpectationJPanel(dependency, expectation), c);
            }
        }
        
        for (Meaning meaning : dependencySet.meanings) {
            c.gridx = 0;
            c.gridy++;
            c.weightx = 0.0;
            c.weighty = 0.0;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.insets = new Insets(1, 5, 0, 0);
            this.ContentsJPanel.add(new MeaningJPanel(meaning), c);
        }
        
        c.gridy++;
        c.weightx = 0.1;
        c.weighty = 0.1;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(0, 0, 0, 0);
        
        JPanel filler = new JPanel();
        filler.setBackground(Color.WHITE);
        
        this.ContentsJPanel.add(filler, c);
        
        this.validate();
        this.repaint();
    }
    
    private Color colorForVariable(String variable) {
        if (this.variableColors.containsKey(variable)) {
            return this.variableColors.get(variable);
        }
        
        cleanColors();
        Color next = nextColor();
        this.variableColors.put(variable, next);
        return next;
    }
    
    private void cleanColors() {
        LinkedList<String> toRemove = new LinkedList();
        VARLOOP:
        for (String variable : this.variableColors.keySet()) {
            for (Dependency dependency : this.dependencySet.dependencies) {
                if (dependency.governor.equals(variable)) {
                    continue VARLOOP;
                }
                if (dependency.dependent.equals(variable)) {
                    continue VARLOOP;
                }
            }
            for (Meaning meaning : this.dependencySet.meanings) {
                if (meaning.target.equals(variable)) {
                    continue VARLOOP;
                }
                if (meaning.wim.equals(variable)) {
                    continue VARLOOP;
                }
            }
            
            toRemove.add(variable);
        }
        
        for (String variable : toRemove) {
            this.variableColors.remove(variable);
        }
    }
    
    private Color nextColor() {
        for (Color color : colors) {
            if (!this.variableColors.containsValue(color)) {
                return color;
            }
        }
        
        return Color.DARK_GRAY;
    }
    
    private class DependencyJPanel extends JPanel {
        
        private Dependency dependency;

        public DependencyJPanel(Dependency dependency) {
            this.dependency = dependency;
            this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
            this.setBackground(Color.WHITE);
            
            this.add(new DeleteDependencyJLabel(dependency));
            this.add(new JLabel(" "));
            this.add(new DependencyTypeJTextField(dependency));
            this.add(new JLabel(" ( "));
            this.add(new DependencyGovernorJTextField(dependency));
            this.add(new JLabel(" , "));
            this.add(new DependencyDependentJTextField(dependency));
            this.add(new JLabel(" ) "));
            this.add(new AddExpectationFLabel(dependency));
        }
        
    }
    
    private class MeaningJPanel extends JPanel {
        
        private Meaning meaning;

        public MeaningJPanel(Meaning meaning) {
            this.meaning = meaning;
            this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
            this.setBackground(Color.WHITE);
            
            this.add(new DeleteMeaningJLabel(meaning));
            this.add(new JLabel(" "));
            this.add(new MeaningTargetJTextField(meaning));
            this.add(new JLabel(" . "));
            this.add(new MeaningRelationJTextField(meaning));
            this.add(new JLabel(" = "));
            this.add(new MeaningWIMJTextField(meaning));
        }
        
    }
    
    private class ExpectationJPanel extends JPanel {
        
        private Dependency dependency;
        private Expectation expectation;

        public ExpectationJPanel(Dependency dependency, Expectation expectation) {
            this.dependency = dependency;
            this.expectation = expectation;
            this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
            this.setBackground(Color.WHITE);
            
            this.add(new DeleteExpectationJLabel(dependency, expectation));
            this.add(new JLabel(" "));
            this.add(new SpecificationJTextField(expectation));
            this.add(new JLabel(" = "));
            this.add(new ExpectationJTextField(expectation));
        }
        
    }
    
    private class DependencySetTitleJTextField extends DiscreteJTextField implements KeyListener {
        
        private DependencySet dependencySet;
        
        public DependencySetTitleJTextField(DependencySet dependencySet) {
            super(dependencySet.label);
            this.dependencySet = dependencySet;
            this.setBackground(DependencySetJPanel.this.HeaderJPanel.getBackground());
            this.addKeyListener(this);
            this.setBorder(null);
            this.setFont(new java.awt.Font("Arial", 1, 14));
        }

        public void keyTyped(KeyEvent e) {}

        public void keyPressed(KeyEvent e) {}

        public void keyReleased(KeyEvent e) {
            this.dependencySet.label = this.getText().trim();
        }
        
    }

    private class DependencyTypeJTextField extends DiscreteJTextField implements KeyListener {
        
        private Dependency dependency;
        
        public DependencyTypeJTextField(Dependency dependency) {
            super(dependency.type);
            this.dependency = dependency;
            this.addKeyListener(this);
        }

        @Override
        protected boolean error() {
            for (String validType : validTypes) {
                if (validType.equals(this.getText())) {
                    return false;
                }
            }
            
            return true;
        }

        public void keyTyped(KeyEvent e) {}

        public void keyPressed(KeyEvent e) {}

        public void keyReleased(KeyEvent e) {
            this.dependency.type = this.getText().trim();
            this.setText(this.getText().trim());
            this.validate();
            this.repaint();
        }
        
    }
    
    private class DependencyGovernorJTextField extends DiscreteJTextField implements KeyListener {
        
        private Dependency dependency;
        
        public DependencyGovernorJTextField(Dependency dependency) {
            super(dependency.governor);
            this.dependency = dependency;
            this.setForeground(DependencySetJPanel.this.colorForVariable(dependency.governor));
            this.addKeyListener(this);
        }

        @Override
        protected boolean warning() {
            if (dependency.governor.equals("SELF")) {
                return false;
            }
            
            int usages = 0;
            for (Dependency d : DependencySetJPanel.this.dependencySet.dependencies) {
                if (d.governor.equals(dependency.governor)) {
                    usages++;
                }
                if (d.dependent.equals(dependency.governor)) {
                    usages++;
                }
            }
            for (Meaning meaning : DependencySetJPanel.this.dependencySet.meanings) {
                if (meaning.target.equals(dependency.governor)) {
                    usages++;
                }
                if (meaning.wim.equals(dependency.governor)) {
                    usages++;
                }
            }
            
            return usages == 0 || usages == 1;
        }

        public void keyTyped(KeyEvent e) {}

        public void keyPressed(KeyEvent e) {}

        public void keyReleased(KeyEvent e) {
            this.dependency.governor = this.getText().trim();
            this.setText(this.getText().trim());
            this.setForeground(DependencySetJPanel.this.colorForVariable(dependency.governor));
            this.validate();
            this.repaint();
        }
        
    }
    
    private class DependencyDependentJTextField extends DiscreteJTextField implements KeyListener {
        
        private Dependency dependency;
        
        public DependencyDependentJTextField(Dependency dependency) {
            super(dependency.dependent);
            this.dependency = dependency;
            this.setForeground(DependencySetJPanel.this.colorForVariable(dependency.dependent));
            this.addKeyListener(this);
        }

        @Override
        protected boolean warning() {
            if (dependency.dependent.equals("SELF")) {
                return false;
            }
            
            int usages = 0;
            for (Dependency d : DependencySetJPanel.this.dependencySet.dependencies) {
                if (d.governor.equals(dependency.dependent)) {
                    usages++;
                }
                if (d.dependent.equals(dependency.dependent)) {
                    usages++;
                }
            }
            for (Meaning meaning : DependencySetJPanel.this.dependencySet.meanings) {
                if (meaning.target.equals(dependency.dependent)) {
                    usages++;
                }
                if (meaning.wim.equals(dependency.dependent)) {
                    usages++;
                }
            }
            
            return usages == 0 || usages == 1;
        }

        public void keyTyped(KeyEvent e) {}

        public void keyPressed(KeyEvent e) {}

        public void keyReleased(KeyEvent e) {
            this.dependency.dependent = this.getText().trim();
            this.setText(this.getText().trim());
            this.setForeground(DependencySetJPanel.this.colorForVariable(dependency.dependent));
            this.validate();
            this.repaint();
        }
        
    }
    
    private class MeaningTargetJTextField extends DiscreteJTextField implements KeyListener {
        
        private Meaning meaning;
        
        public MeaningTargetJTextField(Meaning meaning) {
            super(meaning.target);
            this.meaning = meaning;
            this.setForeground(DependencySetJPanel.this.colorForVariable(meaning.target));
            this.addKeyListener(this);
        }

        @Override
        protected boolean error() {
            if (this.getText().equals("SELF")) {
                return false;
            }
            
            for (Dependency dependency : DependencySetJPanel.this.dependencySet.dependencies) {
                if (dependency.governor.equals(this.getText())) {
                    return false;
                }
                if (dependency.dependent.equals(this.getText())) {
                    return false;
                }
            }
            
            return true;
        }

        public void keyTyped(KeyEvent e) {}

        public void keyPressed(KeyEvent e) {}

        public void keyReleased(KeyEvent e) {
            meaning.target = this.getText().trim();
            this.setText(this.getText().trim());
            this.setForeground(DependencySetJPanel.this.colorForVariable(meaning.target));
            this.validate();
            this.repaint();
        }
        
    }
    
    private class MeaningRelationJTextField extends DiscreteJTextField implements KeyListener {
        
        private Meaning meaning;
        private String[] relations = new String[] { "agent", "theme", "instrument", "beneficiary", "possesses", "location", "time", "destination", "purpose", "date", "duration" };
        
        public MeaningRelationJTextField(Meaning meaning) {
            super(meaning.relation);
            this.meaning = meaning;
            this.addKeyListener(this);
        }

        @Override
        protected boolean warning() {
            for (String relation : relations) {
                if (relation.equals(this.meaning.relation)) {
                    return false;
                }
            }
            
            return true;
        }

        public void keyTyped(KeyEvent e) {}

        public void keyPressed(KeyEvent e) {}

        public void keyReleased(KeyEvent e) {
            this.meaning.relation = this.getText().trim();
            this.setText(this.getText().trim());
            this.validate();
            this.repaint();
        }
        
    }
    
    private class MeaningWIMJTextField extends DiscreteJTextField implements KeyListener {
        
        private Meaning meaning;
        
        public MeaningWIMJTextField(Meaning meaning) {
            super(meaning.wim);
            this.meaning = meaning;
            this.setForeground(DependencySetJPanel.this.colorForVariable(meaning.wim));
            this.addKeyListener(this);
        }
        
        @Override
        protected boolean error() {
            if (this.getText().equals("SELF")) {
                return false;
            }
            
            if (this.getText().startsWith("\"") && this.getText().endsWith("\"") && this.getText().length() > 1) {
                return false;
            }
            
            for (Dependency dependency : DependencySetJPanel.this.dependencySet.dependencies) {
                if (dependency.governor.equals(this.getText())) {
                    return false;
                }
                if (dependency.dependent.equals(this.getText())) {
                    return false;
                }
            }
            
            return true;
        }

        public void keyTyped(KeyEvent e) {}

        public void keyPressed(KeyEvent e) {}

        public void keyReleased(KeyEvent e) {
            this.meaning.wim = this.getText().trim();
            this.setText(this.getText().trim());
            this.setForeground(DependencySetJPanel.this.colorForVariable(meaning.wim));
            this.validate();
            this.repaint();
        }
        
    }
    
    private class SpecificationJTextField extends DiscreteJTextField implements KeyListener {
        
        private Expectation expectation;
        
        public SpecificationJTextField(Expectation expectation) {
            super(expectation.getSpecification());
            this.expectation = expectation;
            this.addKeyListener(this);
        }

        @Override
        protected boolean error() {
            if (this.getText().trim().equals("pos")) {
                return false;
            } else if (this.getText().trim().equals("ont")) {
                return false;
            } else if (this.getText().trim().equals("token")) {
                return false;
            } else if (this.getText().trim().equals("micro")) {
                return false;
            }
            
            return true;
        }
        
        public void keyTyped(KeyEvent e) {}

        public void keyPressed(KeyEvent e) {}

        public void keyReleased(KeyEvent e) {
            this.expectation.setSpecification(this.getText().trim());
            this.setText(this.getText().trim());
            this.validate();
            this.repaint();
        }
        
    }
    
    private class ExpectationJTextField extends DiscreteJTextField implements KeyListener {
        
        private Expectation expectation;
        
        public ExpectationJTextField(Expectation expectation) {
            super(expectation.getExpectation());
            this.expectation = expectation;
            this.addKeyListener(this);
        }
        
        @Override
        protected boolean error() {
            if (expectation.getSpecification().equals("pos")) {
                return !WIMGlobals.tagmaps().doesTagExist(this.getText().trim());
            } else if (expectation.getSpecification().equals("ont")) {
                return !WIMGlobals.ontology().doesConceptExist(this.getText().trim());
            } else if (expectation.getSpecification().equals("token")) {
                return false;
            } else if (expectation.getSpecification().equals("micro")) {
                return false;
            }
            
            return true;
        }
        
        public void keyTyped(KeyEvent e) {}

        public void keyPressed(KeyEvent e) {}

        public void keyReleased(KeyEvent e) {
            this.expectation.setExpectation(this.getText().trim());
            this.setText(this.getText().trim());
            this.validate();
            this.repaint();
        }
        
    }
    
    private class AddExpectationFLabel extends FLabel implements MouseListener {
        
        private Dependency dependency;

        public AddExpectationFLabel(Dependency dependency) {
            this.dependency = dependency;
            this.setIcon(new ImageIcon(ADD.getScaledInstance(12, 12, Image.SCALE_SMOOTH)));
            this.addMouseListener(this);
        }

        public void mouseClicked(MouseEvent e) {}

        public void mousePressed(MouseEvent e) {}

        public void mouseReleased(MouseEvent e) {
            dependency.expectations.add(new Expectation("   ", "   "));
            DependencySetJPanel.this.refresh();
            DependencySetJPanel.this.getParent().validate();
            DependencySetJPanel.this.getParent().validate();
        }

        public void mouseEntered(MouseEvent e) {
            this.setIcon(new ImageIcon(ADD_OVER.getScaledInstance(12, 12, Image.SCALE_SMOOTH)));
            DependencySetJPanel.this.validate();
            DependencySetJPanel.this.repaint();
        }

        public void mouseExited(MouseEvent e) {
            this.setIcon(new ImageIcon(ADD.getScaledInstance(12, 12, Image.SCALE_SMOOTH)));
            DependencySetJPanel.this.validate();
            DependencySetJPanel.this.repaint();
        }
        
    }
    
    private class DeleteDependencyJLabel extends JLabel implements MouseListener {
        
        private Dependency dependency;

        public DeleteDependencyJLabel(Dependency dependency) {
            this.dependency = dependency;
            this.addMouseListener(this);
            this.setIcon(new ImageIcon(DELETE.getScaledInstance(12, 12, Image.SCALE_SMOOTH)));
        }

        public void mouseClicked(MouseEvent e) {}

        public void mousePressed(MouseEvent e) {}

        public void mouseReleased(MouseEvent e) {
            DependencySetJPanel.this.dependencySet.dependencies.remove(dependency);
            DependencySetJPanel.this.refresh();
            DependencySetJPanel.this.getParent().validate();
            DependencySetJPanel.this.getParent().repaint();
        }

        public void mouseEntered(MouseEvent e) {
            this.setIcon(new ImageIcon(DELETE_OVER.getScaledInstance(12, 12, Image.SCALE_SMOOTH)));
        }

        public void mouseExited(MouseEvent e) {
            this.setIcon(new ImageIcon(DELETE.getScaledInstance(12, 12, Image.SCALE_SMOOTH)));
        }
        
    }
    
    private class DeleteMeaningJLabel extends JLabel implements MouseListener {
        
        private Meaning meaning;

        public DeleteMeaningJLabel(Meaning meaning) {
            this.meaning = meaning;
            this.setIcon(new ImageIcon(DELETE.getScaledInstance(12, 12, Image.SCALE_SMOOTH)));
            this.addMouseListener(this);
        }

        public void mouseClicked(MouseEvent e) {}

        public void mousePressed(MouseEvent e) {}

        public void mouseReleased(MouseEvent e) {
            DependencySetJPanel.this.dependencySet.meanings.remove(meaning);
            DependencySetJPanel.this.refresh();
            DependencySetJPanel.this.getParent().validate();
            DependencySetJPanel.this.getParent().repaint();
        }

        public void mouseEntered(MouseEvent e) {
            this.setIcon(new ImageIcon(DELETE_OVER.getScaledInstance(12, 12, Image.SCALE_SMOOTH)));
        }

        public void mouseExited(MouseEvent e) {
            this.setIcon(new ImageIcon(DELETE.getScaledInstance(12, 12, Image.SCALE_SMOOTH)));
        }
        
    }
    
    public class DeleteExpectationJLabel extends JLabel implements MouseListener {
        
        private Dependency dependency;
        private Expectation expectation;

        public DeleteExpectationJLabel(Dependency dependency, Expectation expectation) {
            this.dependency = dependency;
            this.expectation = expectation;
            this.setIcon(new ImageIcon(DELETE.getScaledInstance(12, 12, Image.SCALE_SMOOTH)));
            this.addMouseListener(this);
        }
        
        public void mouseClicked(MouseEvent e) {}

        public void mousePressed(MouseEvent e) {}

        public void mouseReleased(MouseEvent e) {
            dependency.expectations.remove(expectation);
            DependencySetJPanel.this.refresh();
            DependencySetJPanel.this.getParent().validate();
            DependencySetJPanel.this.getParent().repaint();
        }

        public void mouseEntered(MouseEvent e) {
            this.setIcon(new ImageIcon(DELETE_OVER.getScaledInstance(12, 12, Image.SCALE_SMOOTH)));
        }

        public void mouseExited(MouseEvent e) {
            this.setIcon(new ImageIcon(DELETE.getScaledInstance(12, 12, Image.SCALE_SMOOTH)));
        }
        
    }
 
}
