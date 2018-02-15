/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

/**
 *
 * @author nicot
 */
public class BackupManager implements ListModel<String>, Observer {
    private File backupDirectory = null;
    private SaveGame originalSaveGame = null;
    private final List<SaveGame> saveGames = new LinkedList<>();
    private final List<ListDataListener> listeners = new LinkedList<>();
    
    public boolean backupIfNeeded(int maxGenericSaveGames) throws IOException {
        if(this.originalSaveGame == null || !this.originalSaveGame.exists()) {
            throw new UnsupportedOperationException("No save game available!");
        }
        if(!this.originalSaveGame.isAvailable()) {
            throw new IOException("Save game is probably currently changing.");
        }
        for(SaveGame saveGame : this.saveGames) {
            if(saveGame.equals(this.originalSaveGame)) {
                return false;
            }
        }
        if(!this.backupDirectory.exists() && !this.backupDirectory.mkdir()) {
            throw new IOException("Creation of backup directory failed!");
        }
        
        String timestamp = new SimpleDateFormat("YYYY-MM-dd_HH-mm-ss").format(new Date(this.originalSaveGame.getLastModified()));
        File destinationDir = new File(this.backupDirectory.getAbsolutePath() + File.separator + timestamp);
        if(destinationDir.isFile()) {
            throw new UnsupportedOperationException("Save game copy destination path points to a non-directory!");
        }
        if(!destinationDir.exists() && !destinationDir.mkdir()) {
            throw new IOException("Creation of copy destination directory for save game failed!");
        }
        SaveGame saveGame = this.originalSaveGame.copy(destinationDir);
        saveGame.addObserver(this);
        for(int i = 0; i < this.saveGames.size(); ++i) {
            if(this.saveGames.get(i).getDirectory().equals(saveGame.getDirectory())) {
                this.saveGames.get(i).deleteObserver(this);
                this.saveGames.set(i, saveGame);
                ListDataEvent evt = new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, i, i);
                for(ListDataListener l : this.listeners) {
                    l.contentsChanged(evt);
                }
                return true;
            }
        }
        int index = -Collections.binarySearch(this.saveGames, saveGame) - 1;
        this.saveGames.add(index, saveGame);
        ListDataEvent evt = new ListDataEvent(this, ListDataEvent.INTERVAL_ADDED, index, index);
        for(ListDataListener l : this.listeners) {
            l.intervalAdded(evt);
        }
        if(maxGenericSaveGames != -1) {
            SaveGame toDelete = null;
            int count = 0;
            for(SaveGame t : this.saveGames) {
                if(!t.hasCustomName()) {
                    ++count;
                    if(toDelete == null) {
                        toDelete = t;
                    }
                }
            }
            if(count > maxGenericSaveGames) {
                this.delete(toDelete);
            }
        }
        return true;
    }
    
    public void setSaveGameDirectory(String path) throws IOException {
        File tempBackupDirectory = path != null ? new File(path + File.separator + "Backup") : null;
        if(tempBackupDirectory != null && tempBackupDirectory.isFile()) {
            throw new UnsupportedOperationException("Path to backup directory points to an actual file!");
        }
        if((tempBackupDirectory == null && this.backupDirectory != null) || (tempBackupDirectory != null && !tempBackupDirectory.equals(this.backupDirectory))) {
            this.originalSaveGame = path != null ? new SaveGame(new File(path + File.separator + "Story" + File.separator + "HonourMode")) : null;
            this.backupDirectory = tempBackupDirectory;
            this.refresh();
        }
    }
    
    public void refresh() {
        if(!this.saveGames.isEmpty()) {
            for(SaveGame saveGame : this.saveGames) {
                saveGame.deleteObserver(this);
            }
            ListDataEvent evt = new ListDataEvent(this, ListDataEvent.INTERVAL_REMOVED, 0, this.saveGames.size());
            this.saveGames.clear();
            for(ListDataListener l : this.listeners) {
                l.intervalRemoved(evt);
            }
        }
        if(this.backupDirectory != null && this.backupDirectory.isDirectory()) {
            for(File saveGameDir : this.backupDirectory.listFiles()) {
                if(saveGameDir.isDirectory()) {
                    SaveGame saveGame = new SaveGame(saveGameDir);
                    if(saveGame.exists()) {
                        saveGame.addObserver(this);
                        this.saveGames.add(saveGame);
                    }
                }
            }
            if(!this.saveGames.isEmpty()) {
                Collections.sort(this.saveGames);
                ListDataEvent evt = new ListDataEvent(this, ListDataEvent.INTERVAL_ADDED, 0, this.saveGames.size());
                for(ListDataListener l : this.listeners) {
                    l.intervalAdded(evt);
                }
            }
        }
    }
    
    public void restore(SaveGame saveGame) throws IOException {
        int index = this.saveGames.indexOf(saveGame);
        if(index != -1) {
            int lastIndex = this.saveGames.indexOf(this.originalSaveGame);
            this.originalSaveGame = this.saveGames.get(index).copy(this.originalSaveGame.getDirectory());
            ListDataEvent evt1 = new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, lastIndex, lastIndex);
            ListDataEvent evt2 = new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, index, index);
            for(ListDataListener l : this.listeners) {
                l.contentsChanged(evt1);
                l.contentsChanged(evt2);
            }
        }
    }
    
    public void delete(int indices[]) {
        Arrays.sort(indices);
        for(int i = indices.length; i != 0; --i) {
            if(indices[i - 1] < this.saveGames.size()) {
                this.delete(this.saveGames.get(indices[i - 1]));
            }
        }
    }
    
    public void delete(SaveGame saveGame) {
        int index = this.saveGames.indexOf(saveGame);
        if(index != -1) {
            saveGame.delete();
            saveGame.deleteObserver(this);
            this.saveGames.remove(index);
            ListDataEvent evt = new ListDataEvent(this, ListDataEvent.INTERVAL_REMOVED, index, index + 1);
            for(ListDataListener l : this.listeners) {
                l.intervalRemoved(evt);
            }
        }
    }
    
    public SaveGame getSaveGame(int index) {
        return index < this.saveGames.size() ? this.saveGames.get(index) : null;
    }

    @Override
    public int getSize() {
        return this.saveGames.size();
    }

    @Override
    public String getElementAt(int index) {
        if(index >= this.saveGames.size()) {
            return null;
        }
        return this.saveGames.get(index).getName() + (this.saveGames.get(index).equals(this.originalSaveGame) ? " (current)" : "");
    }

    @Override
    public void addListDataListener(ListDataListener l) {
        this.listeners.add(l);
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        this.listeners.remove(l);
    }

    @Override
    public void update(Observable o, Object arg) {
        int index = this.saveGames.indexOf(o);
        if(index != -1) {
            ListDataEvent evt = new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, index, index);
            for(ListDataListener l : this.listeners) {
                l.contentsChanged(evt);
            }
        }
    }
}
