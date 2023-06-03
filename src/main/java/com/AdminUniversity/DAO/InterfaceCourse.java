package com.AdminUniversity.DAO;

import com.AdminUniversity.DTO.Student;
import com.AdminUniversity.DTO.Teacher;
public interface InterfaceCourse {
    void generateCourseReport();
    void addStudent(Student student);
    void removeStudent(Student student);
    void setTeacher(Teacher teacher);
    void removeTeacher(Teacher teacher);
}
