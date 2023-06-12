package com.AdminUniversity.DAO;

import com.AdminUniversity.DTO.Course;
import com.AdminUniversity.DTO.Teacher;

public interface InterfaceTeacher {
    public void generateTeacherReport(Teacher teacher);
    public void setCourse(Course course);
}
