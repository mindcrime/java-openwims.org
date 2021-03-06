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
public class FrequencyEditorJPanel extends javax.swing.JPanel {
    
    private Sense sense;

    /**
     * Creates new form DefinitionEditorJPanel
     */
    public FrequencyEditorJPanel(Sense sense) {
        initComponents();
        this.sense = sense;
        
        this.FrequencyFTextField.setText("" + sense.getFrequency());
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

        FrequencyFTextField = new com.jesseenglish.swingftfy.extensions.FTextField();

        setLayout(new java.awt.GridBagLayout());

        FrequencyFTextField.setHintText("frequency");
        FrequencyFTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                FrequencyFTextFieldKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        add(FrequencyFTextField, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void FrequencyFTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FrequencyFTextFieldKeyReleased
        this.sense.setFrequency(Double.parseDouble(this.FrequencyFTextField.getText().trim()));
    }//GEN-LAST:event_FrequencyFTextFieldKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.jesseenglish.swingftfy.extensions.FTextField FrequencyFTextField;
    // End of variables declaration//GEN-END:variables
}
