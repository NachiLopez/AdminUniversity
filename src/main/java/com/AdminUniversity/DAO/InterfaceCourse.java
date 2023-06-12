package com.AdminUniversity.DAO;

import com.AdminUniversity.DTO.Course;
import com.AdminUniversity.DTO.Student;
import com.AdminUniversity.DTO.Teacher;
import com.AdminUniversity.repository.CourseRepository;

public interface InterfaceCourse {
    public void generateCourseReport(Course course);
    public void addStudent(Student student);
    public void removeStudent(Student student);
    public void setTeacher(Teacher teacher);
    public void removeTeacher(Teacher teacher);
}
