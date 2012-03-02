/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tom.model;

/**
 *
 * @author Tom
 */
public class DisplayObject {
    // VARIABLES
    public String title,summary,lastUpdate;
   
    // CONSTRUCTORS
    public DisplayObject(){}
    public DisplayObject(String title, String summary, String lastUpdate) {
        this.title = title;
        this.summary = summary;
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