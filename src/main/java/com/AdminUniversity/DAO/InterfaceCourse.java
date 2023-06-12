package com.AdminUniversity.DAO;

import com.AdminUniversity.DTO.Student;
import com.AdminUniversity.DTO.Teacher;
public interface InterfaceCourse {
    public void generateCourseReport();
    public void addStudent(Student student);
    public void removeStudent(Student student);
    public void setTeacher(Teacher teacher);
    public void removeTeacher(Teacher teacher);
}
