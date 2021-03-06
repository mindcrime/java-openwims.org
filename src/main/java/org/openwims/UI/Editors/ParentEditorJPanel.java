/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openwims.UI.Editors;

import java.awt.Color;
import org.openwims.Objects.Ontology.Concept;
import org.openwims.WIMGlobals;

/**
 *
 * @author jesseenglish
 */
public class ParentEditorJPanel extends javax.swing.JPanel {
    
    private Concept concept;

    /**
     * Creates new form DefinitionEditorJPanel
     */
    public ParentEditorJPanel(Concept concept) {
        initComponents();
        this.concept = concept;
        
        this.ParentFTextField.setText(concept.getParent().getName());
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

        ParentFTextField = new com.jesseenglish.swingftfy.extensions.FTextField();

        setLayout(new java.awt.GridBagLayout());

        ParentFTextField.setHintText("parent");
        ParentFTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ParentFTextFieldKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        add(ParentFTextField, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void ParentFTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ParentFTextFieldKeyReleased
        if (WIMGlobals.ontology().doesConceptExist(this.ParentFTextField.getText().trim())) {
            concept.setParent(WIMGlobals.ontology().concept(this.ParentFTextField.getText().trim()));
            this.ParentFTextField.setBackground(Color.WHITE);
        } else {
            this.ParentFTextField.setBackground(Color.PINK);
        }
        
        this.validate();
        this.repaint();
    }//GEN-LAST:event_ParentFTextFieldKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.jesseenglish.swingftfy.extensions.FTextField ParentFTextField;
    // End of variables declaration//GEN-END:variables
}
