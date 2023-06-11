package com.AdminUniversity.Controller;

import com.AdminUniversity.DAO.InterfaceStudent;
import com.AdminUniversity.DTO.AbstractUser;
import com.AdminUniversity.DTO.Course;
import com.AdminUniversity.DTO.Student;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentController implements InterfaceStudent {

    @Override
    public double average() {
        return 0.0;
    }

    @Override
    public void generateStudentReport() {

    }

    @Override
    public void suscribeCourse(Course course) {

    }

    @Override
    public void unsuscribeCourse(Course course) {

    }

    public static void loginStudent(AbstractUser user) {
        if (user instanceof Student) {
            Scanner sc = new Scanner(System.in);
            ArrayList<AbstractUser> users = new ArrayList<>();
            Student student = (Student) user;
            System.out.println("Ingrese las credenciales!! ");
            System.out.println("Ingrese el nombre de  STUDENT: " );
            String username = sc.next();
            System.out.println("Ingrese la contraseña: ");
            String password = sc.next();

            users.add(student);

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
