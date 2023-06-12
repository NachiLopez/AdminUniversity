package com.AdminUniversity.DTO;

import com.AdminUniversity.repository.Repositories;
import lombok.*;


import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@ToString
public abstract class AbstractUser extends Identifiable {
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


    public static void loginSystem() {
        Scanner sc = new Scanner(System.in);

        ArrayList<AbstractUser> users = new ArrayList<>();
        users.addAll(Repositories.getInstance().getAdminRepository().getDB());
        users.addAll(Repositories.getInstance().getStudentRepository().getDB());
        users.addAll(Repositories.getInstance().getTeacherRepository().getDB());

        System.out.println("ENTER CREDENTIALS!! ");
        System.out.println("Enter the username: " );
        String username = sc.next();
        System.out.println("Enter Password: ");
        String password = sc.next();

        AbstractUser userLoggedIn=null;
        for (AbstractUser currentUser : users) {
            if (currentUser.getUser().equals(username) && currentUser.getPassword().equals(password)) {
                userLoggedIn=currentUser;
                break;
            }
        }

        if (userLoggedIn!=null) {
            System.out.println("SUCCESSFUL LOGIN!!");


            switch (userLoggedIn) {
                case Admin admin -> {
                    // todo: admin menu
                }

                case Teacher teacher -> {
                    // todo: teacher menu
                }

                case Student student -> {
                    // todo: student menu
                }
                default -> throw new IllegalStateException("Unexpected value: " + userLoggedIn);
            }
        } else {
            System.out.println("INVALID CREDENTIALS");
        }
    }


}






