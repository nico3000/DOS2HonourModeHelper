/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import javax.swing.JOptionPane;

/**
 *
 * @author nicot
 */
public class MainGui extends javax.swing.JFrame {
    private WatchThread watchThread;
    
    /**
     * Creates new form MainGui
     */
    public MainGui() {
        initComponents();
        this.defaultDirButtonActionPerformed(null);        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backupManager = new model.BackupManager();
        profileManager = new model.ProfileManager();
        profilesPanel = new javax.swing.JPanel();
        profileDirLabel = new javax.swing.JLabel();
        profileDirTextField = new javax.swing.JTextField();
        profileDirButton = new javax.swing.JButton();
        profileComboBox = new javax.swing.JComboBox<>();
        defaultDirButton = new javax.swing.JButton();
        backupsPanel = new javax.swing.JPanel();
        copyrightLabel = new javax.swing.JLabel();
        backupsListPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        backupsList = new javax.swing.JList<>();
        limitCheckBox = new javax.swing.JCheckBox();
        limitSpinner = new javax.swing.JSpinner();
        deleteSelectionButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();
        backupButton = new javax.swing.JButton();
        autoToggleButton = new javax.swing.JToggleButton();
        separator = new javax.swing.JSeparator();
        backupDetailPanel = new view.BackupDetailPanel();
        horizontalSeparator = new javax.swing.JSeparator();
        bannerImageComponent = new view.ImageComponent();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Divinity: Original Sin 2 - Honour Mode Helper");
        setIconImage(Toolkit.getDefaultToolkit().getImage(MainGui.class.getResource("/resource/nicotopia.png")));
        setResizable(false);

        profileDirLabel.setText("Profiles Directory");

        profileDirTextField.setText(this.profileManager.getBaseDirectory());
        profileDirTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profileDirTextFieldActionPerformed(evt);
            }
        });

        profileDirButton.setText("...");
        profileDirButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profileDirButtonActionPerformed(evt);
            }
        });

        profileComboBox.setModel(profileManager);
        profileComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                profileComboBoxItemStateChanged(evt);
            }
        });

        defaultDirButton.setText("Default");
        defaultDirButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                defaultDirButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout profilesPanelLayout = new javax.swing.GroupLayout(profilesPanel);
        profilesPanel.setLayout(profilesPanelLayout);
        profilesPanelLayout.setHorizontalGroup(
            profilesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(profilesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(profileDirLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(profileDirTextField)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(profileDirButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(profileComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(defaultDirButton)
                .addContainerGap())
        );
        profilesPanelLayout.setVerticalGroup(
            profilesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(profilesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(profilesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(profileDirLabel)
                    .addComponent(profileDirTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(profileDirButton)
                    .addComponent(defaultDirButton)
                    .addComponent(profileComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        copyrightLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        copyrightLabel.setText("©2018 Nicotopia");

        backupsList.setModel(backupManager);
        backupsList.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        backupsList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backupsListMouseClicked(evt);
            }
        });
        backupsList.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                backupsListKeyReleased(evt);
            }
        });
        backupsList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                backupsListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(backupsList);

        limitCheckBox.setText("Limit Backup Count:");
        limitCheckBox.setToolTipText("");
        limitCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limitCheckBoxActionPerformed(evt);
            }
        });

        limitSpinner.setModel(new javax.swing.SpinnerNumberModel(10, 5, 25, 1));
        limitSpinner.setToolTipText("");
        limitSpinner.setEnabled(false);

        deleteSelectionButton.setText("Delete Selection");
        deleteSelectionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteSelectionButtonActionPerformed(evt);
            }
        });

        refreshButton.setText("Refresh");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        backupButton.setText("Backup Now");
        backupButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backupButtonActionPerformed(evt);
            }
        });

        autoToggleButton.setText("Automatic Backup");
        autoToggleButton.setToolTipText("");
        autoToggleButton.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                autoToggleButtonItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout backupsListPanelLayout = new javax.swing.GroupLayout(backupsListPanel);
        backupsListPanel.setLayout(backupsListPanelLayout);
        backupsListPanelLayout.setHorizontalGroup(
            backupsListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(backupsListPanelLayout.createSequentialGroup()
                .addComponent(deleteSelectionButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(refreshButton))
            .addGroup(backupsListPanelLayout.createSequentialGroup()
                .addComponent(backupButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(autoToggleButton))
            .addGroup(backupsListPanelLayout.createSequentialGroup()
                .addComponent(limitCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(limitSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        backupsListPanelLayout.setVerticalGroup(
            backupsListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backupsListPanelLayout.createSequentialGroup()
                .addGroup(backupsListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backupButton)
                    .addComponent(autoToggleButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(backupsListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(limitCheckBox)
                    .addComponent(limitSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(backupsListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteSelectionButton)
                    .addComponent(refreshButton)))
        );

        separator.setOrientation(javax.swing.SwingConstants.VERTICAL);

        backupDetailPanel.setSaveGameManager(backupManager);

        javax.swing.GroupLayout backupsPanelLayout = new javax.swing.GroupLayout(backupsPanel);
        backupsPanel.setLayout(backupsPanelLayout);
        backupsPanelLayout.setHorizontalGroup(
            backupsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backupsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(backupsListPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(separator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(backupsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backupsPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(copyrightLabel))
                    .addGroup(backupsPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(backupDetailPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        backupsPanelLayout.setVerticalGroup(
            backupsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backupsPanelLayout.createSequentialGroup()
                .addGroup(backupsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backupsPanelLayout.createSequentialGroup()
                        .addComponent(backupDetailPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(copyrightLabel))
                    .addComponent(separator)
                    .addComponent(backupsListPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        bannerImageComponent.setImage(MainGui.class.getResource("/resource/banner.jpg"));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backupsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(profilesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(horizontalSeparator)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(bannerImageComponent, javax.swing.GroupLayout.PREFERRED_SIZE, 768, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(bannerImageComponent, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(profilesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(horizontalSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(backupsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void profileDirButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profileDirButtonActionPerformed
        JFileChooser jfc = new JFileChooser(this.profileDirTextField.getText());
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if(jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            this.setBaseDirectory(jfc.getSelectedFile());
        }
    }//GEN-LAST:event_profileDirButtonActionPerformed

    private void backupsListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_backupsListValueChanged
        this.backupDetailPanel.setSaveGame(this.backupsList.getSelectedIndices().length == 1 ? this.backupsList.getSelectedIndex() : -1);
    }//GEN-LAST:event_backupsListValueChanged

    private void backupButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backupButtonActionPerformed
        try {
            if(!this.backupManager.backupIfNeeded(this.limitSpinner.isEnabled() ? (Integer)this.limitSpinner.getValue() : -1)) {
                JOptionPane.showMessageDialog(this, "The current save game is already backed up.", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch(UnsupportedOperationException | IOException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_backupButtonActionPerformed

    private void autoToggleButtonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_autoToggleButtonItemStateChanged
        if(this.autoToggleButton.isSelected() && this.watchThread == null) {
            try {
                this.watchThread = new WatchThread(this.profileManager.getSaveGameDirectory() + File.separator + "Story" + File.separator + "HonourMode");
                this.watchThread.start();
            } catch (IOException ex) {
                this.watchThread = null;
                this.autoToggleButton.setSelected(false);
                JOptionPane.showMessageDialog(this, ex.getLocalizedMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if(!this.autoToggleButton.isSelected() && this.watchThread != null) {
            this.watchThread.interrupt();
            this.watchThread = null;
        }
    }//GEN-LAST:event_autoToggleButtonItemStateChanged

    private void profileDirTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profileDirTextFieldActionPerformed
        File file = new File(this.profileDirTextField.getText());
        if(file.exists() && file.isDirectory()) {
            this.setBaseDirectory(file);
        } else {
            JOptionPane.showMessageDialog(this, "Selected path does not exist!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_profileDirTextFieldActionPerformed

    private void backupsListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backupsListMouseClicked
        if(evt.getClickCount() == 2 && evt.getButton() == MouseEvent.BUTTON1 && this.backupsList.getSelectedIndices().length == 1) {
            this.backupDetailPanel.restore();
        }
    }//GEN-LAST:event_backupsListMouseClicked

    private void deleteSelectionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteSelectionButtonActionPerformed
        if(JOptionPane.showConfirmDialog(this, "Do you really want to DELETE all SELECTED save games?", "Confirm Deletion", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            this.backupManager.delete(this.backupsList.getSelectedIndices());
        }
    }//GEN-LAST:event_deleteSelectionButtonActionPerformed

    private void profileComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_profileComboBoxItemStateChanged
        try {
            this.backupManager.setSaveGameDirectory(this.profileManager.getSaveGameDirectory());
        } catch (UnsupportedOperationException | IOException ex) {
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_profileComboBoxItemStateChanged

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        this.backupManager.refresh();
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void backupsListKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_backupsListKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_DELETE) {
            this.deleteSelectionButtonActionPerformed(null);
        }
    }//GEN-LAST:event_backupsListKeyReleased

    private void defaultDirButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_defaultDirButtonActionPerformed
        String defaultBaseDirectory = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "Larian Studios" + File.separator + "Divinity Original Sin 2" + File.separator + "PlayerProfiles";
        this.setBaseDirectory(new File(defaultBaseDirectory));
    }//GEN-LAST:event_defaultDirButtonActionPerformed

    private void limitCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limitCheckBoxActionPerformed
        this.limitSpinner.setEnabled(this.limitCheckBox.isSelected());
    }//GEN-LAST:event_limitCheckBoxActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainGui().setVisible(true);
            }
        });
    }
    
    private void setBaseDirectory(File file) {
        this.profileDirTextField.setText(file.getAbsolutePath());
        try {
            this.profileManager.setBaseDirectory(file);
        } catch(UnsupportedOperationException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private class WatchThread extends Thread {
        private final Path path;
        private final WatchService watchService;
        private final WatchKey watchKey;

        WatchThread(String saveGameDirectory) throws IOException {
            this.path = new File(saveGameDirectory).toPath();
            this.watchService = FileSystems.getDefault().newWatchService();
            this.watchKey = this.path.register(this.watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_DELETE);
        }
        
        @Override
        public void run() {
            boolean bContinue = true;
            while(bContinue) {
                WatchKey key;
                try {
                    key = this.watchService.take();
                } catch (InterruptedException ex) {
                    key = null;
                    bContinue = false;
                }
                if(key != null) {
                    for(WatchEvent evt : key.pollEvents()) {
                        if(evt.kind() == StandardWatchEventKinds.ENTRY_DELETE && ((WatchEvent<Path>)evt).context().toFile().getName().equals("HonourMode.lsv.old")) {
                            MainGui.this.backupButtonActionPerformed(null);
                        }
                    }
                    key.reset();
                }
            }
            this.watchKey.cancel();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton autoToggleButton;
    private javax.swing.JButton backupButton;
    private view.BackupDetailPanel backupDetailPanel;
    private model.BackupManager backupManager;
    private javax.swing.JList<String> backupsList;
    private javax.swing.JPanel backupsListPanel;
    private javax.swing.JPanel backupsPanel;
    private view.ImageComponent bannerImageComponent;
    private javax.swing.JLabel copyrightLabel;
    private javax.swing.JButton defaultDirButton;
    private javax.swing.JButton deleteSelectionButton;
    private javax.swing.JSeparator horizontalSeparator;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JCheckBox limitCheckBox;
    private javax.swing.JSpinner limitSpinner;
    private javax.swing.JComboBox<String> profileComboBox;
    private javax.swing.JButton profileDirButton;
    private javax.swing.JLabel profileDirLabel;
    private javax.swing.JTextField profileDirTextField;
    private model.ProfileManager profileManager;
    private javax.swing.JPanel profilesPanel;
    private javax.swing.JButton refreshButton;
    private javax.swing.JSeparator separator;
    // End of variables declaration//GEN-END:variables
}
