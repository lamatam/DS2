/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.ds_project;

/**
 *
 * @author loolo
 */
public class StudentList implements IStudentList {
     public LinkedList <IStudent> students = new LinkedList <IStudent> ();

    @Override
    public boolean add(IStudent student)
    {
    if ( students.empty()) //if empty 
        {
            students.insert(new Student (student)); //send object 
            return true;
        }//end if 
    
    students.findFirst(); //otherwise
    if (student.compareTo(students.retrieve()) < 0) //comparing ID
    {
        students.insertAtBegin(student);
            return true;
    }//end if
    
    while ( ! students.last() && (student.compareTo(students.retrieve()) >= 0))
            students.findNext();

    if (student.getStudentId() == students.retrieve().getStudentId())
       return false; //becouse it exist
    int comp = student.compareTo(students.retrieve()); //comparing ID
    if (comp > 0 )
       students.insert(student);
     else 
       students.insertBefore(student);
    return true;
    }

    @Override
    public IStudent findById(int studentId) {
    IStudent student = null;
        
        boolean Found = false;
        if ( ! students.empty())
        {
            students.findFirst();
        
            while ( ! students.last() && ! Found)
            {    if ( studentId == students.retrieve().getStudentId())
                {
                    Found = true;
                    student = students.retrieve();
                }
                 else   
                    students.findNext();
            }
            if ( ! Found && (studentId == students.retrieve().getStudentId()))
            {
                Found = true;
                student = students.retrieve();
            }
        }
        return student;    }

    @Override
    public LinkedList<IStudent> findByName(String fullName) {
    LinkedList<IStudent> data = new LinkedList<IStudent>();
       if ( ! students.empty())
        {
            students.findFirst();
            while ( ! students.last())
            {
                if ( students.retrieve().getName().equalsIgnoreCase(fullName))
                    data.insert(new Student(students.retrieve()));
                students.findNext();
            }
            if ( students.retrieve().getName().equalsIgnoreCase(fullName))
                data.insert(new Student(students.retrieve()));
        }
        
        return data;    }

    @Override
    public LinkedList<IStudent> findByNameContains(String partialName) {
    LinkedList<IStudent> data = new LinkedList<IStudent>();

        if ( ! students.empty())
        {
            students.findFirst();
            while ( ! students.last())
            {   if ( students.retrieve().getName().contains(partialName))
                    data.insert(students.retrieve());
                students.findNext();
            }
            if ( students.retrieve().getName().contains(partialName))
                data.insert(students.retrieve());
        }
        return data;    }

    @Override
    public IStudent findByEmail(String email) {
    IStudent student = null;
        
        boolean Found = false;
        if ( ! students.empty())
        {
            students.findFirst();
            while ( ! students.last() && ! Found)
            {
                if ( email.compareToIgnoreCase(students.retrieve().getEmail()) == 0)
                {
                    Found = true;
                    student = students.retrieve();
                }
                 else   
                    students.findNext();
            }
        }
        if ( email.compareToIgnoreCase(students.retrieve().getEmail()) == 0)
        {
            Found = true;
            student = students.retrieve();
        }
        
        return student;    }

    @Override
    public LinkedList<IStudent> findByMajor(String major) {
    LinkedList<IStudent> data = new LinkedList<IStudent>();

        if ( ! students.empty())
        {
            students.findFirst();
            while ( ! students.last())
            {
                if ( students.retrieve().getMajor().compareToIgnoreCase(major) == 0)
                    data.insert(students.retrieve());
                students.findNext();
            }
            if ( students.retrieve().getMajor().compareToIgnoreCase(major) == 0)
                data.insert(students.retrieve());
        }
        return data;    }

    @Override
    public LinkedList<IStudent> findByYearLevel(int yearLevel) {
    LinkedList<IStudent> data = new LinkedList<IStudent>();

        if ( ! students.empty())
        {
            students.findFirst();
            while ( ! students.last())
            {
                if ( students.retrieve().getYearLevel() == yearLevel)
                    data.insert(students.retrieve());
                students.findNext();
            }
            if ( students.retrieve().getYearLevel() == yearLevel)
                data.insert(students.retrieve());
        }
        return data;    }

    @Override
    public LinkedList<IStudent> getAll() {
    return students;  
    }

    @Override
    public boolean deleteById(int studentId) {
    boolean Delete = false;
        if ( ! students.empty())
        {
            students.findFirst();
            while ( ! students.last() && ! Delete)
            {
                if ( studentId == students.retrieve().getStudentId())
                {
                    Delete = true;
                    students.remove();
                }
                 else   
                    students.findNext();
            }
            if ( ! Delete && studentId == students.retrieve().getStudentId())
            {
                Delete = true;
                students.remove();
            }
        }
        return Delete;
    }

    @Override
    public int size() {
        return students.getSize();
    }
    
}
