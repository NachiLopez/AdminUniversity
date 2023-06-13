package com.AdminUniversity.Controller;

import com.AdminUniversity.DAO.InterfaceTeacher;
import com.AdminUniversity.DTO.AbstractUser;
import com.AdminUniversity.DTO.Course;
import com.AdminUniversity.DTO.StudentCourse;
import com.AdminUniversity.DTO.Teacher;
import com.AdminUniversity.repository.Repositories;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class TeacherController implements InterfaceTeacher {
    @Override
    public void generateTeacherReport(Teacher teacher, boolean sendEmail) {
        Document document = new Document();
        String fileName = ("teacher_report_idTeacher" + teacher.getId() + ".pdf");
        String destiny = System.getProperty("user.home") + File.separator + "Documents" + File.separator + fileName;
        try {
            PdfWriter.getInstance(document, new FileOutputStream(destiny));
            document.open();

            document.add(new Paragraph("Teacher Report"));
            document.add(new Paragraph("ID Teacher: " + teacher.getId()));
            document.add(new Paragraph("Name: " + teacher.getFirstName() + " " + teacher.getLastName()));
            document.add(new Paragraph("User: " + teacher.getUser()));
            document.add(new Paragraph("Email: " + teacher.getEmail()));
            document.add(new Paragraph("Address: " + teacher.getAddress()));
            document.add(new Paragraph("-------------------------------------"));
            document.add(new Paragraph("Courses information"));

            if (teacher.getCourses() != null) {
                for (Course course : teacher.getCourses()) {
                    document.add(new Paragraph("Name of course: " + course.getName()));
                    document.add(new Paragraph("ID Course: " + course.getId()));
                    document.add(new Paragraph("-------------------------------------"));
                }
            } else {
                document.add(new Paragraph("No courses found for this teacher."));
            }

            document.close();
            System.out.println("Teacher report generated successfully.");

            if (sendEmail) {
                EmailController.sendEmail(AbstractUser.getCurrentUser().getEmail(), "Course report", new File(destiny));
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void setCourse(Course course, Teacher teacher) {
        if(course.getTeacher() == null){
            course.setTeacher(teacher);
        } else {
            System.out.println("The course " + course.getName() + " already has a teacher. First remove the actual teacher and after add a new teacher.");
        }
    }
}
