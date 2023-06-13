package com.AdminUniversity.Main;


import com.AdminUniversity.Controller.AdminController;
import com.AdminUniversity.Controller.TeacherController;
import com.AdminUniversity.DTO.AbstractUser;
import com.AdminUniversity.DTO.Admin;
import com.AdminUniversity.DTO.Student;
import com.AdminUniversity.DTO.Teacher;
import com.AdminUniversity.repository.Repositories;

import java.util.ArrayList;

import static com.AdminUniversity.DTO.AbstractUser.loginSystem;

public class Main {
    public static void main(String[] args) {
        AdminController adminController = new AdminController();
        Student student = new Student("student", "123", "", "", "", "");
        new AdminController().addStudent(student);
        Teacher teacher = new Teacher("teacher", "456", "", "", "", "");
        new AdminController().addTeacher(teacher);
        Admin admin = Admin.getInstanceAdmin("admin", "admin", "jm.arango03@gmail.com", "", "", "");
        Repositories.getInstance().getAdminRepository().save(admin);
        AbstractUser.loginSystem(adminController);
    }
}
