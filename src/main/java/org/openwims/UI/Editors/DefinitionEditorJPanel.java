/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openwims.UI.Editors;

import org.openwims.Objects.Lexicon.Sense;

/**
 *
 * @author jesseenglish
 */
public class DefinitionEditorJPanel extends javax.swing.JPanel {
    
    private Sense sense;

    /**
     * Creates new form DefinitionEditorJPanel
     */
    public DefinitionEditorJPanel(Sense sense) {
        initComponents();
        this.sense = sense;
        
        this.DefinitionFTextField.setText(sense.getDefinition());
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

        DefinitionFTextField = new com.jesseenglish.swingftfy.extensions.FTextField();

        setLayout(new java.awt.GridBagLayout());

        DefinitionFTextField.setHintText("definition");
        DefinitionFTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                DefinitionFTextFieldKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        add(DefinitionFTextField, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void DefinitionFTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DefinitionFTextFieldKeyReleased
        this.sense.setDefinition(this.DefinitionFTextField.getText().trim());
    }//GEN-LAST:event_DefinitionFTextFieldKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.jesseenglish.swingftfy.extensions.FTextField DefinitionFTextField;
    // End of variables declaration//GEN-END:variables
}