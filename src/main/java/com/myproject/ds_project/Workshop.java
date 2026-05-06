/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.ds_project;

/**
 *
 * @author loolo
 */
public class Workshop implements IWorkshop{
    public IStudentList students;
    final int EventId;
    String title;
    IDateTime startDateTime;
    IDateTime endDateTime;
    String location;
    
    public Workshop(int eID, IDateTime sDT , IDateTime eDT )
        {
            students = new StudentList();
            EventId = eID;
            startDateTime = new DateTime (sDT);
            endDateTime = new DateTime (eDT);
            
        }
    public Workshop(int eID, String t, IDateTime sDT , IDateTime eDT, String loc )
        {
            EventId = eID;
            title = t;
            startDateTime = new DateTime (sDT);
            endDateTime = new DateTime (eDT);
            location = loc;
            
            students = new StudentList();
        }
    public Workshop(int eID, String t, IDateTime sDT , IDateTime eDT, String loc, IStudent std )
        {
            EventId = eID;
            title = t;
            startDateTime = new DateTime (sDT);
            endDateTime = new DateTime (eDT);
            location = loc;

            students = new StudentList();
            students.add(std);
        }
    public Workshop(Workshop workshop )
        {
            EventId = workshop.EventId;
            title = workshop.title;
            startDateTime = new DateTime (workshop.startDateTime);
            endDateTime = new DateTime (workshop.endDateTime);
            location = workshop.location;

            students = new StudentList();
            if (! workshop.getParticipants().empty())
            {
                workshop.getParticipants().findFirst();
                while ( ! workshop.getParticipants().last())
                {
                    students.add(workshop.getParticipants().retrieve());
                    workshop.getParticipants().findNext();
                }
                students.add(workshop.getParticipants().retrieve());
            }
        }

    @Override
    public LinkedList<IStudent> getParticipants() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean addParticipant(IStudent student) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean removeParticipantById(int studentId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean isEmpty() {
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
