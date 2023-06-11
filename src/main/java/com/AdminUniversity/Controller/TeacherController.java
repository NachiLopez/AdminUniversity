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
            System.out.println("Ingrese las credenciales!! ");
            System.out.println("Ingrese el nombre de TEACHER: " );
            String username = sc.next();
            System.out.println("Ingrese la contraseña: ");
            String password = sc.next();

            users.add(teacher);

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
