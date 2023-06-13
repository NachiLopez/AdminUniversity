package com.AdminUniversity.DAO;

import com.AdminUniversity.DTO.Course;
import com.AdminUniversity.DTO.Teacher;

public interface InterfaceTeacher {
    public void generateTeacherReport(Teacher teacher, boolean sendEmail);
    public void setCourse(Course course, Teacher teacher);
}
