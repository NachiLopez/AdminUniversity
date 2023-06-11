package com.AdminUniversity.Main;


import com.AdminUniversity.DTO.AbstractUser;
import com.AdminUniversity.DTO.Admin;
import com.AdminUniversity.DTO.Student;
import com.AdminUniversity.DTO.Teacher;

import java.util.ArrayList;

import static com.AdminUniversity.DTO.AbstractUser.loginSystem;

public class Main {
    public static void main(String[] args) {
        //Aquí se puede agregar un switch Case el cual evalue el tipo de usuario
        Student student = new Student("Duvan","123","duvan@gmail.com","Carlos","Labrador","Conjunto la macarena");
        Teacher teacher = new Teacher("Messi","456","messi@gmail.com","Leo","Messi","Miami");
        Admin admin = Admin.getInstanceAdmin("Admin","admin","admin@gmail.com","Admin","Super","N/A");


        //loginSystem(student);
        //loginSystem(teacher);
        admin. loginSystem(admin);

    }
}
