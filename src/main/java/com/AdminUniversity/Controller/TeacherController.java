package com.AdminUniversity.Controller;

import com.AdminUniversity.DAO.InterfaceTeacher;
import com.AdminUniversity.DTO.AbstractUser;
import com.AdminUniversity.DTO.Course;
import com.AdminUniversity.DTO.Teacher;

import java.util.ArrayList;
import java.util.Scanner;

public class TeacherController implements InterfaceTeacher {
    @Override
    public void generateTeacherReport() {

    }

    @Override
    public void setCourse(Course course) {

    }

    public static void loginTeacher(AbstractUser user) {
        if (user instanceof Teacher) {
            Scanner sc = new Scanner(System.in);
            ArrayList<AbstractUser> users = new ArrayList<>();
            Teacher teacher = (Teacher) user;
            System.out.println("ENTER CREDENTIALS!! ");
            System.out.println("Enter the TEACHER username: " );
            String username = sc.next();
            System.out.println("Enter Password: ");
            String password = sc.next();

            users.add(teacher);

            boolean loginState = false;

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
