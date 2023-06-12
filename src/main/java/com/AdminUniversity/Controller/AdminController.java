package com.AdminUniversity.Controller;

import com.AdminUniversity.DTO.*;
import com.AdminUniversity.repository.Repositories;

import java.util.ArrayList;
import java.util.Scanner;

public class AdminController {

    public void addStudent(Student student) {
        Repositories.getInstance().getStudentRepository().save(student);
    }


    public void updateStudent(Student student) {

    }


    public void deleteStudent(Student student) {
        Repositories.getInstance().getStudentRepository().delete(student);
    }


    public void addTeacher(Teacher teacher) {

    }


    public void updateTeacher(Teacher teacher) {

    }


    public void deleteTeacher(Teacher teacher) {

    }


    public void addCourse(Course course) {

    }


    public void updateCourse(Course course) {

    }


    public void deleteCourse(Course course) {

    }



}
