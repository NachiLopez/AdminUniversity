package com.AdminUniversity.Controller;

import com.AdminUniversity.DAO.InterfaceStudent;
import com.AdminUniversity.DTO.AbstractUser;
import com.AdminUniversity.DTO.Course;
import com.AdminUniversity.DTO.Student;
import com.AdminUniversity.DTO.StudentCourse;
import com.AdminUniversity.repository.Repositories;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class StudentController implements InterfaceStudent {

    @Override
    public void generateStudentReport(Course course, Student student, boolean sendEmail) {
        Document document = new Document();
        StudentCourse studentCourse = null;
        String fileName = ("student_report_idStudent" + student.getId() + ".pdf");
        String destiny = System.getProperty("user.home") + File.separator + "Documents" + File.separator + fileName;

        try {
            for (StudentCourse sc : student.getCoursesSubscribed()) {
                if (sc.getIdStudent() == student.getId() && sc.getIdCourse() == course.getId()) {
                    studentCourse = sc;
                    break;
                }
            }

            PdfWriter.getInstance(document, new FileOutputStream(destiny));
            document.open();

            document.add(new Paragraph("Student Report"));
            document.add(new Paragraph("ID Student: " + student.getId()));
            document.add(new Paragraph("Name: " + student.getFirstName() + " " + student.getLastName()));
            document.add(new Paragraph("User: " + student.getUser()));
            document.add(new Paragraph("Email: " + student.getEmail()));
            document.add(new Paragraph("Address: " + student.getAddress()));
            document.add(new Paragraph("-------------------------------------"));
            document.add(new Paragraph("Course information"));
            document.add(new Paragraph("ID Course: " + course.getId()));
            document.add(new Paragraph("Name course: " + course.getName()));

            // Verificar si hay un profesor asignado al curso
            if (course.getTeacher() != null) {
                document.add(new Paragraph("Teacher of course: " + course.getTeacher().getFirstName() + " " + course.getTeacher().getLastName()));
            } else {
                document.add(new Paragraph("Teacher of course: N/A"));
            }

            // Agregar las notas del estudiante para el curso
            if (studentCourse != null) {
                document.add(new Paragraph("First note: " + studentCourse.getFirstNote()));
                document.add(new Paragraph("Second note: " + studentCourse.getSecondNote()));
                document.add(new Paragraph("Third note: " + studentCourse.getThirdNote()));
                document.add(new Paragraph("Final note: " + studentCourse.getFinalNote()));
            } else {
                document.add(new Paragraph("No grades found for this student in the course."));
            }

            document.close();
            System.out.println("Student report generated successfully.");

            if (sendEmail) {
                EmailController.sendEmail(student.getEmail(), "Student report", new File(destiny));
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Override
    public void suscribeCourse(Course course, Student student) {
        if(!course.getStudents().contains(student)){
            student = Repositories.getInstance().getStudentRepository().getById(student.getId());
            StudentCourse studentCourse = new StudentCourse(student.getId(), course.getId(), course.getName());
            student.addCourse(studentCourse);
            course.addStudent(student);
            Repositories.getInstance().getCourseRepository().save(course);
            Repositories.getInstance().getStudentRepository().save(student);
            course.getStudents().add(student);
        } else {
            System.out.println("This student already is subscribed in this course.");
        }
    }


    @Override
    public void unsuscribeCourse(Course course, Student student) {
        if(course.getStudents().contains(student)){
            student = Repositories.getInstance().getStudentRepository().getById(student.getId());
            student.removeCourse(student.getSpecificCourse(course, student));
            Repositories.getInstance().getCourseRepository().save(course);
            Repositories.getInstance().getStudentRepository().save(student);
            course.getStudents().remove(student);
        } else {
            System.out.println("This student already is not in this course.");
        }
    }



    @Override
    public double average(Student student, Course course) {
        StudentCourse sc = student.getSpecificCourse(course, student);

        return ((sc.getFirstNote() + sc.getSecondNote() + sc.getThirdNote() + sc.getFinalNote()) / 4);
    }



}
