/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.myproject.ds_project;
import java.util.Scanner;
/**
 *
 * @author loolo
 */
public class DS_Project {
    
public static Scanner input = new Scanner (System.in);
 public static AdvisingSystem advisingSystem = new AdvisingSystem();
    
    public static void main(String[] args) {
        advisingSystem.loadStudentsFromCSV("students_100.csv");
        advisingSystem.loadEventsFromCSV("events_40.csv");
        
int choice;
int choice2;
        //menu
        while (true) {

   System.out.println("\nWelcome to the Student Advising System!");
    System.out.println("\nPlease choose an option:");

    //System.out.println("0. Load data from files");
    System.out.println("1. Add a student");
    System.out.println("2. Search for a student");
    System.out.println("3. Delete a student");
    System.out.println("4. Schedule a meeting/workshop");
    System.out.println("5. Print meeting/workshop details");
    System.out.println("6. Print students by name");
    System.out.println("7. Print all meetings/workshops alphabetically");
    System.out.println("8. Print all workshops certain TITLE alphabetically");
    System.out.println("9. Print students ordered by ID");
    System.out.println("10. Exit");  

    System.out.print("\nEnter your choice: ");
    choice = input.nextInt();
    input.nextLine();
    switch (choice){
            // add new student 
 case 1:  {// add new student 
                    System.out.print("Enter the student's name: ");
                    String name = input.nextLine();

                    System.out.print("Enter the student's ID: ");
                    int id = input.nextInt();
                    input.nextLine();

                    System.out.print("Enter the student's email address: ");
                    String email = input.nextLine();

                    System.out.print("Enter the student's major: ");
                    String major = input.nextLine();

                    System.out.print("Enter the student's year level: ");
                    int year = input.nextInt();
                    input.nextLine();

                    System.out.print("Enter any notes for the student: ");
                    String notes = input.nextLine();

                    IStudent std = new Student (id);
                    std.setName(name);
                    std.setEmail(email);
                    std.setMajor(major);
                    std.setYearLevel(year);
                    std.setNotes(notes);

                    if (advisingSystem.addStudent(std)) {
                        System.out.println("Student added successfully!");
                    } else {
                        System.out.println("Failed to add student!");
                    }

                    break;
                }


            case 2: // search for student
            System.out.println("Enter search criteria: ");
                System.out.println("1. Name " ); // by name
                System.out.println("2. Student ID"); // by id
                System.out.println("3. Email"); // by id
                System.out.println("\n Enter your choice: ");
                choice2 = input.nextInt();
                input.nextLine();
                switch( choice2)
                {
                    case 1:
                        System.out.println("Enter the student's name: ");
                        String name = input.nextLine();
                        //name = input.nextLine();
                        advisingSystem.printStudentsByName(name);
                        break;
                    case 2:
                    {
                        System.out.print("Enter the student's ID: ");
                        int sID = input.nextInt();
                        IStudent student =advisingSystem.searchStudentById(sID);
                        if (student != null)
                                System.out.println( student );
                    }
                    break;
                    case 3:
                    {
                        System.out.print("Enter the student's Email: ");
                        String email = input.nextLine();
                        //email = input.nextLine();
                        IStudent student =advisingSystem.searchStudentByEmail(email);
                        if (student != null)
                                System.out.println( student );
                    }
                    break;
                    default :
                        System.out.println("Bad choice , try again");
                }
            
            
            break;
           // delete student by ID
              case 3 : {
                System.out.print("Enter the student's ID: ");
                  int sID = input.nextInt();
                  if (advisingSystem.searchStudentById(sID) != null)
                  {
                      boolean deleted = advisingSystem.deleteStudent(sID);
                      if (deleted)
                          System.out.println("Student deleted successfully!");
                      else
                          System.out.println("Could not delete student");
                  }
                  else
                      System.out.println("3 could not find student ID");
                   /* System.out.print("Enter student ID to delete: ");
                    int id = input.nextInt();

                    if (system.deleteStudent(id)) {
                        System.out.println("Student deleted successfully!");
                    } else {
                        System.out.println("Student not found!"); */
                    }

                    break;
                

            case 4: //Schedual new workshop or new mwwtwing
             {
                System.out.println("Enter search criteria: ");
                System.out.println("1. Workshop " );
                System.out.println("2. Meeting");
                System.out.println("\n Enter your choice: ");
                choice2 = input.nextInt();
                input.nextLine();
                switch( choice2)
                {
                
                case 1: //new workshop
                {System.out.print("Enter workshop title: ");
                String title = input.nextLine();
                //title = input.nextLine();
                
                System.out.print("Enter student names separated by a comma: ");
                String [] studentnames = input.nextLine().split(",");
                int [] IDs = new int [ studentnames.length ];
                int j = 0;
                for ( int i = 0; i < studentnames.length ; i++)
                {
                    LinkedList <IStudent> stds = advisingSystem.searchStudentByName(studentnames[i]);
                    if( ! stds.empty()) 
                    {  
                        stds.findFirst();
                        IDs [j++] = stds.retrieve().getStudentId();
                    }
                }

                if (studentnames.length != j)
                    System.out.println("Could not scheduled, could not find student/s");
                else
                {
                    System.out.print("Enter workshop start date and time (MM/DD/YYYY HH:MM): ");
                    String sdate = input.nextLine();
                    IDateTime sDate = new DateTime (sdate);

                    System.out.print("Enter workshop end date and time (MM/DD/YYYY HH:MM): ");
                    String edate = input.nextLine();
                    IDateTime eDate = new DateTime (edate);

                    System.out.print("Enter workshop location: ");
                    String loc = input.nextLine();

                    if (advisingSystem.scheduleWorkshop(title, sDate, eDate, loc, IDs))
                        System.out.println("Workshop scheduled successfully!");
                    else
                        System.out.println("Could not scheduled");
                }
            }    
                break;
                case 2:
                {
                    System.out.print("Enter meeting title: ");
                    String title = input.nextLine();
                    title = input.nextLine();
                        
                    System.out.print("Enter student name: ");
                    String sname = input.nextLine();
                    sname = input.nextLine();
                     
                    int sID = 0;
                    LinkedList <IStudent> stds = advisingSystem.searchStudentByName(sname);
                    if ( ! stds.empty())
                    {
                        stds.findFirst();
                        sID = stds.retrieve().getStudentId();
                    
                        System.out.print("Enter meeting start date and time (MM/DD/YYYY HH:MM): ");
                        String sdate = input.nextLine();
                        IDateTime sDate = new DateTime (sdate);

                        System.out.print("Enter meeting end date and time (MM/DD/YYYY HH:MM): ");
                        String edate = input.nextLine();
                        IDateTime eDate = new DateTime (edate);

                        System.out.print("Enter meeting location: ");
                        String loc = input.nextLine();

                        if (advisingSystem.scheduleMeeting(title, sDate, eDate, loc, sID))
                            System.out.println("Meeting scheduled successfully!");
                        else
                            System.out.println("Could not scheduled ");
                    }
                    else
                        System.out.println("Could not scheduled, could not find student");
                }
                    break;
                default :
                    System.out.println("Bad choice , try again");
                }
            }
                break;
            case 5: // print event details
            {
                System.out.println("Enter search criteria: ");
                System.out.println("1. Student name " );
                System.out.println("2. Workshop/Meeting title");
                System.out.println("\n Enter your choice: ");
                choice2 = input.nextInt();
                String name;
                switch( choice2)
                {
                    case 1:
                        System.out.println("Enter student Name : ");
                        name = input.nextLine();
                        name = input.nextLine();
                        advisingSystem.printEventDetailsByStudentName(name);
                        break;
                    
                    case 2:
                        System.out.print("Enter the workshop/meeting title: ");
                        name = input.nextLine();
                        name = input.nextLine();
                        advisingSystem.printEventDetailsByTitle(name);
                        break;
                    
                    default :
                        System.out.println("Bad choice , try again");
                }
            }
                break;
            case 6: 
            {
                System.out.print("Enter student partial name:");
                String name=input.nextLine();
                name=input.nextLine();
                System.out.println("");
                advisingSystem.printStudentsByPartialName(name);
                }
            break;
            case 7: 
            advisingSystem.printAllEventsAlphabetically();
             break;
            case 8:
            {
            System.out.print("Enter the workshop/meeting tite");
            String WorkshopTitle=input.nextLine();
            WorkshopTitle=input.nextLine();
            advisingSystem.printWorkshopStudents(WorkshopTitle);
            }
            break;
            
            case 9:
            advisingSystem.printAllStudents();
            break;
            
            case 10:
            System.out.println("Goodbye!");
            return;
            
        default:
            System.out.println("Bad choice! Try again");
            
            
            
            
    }
            
}
    }
}

