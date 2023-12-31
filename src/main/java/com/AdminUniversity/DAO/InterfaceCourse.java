package com.AdminUniversity.DAO;

import com.AdminUniversity.DTO.Course;
import com.AdminUniversity.DTO.Student;
import com.AdminUniversity.DTO.Teacher;
import com.AdminUniversity.repository.CourseRepository;

public interface InterfaceCourse {
    public void generateCourseReport(Course course, boolean sendEmail);
    public void addStudent(Student student, Course course);
    public void removeStudent(Student student, Course course);
    public void setTeacher(Teacher teacher, Course course);
    public void removeTeacher(Course course);
}
