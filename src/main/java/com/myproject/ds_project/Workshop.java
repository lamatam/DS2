/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.ds_project;

/**
 *
 * @author loolo
 */
public class Workshop extends Event implements IWorkshop {

    public IStudentList students;

    public Workshop(int eID, IDateTime sDT, IDateTime eDT) {
        students = new StudentList();
        eventId = eID;
        startDateTime = new DateTime(sDT);
        endDateTime = new DateTime(eDT);
    }

    public Workshop(int eID, String t, IDateTime sDT, IDateTime eDT, String loc) {
        eventId = eID;
        title = t;
        startDateTime = new DateTime(sDT);
        endDateTime = new DateTime(eDT);
        location = loc;
        students = new StudentList();
    }

    public Workshop(int eID, String t, IDateTime sDT, IDateTime eDT, String loc, IStudent std) {
        eventId = eID;
        title = t;
        startDateTime = new DateTime(sDT);
        endDateTime = new DateTime(eDT);
        location = loc;
        students = new StudentList();
        students.add(std);
    }

    public Workshop(Workshop workshop) {
        eventId = workshop.eventId;
        title = workshop.title;
        startDateTime = new DateTime(workshop.startDateTime);
        endDateTime = new DateTime(workshop.endDateTime);
        location = workshop.location;
        students = new StudentList();
        if (!workshop.getParticipants().empty()) {
            workshop.getParticipants().findFirst();
            while (!workshop.getParticipants().last()) {
                students.add(workshop.getParticipants().retrieve());
                workshop.getParticipants().findNext();
            }
            students.add(workshop.getParticipants().retrieve());
        }
    }

    @Override
    public LinkedList<IStudent> getParticipants() {
        return students.getAll();
    }

    @Override
    public boolean addParticipant(IStudent student) {
        if (hasStudent(student.getStudentId())) {//to insure there is no duplicates
            return false;
        }
        students.add(student);
        return true;
    }

    @Override
    public boolean removeParticipantById(int studentId) {
        if (!hasStudent(studentId)) {
            return false; //There is no such a student participated in this workshop
        }
        students.deleteById(studentId);
        return true;
    }

    @Override
    public boolean isEmpty() {
        return getParticipants().empty();//reuse
    }

    @Override
    public boolean hasStudent(int studentId) {
        if (isEmpty()) {
            return false;
        }
        LinkedList tmp = students.getAll();
        tmp.findFirst();
        for (int i = 0; i < tmp.size; i++) {
            if (tmp.retrieve() != null && ((Student) (tmp.retrieve())).getStudentId() == studentId) {
                return true;
            }
            if (!tmp.last()) {
                tmp.findNext();
            }
        }
        return false;
    }

    public String getEventType() {
        return "Workshop";
    }
}
