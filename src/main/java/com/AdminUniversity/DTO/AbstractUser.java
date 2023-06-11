package com.AdminUniversity.DTO;

import lombok.*;


import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

import static com.AdminUniversity.Controller.AdminController.loginAdmin;
import static com.AdminUniversity.Controller.TeacherController.loginTeacher;
import static com.AdminUniversity.Controller.StudentController.loginStudent;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public abstract class AbstractUser {

    private String user;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String address;
    private Set<Course> courses;


    public AbstractUser(String user, String password, String email, String firstName, String lastName, String address) {
        this.user = user;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }


    public static void loginSystem(AbstractUser user) {
        Scanner sc = new Scanner(System.in);
        menu();
        System.out.println("Ingrese la opción: ");
        int opcion = sc.nextInt();

        switch (opcion) {
            case 1:
                loginAdmin(user);
                break;

            case 2:
                loginTeacher(user);
                break;
            case 3:
                loginStudent(user);
                break;
            default:
                System.out.println("Opción inválida");
                break;
        }
    }


    public static void menu(){
        System.out.println("MENU DE OPCIONES:\n" +
                "        1. Inicio de sesión Admin\n" +
                "        2. Inicio de sesión Teacher\n" +
                "        3. Inicio de sesion Student");
    }



}






