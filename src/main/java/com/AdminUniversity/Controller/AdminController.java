package com.AdminUniversity.Controller;

import com.AdminUniversity.DTO.*;
import com.AdminUniversity.repository.Repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminController {
    private TeacherController teacherController;

    public void setTeacherController(TeacherController teacherController) {
        this.teacherController = teacherController;
    }

    public void addStudent(Student student) {
        if(!Repositories.getInstance().getStudentRepository().getDB().contains(student)){
            Repositories.getInstance().getStudentRepository().save(student);
        } else {
            System.out.println("This student already is in the database.");
        }
        Repositories.getInstance().getStudentRepository().save(student);

    }



    public void updateStudent(Student student) {
        Student existingStudent = Repositories.getInstance().getStudentRepository().findById(student.getId());

        if (existingStudent != null) {
            existingStudent.setFirstName(student.getFirstName());
            existingStudent.setLastName(student.getLastName());
            existingStudent.setEmail(student.getEmail());
            existingStudent.setAddress(student.getAddress());
            existingStudent.setCourses(student.getCourses());

            // Guardar los cambios en el repositorio
            Repositories.getInstance().getStudentRepository().save(existingStudent);

            System.out.println("Student updated successfully.");
        } else {
            System.out.println("Student not found with ID: " + student.getId());
        }
    }



    public void deleteStudent(Student student) {

        Repositories.getInstance().getStudentRepository().delete(student);
    }


    public void addTeacher(Teacher teacher) {
        if(!Repositories.getInstance().getTeacherRepository().getDB().contains(teacher)){
            Repositories.getInstance().getTeacherRepository().save(teacher);
        } else {
            System.out.println("This teacher already is in the database.");
        }
        Repositories.getInstance().getTeacherRepository().save(teacher);
    }


    public void updateTeacher(Teacher teacher) {
        Teacher existingTeacher = Repositories.getInstance().getTeacherRepository().findById(teacher.getId());

        if (existingTeacher != null) {
            existingTeacher.setFirstName(teacher.getFirstName());
            existingTeacher.setLastName(teacher.getLastName());
            existingTeacher.setEmail(teacher.getEmail());
            existingTeacher.setAddress(teacher.getAddress());
            existingTeacher.setCourses(teacher.getCourses());

            // Guardar los cambios en el repositorio
            Repositories.getInstance().getTeacherRepository().save(existingTeacher);

            System.out.println("Teacher updated successfully.");
        } else {
            System.out.println("Teacher not found with ID: " + teacher.getId());
        }
    }
    public Teacher getTeacherById(int teacherId) {

        ArrayList<Teacher> teachers = getAllTeachers();


        for (Teacher teacher : teachers) {
            if (teacher.getId() == teacherId) {
                return teacher;
            }
        }

        return null;
    }

    public Course getCourseById(int courseId) {
        ArrayList<Course> courses = getAllCourses();

        for (Course course : courses) {
            if (course.getId() == courseId) {
                return course;
            }
        }
        return null;
    }

    public Student getStudentById(int studentId) {

        ArrayList<Student> students = getAllStudents();


        for (Student st : students) {
            if (st.getId() == studentId) {
                return st;
            }
        }

        return null;
    }


    public void deleteTeacher(Teacher teacher) {
        if (Repositories.getInstance().getTeacherRepository().getDB().contains(teacher)) {
            Repositories.getInstance().getTeacherRepository().delete(teacher);
        } else {
            System.out.println("This teacher already is not in the database");
        }
    }

    public void addCourse(Course course) {

    }

    public void updateCourse(Course course) {

    }


    public void deleteCourse(Course course) {

    }
    public ArrayList<Teacher> getAllTeachers() {
        List<Teacher> teacherList = Repositories.getInstance().getTeacherRepository().getDB();
        return new ArrayList<>(teacherList);
    }

    public ArrayList<Student> getAllStudents() {
        List<Student> studentList = Repositories.getInstance().getStudentRepository().getDB();
        return new ArrayList<>(studentList);
    }

    public ArrayList<Course> getAllCourses() {
        List<Course> courseList = Repositories.getInstance().getCourseRepository().getDB();
        return new ArrayList<>(courseList);
    }
}
