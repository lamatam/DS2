/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.ds_project;

/**
 *
 * @author loolo
 */
public class Meeting extends Event implements IMeeting{
        public IStudent student;
    
    public Meeting(int eID, IDateTime sDT , IDateTime eDT ){
            eventId = eID;
            startDateTime = new DateTime(sDT);
            endDateTime = new DateTime(eDT);
        }
    public Meeting(int eID, String t, IDateTime sDT , IDateTime eDT, String loc ){
            eventId = eID;
            title = t;
            startDateTime = new DateTime (sDT);
            endDateTime = new DateTime (eDT);
            location = loc;
        }
    public Meeting(int eID, String t, IDateTime sDT , IDateTime eDT, String loc, IStudent std ){
            eventId = eID;
            title = t;
            startDateTime = new DateTime (sDT);
            endDateTime = new DateTime (eDT);
            location = loc;
            student = new Student(std);
        }
    public Meeting(Meeting M ){
            eventId = M.eventId;
            title = M.title;
            startDateTime = new DateTime (M.startDateTime);
            endDateTime = new DateTime (M.endDateTime);
            location = M.location;
            student = new Student( M.getStudent()); 
        }
    @Override
    public IStudent getStudent() {
        return student;
    }

    @Override
    public void setStudent(IStudent student) {
        this.student = student;
    }

    @Override
    public boolean hasStudent(int studentId) {
        if (student == null)
            return false;
        return student.getStudentId() == studentId;
    }  

    public String getEventType() {
        return "Meeting";
    }

}
