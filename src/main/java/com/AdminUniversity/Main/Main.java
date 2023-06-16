package com.AdminUniversity.Main;


import com.AdminUniversity.Controller.AdminController;
import com.AdminUniversity.Controller.CourseController;
import com.AdminUniversity.Controller.StudentController;
import com.AdminUniversity.Controller.TeacherController;
import com.AdminUniversity.DTO.*;
import com.AdminUniversity.repository.Repositories;

import java.util.ArrayList;

import static com.AdminUniversity.DTO.AbstractUser.loginSystem;

public class Main {
    public static void main(String[] args) {



        AdminController adminController = new AdminController();
        Student student = new Student("student", "123", "kodigoteam4@gmail.com", "Lautaro", "Acazuzo", "Calle Falsa 123");
        adminController.addStudent(student);
        Teacher teacher = new Teacher("teacher", "456", "kodigoteam4@gmail.com", "Joaquin", "Mamba", "Calle Falsa 333");
        adminController.addTeacher(teacher);
        Admin admin = Admin.getInstanceAdmin("admin", "admin", "kodigoteam4@gmail.com", "", "", "");
        Repositories.getInstance().getAdminRepository().save(admin);

        Course course = Repositories.getInstance().getCourseRepository().save(new Course("Curso 1"));
        new CourseController().setTeacher(teacher, course);
        new StudentController().suscribeCourse(course, student);

        AbstractUser.loginSystem(adminController);
    }
}
