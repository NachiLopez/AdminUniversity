package com.AdminUniversity.DAO;

import com.AdminUniversity.DTO.Course;

public interface InterfaceStudent {
    public double average();
    public void generateStudentReport();
    public void suscribeCourse(Course course);
    public void unsuscribeCourse(Course course);
}
