/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.myproject.ds_project;

/**
 *
 * @author loolo
 */
public class DS_Project {

    public static void main(String[] args) {

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

   // System.out.println("9. Print students by major");
   //System.out.println("10. Print students by year level");
    //System.out.println("11. Print students by partial name");
    //System.out.println("12. Print workshop students");
    //System.out.println("13. Search student by email");
    //System.out.println("14. Print all students");
    //System.out.println("15. Print event details by title");
    //System.out.println("16. Print event details by student name");

    System.out.print("\nEnter your choice: ");
    int choice = input.nextInt();
    input.nextLine();
    switch (choice){
            // add new student 
        case 1:  {
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

                    IStudent s = new Student(id,name, email, major, year, notes);

                    if (system.addStudent(s)) {
                        System.out.println("Student added successfully!");
                    } else {
                        System.out.println("Failed to add student!");
                    }

                    break;
                }


            case 2: // search for student
            
           // delete student by ID
              case 3 : {

                    System.out.print("Enter student ID to delete: ");
                    int id = input.nextInt();

                    if (system.deleteStudent(id)) {
                        System.out.println("Student deleted successfully!");
                    } else {
                        System.out.println("Student not found!");
                    }

                    break;
                }

            case 4: //Schedual new workshop or new mwwtwing
            
            case 5: // print event details
            
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
            advisisngSystem.printWorkshopStudents(WorkshopTitle);
            }
            break;
            
            case 9:
            advisingSystem.printAllStudents();
            break;
            
            case 10:
            System.out.println("Goodbye!");
            break;
            
        default:
            System.out.println("Bad choice! Try again");
            
            
            
            
    }
            
}
    }
}
