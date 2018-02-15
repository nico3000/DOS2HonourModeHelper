/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

/**
 *
 * @author nicot
 */
public class ProfileManager implements ComboBoxModel<String> {
    private String baseDirectory;
    private final List<String> profiles = new LinkedList<>();
    private int selected = -1;
    private final List<ListDataListener> listeners = new LinkedList<>();
    private String lastError;
    
    public String getLastError() {
        String temp = this.lastError;
        this.lastError = null;
        return temp;
    }
            
    public String getSaveGameDirectory() {
        return this.selected != -1 ? this.baseDirectory + File.separator + this.profiles.get(this.selected) + File.separator + "Savegames" : null;
    }
    
    public String getBaseDirectory() {
        return this.baseDirectory;
    }
    
    public void clear() {
        if(!this.profiles.isEmpty()) {
            this.selected = -1;
            ListDataEvent evt = new ListDataEvent(this, ListDataEvent.INTERVAL_REMOVED, 0, this.profiles.size());
            for(ListDataListener l : this.listeners) {
                l.intervalRemoved(evt);
            }
            this.profiles.clear();
        }
    }
    
    public void setBaseDirectory(File baseDirectory) {
        if(baseDirectory != null && baseDirectory.exists()) {
            this.clear();
            this.baseDirectory = !baseDirectory.exists() ? null : baseDirectory.getPath();
            for(File profile : baseDirectory.listFiles()) {
                if(profile.isDirectory()) {
                    this.profiles.add(profile.getName());
                }
            }
            if(!this.profiles.isEmpty()) {
                this.selected = 0;
                ListDataEvent evt = new ListDataEvent(this, ListDataEvent.INTERVAL_ADDED, 0, this.profiles.size() - 1);
                for(ListDataListener l : this.listeners) {
                    l.intervalAdded(evt);
                }
            }
        }
    }
    
    @Override
    public void setSelectedItem(Object anItem) {
        this.selected = this.profiles.indexOf(anItem);
    }

    @Override
    public Object getSelectedItem() {
        return this.selected == -1 ? null : this.profiles.get(this.selected);
    }

    @Override
    public int getSize() {
        return this.profiles.size();
    }

    @Override
    public String getElementAt(int index) {
        return index < this.profiles.size() ? this.profiles.get(index) : null;
    }

    @Override
    public void addListDataListener(ListDataListener l) {
        this.listeners.add(l);
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        this.listeners.remove(l);
    }
}
