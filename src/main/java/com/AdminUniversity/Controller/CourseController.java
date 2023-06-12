package com.AdminUniversity.Controller;

import com.AdminUniversity.DAO.InterfaceCourse;
import com.AdminUniversity.DTO.Course;
import com.AdminUniversity.DTO.Student;
import com.AdminUniversity.DTO.StudentCourse;
import com.AdminUniversity.DTO.Teacher;
import com.AdminUniversity.repository.Repositories;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;

public class CourseController implements InterfaceCourse {


    @Override
    public void generateCourseReport(Course course) {
        Document document = new Document();
        String fileName = ("course_report_idCourse" + course.getId() + ".pdf");
        String destiny = System.getProperty("user.home") + File.separator + "Documents" + File.separator + fileName;

        try {
            PdfWriter.getInstance(document, new FileOutputStream(destiny));
            document.open();

            document.add(new Paragraph("Course Report"));
            document.add(new Paragraph("ID Course: " + course.getId()));
            document.add(new Paragraph("Name: " + course.getName()));
            document.add(new Paragraph("Teacher: " + course.getTeacher()));
            document.add(new Paragraph("Student list of this course:"));
            for (Student students : course.getStudents()){
                document.add(new Paragraph("-------------------------------------"));
                document.add(new Paragraph("Name: " + students.getFirstName() + " " + students.getLastName()));
                document.add(new Paragraph("User: " + students.getUser()));
                document.add(new Paragraph("Email: "+ students.getEmail()));
            }
            document.add(new Paragraph("-------------------------------------"));

            document.close();
            System.out.println("Course report generated successfully.");
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addStudent(Student student) {

    }

    @Override
    public void removeStudent(Student student) {

    }

    @Override
    public void setTeacher(Teacher teacher) {

    }

    @Override
    public void removeTeacher(Teacher teacher) {

    }
}
