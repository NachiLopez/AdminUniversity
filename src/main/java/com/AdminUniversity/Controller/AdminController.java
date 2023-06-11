package com.AdminUniversity.Controller;

import com.AdminUniversity.DTO.*;

import java.util.ArrayList;
import java.util.Scanner;

public class AdminController {

    public void addStudent(Student student) {

    }


    public void updateStudent(Student student) {

    }


    public void deleteStudent(Student student) {

    }


    public void addTeacher(Teacher teacher) {

    }


    public void updateTeacher(Teacher teacher) {

    }


    public void deleteTeacher(Teacher teacher) {

    }


    public void addCourse(Course course) {

    }


    public void updateCourse(Course course) {

    }


    public void deleteCourse(Course course) {

    }

    public static void loginAdmin(AbstractUser user) {
        if (user instanceof Admin) {
            Scanner sc = new Scanner(System.in);
            ArrayList<AbstractUser> users = new ArrayList<>();
            Admin admin = (Admin) user;
            System.out.println("Ingrese las credenciales!! ");
            System.out.println("Ingrese el nombre de usuario ADMIN: " );
            String username = sc.next();
            System.out.println("Ingrese la contraseña: ");
            String password = sc.next();

            users.add(admin);

            boolean estadoLogin = false;

            for (int i = 0; i < users.size(); i++) {
                AbstractUser currentUser = users.get(i);
                if (currentUser.getUser().equals(username) && currentUser.getPassword().equals(password)) {
                    estadoLogin = true;
                    break;
                }
            }

            if (estadoLogin) {
                System.out.println("Inicio de sesión exitoso");
            } else {
                System.out.println("Credenciales inválidas");
            }
        }
    }

}
