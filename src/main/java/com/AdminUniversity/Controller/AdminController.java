package com.AdminUniversity.Controller;

import com.AdminUniversity.DTO.*;
import com.AdminUniversity.repository.Repositories;

import java.util.ArrayList;
import java.util.Scanner;

public class AdminController {

    public void addStudent(Student student) {
        if(!Repositories.getInstance().getStudentRepository().getDB().contains(student)){
            Repositories.getInstance().getStudentRepository().save(student);
        } else {
            System.out.println("This student already is in the database.");
        }
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

    public static void loginAdmin(AbstractUser user) {
        if (user instanceof Admin) {
            Scanner sc = new Scanner(System.in);
            ArrayList<AbstractUser> users = new ArrayList<>();
            Admin admin = (Admin) user;
            System.out.println("ENTER CREDENTIALS!! ");
            System.out.println("Enter the ADMIN username: " );
            String username = sc.next();
            System.out.println("Enter Password: ");
            String password = sc.next();

            users.add(admin);

            boolean loginState= false;

            for (int i = 0; i < users.size(); i++) {
                AbstractUser currentUser = users.get(i);
                if (currentUser.getUser().equals(username) && currentUser.getPassword().equals(password)) {
                    loginState = true;
                    break;
                }
            }

            if (loginState) {
                System.out.println("SUCCESSFUL LOGIN!!");
            } else {
                System.out.println("INVALID CREDENTIALS");
            }
        }
    }

}
