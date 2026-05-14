/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.ds_project;

/**
 *
 * @author loloo
 */
public class AdvisingSystem implements IAdvisingSystem {
    public StudentList slist= new StudentList();
    public EventList Elist= new EventList();
    //counter for events?? has no ID
    //public static int eventIDcounter=41;
    public static int eventIDcounter=41;
    private IEventList scheduled_eventList;
    private IEventList pure_eventList;//check
    private LinkedList<IEvent> pure_workShoptList;//check

///check
     public AdvisingSystem() {
        this.studentList = new StudentList();
        this.scheduled_eventList = new EventList();
        pure_eventList = new EventList();
        pure_workShoptList = new LinkedList<>();
    
    }

    //by sarah 
    @Override
    public boolean loadStudentsFromCSV(String studentsFilePath) {
        try {
            Scanner scanner = new Scanner(new File(studentsfilePath));

            if (read.hasNextLine()) {
                read.nextLine();
            }

            while (read.hasNextLine()) {
                String[] studentData = read.nextLine().trim().split(",");
                IStudent student = new Student(                        
                        Integer.parseInt(studentData[0]),//student_id
                        studentData[1],
                        studentData[2],
                        studentData[3],
                        Integer.parseInt(studentData[4]),//year level 
                        studentData[5]
                );

                addStudent(s);
            }
            read.close();
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
            int not_exist_in_event_id=-1;
            while (scanner.hasNextLine()) {
                 boolean stud_not_exist=false;
               
                String line = scanner.nextLine();
                String[] d = line.trim().split(",");

                int eventId = Integer.parseInt(d[0]);
                String title = d[1];
                String type = d[2];
                int studentId = Integer.parseInt(d[3]);

                IDateTime start = parseDateTime(d[4]);  ///done             

                IDateTime end = parseDateTime(d[5]);///done
                String location = d[6];

                IStudent student =searchStudentById(studentId); //studentList.findById(studentId); 
                if (student == null) {                    
                    if (type.equalsIgnoreCase("Workshop")){
                        stud_not_exist=true;
                         not_exist_in_event_id=eventId;
                      }
                  continue;
                }
               else if(eventId== not_exist_in_event_id)
                   continue;
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
                     
                    if(!pure_workShoptList.empty()&&eventId==pure_workShoptList.retrieve().getEventId())
                    {
                      ((Workshop) pure_workShoptList.retrieve()).addParticipant(student);   
                       
                    }
                    
                   else if(pure_workShoptList.empty()||eventId>pure_workShoptList.retrieve().getEventId())
                    {
                      Workshop w1 = new Workshop(eventId, title, start, end, location);
                      pure_eventList.addEvent(w1);
                      pure_workShoptList.insert(w1);
                      w1.addParticipant(student);                      
                             }                   
                       }
                }
          schedule_All_workShops();//999
           scanner.close();
          
            return true;
        
        }catch (Exception e) {
            System.out.println(" error reading ! ");
            System.out.println(e.getMessage());
            return false;
        }

    }    

    @Override
    public boolean addStudent(IStudent student) {
        return studentList.add(student);
    }

    @Override
    public IStudent searchStudentById(int studentId) {
        return studentList.findById(studentId);
    }

    @Override
    public IStudent searchStudentByEmail(String email) {
      return studentList.findByEmail(email);
    }
//============================oo
    @Override
    public void printStudentsByMajor(String major) {
        printStudentList(studentList.findByMajor(major));    
    }

    @Override
    public void printStudentsByYearLevel(int yearLevel) {
        printStudentList(studentList.findByYearLevel(yearLevel));  
    }

    @Override
    public void printStudentsByName(String fullName) {
         printStudentList(studentList.findByName(fullName));   
    }

    @Override
    public void printStudentsByPartialName(String partialName) {
       printStudentList(studentList.findByNameContains(partialName));   
    }

    @Override
    public void printAllStudents() {
       printStudentList(studentList.getAll());
    System.out.println("num of allstudents=" + studentList.size());   
    
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
/////oooo
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

public IStudent searchStudentByName(String name) {

    LinkedList<IStudent> list = studentList.findByName(name);

    if (list.empty()) {
        return null;
    }

    list.findFirst();

    return list.retrieve();
}////oooo

    
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
    public boolean deleteStudent(int studentId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override 
    public boolean scheduleMeeting(String title, IDateTime startDateTime, IDateTime endDateTime, String location, int studentId) {
       IStudent s=slist.findById(studentId); 
       if (s==null)//check if student exist
           return false;
       
       
       LinkedList<IEvent> studentEvents=s.getSchedule();
       if (!studentEvents.empty()){
           studentEvents.findFirst();
           while(true){
               IEvent CurrentEvent=studentEvents.retrieve();
               if (startDateTime.compareTo(CurrentEvent.getEndDateTime()) <0 
                       && endDateTime.compareTo(CurrentEvent.getStartDateTime())>0 )
                   return false;
               if(studentEvents.last()) break;
               studentEvents.findFirst();
               
           }//end check any conflict
       }//end if
       
       int eventId=eventIDcounter++; //it will increased in all class
       IEvent e=new Meeting(eventId, title, startDateTime, endDateTime, location, s); 
       boolean addedEvent = Elist.addEvent(e);
       if (addedEvent){
           slist.deleteById(studentId);
           s.getSchedule().insert(e);
           slist.add(s);
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
            boolean is_conflict = is_Event_conflict_with_student_schedule(m, student_schedule);////999
            if (is_conflict) {
                return false;
            } 
        }//end if 
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
    }
    @Override
    public void printEventDetailsByTitle(String title) {
        displayEventList(scheduled_eventList.findByTitle(title));///999
    }

    @Override
    public void printEventDetailsByStudentName(String studentName) {
     displayEventList(scheduled_eventList.findByStudentName(studentName));   ///999
}

    @Override
    public void printWorkshopStudents(String workshopTitle)  {

       LinkedList<IEvent> list=scheduled_eventList.getAllAlphabetically();
        if (list.empty()) {
            System.out.println("no events exist");
            return;
        }
        list.findFirst();

        while (true) {
            IEvent e = list.retrieve();       

            if( (e instanceof Workshop)
               &&e.getTitle().equalsIgnoreCase(title))
            {         
                     System.out.println("event id: "+e.getEventId());
                     System.out.println("event type: "+e.getEventType());//999
                     System.out.println("event title: "+e.getTitle());
                    System.out.print("Student Id(s): ");
                     LinkedList<IStudent> p= ((Workshop) e).getParticipants();
                     printParticipants(p);///oo
            }              

            if (list.last()) 
            {
                break;
            }

            list.findNext();
        }
    }  
    

    @Override
    public void printAllEventsAlphabetically() {
       printEventList(scheduled_eventList.getAllAlphabetically());
      System.out.println("num of all schedulled events="+scheduled_eventList.size());
    }
    }//-----end sarah

    
}
