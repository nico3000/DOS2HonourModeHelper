/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Desktop;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.SaveGame;
import model.SaveGameManager;

/**
 *
 * @author nicot
 */
public class SaveGameDetailPanel extends javax.swing.JPanel {
    private SaveGameManager saveGameManager;
    private SaveGame saveGame;
    
    /**
     * Creates new form SaveGameDetail
     */
    public SaveGameDetailPanel() {
        initComponents();
    }
    
    public void setSaveGameManager(SaveGameManager manager) {
        if(this.saveGameManager != null) {
            throw new UnsupportedOperationException("Save game manager can be set only once!");
        }
        this.saveGameManager = manager;
    }
    
    public void setSaveGame(int saveGameIndex) {
        this.saveGame = saveGameIndex != -1 ? this.saveGameManager.getSaveGame(saveGameIndex) : null;
        URL url = null;
        try {
            url = this.saveGame != null ? this.saveGame.getImageFile().toURI().toURL() : null;
        } catch (MalformedURLException ex) {
            Logger.getLogger(SaveGameDetailPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.saveGameImageComponent.setImage(url);
        this.nameTextField.setText(this.saveGame != null ? this.saveGame.getName() : "");
        this.nameTextField.setEnabled(this.saveGame != null);
        this.timeLabel.setText(this.saveGame != null ? new SimpleDateFormat("dd.MM.YYYY - HH:mm:ss").format(new Date(this.saveGame.getLastModified())) : "");
        this.sizeLabel.setText(this.saveGame != null ? this.formatByteWidth(this.saveGame.getByteWidth()) : "");
        this.applyNameButton.setEnabled(this.saveGame != null);
        this.gotoButton.setEnabled(this.saveGame != null);
        this.restoreButton.setEnabled(this.saveGame != null);
        this.deleteButton.setEnabled(this.saveGame != null);
    }
    
    private String formatByteWidth(long byteWidth) {
        int exp = 0;
        long rem = 0;
        String units[] = { "B", "KiB", "MiB", "GiB", "TiB", };
        while(byteWidth > 1000 && exp < units.length - 1) {
            rem = byteWidth % 1000;
            byteWidth /= 1000;
            ++exp;
        }
        return String.format("%d.%03d %s", byteWidth, rem, units[exp]);
    }
    
    public void restore() {
        if(JOptionPane.showConfirmDialog(this, "Do you really want to RESTORE this save game?", "Confirm Restoration", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            try {
                this.saveGameManager.restore(this.saveGame);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        saveGameImageComponent = new view.ImageComponent();
        nameLabel = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        applyNameButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        restoreButton = new javax.swing.JButton();
        gotoButton = new javax.swing.JButton();
        sizeLabel = new javax.swing.JLabel();
        timeLabel = new javax.swing.JLabel();
        timeInfoLabel = new javax.swing.JLabel();
        sizeInfoLabel = new javax.swing.JLabel();

        saveGameImageComponent.setPreferredSize(new java.awt.Dimension(480, 270));

        nameLabel.setText("Name:");

        nameTextField.setEnabled(false);
        nameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameTextFieldActionPerformed(evt);
            }
        });

        applyNameButton.setText("Apply");
        applyNameButton.setEnabled(false);
        applyNameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                applyNameButtonActionPerformed(evt);
            }
        });

        deleteButton.setText("Delete");
        deleteButton.setEnabled(false);
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        restoreButton.setText("Restore");
        restoreButton.setEnabled(false);
        restoreButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restoreButtonActionPerformed(evt);
            }
        });

        gotoButton.setText("Goto");
        gotoButton.setEnabled(false);
        gotoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gotoButtonActionPerformed(evt);
            }
        });

        sizeLabel.setText("???");

        timeLabel.setText("???");

        timeInfoLabel.setText("Created:");

        sizeInfoLabel.setText("Size:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(saveGameImageComponent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(timeInfoLabel)
                    .addComponent(sizeInfoLabel)
                    .addComponent(nameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(timeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(sizeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(gotoButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(restoreButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteButton))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nameTextField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(applyNameButton))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(saveGameImageComponent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameLabel)
                    .addComponent(applyNameButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(timeInfoLabel)
                            .addComponent(timeLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sizeInfoLabel)
                            .addComponent(sizeLabel)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(restoreButton)
                        .addComponent(deleteButton)
                        .addComponent(gotoButton))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void nameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameTextFieldActionPerformed
        try {
            this.saveGame.setName(this.nameTextField.getText());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_nameTextFieldActionPerformed

    private void applyNameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applyNameButtonActionPerformed
        this.nameTextFieldActionPerformed(evt);
    }//GEN-LAST:event_applyNameButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        if(JOptionPane.showConfirmDialog(this, "Do you really want to DELETE this save game?", "Confirm Deletion", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            this.saveGameManager.delete(this.saveGame);
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void restoreButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restoreButtonActionPerformed
        this.restore();
    }//GEN-LAST:event_restoreButtonActionPerformed

    private void gotoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gotoButtonActionPerformed
        try {
            Desktop.getDesktop().open(this.saveGame.getDirectory());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_gotoButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton applyNameButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton gotoButton;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JButton restoreButton;
    private view.ImageComponent saveGameImageComponent;
    private javax.swing.JLabel sizeInfoLabel;
    private javax.swing.JLabel sizeLabel;
    private javax.swing.JLabel timeInfoLabel;
    private javax.swing.JLabel timeLabel;
    // End of variables declaration//GEN-END:variables
}
