//Author Tom Roberts.

package com.semantico.radiators.jirart;

public class JIRAdisplay {
    // VARIABLES
    public String title,summary,lastUpdate;
   
    // CONSTRUCTORS
    public JIRAdisplay(){}
    public JIRAdisplay(String title, String summary, String lastUpdate) {
        this.title = title;
        this.summary = summary.replaceAll("<", "&lt;");
        this.lastUpdate = lastUpdate;
    }
    
    // GETTERS & SETTERS
    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getLastUpdate() {
        return lastUpdate;
    }
    public String getSummary() {
        return summary;
    }
    public String getTitle() {
        return title;
    }
}