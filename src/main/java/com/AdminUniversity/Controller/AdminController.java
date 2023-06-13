package com.AdminUniversity.Controller;

import com.AdminUniversity.DTO.*;
import com.AdminUniversity.repository.Repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminController {

    public void addStudent(Student student) {
        if(!Repositories.getInstance().getStudentRepository().getDB().contains(student)){
            Repositories.getInstance().getStudentRepository().save(student);
        } else {
            System.out.println("This student already is in the database.");
        }
        Repositories.getInstance().getStudentRepository().save(student);
    }



    public void updateStudent(Student student) {

    }


    public void deleteStudent(Student student) {
        Repositories.getInstance().getStudentRepository().delete(student);
    }


    public void addTeacher(Teacher teacher) {
        if(!Repositories.getInstance().getTeacherRepository().getDB().contains(teacher)){
            Repositories.getInstance().getTeacherRepository().save(teacher);
        } else {
            System.out.println("This teacher already is in the database.");
        }
        Repositories.getInstance().getTeacherRepository().save(teacher);
    }


    public void updateTeacher(Teacher teacher) {

    }


    public void deleteTeacher(Teacher teacher) {
        if (Repositories.getInstance().getTeacherRepository().getDB().contains(teacher)) {
            Repositories.getInstance().getTeacherRepository().delete(teacher);
        } else {
            System.out.println("This teacher already is not in the database");
        }
    }

    public void addCourse(Course course) {

    }

    public void updateCourse(Course course) {

    }


    public void deleteCourse(Course course) {

    }
    public ArrayList<Teacher> getAllTeachers() {
        List<Teacher> teacherList = Repositories.getInstance().getTeacherRepository().getDB();
        return new ArrayList<>(teacherList);
    }




}
