//Author Tom Roberts.

package com.semantico.radiators.jirart;

public class RTdisplay {
     private String ticketNum,summary,queue;
     
     //CONSTRUCTOR
     public RTdisplay (String ticketNum,String summary, String queue) {
        this.ticketNum=ticketNum;
        this.summary=summary;
        this.queue=queue;
    }
     
    // GETTERS AND SETTERS
    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTicketNum() {
        return ticketNum;
    }

    public void setTicketNum(String ticketNum) {
        this.ticketNum = ticketNum;
    }
    
   
}
