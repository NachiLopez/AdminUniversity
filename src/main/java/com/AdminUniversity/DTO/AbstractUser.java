package com.AdminUniversity.DTO;

import com.AdminUniversity.Controller.AdminController;
import com.AdminUniversity.Controller.TeacherController;
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


    public static void loginSystem(AdminController adminController) {
        Scanner sc = new Scanner(System.in);

        ArrayList<AbstractUser> users = new ArrayList<>();
        users.addAll(Repositories.getInstance().getAdminRepository().getDB());
        users.addAll(Repositories.getInstance().getStudentRepository().getDB());
        users.addAll(Repositories.getInstance().getTeacherRepository().getDB());
        TeacherController teacherController = new TeacherController();
        adminController.setTeacherController(teacherController);


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
                    boolean login =true;
                    while (login){
                        menuAdmin();
                        System.out.println("ENTER THE OPTION: ");
                        int option = sc.nextInt();

                        switch (option) {
                            case 1:
                                System.out.println("1. Create a Teacher");
                                System.out.println("Enter teacher details:");
                                System.out.print("Username: ");
                                String teacherUsername = sc.next();
                                System.out.print("Password: ");
                                String teacherPassword = sc.next();
                                System.out.print("Email: ");
                                String teacherEmail = sc.next();
                                System.out.print("First Name: ");
                                String teacherFirstName = sc.next();
                                System.out.print("Last Name: ");
                                String teacherLastName = sc.next();
                                System.out.print("Address: ");
                                String teacherAddress = sc.next();
                                Teacher teacher = new Teacher(teacherUsername, teacherPassword, teacherEmail, teacherFirstName, teacherLastName, teacherAddress);
                                adminController.addTeacher(teacher);
                                break;
                            case 2:
                                ArrayList<Teacher> teachers = adminController.getAllTeachers();
                                System.out.println("2. Query a Teacher");
                                if (teachers.isEmpty()) {
                                    System.out.println("Sorry, this list don´t have any teachers!!");
                                } else {
                                    for (int i = 0; i < teachers.size(); i++) {
                                        System.out.println(teachers.get(i));
                                    }
                                }
                                break;
                            case 3:
                                ArrayList<Teacher> teacherss = adminController.getAllTeachers();
                                System.out.println("3. Generate a Report of the Teachers");

                                if (teacherss.isEmpty()) {
                                    System.out.println("Sorry, this list doesn't have any teachers!!");
                                } else {
                                    for (Teacher tch : teacherss) {
                                        teacherController.generateTeacherReport(tch);
                                    }
                                    System.out.println("Teacher reports generated successfully.");
                                }

                                break;
                            case 0:
                                System.out.println("Thanks for use our system!!");
                                login = false;
                                break;

                            default:
                                System.out.println("INVALID OPTION!!");


                         }
                        }

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

    public static void menuAdmin(){
        System.out.println(

                "OPTIONS MENU:\n" +
                        "        ===============TEACHER===============\n" +
                        "        1. Create a Teacher\n" +
                        "        2. Query a Teacher\n" +
                        "        3. Generate a Report of the Teachers\n" +
                        "        4. Update a Teacher\n" +
                        "        5. Delete a Teacher\n" +

                        "        ===============STUDENT===============\n" +
                        "        6. Create a Student\n" +
                        "        7. Query a Student\n" +
                        "        8. Generate a Report of the Students\n" +
                        "        9. Update a Student\n" +
                        "        10. Delete a Student\n" +

                        "        ===============COURSE===============\n" +
                        "        11. Assign a course to a teacher and students\n" +
                        "        12. Add University grades \n" +
                        "        13. Query University grades \n" +
                        "        14. Update University grades \n" +
                        "        15. Generate a Report of the University grades \n" +

                        "        0. Salir\n" +
                        "        ==============================");
    }


}






