/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nicot
 */
public class SaveGame extends Observable implements Comparable<SaveGame> {
    private final File directory;
    private final File lsvFile;
    private final File imgFile;
    private final File txtFile;
    private byte[] hash;
    private String name;
    
    public SaveGame(File directory) {
        this.directory = directory;
        this.lsvFile = new File(directory.getAbsolutePath() + File.separator + "HonourMode.lsv");
        this.imgFile = new File(directory.getAbsolutePath() + File.separator + "HonourMode.png");
        this.txtFile = new File(directory.getAbsolutePath() + File.separator + "HonourMode.txt");
        this.updateHash();
        if(this.txtFile.exists() && this.txtFile.isFile()) {
            try {
                this.name = new String(Files.readAllBytes(this.txtFile.toPath()));
            } catch (IOException ex) {
                Logger.getLogger(SaveGame.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            this.name = this.directory.getName();
        }
    }
    
    public boolean exists() {
        this.updateHash();
        return this.hash != null;
    }
    
    public boolean isAvailable() {
        return this.exists() && this.lsvFile.canWrite();
    }
    
    private void updateHash() {
        try {
            byte[] b = Files.readAllBytes(this.lsvFile.toPath());
            this.hash = MessageDigest.getInstance("MD5").digest(b);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SaveGame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            this.hash = null;
        }        
    }
    
    public void setName(String name) throws IOException {
        if(name != null && !name.isEmpty()) {
            if(!this.txtFile.exists()) {
                this.txtFile.createNewFile();
            }
            if(!this.txtFile.isFile()) {
                throw new IOException(this.txtFile.getPath() + " is no file!");
            }
            Files.write(this.txtFile.toPath(), name.getBytes());
        } else if(this.txtFile.exists()) {
            this.txtFile.delete();
        }
        this.name = name;
        this.setChanged();
        this.notifyObservers();
    }
    
    public boolean hasCustomName() {
        return this.txtFile.isFile();
    }
    
    public String getName() {
        return this.name;
    }
    
    public long getByteWidth() {
        return this.lsvFile.length();
    }
    
    public long getLastModified() {
        return this.lsvFile.lastModified();
    }
    
    public File getImageFile() {
        return this.imgFile;
    }
    
    public File getDirectory() {
        return this.directory;
    }
    
    public void delete() {
        this.txtFile.delete();
        this.imgFile.delete();
        this.lsvFile.delete();
        this.directory.delete();
    }
    
    public SaveGame copy(File destinationDir) throws IOException {
        if(!destinationDir.isDirectory()) {
            throw new IOException("Directory to copy save game to does not exist or is no directory!");
        }
        Files.copy(this.lsvFile.toPath(), new File(destinationDir.getAbsolutePath() + File.separator + this.lsvFile.getName()).toPath(), StandardCopyOption.REPLACE_EXISTING);
        Files.copy(this.imgFile.toPath(), new File(destinationDir.getAbsolutePath() + File.separator + this.imgFile.getName()).toPath(), StandardCopyOption.REPLACE_EXISTING);
        return new SaveGame(destinationDir);
    }
    
    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Arrays.hashCode(this.hash);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SaveGame other = (SaveGame) obj;
        if (!Arrays.equals(this.hash, other.hash)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(SaveGame o) {
        return new Long(this.getLastModified()).compareTo(o == null ? null : o.getLastModified());
    }
}
