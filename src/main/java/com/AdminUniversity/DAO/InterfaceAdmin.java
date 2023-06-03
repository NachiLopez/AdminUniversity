package com.AdminUniversity.DAO;

import com.AdminUniversity.DTO.Course;
import com.AdminUniversity.DTO.Student;
import com.AdminUniversity.DTO.Teacher;

public interface InterfaceAdmin {
    void addStudent(Student student);
    void updateStudent(Student student);
    void deleteStudent(Student student);
    void addTeacher(Teacher teacher);
    void updateTeacher(Teacher teacher);
    void deleteTeacher(Teacher teacher);
    void addCourse(Course course);
    void updateCourse(Course course);
    void deleteCourse(Course course);
}
