package com.AdminUniversity.DAO;

import com.AdminUniversity.DTO.Course;

public interface InterfaceStudent {
    int average();
    void generateStudentReport();
    void suscribeCourse(Course course);
    void unsuscribeCourse(Course course);
}
