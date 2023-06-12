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
        System.out.println("Enter Option: ");
        int option = sc.nextInt();

        switch (option) {
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
                System.out.println("Invalid Option");
                break;
        }
    }


    public static void menu(){
        System.out.println("OPTION MENUS:\n" +
                "        1. Login Admin\n" +
                "        2. Login Teacher\n" +
                "        3. Login Student");
    }



}






