/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.ds_project;

/**
 *
 * @author loolo
 */
public class Student implements IStudent {

    final int STUDENTID;
    String studentName; //full name
    String email;
    String major;
    int yearLevel;
    String notes;
    LinkedList<IEvent> sEvents;

    public Student(int ID) {
        STUDENTID = ID;
        sEvents = new LinkedList<IEvent>(); //new empty linkedlist

    }

    public Student(IStudent student) { //copy object to object
        STUDENTID = student.getStudentId();
        studentName = student.getName();
        email = student.getEmail();
        major = student.getEmail();
        yearLevel = student.getYearLevel();
        notes = student.getNotes();
        sEvents = new LinkedList<IEvent>();//new empty linkedlist
        IEvent e;
        if (!student.getSchedule().empty()) { //if student has events in schedual

            student.getSchedule().findFirst(); //first event

            while (!student.getSchedule().last()) { //loop for all events
                if (student.getSchedule().retrieve() instanceof Meeting) {
                    e = new Meeting((Meeting) (student.getSchedule().retrieve()));
                } else {
                    e = new Workshop((Workshop) (student.getSchedule().retrieve()));
                }

                sEvents.insert(e);
                student.getSchedule().findNext();
            } //end while

            if (student.getSchedule().retrieve() instanceof Meeting) //last event
            {
                e = new Meeting((Meeting) (student.getSchedule().retrieve()));
            } else {
                e = new Workshop((Workshop) (student.getSchedule().retrieve()));
            }
            sEvents.insert(e);
        }//end if
    }//end cons

    @Override
    public String getName() {
        return studentName;
    }

    @Override
    public void setName(String name) {
        studentName = name.trim();// trim()...used to remove leading (at the start) and trailing (at the end) whitespace from a string
    }

    @Override
    public int getStudentId() {
        return STUDENTID;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getMajor() {
        return major;
    }

    @Override
    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public int getYearLevel() {
        return yearLevel;
    }

    @Override
    public void setYearLevel(int yearLevel) {
        this.yearLevel = yearLevel;
    }

    @Override
    public String getNotes() {
        return notes;
    }

    @Override
    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String getFirstName() {
        return studentName.substring(0, studentName.indexOf(" "));
    }

    @Override
    public LinkedList<IEvent> getSchedule() {
        return sEvents;
    }

    public String toString() {
        String str = "";
        str += "Name: " + this.studentName + "\n";
        str += "Student ID: " + this.STUDENTID + "\n";
        str += "Email Address: " + this.email + "\n";
        str += "Major: " + this.major + "\n";
        str += "Year Level:" + this.yearLevel + "\n";
        str += "Notes: " + this.notes + "\n";

        str += "Student events Schedules : " + "\n";
        if (!sEvents.empty()) //print events
        {
            sEvents.findFirst();
            while (!sEvents.last()) {
                str += sEvents.retrieve().getTitle() + "  (" + sEvents.retrieve().getEventId() + ")  \n";
                sEvents.findNext();
            }
            str += sEvents.retrieve().getTitle() + "  (" + sEvents.retrieve().getEventId() + ")  \n";
        } else //if there is no events Scheduled
        {
            str += "No Scheduled Events. " + "\n";
        }

        return str;
    }

    @Override
    public int compareTo(IStudent other) {
        if (this.getStudentId() == other.getStudentId()) {
            return 0;
        }

        if (this.getStudentId() < other.getStudentId()) {
            return -1;
        }

        return 1;
    }

}
