/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.ds_project;

/**
 *
 * @author loolo
 */
public abstract class Event implements IEvent{
    //attributes:
    int eventId;
    String title;
    IDateTime startDateTime;
    IDateTime endDateTime;
    String location;
    
    @Override
    public int getEventId() {
        return eventId;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public IDateTime getStartDateTime() {
        return startDateTime;
    }

    @Override
    public IDateTime getEndDateTime() {
        return endDateTime;
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean hasStudent(int studentId) { //check if student is in this event
        return false;//overriden by its children
    }
    
    @Override
    public String toString(){
        String str;
        str = "Event title :" + title + "\n Event ID: " + eventId + "\n Start date and time: " +
                startDateTime+ "\n End date and time: " + endDateTime + "\n Location: " + location;
        return str;
    }
    @Override
    public int compareTo(IEvent other) {
        if(other.getTitle() == null)
            throw new NullPointerException("The other event cannot be null");
        int len1 = this.title.length();
        int len2 = other.getTitle().length();
        int minLen;
        if(len1>len2)
            minLen = len2;
        else
            minLen = len1;
        
        char c1,c2;
        for(int i=0; i<minLen; i++){
            c1 = this.title.charAt(i);
            c2 = other.getTitle().charAt(i);
            
            if(c1>c2)
                return 1;
            if(c1<c2)
                return -1;
        }
        
        if(len1<len2)
            return -1;
        if(len1>len2)
            return 1;
    
        return 0;// in this case the titles are identical
                
    }
    
}
