package com.AdminUniversity.DAO;

import com.AdminUniversity.DTO.Course;
import com.AdminUniversity.DTO.Student;

public interface InterfaceStudent {
    public double average(Student student, Course course);
    public void generateStudentReport(Course course, Student student);
    public void suscribeCourse(Course course, Student student);
    public void unsuscribeCourse(Course course, Student student);
}
