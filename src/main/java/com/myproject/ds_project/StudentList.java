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

    public LinkedList<IStudent> students = new LinkedList<IStudent>();

    @Override
    public boolean add(IStudent student) {
        if (students.empty()) //if empty 
        {
            students.insert(new Student(student)); //add and send object 
            return true; //success
        }//end if 

        students.findFirst(); //otherwise
        if (student.compareTo(students.retrieve()) < 0) //comparing ID
        {
            students.insertAtBegin(student);
            return true; //success
        }//end if

        while (!students.last() && (student.compareTo(students.retrieve()) >= 0)) {
            students.findNext();
        }

        if (student.getStudentId() == students.retrieve().getStudentId()) {
            return false; //becouse it exist
        }
        int comp = student.compareTo(students.retrieve()); //comparing ID 0 if it equal, -1 if left small than right, 1 otherwise
        if (comp > 0) {
            students.insert(student); //insert after current
        } else {
            students.insertBefore(student); //insert before current
        }
        return true;
    }

    @Override
    public IStudent findById(int studentId) { //return null if the student not found
        IStudent student = null;

        boolean Found = false;
        if (!students.empty()) {
            students.findFirst();

            while (!students.last() && !Found) {
                if (studentId == students.retrieve().getStudentId()) {
                    Found = true;
                    student = students.retrieve();
                } else {
                    students.findNext();
                }
            }
            if (!Found && (studentId == students.retrieve().getStudentId())) //comparing last id
            {
                Found = true;
                student = students.retrieve();
            }
        }
        return student;
    }

    @Override
    public LinkedList<IStudent> findByName(String fullName) {
        LinkedList<IStudent> data = new LinkedList<IStudent>();
        if (!students.empty()) {
            students.findFirst();
            while (!students.last()) {
                if (students.retrieve().getName().equalsIgnoreCase(fullName)) {
                    data.insert(new Student(students.retrieve()));
                }
                students.findNext();
            }
            if (students.retrieve().getName().equalsIgnoreCase(fullName)) {
                data.insert(new Student(students.retrieve()));
            }
        }

        return data;
    }

    @Override
    public LinkedList<IStudent> findByNameContains(String partialName) {
        LinkedList<IStudent> data = new LinkedList<IStudent>();

        if (!students.empty()) {
            students.findFirst();
            while (!students.last()) {
                if (students.retrieve().getName().contains(partialName)) {
                    data.insert(students.retrieve());
                }
                students.findNext();
            }
            if (students.retrieve().getName().contains(partialName)) {
                data.insert(students.retrieve());
            }
        }
        return data;
    }

    @Override
    public IStudent findByEmail(String email) {

        if (students.empty()) {
            return null;
        }

        students.findFirst();

        while (true) {

            if (students.retrieve().getEmail().equalsIgnoreCase(email)) {
                return students.retrieve();
            }

            if (students.last()) {
                break;
            }

            students.findNext();
        }

        return null;
    }

    @Override
    public LinkedList<IStudent> findByMajor(String major) {
        LinkedList<IStudent> data = new LinkedList<IStudent>();

        if (!students.empty()) {
            students.findFirst();
            while (!students.last()) {
                if (students.retrieve().getMajor().compareToIgnoreCase(major) == 0) {
                    data.insert(students.retrieve());
                }
                students.findNext();
            }
            if (students.retrieve().getMajor().compareToIgnoreCase(major) == 0) {
                data.insert(students.retrieve());
            }
        }
        return data;
    }

    @Override
    public LinkedList<IStudent> findByYearLevel(int yearLevel) {
        LinkedList<IStudent> data = new LinkedList<IStudent>();

        if (!students.empty()) {
            students.findFirst();
            while (!students.last()) {
                if (students.retrieve().getYearLevel() == yearLevel) {
                    data.insert(students.retrieve());
                }
                students.findNext();
            }
            if (students.retrieve().getYearLevel() == yearLevel) {
                data.insert(students.retrieve());
            }
        }
        return data;
    }

    @Override
    public LinkedList<IStudent> getAll() {
        return students;
    }

    @Override
    public boolean deleteById(int studentId) {
        boolean Delete = false;
        if (!students.empty()) {
            students.findFirst();
            while (!students.last() && !Delete) {
                if (studentId == students.retrieve().getStudentId()) {
                    Delete = true;
                    students.remove();
                } else {
                    students.findNext();
                }
            }
            if (!Delete && studentId == students.retrieve().getStudentId()) {
                Delete = true;
                students.remove();
            }
        }//end iff
        return Delete;
    }

    @Override
    public int size() {
        return students.getSize();
    }

}
