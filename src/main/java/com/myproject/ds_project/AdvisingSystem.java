/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.ds_project;

/**
 *
 * @author loloo
 */
import java.util.Scanner;
import java.io.File;

public class AdvisingSystem implements IAdvisingSystem {

    public StudentList slist = new StudentList();
    public EventList Elist = new EventList();
    //counter for events?? has no ID
    //public static int eventIDcounter=41;
    public static int eventIDcounter = 41;
    private IEventList scheduled_eventList;
    private IEventList pure_eventList;//oo
    private LinkedList<IEvent> pure_workShoptList;//oo

    ///oo
     public AdvisingSystem() {
        this.slist = new StudentList();
        this.scheduled_eventList = new EventList();
        pure_eventList = new EventList();
        pure_workShoptList = new LinkedList<>();

    }//oo

    //by sarah 
    @Override
    public boolean loadStudentsFromCSV(String studentsFilePath) {
        try {
            Scanner scanner = new Scanner(new File(studentsFilePath));

            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {

                String[] studentData = scanner.nextLine().trim().split(","); //scanner
                IStudent student = new Student(
                        Integer.parseInt(studentData[0])
                );

                student.setName(studentData[1]);
                student.setEmail(studentData[2]);
                student.setMajor(studentData[3]);
                student.setYearLevel(Integer.parseInt(studentData[4]));
                student.setNotes(studentData[5]);

                addStudent(student);
            }
            scanner.close();
            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean loadEventsFromCSV(String eventsFilePath) {
        try {

            Scanner scanner = new Scanner(new File(eventsFilePath));

            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            int not_exist_in_event_id = -1;

            while (scanner.hasNextLine()) {
                boolean stud_not_exist = false;

                String line = scanner.nextLine();

                String[] d = line.trim().split(",");

                int eventId = Integer.parseInt(d[0]);
                String title = d[1];
                String type = d[2];
                int studentId = Integer.parseInt(d[3]);

                IDateTime start = parseDateTime(d[4]);
                ///////// later            

                IDateTime end = parseDateTime(d[5]);
                //////////// later
                String location = d[6];

                IStudent student = searchStudentById(studentId); //studentList.findById(studentId); 
                if (student == null) {
                    if (type.equalsIgnoreCase("Workshop")) {
                        stud_not_exist = true;
                        not_exist_in_event_id = eventId;
                    }
                    continue;
                } else if (eventId == not_exist_in_event_id) {
                    continue;
                }
                // meeting              
                if (type.equalsIgnoreCase("Meeting")) {
                    IEvent m = new Meeting(eventId, title, start, end, location, student);
                    pure_eventList.addEvent(m);

                    //schedule meeting 
                    boolean sched = scheduleMeeting("-1", start, end, location, studentId);
                    if (sched == true) {
                        scheduled_eventList.addEvent(m);
                    }

                } else if (type.equalsIgnoreCase("Workshop")) {

                    if (!pure_workShoptList.empty() && eventId == pure_workShoptList.retrieve().getEventId()) {
                        ((Workshop) pure_workShoptList.retrieve()).addParticipant(student);

                    } else if (pure_workShoptList.empty() || eventId > pure_workShoptList.retrieve().getEventId()) {
                        Workshop w1 = new Workshop(eventId, title, start, end, location);
                        pure_eventList.addEvent(w1);
                        pure_workShoptList.insert(w1);
                        w1.addParticipant(student);
                    }
                }
            }
            schedule_All_workShops();
            //////////later
           scanner.close();

            return true;

        } catch (Exception e) {
            System.out.println(" error reading ! ");
            System.out.println(e.getMessage());
            return false;
        }

    }

    private boolean schedule_All_workShops() {
        if (pure_workShoptList.empty()) {
            System.out.println("no workshops");
            return false;
        } else {
            pure_workShoptList.findFirst();
            while (true) {
                Workshop cur = (Workshop) pure_workShoptList.retrieve();
                LinkedList<IStudent> Participants = cur.getParticipants();
                if (Participants.empty()) {
                    scheduled_eventList.addEvent(cur);
                    return true;
                }
                int[] ids = new int[Participants.getSize()];
                Participants.findFirst();
                for (int i = 0; i < Participants.getSize(); i++) {
                    ids[i] = Participants.retrieve().getStudentId();
                    if (!Participants.last()) {
                        Participants.findNext();
                    }
                }

                boolean sched = scheduleWorkshop("-1", cur.getStartDateTime(), cur.getEndDateTime(), cur.getLocation(), ids);

                if (sched == true) {
                    scheduled_eventList.addEvent(cur);
                    Participants.findFirst();
                    for (int i = 0; i < Participants.getSize(); i++) {
                        Participants.retrieve().getSchedule().insert(cur);
                        if (!Participants.last()) {
                            Participants.findNext();
                        }
                    }
                }
//                             

                if (pure_workShoptList.last()) {
                    break;
                }

                pure_workShoptList.findNext();
            }
            return true;
        }
    }

    @Override
    public boolean addStudent(IStudent student) {
        return slist.add(student);
    

    /////slist
    }

    @Override
    public IStudent searchStudentById(int studentId) {
        return slist.findById(studentId);
    

    //////
    }

    @Override
    public IStudent searchStudentByEmail(String email) {
        return slist.findByEmail(email);
    

    //////
    }
//============================oo
    @Override
    public void printStudentsByMajor(String major) {
        printStudentList(slist.findByMajor(major));
    

    //////
    }

    @Override
    public void printStudentsByYearLevel(int yearLevel) {
        printStudentList(slist.findByYearLevel(yearLevel));
    }

    @Override
    public void printStudentsByName(String fullName) {
        printStudentList(slist.findByName(fullName));
    }

    @Override
    public void printStudentsByPartialName(String partialName) {
        printStudentList(slist.findByNameContains(partialName));
    }

    @Override
    public void printAllStudents() {
        printStudentList(slist.getAll());
        System.out.println("num of allstudents=" + slist.size());

    }

    // useing Helper methods used to printing student info
    // Print all students in the given list with their schedules.
    private void printStudentList(LinkedList<IStudent> list) {

        if (list.empty()) {
            System.out.println("no existing students");
            return;
        }

        list.findFirst();

        while (true) {

            IStudent student = list.retrieve();

            System.out.println(student);

            printStudentSchedule(student.getSchedule());

            System.out.println("\n-----------------------------------");

            if (list.last()) {
                break;
            }

            list.findNext();
        }
    }

    private IDateTime parseDateTime(String input) {

        String[] parts = input.trim().split(" ");

        String[] date = parts[0].split("/");
        String[] time = parts[1].split(":");

        int month = Integer.parseInt(date[0]);
        int day = Integer.parseInt(date[1]);
        int year = Integer.parseInt(date[2]);

        int hour = Integer.parseInt(time[0]);
        int minute = Integer.parseInt(time[1]);

        return new DateTime(year, month, day, hour, minute);
    }

    public LinkedList<IStudent> searchStudentByName(String name) {
        //////////
    return slist.findByName(name);
    }

    /////oooo
/*
public IStudent searchStudentByName(String name) {

    LinkedList<IStudent> list = slist.findByName(name);

    if (list.empty()) {
        return null;
    }

    list.findFirst();

    return list.retrieve(); 
}////oooo 
*/

    
// Print IDs of workshop participants.
private void printParticipants(LinkedList<IStudent> list) {

        if (list.empty()) {
            System.out.println("no events exist");
            return;
        }

        list.findFirst();

        while (true) {

            IStudent student = list.retrieve();

            System.out.print(student.getStudentId() + ",");

            if (list.last()) {
                break;
            }

            list.findNext();
        }

        System.out.println("\n-----------------------------------");
    }

// Print event IDs from a student's schedule.
    private void printStudentSchedule(LinkedList<IEvent> list) {

        if (list.empty()) {
            System.out.println("no events exist");
            return;
        }

        list.findFirst();

        while (true) {

            System.out.print(list.retrieve().getEventId() + ",");

            if (list.last()) {
                break;
            }

            list.findNext();
        }

        System.out.println("\n--------------");
    }
//-------- end sarah

    @Override
    public boolean deleteStudent(int studentId) {//Deletes a student and performs cascade deletion of associated events
        IStudent student = slist.findById(studentId);
        if (student == null) {
            return false;
        }
        LinkedList<IEvent> studentSchedule = student.getSchedule();
        studentSchedule.findFirst();
        IEvent event = null;
        for (int i = 0; i < studentSchedule.size; i++) {
            event = (IEvent) studentSchedule.retrieve();
            if (event instanceof IMeeting)//All meetings involving the student are deleted.
            {
                Elist.removeEventById(event.getEventId());
            }
            if (event instanceof IWorkshop) {//The student is removed from any workshops.
                IWorkshop workshop = (IWorkshop) event;
                workshop.removeParticipantById(studentId);
                if (workshop.isEmpty())//Workshops with no remaining participants are deleted.
                {
                    Elist.removeEventById(event.getEventId());
                }
            }
            studentSchedule.findNext();
        }
        return slist.deleteById(studentId);//It returns true if deletion occurred
    }

    @Override
    public boolean scheduleMeeting(String title, IDateTime startDateTime, IDateTime endDateTime, String location, int studentId) {
        IStudent s = slist.findById(studentId);
        if (s == null)//check if student exist
        {
            return false;
        }

        LinkedList<IEvent> studentEvents = s.getSchedule();
        if (!studentEvents.empty()) {
            studentEvents.findFirst();
            while (true) {
                IEvent CurrentEvent = studentEvents.retrieve();
                if (startDateTime.compareTo(CurrentEvent.getEndDateTime()) < 0
                        && endDateTime.compareTo(CurrentEvent.getStartDateTime()) > 0) {
                    return false;
                }
                if (studentEvents.last()) {
                    break;
                }
                studentEvents.findNext();
            }//end check any conflict
        }//end if

        int eventId = eventIDcounter++; //it will increased in all class
        IEvent e = new Meeting(eventId, title, startDateTime, endDateTime, location, s);
        boolean addedEvent = Elist.addEvent(e);
        if (addedEvent) {
            s.getSchedule().insert(e);
            return true;
        }
        return false;

    }
// ----start sarah

    @Override
    public boolean scheduleWorkshop(String title, IDateTime startDateTime, IDateTime endDateTime, String location, int[] studentIds) {
        Workshop m = new Workshop(-1, title, startDateTime, endDateTime, location);
        for (int i = 0; i < studentIds.length; i++) {
            IStudent s = searchStudentById(studentIds[i]);
            if (s == null) {
                return false;
            }

            LinkedList<IEvent> student_schedule = s.getSchedule();
            boolean is_conflict = is_Event_conflict_with_student_schedule(m, student_schedule);
            ////now 
            if (is_conflict) {
                return false;
            }//end if 
        }

        if (!title.equals("-1")) // If the workshop is manually scheduled
        {
            scheduled_eventList.addEvent(m);

            for (int i = 0; i < studentIds.length; i++) {
                IStudent s = searchStudentById(studentIds[i]);
                LinkedList<IEvent> student_schedule = s.getSchedule();
                student_schedule.insert(m);
            }
        }

        return true;
    }

    private boolean is_Event_conflict_with_student_schedule(IEvent e, LinkedList<IEvent> student_schedule) {
        if (student_schedule.empty()) {
            return false;
        }
        student_schedule.findFirst();

        while (true) {
            IEvent current = student_schedule.retrieve();
            if (is_2_Events_Conflict(current, e)) {
                return true;
            }

            if (student_schedule.last()) {
                break;
            }

            student_schedule.findNext();

        }
        return false;
    }

    private boolean is_2_Events_Conflict(IEvent e1, IEvent e2) {
        if (e1.getEndDateTime().compareTo(e2.getStartDateTime()) <= 0) {
            return false;
        } else if (e2.getEndDateTime().compareTo(e1.getStartDateTime()) <= 0) {
            return false;
        }

        return true;
    }

    @Override
    public void printEventDetailsByTitle(String title) {
LinkedList<IEvent> result = pure_eventList.findByTitle(title);

        if (result == null || result.empty()) {
            System.out.println("No event found with this title.");
        } else {
            result.printList();
        }
    }

    @Override
    public void printEventDetailsByStudentName(String studentName) {
    LinkedList<IEvent> events = pure_eventList.findByStudentName(studentName);

        if (!events.empty()) {
            events.printList();
        } else {
            System.out.println("No events for student (" + studentName + ")");   //now
        }
    }

    @Override
    public void printWorkshopStudents(String workshopTitle) {

        LinkedList<IEvent> list = scheduled_eventList.getAllAlphabetically();
        if (list.empty()) {
            System.out.println("no events exist");
            return;
        }
        list.findFirst();

        while (true) {
            IEvent e = list.retrieve();

            if ((e instanceof Workshop)
                    && e.getTitle().equalsIgnoreCase(workshopTitle)) {
                System.out.println("event id: " + e.getEventId());
                System.out.println("event type: " + e.getEventType()); //now
                System.out.println("event title: " + e.getTitle());
                System.out.print("Student Id(s): ");
                LinkedList<IStudent> p = ((Workshop) e).getParticipants();
                printParticipants(p);
            
            ///oo
            }              

            if (list.last()) {
                break;
            }

            list.findNext();
        }
    }

    @Override
    public void printAllEventsAlphabetically() {
    LinkedList<IEvent> events = pure_eventList.getAllAlphabetically();
        if (!events.empty()) {
            events.findFirst();
            while (!events.last()) {
                System.out.println(events.retrieve().getTitle() + " (" + events.retrieve().getEventId() + ") ");
                events.findNext();
            }
            System.out.println(events.retrieve().getTitle() + " (" + events.retrieve().getEventId() + ") ");
        }

    }//-----end sarah

}
