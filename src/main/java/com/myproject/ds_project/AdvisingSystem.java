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


    //by sarah 
    @Override
    public boolean loadStudentsFromCSV(String studentsFilePath) {
        try {
            Scanner read = new Scanner(new File(studentsfilePath));

            if (read.hasNextLine()) {
                read.nextLine();
            }

            while (read.hasNextLine()) {
                String[] d = read.nextLine().trim().split(",");
                IStudent s = new Student(                        
                        Integer.parseInt(d[0]),//student_id
                        d[1],
                        d[2],
                        d[3],
                        Integer.parseInt(d[4]),//year level 
                        d[5]
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

            Scanner read = new Scanner(new File(eventfilePath));
            
            if (read.hasNextLine()) {
                read.nextLine();
            }
            int not_exist_in_event_id=-1;
            while (read.hasNextLine()) {
                 boolean stud_not_exist=false;
               
                String line = read.nextLine();
                String[] d = line.trim().split(",");

                int eventId = Integer.parseInt(d[0]);
                String title = d[1];
                String type = d[2];
                int studentId = Integer.parseInt(d[3]);

                IDateTime start = parseDateTime(d[4]);                

                IDateTime end = parseDateTime(d[5]);
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
                //  MEETING               
                if (type.equalsIgnoreCase("Meeting")) {
                     IEvent m = new Meeting(eventId, title, start, end, location, student);
                       pure_eventList.addEvent(m);
                      // pure_meetings.insert(m);
                       //schedule meeting
                    boolean sched = scheduleMeeting("-1", start, end, location, studentId);                    
                    if (sched == true) {                      
                        student.getSchedule().insert(m);
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
          schedule_All_workShops();
            read.close();
          
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public IStudent searchStudentByEmail(String email) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void printStudentsByMajor(String major) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void printStudentsByYearLevel(int yearLevel) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void printStudentsByName(String fullName) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void printStudentsByPartialName(String partialName) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void printAllStudents() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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

    @Override
    public boolean scheduleWorkshop(String title, IDateTime startDateTime, IDateTime endDateTime, String location, int[] studentIds) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
/// ----start sarah
    @Override
    public void printEventDetailsByTitle(String title) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void printEventDetailsByStudentName(String studentName) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void printWorkshopStudents(String workshopTitle) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void printAllEventsAlphabetically() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
//-----end sarah
