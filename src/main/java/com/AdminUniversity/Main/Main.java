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

        Student student = new Student("test", "test", "", "", "", "");
        new AdminController().addStudent(student);


        AbstractUser.loginSystem();
    }
}
