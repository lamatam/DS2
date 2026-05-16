/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.ds_project;

/**
 *
 * @author loolo
 */
public class EventList implements IEventList {

    public LinkedList<IEvent> events = new LinkedList<IEvent>();

    @Override
    public boolean addEvent(IEvent event) {
        if (events.empty()) {
            events.insert(event);
            return true;
        }
        events.findFirst();
        if ((event.getTitle()).compareTo(events.retrieve().getTitle()) < 0) {
            events.insertAtBegin(event);
            return true;
        }
        for (int i = 0; i < events.size; i++) {
            if ((event.getTitle()).compareTo(events.retrieve().getTitle()) >= 0) {
                events.findNext(); //move current to insert the new event before it
                events.insertBefore(event);
                return true;
            }
            events.findNext();//move current for next iteration
        }
        return false;
    }

    @Override
    public boolean removeEventById(int eventId) {
        if (events == null) {
            return false;
        }
        events.findFirst();// move current to start
        for (int i = 0; i < events.size; i++) {
            if (events.retrieve().getEventId() == eventId) {
                events.remove();
                return true;
            }
            events.findNext();//to move current (prevent meaningless loop)
        }
        return false;
    }

    @Override
    public LinkedList<IEvent> getAllAlphabetically() {
        return events;
    }

    @Override
    public LinkedList<IEvent> findByTitle(String title) {
        if (events.empty()) {
            return null;
        }
        events.findFirst();// move current to start
        LinkedList<IEvent> matchingEvents = new LinkedList<IEvent>();
        for (int i = 0; i < events.size; i++) {
            if (title.compareTo(events.retrieve().getTitle()) == 0) {
                matchingEvents.insert(events.retrieve());
            }
            if (!events.last()) {
                events.findNext();//to move current (prevent meaningless loop)
            }
        }
        return matchingEvents;
    }

    @Override
    public LinkedList<IEvent> findByStudentName(String studentFullName) {

        LinkedList<IEvent> matchingEvents = new LinkedList<IEvent>();

        if (events.empty()) {
            return matchingEvents;
        }

        events.findFirst();

        for (int i = 0; i < events.size; i++) {

            IEvent currentEvent = events.retrieve();
            LinkedList<IStudent> studentse = new LinkedList<IStudent>();

            if (currentEvent instanceof Workshop workshop) {
                studentse = workshop.getParticipants();
            } else if (currentEvent instanceof Meeting meeting) {
                studentse.insert(meeting.getStudent());
            }

            if (!studentse.empty()) {
                studentse.findFirst();

                for (int j = 0; j < studentse.size; j++) {

                    if (studentse.retrieve().getName().equalsIgnoreCase(studentFullName)) {
                        matchingEvents.insert(currentEvent);
                        break;
                    }

                    if (!studentse.last()) {
                        studentse.findNext();
                    }
                }
            }

            if (!events.last()) {
                events.findNext();
            }
        }

        return matchingEvents;
    }

    @Override
    public int size() {
        return events.size;
    }

}
