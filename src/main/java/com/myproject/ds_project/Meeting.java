/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.ds_project;

/**
 *
 * @author loolo
 */
public class Meeting implements IMeeting{
        public IStudent student;
        final int EventId;
        String Title;
        IDateTime startDateTime;
        IDateTime endDateTime;
        String location;
    
    public Meeting(int eID, IDateTime sDT , IDateTime eDT )
        {
            EventId = eID;
            startDateTime = new DateTime(sDT);
            endDateTime = new DateTime(eDT);
            
        }
    public Meeting(int eID, String t, IDateTime sDT , IDateTime eDT, String loc )
        {
            EventId = eID;
            Title = t;
            startDateTime = new DateTime (sDT);
            endDateTime = new DateTime (eDT);
            location = loc;
            
        }
    public Meeting(int eID, String t, IDateTime sDT , IDateTime eDT, String loc, IStudent std )
        {
            EventId = eID;
            Title = t;
            startDateTime = new DateTime (sDT);
            endDateTime = new DateTime (eDT);
            location = loc;
            student = new Student(std);
            
        }
    public Meeting(Meeting M )
        {
            EventId = M.EventId;
            Title = M.Title;
            startDateTime = new DateTime (M.startDateTime);
            endDateTime = new DateTime (M.endDateTime);
            location = M.location;
            student = new Student( M.getStudent()); 
            
        }
    @Override
    public IStudent getStudent() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setStudent(IStudent student) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getEventId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getTitle() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setTitle(String title) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public IDateTime getStartDateTime() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public IDateTime getEndDateTime() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getLocation() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setLocation(String location) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean hasStudent(int studentId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int compareTo(IEvent other) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
