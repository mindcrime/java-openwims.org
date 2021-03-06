/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openwims.UI;

import com.jesseenglish.swingftfy.extensions.FLabel;
import java.awt.Color;
import java.awt.Component;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.openwims.Objects.Disambiguation.Interpretation;
import org.openwims.Objects.Lexicon.Sense;
import org.openwims.Objects.Lexicon.Word;
import org.openwims.Objects.Preprocessor.PPDocument;
import org.openwims.Objects.Preprocessor.PPToken;
import org.openwims.Objects.WIM;
import org.openwims.Processors.Iterators.NaivePossibilityIterator;
import org.openwims.Processors.Iterators.PossibilityIterator;
import org.openwims.Processors.LandGrabDisambiguation;
import org.openwims.Processors.WIMProcessor;
import org.openwims.Processors.WIMProcessor.WSDProcessor;
import org.openwims.Processors.WIMProcessor.WSEProcessor;
import org.openwims.Serialization.JSONPPDocumentSerializer;
import org.openwims.Stanford.StanfordHelper;
import org.openwims.UI.LexiconEditor.LexiconEditorJFrame;
import org.openwims.WIMGlobals;

/**
 *
 * @author jesse
 */
public class MainJFrame extends javax.swing.JFrame {
    
    private PPDocument document;
    private OntologyJTree ontologyTree;
    private Sense selected;
    
    
    
    private Class<? extends PossibilityIterator> iterator;
    private WSDProcessor wsdProcessor;
    private WSEProcessor wseProcessor;
    
    private ButtonGroup iteratorGroup;
    private ButtonGroup wsdProcessorGroup;
    private ButtonGroup wseProcessorGroup;
    
    private DebugJPanel debug;
    
    /**
     * Creates new form MainJFrame
     */
    public MainJFrame() {
        initComponents();
        this.setSize(800, 800);
        
        WIMGlobals.frame = this;
        
        this.ontologyTree = new OntologyJTree();
        this.OntologyTreeContainerJPanel.add(this.ontologyTree);
        
        this.document = null;
        
        FileDropJPanel fileDropJPanel = new FileDropJPanel();
        fileDropJPanel.addFilesDraggedListener(new PPFilesDraggedEventListener());
        
        this.DocumentContainerJPanel.add(fileDropJPanel);
        
        this.SensesJList.addListSelectionListener(new SensesListSelectionListener());
        this.SensesJList.setCellRenderer(new SensesListCellRenderer());
        
        this.DocumentJScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        this.WIMsJScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        this.OntologyJScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        
        this.iteratorGroup = new ButtonGroup();
        this.wsdProcessorGroup = new ButtonGroup();
        this.wseProcessorGroup = new ButtonGroup();
        
        this.iterator = NaivePossibilityIterator.class;
        this.wseProcessor = new LandGrabDisambiguation();
        this.wsdProcessor = new LandGrabDisambiguation();
        
        
        this.PossibilityIteratorJMenu.add(new PossibilityIteratorJMenuItem("Naive", NaivePossibilityIterator.class));
        this.WSEJMenu.add(new WSEJMenuItem("Land Grab", this.wseProcessor));
        this.WSDJMenu.add(new WSDJMenuItem("Land Grab", this.wsdProcessor));
        
        WIMProcessor.wseLoggers.add(new WIMProcessor.WSELogger() {
            public void logPossibilityElimination(HashMap<PPToken, Sense> possibility, Exception reason) {
                if (debug != null) {
                    for (PPToken token : possibility.keySet()) {
                        if (debug.getMapping().get(token) != possibility.get(token)) {
                            return;
                        }
                    }
                    
                    StringBuilder log = new StringBuilder();
                    
                    log.append("Eliminating possibility:\n");
                    for (PPToken token : possibility.keySet()) {
                        log.append("  ");
                        log.append(token.anchor());
                        log.append(" = ");
                        log.append(possibility.get(token).getId());
                        log.append("\n");
                    }
                    
                    log.append("Due to:\n");
                    log.append(reason.toString());
                    
                    LogJTextArea.setText(log.toString());
                }
            }
        });
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

        LeftJTabbedPane = new javax.swing.JTabbedPane();
        LexiconJPanel = new javax.swing.JPanel();
        LexiconContainerJPanel = new javax.swing.JPanel();
        SearchJTextField = new javax.swing.JTextField();
        NewSenseJButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        SensesJList = new javax.swing.JList();
        DeleteSenseJButton = new javax.swing.JButton();
        SenseContainerJPanel = new javax.swing.JPanel();
        OntologyJPanel = new javax.swing.JPanel();
        DefinitionJLabel = new javax.swing.JLabel();
        OntologyJScrollPane = new javax.swing.JScrollPane();
        OntologyTreeContainerJPanel = new javax.swing.JPanel();
        RightJPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        InputJTextArea = new javax.swing.JTextArea();
        jToolBar1 = new javax.swing.JToolBar();
        PreprocessJButton = new javax.swing.JButton();
        FullProcessJButton = new javax.swing.JButton();
        WIMifyJButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        SavePPJButton = new javax.swing.JButton();
        LoadPPJButton = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        LoggingJButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        DocumentJScrollPane = new javax.swing.JScrollPane();
        DocumentContainerJPanel = new javax.swing.JPanel();
        WIMsJScrollPane = new javax.swing.JScrollPane();
        WIMsContainerJPanel = new javax.swing.JPanel();
        DebugContainerJPanel = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        LogJTextArea = new javax.swing.JTextArea();
        MainJMenuBar = new javax.swing.JMenuBar();
        ViewJMenu = new javax.swing.JMenu();
        ShowLexiconEditorJMenuItem = new javax.swing.JMenuItem();
        OptionsJMenu = new javax.swing.JMenu();
        PossibilityIteratorJMenu = new javax.swing.JMenu();
        WSEJMenu = new javax.swing.JMenu();
        WSDJMenu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("WIMs Explorer");
        getContentPane().setLayout(new java.awt.GridLayout(1, 2, 5, 0));

        LexiconJPanel.setLayout(new java.awt.GridLayout(2, 1, 0, 5));

        LexiconContainerJPanel.setLayout(new java.awt.GridBagLayout());

        SearchJTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SearchJTextFieldKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 0, 0);
        LexiconContainerJPanel.add(SearchJTextField, gridBagConstraints);

        NewSenseJButton.setText("New");
        NewSenseJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewSenseJButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        LexiconContainerJPanel.add(NewSenseJButton, gridBagConstraints);

        SensesJList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(SensesJList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        LexiconContainerJPanel.add(jScrollPane1, gridBagConstraints);

        DeleteSenseJButton.setText("Delete");
        DeleteSenseJButton.setEnabled(false);
        DeleteSenseJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteSenseJButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        LexiconContainerJPanel.add(DeleteSenseJButton, gridBagConstraints);

        LexiconJPanel.add(LexiconContainerJPanel);

        SenseContainerJPanel.setLayout(new java.awt.GridBagLayout());
        LexiconJPanel.add(SenseContainerJPanel);

        LeftJTabbedPane.addTab("Lexicon", LexiconJPanel);

        OntologyJPanel.setLayout(new java.awt.GridBagLayout());

        DefinitionJLabel.setText("Select a concept to see its definition");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 3, 2);
        OntologyJPanel.add(DefinitionJLabel, gridBagConstraints);

        OntologyTreeContainerJPanel.setLayout(new java.awt.GridLayout(1, 1));
        OntologyJScrollPane.setViewportView(OntologyTreeContainerJPanel);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        OntologyJPanel.add(OntologyJScrollPane, gridBagConstraints);

        LeftJTabbedPane.addTab("Ontology", OntologyJPanel);

        getContentPane().add(LeftJTabbedPane);

        RightJPanel.setLayout(new java.awt.GridBagLayout());

        jScrollPane2.setMinimumSize(new java.awt.Dimension(23, 52));

        InputJTextArea.setColumns(15);
        InputJTextArea.setLineWrap(true);
        InputJTextArea.setRows(3);
        InputJTextArea.setWrapStyleWord(true);
        jScrollPane2.setViewportView(InputJTextArea);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        RightJPanel.add(jScrollPane2, gridBagConstraints);

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        PreprocessJButton.setText("PP");
        PreprocessJButton.setFocusable(false);
        PreprocessJButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        PreprocessJButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        PreprocessJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PreprocessJButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(PreprocessJButton);

        FullProcessJButton.setText("Full Process");
        FullProcessJButton.setFocusable(false);
        FullProcessJButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        FullProcessJButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        FullProcessJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FullProcessJButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(FullProcessJButton);

        WIMifyJButton.setText("WIMify");
        WIMifyJButton.setFocusable(false);
        WIMifyJButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        WIMifyJButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        WIMifyJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WIMifyJButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(WIMifyJButton);
        jToolBar1.add(jSeparator1);

        SavePPJButton.setText("Save PP");
        SavePPJButton.setFocusable(false);
        SavePPJButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        SavePPJButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        SavePPJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SavePPJButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(SavePPJButton);

        LoadPPJButton.setText("Load PP");
        LoadPPJButton.setFocusable(false);
        LoadPPJButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        LoadPPJButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        LoadPPJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoadPPJButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(LoadPPJButton);
        jToolBar1.add(jSeparator2);

        LoggingJButton.setText("Logging OFF");
        LoggingJButton.setFocusable(false);
        LoggingJButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        LoggingJButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        LoggingJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoggingJButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(LoggingJButton);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        RightJPanel.add(jToolBar1, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        jPanel3.setLayout(new java.awt.GridLayout(2, 1));

        DocumentContainerJPanel.setLayout(new java.awt.GridLayout(1, 1));
        DocumentJScrollPane.setViewportView(DocumentContainerJPanel);

        jPanel3.add(DocumentJScrollPane);

        WIMsContainerJPanel.setLayout(new java.awt.GridLayout(1, 1));
        WIMsJScrollPane.setViewportView(WIMsContainerJPanel);

        jPanel3.add(WIMsJScrollPane);

        jTabbedPane1.addTab("Results", jPanel3);

        DebugContainerJPanel.setLayout(new java.awt.GridLayout(1, 1));
        jTabbedPane1.addTab("Debug", DebugContainerJPanel);

        jPanel4.setLayout(new java.awt.GridLayout(1, 1));

        LogJTextArea.setColumns(20);
        LogJTextArea.setEditable(false);
        LogJTextArea.setLineWrap(true);
        LogJTextArea.setRows(5);
        LogJTextArea.setWrapStyleWord(true);
        jScrollPane3.setViewportView(LogJTextArea);

        jPanel4.add(jScrollPane3);

        jTabbedPane1.addTab("Log", jPanel4);

        jPanel2.add(jTabbedPane1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        RightJPanel.add(jPanel2, gridBagConstraints);

        getContentPane().add(RightJPanel);

        ViewJMenu.setText("View");

        ShowLexiconEditorJMenuItem.setText("Lexicon Editor");
        ShowLexiconEditorJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowLexiconEditorJMenuItemActionPerformed(evt);
            }
        });
        ViewJMenu.add(ShowLexiconEditorJMenuItem);

        MainJMenuBar.add(ViewJMenu);

        OptionsJMenu.setText("Options");

        PossibilityIteratorJMenu.setText("Possibility Iterator");
        OptionsJMenu.add(PossibilityIteratorJMenu);

        WSEJMenu.setText("WSE Processor");
        OptionsJMenu.add(WSEJMenu);

        WSDJMenu.setText("WSD Processor");
        OptionsJMenu.add(WSDJMenu);

        MainJMenuBar.add(OptionsJMenu);

        setJMenuBar(MainJMenuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SearchJTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchJTextFieldKeyReleased
        search();
    }//GEN-LAST:event_SearchJTextFieldKeyReleased

    private void NewSenseJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewSenseJButtonActionPerformed
        NewSenseJDialog d = new NewSenseJDialog(this, true);
        d.setVisible(true);
        
        if (d.isOk()) {
            Sense sense = d.sense();
            WIMGlobals.lexicon().addSense(sense);
            search();
        }
    }//GEN-LAST:event_NewSenseJButtonActionPerformed

    private void DeleteSenseJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteSenseJButtonActionPerformed
        WIMGlobals.lexicon().removeSense(selected);
        search();
        this.SenseContainerJPanel.removeAll();
        this.validate();
        this.repaint();
    }//GEN-LAST:event_DeleteSenseJButtonActionPerformed

    private void FullProcessJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FullProcessJButtonActionPerformed
        fullProcess();
    }//GEN-LAST:event_FullProcessJButtonActionPerformed

    private void WIMifyJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WIMifyJButtonActionPerformed
        wimify();
    }//GEN-LAST:event_WIMifyJButtonActionPerformed

    private void SavePPJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SavePPJButtonActionPerformed
        savePP();
    }//GEN-LAST:event_SavePPJButtonActionPerformed

    private void LoadPPJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoadPPJButtonActionPerformed
        loadPP();
    }//GEN-LAST:event_LoadPPJButtonActionPerformed

    private void LoggingJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoggingJButtonActionPerformed
        WIMProcessor.LOGGING = !WIMProcessor.LOGGING;
        LoggingJButton.setText("Logging " + (WIMProcessor.LOGGING == true ? "ON" : "OFF"));
        this.validate();
        this.repaint();
    }//GEN-LAST:event_LoggingJButtonActionPerformed

    private void ShowLexiconEditorJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowLexiconEditorJMenuItemActionPerformed
        LexiconEditorJFrame lexEditor = new LexiconEditorJFrame();
        lexEditor.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        lexEditor.setVisible(true);
    }//GEN-LAST:event_ShowLexiconEditorJMenuItemActionPerformed

    private void PreprocessJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PreprocessJButtonActionPerformed
        String input = this.InputJTextArea.getText().trim();
        if (input.equalsIgnoreCase("")) {
            return;
        }
        
        setDocument(StanfordHelper.convert(StanfordHelper.annotate(input)));
    }//GEN-LAST:event_PreprocessJButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new MainJFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel DebugContainerJPanel;
    private javax.swing.JLabel DefinitionJLabel;
    private javax.swing.JButton DeleteSenseJButton;
    private javax.swing.JPanel DocumentContainerJPanel;
    private javax.swing.JScrollPane DocumentJScrollPane;
    private javax.swing.JButton FullProcessJButton;
    private javax.swing.JTextArea InputJTextArea;
    private javax.swing.JTabbedPane LeftJTabbedPane;
    private javax.swing.JPanel LexiconContainerJPanel;
    private javax.swing.JPanel LexiconJPanel;
    private javax.swing.JButton LoadPPJButton;
    private javax.swing.JTextArea LogJTextArea;
    private javax.swing.JButton LoggingJButton;
    private javax.swing.JMenuBar MainJMenuBar;
    private javax.swing.JButton NewSenseJButton;
    private javax.swing.JPanel OntologyJPanel;
    private javax.swing.JScrollPane OntologyJScrollPane;
    private javax.swing.JPanel OntologyTreeContainerJPanel;
    private javax.swing.JMenu OptionsJMenu;
    private javax.swing.JMenu PossibilityIteratorJMenu;
    private javax.swing.JButton PreprocessJButton;
    private javax.swing.JPanel RightJPanel;
    private javax.swing.JButton SavePPJButton;
    private javax.swing.JTextField SearchJTextField;
    private javax.swing.JPanel SenseContainerJPanel;
    private javax.swing.JList SensesJList;
    private javax.swing.JMenuItem ShowLexiconEditorJMenuItem;
    private javax.swing.JMenu ViewJMenu;
    private javax.swing.JButton WIMifyJButton;
    private javax.swing.JPanel WIMsContainerJPanel;
    private javax.swing.JScrollPane WIMsJScrollPane;
    private javax.swing.JMenu WSDJMenu;
    private javax.swing.JMenu WSEJMenu;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables

    private void loadPP() {
        try {
            FileDialog d = new FileDialog(this, "Load PP Document", FileDialog.LOAD);
            d.setVisible(true);

            if (d.getDirectory() != null && d.getFile() != null) {
                String file = d.getDirectory() + "/" + d.getFile();
                PPDocument document = JSONPPDocumentSerializer.deserialize(file);
                setDocument(document);
            } 
        } catch (Exception ex) {
            Logger.getLogger(MainJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void savePP() {
        try {
            FileDialog d = new FileDialog(this, "Save PP Document", FileDialog.SAVE);
            d.setVisible(true);
            
            if (d.getDirectory() != null && d.getFile() != null) {
                String file = d.getDirectory() + "/" + d.getFile();
                JSONPPDocumentSerializer.serialize(document, file);
            }
        } catch (Exception ex) {
            Logger.getLogger(MainJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void search() {
        Word word = WIMGlobals.lexicon().word(this.SearchJTextField.getText());
        
        LinkedList<Sense> senses = word.listSenses();
        Collections.sort(senses, new SenseComparator());
        
        DefaultListModel model = new DefaultListModel();
        for (Sense sense : senses) {
            model.addElement(sense);
        }
        this.SensesJList.setModel(model);
        
        this.validate();
        this.repaint();
    }
    
    private void load(Sense sense) {
        this.SenseContainerJPanel.removeAll();
        
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 0.1;
        c.weighty = 0.1;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(5, 5, 5, 5);
        
        JScrollPane scroller = new JScrollPane(new SenseJTree(sense));
        
        this.SenseContainerJPanel.add(scroller, c);
        
        this.validate();
        this.repaint();
    }
    
    private void load(LinkedList<WIM> wims) {
        this.WIMsContainerJPanel.removeAll();
        
        WIMSJTree tree = new WIMSJTree(wims);
        tree.addSenseSelectionListener(new WIMSJTree.SenseSelectionListener() {
            @Override
            public void senseSelected(Sense sense) {
                load(sense);
            }
        });
        
        this.WIMsContainerJPanel.add(tree);
        
        this.validate();
        this.repaint();
    }
    
    private void load(PPDocument document) {
        this.DocumentContainerJPanel.removeAll();
        
        PPDocumentJTree tree = new PPDocumentJTree(document);
        tree.addFilesDraggedListener(new PPFilesDraggedEventListener());
        this.DocumentContainerJPanel.add(tree);
        
        this.debug = new DebugJPanel(document);
        this.DebugContainerJPanel.removeAll();
        this.DebugContainerJPanel.add(new JScrollPane(this.debug));
        
        this.validate();
        this.repaint();
    }
    
    public void setOntologyDefinition(String definition) {
        this.DefinitionJLabel.setText(definition);
        this.validate();
        this.repaint();
    }
    
    public void setDocument(PPDocument document) {
        this.document = document;
        this.InputJTextArea.setText(this.document.text());
        this.WIMifyJButton.setEnabled(true);
        
        load(this.document);
        
        this.validate();
        this.repaint();
    }
    
    private void fullProcess() {
        String input = this.InputJTextArea.getText().trim();
        if (input.equalsIgnoreCase("")) {
            return;
        }
        
        setDocument(StanfordHelper.convert(StanfordHelper.annotate(input)));
        wimify();
    }
    
    private void wimify() {
        if (this.document == null) {
            return;
        }
        
        System.out.println("TOTAL POSSIBILITIES: " + this.document.countPossibleSenseInterpretations());
        
        try {
            PossibilityIterator i = iterator.getConstructor(PPDocument.class).newInstance(document);
            
            LinkedList<Interpretation> wse = this.wseProcessor.wse(i);
            LinkedList<WIM> wims = new LinkedList();
            for (Interpretation interpretation : wse) {
                wims.add(interpretation.wim());
            }

            load(wims);
            
        } catch (Exception ex) {
            Logger.getLogger(MainJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    private class PPFilesDraggedEventListener implements FileDropJPanel.FilesDraggedListener {

        @Override
        public void filesDraggedEvent(List<File> files) {
            try {
                setDocument(JSONPPDocumentSerializer.deserialize(files.get(0).getAbsolutePath()));
            } catch (Exception err) {
                err.printStackTrace();
            }
            
            wimify();
        }
        
    }
    
    private class SensesListSelectionListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent lse) {
            Sense sense = (Sense)SensesJList.getSelectedValue();
            if (sense == null) {
                DeleteSenseJButton.setEnabled(false);
                return;
            }
            
            load(sense);
            selected = sense;
            DeleteSenseJButton.setEnabled(true);
        }
        
    }
    
    private class SensesListCellRenderer extends DefaultListCellRenderer {

        @Override
        public Component getListCellRendererComponent(JList list, Object o, int i, boolean bln, boolean bln1) {
            
            JPanel panel = new JPanel();
            panel.setBackground(Color.WHITE);
            panel.setBorder(new LineBorder(Color.WHITE, 2));
            
            if (i % 2 == 1) {
                panel.setBackground(new Color(0.9f, 0.95f, 1.0f));
                panel.setBorder(new LineBorder(Color.WHITE, 1));
            }
            
            if (bln) {
                panel.setBackground(new Color(0.8f, 0.85f, 1.0f));
            }
            
            panel.setLayout(new GridLayout(2, 1));
            
            if (o instanceof Sense) {
                Sense sense = (Sense) o;
                
                FLabel idLabel = new FLabel(sense.getId());
                idLabel.setBold(true);
                
                FLabel defLabel = new FLabel(sense.getDefinition());
                defLabel.setFont(defLabel.getFont().deriveFont(Font.ITALIC));
                defLabel.setForeground(Color.DARK_GRAY);
                
                panel.add(idLabel);
                panel.add(defLabel);
            }
            
            return panel;
        }
        
    }
    
    private class SenseComparator implements Comparator<Sense> {

        @Override
        public int compare(Sense t, Sense t1) {
            return t.getId().compareTo(t1.getId());
        }
        
    }
    
    private class WSEJMenuItem extends JRadioButtonMenuItem implements ActionListener {
        
        private WSEProcessor processor;

        public WSEJMenuItem(String label, WSEProcessor processor) {
            super(label);
            this.processor = processor;
            this.addActionListener(this);
            
            if (MainJFrame.this.wseProcessor == processor) {
                this.setSelected(true);
            }
            
            MainJFrame.this.wseProcessorGroup.add(this);
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            MainJFrame.this.wseProcessor = processor;
            this.setSelected(true);
        }
        
    }
    
    private class WSDJMenuItem extends JRadioButtonMenuItem implements ActionListener {
        
        private WSDProcessor processor;

        public WSDJMenuItem(String label, WSDProcessor processor) {
            super(label);
            this.processor = processor;
            this.addActionListener(this);
            
            if (MainJFrame.this.wsdProcessor == processor) {
                this.setSelected(true);
            }
            
            MainJFrame.this.wsdProcessorGroup.add(this);
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            MainJFrame.this.wsdProcessor = processor;
            this.setSelected(true);
        }
        
    }
    
    private class PossibilityIteratorJMenuItem extends JRadioButtonMenuItem implements ActionListener {
        
        private Class<? extends PossibilityIterator> iterator;
        
        public PossibilityIteratorJMenuItem(String label, Class<? extends PossibilityIterator> iterator) {
            super(label);
            this.iterator = iterator;
            this.addActionListener(this);
            
            if (MainJFrame.this.iterator == iterator) {
                this.setSelected(true);
            }
            
            MainJFrame.this.iteratorGroup.add(this);
        }

        public void actionPerformed(ActionEvent e) {
            MainJFrame.this.iterator = iterator;
            this.setSelected(true);
        }
        
    }

}
