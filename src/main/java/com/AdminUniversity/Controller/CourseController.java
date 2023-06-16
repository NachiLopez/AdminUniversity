package com.AdminUniversity.Controller;

import com.AdminUniversity.DAO.InterfaceCourse;
import com.AdminUniversity.DTO.*;
import com.AdminUniversity.repository.Repositories;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;

public class CourseController implements InterfaceCourse {
    @Override
    public void generateCourseReport(Course course, boolean sendEmail) {
        Document document = new Document();
        String fileName = ("course_report_idCourse" + course.getId() + ".pdf");
        String destiny = System.getProperty("user.home") + File.separator + "Documents" + File.separator + fileName;

        try {
            PdfWriter.getInstance(document, new FileOutputStream(destiny));
            document.open();

            document.add(new Paragraph("Course Report"));
            document.add(new Paragraph("ID Course: " + course.getId()));
            document.add(new Paragraph("Name: " + course.getName()));
            document.add(new Paragraph("Teacher: " + course.getTeacher().getFirstName() + " " + course.getTeacher().getLastName()));
            document.add(new Paragraph("-------------------------------------"));
            if (course.getStudents() != null){
                document.add(new Paragraph("Student list of this course:"));
                for (Student students : course.getStudents()){
                    document.add(new Paragraph("Name: " + students.getFirstName() + " " + students.getLastName()));
                    document.add(new Paragraph("User: " + students.getUser()));
                    document.add(new Paragraph("Email: "+ students.getEmail()));
                    document.add(new Paragraph("-------------------------------------"));
                }
            } else{
                document.add(new Paragraph("This course doesn't have any student."));
            }

            document.close();
            System.out.println("Course report generated successfully.");
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (sendEmail) {
            EmailController.sendEmail(AbstractUser.getCurrentUser().getEmail(), "Course report", new File(destiny));
        }
    }

    @Override
    public void addStudent(Student student) {
        if(!Repositories.getInstance().getStudentRepository().getDB().contains(student)){
            Repositories.getInstance().getStudentRepository().save(student);
        } else {
            System.out.println("This student already is in this course");
        }
    }

    @Override
    public void removeStudent(Student student) {
        if(Repositories.getInstance().getStudentRepository().getDB().contains(student)){
            Repositories.getInstance().getStudentRepository().delete(student);
        } else {
            System.out.println("This student already is not in this course");
        }
    }

    @Override
    public void setTeacher(Teacher teacher, Course course) {
        if(course.getTeacher() == null){
            course.setTeacher(teacher);
        } else {
            System.out.println("This course already has a teacher. First remove the actual teacher and after assign the new teacher");
        }
    }

    @Override
    public void removeTeacher(Course course) {
        if(course.getTeacher() != null){
            course.setTeacher(null);
        } else {
            System.out.println("This course has not a teacher. Add a teacher.");
        }
    }
}
