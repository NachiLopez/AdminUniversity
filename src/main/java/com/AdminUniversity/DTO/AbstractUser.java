package com.AdminUniversity.DTO;

import com.AdminUniversity.Controller.AdminController;
import com.AdminUniversity.Controller.StudentController;
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

    @Getter
    @Setter
    private static AbstractUser currentUser;

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
        StudentController studentController = new StudentController();
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
            currentUser=userLoggedIn;
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
                                    System.out.println("Send report to your email? yes/no");
                                    boolean sendEmail = sc.next().equalsIgnoreCase("yes");
                                    for (Teacher tch : teacherss) {
                                        teacherController.generateTeacherReport(tch, sendEmail);
                                    }
                                    System.out.println("Teacher reports generated successfully.");
                                }

                                break;
                            case 4:

                                System.out.println("4. Update a Teacher");

                                // Solicitar al usuario el ID del profesor a actualizar
                                System.out.println("Enter the ID of the teacher to update:");
                                int teacherIdToUpdate = sc.nextInt();
                                sc.nextLine(); // Limpiar el buffer de entrada

                                // Buscar el profesor por su ID
                                Teacher teacherToUpdate = adminController.getTeacherById(teacherIdToUpdate);


                                if (teacherToUpdate == null) {
                                    System.out.println("Teacher not found with ID: " + teacherIdToUpdate);
                                } else {
                                    // Solicitar al usuario los nuevos datos del profesor
                                    System.out.println("Enter the new first name:");
                                    String newFirstName = sc.nextLine();

                                    System.out.println("Enter the new last name:");
                                    String newLastName = sc.nextLine();

                                    System.out.println("Enter the new email:");
                                    String newEmail = sc.nextLine();

                                    System.out.println("Enter the new address:");
                                    String newAddress = sc.nextLine();

                                    // Actualizar los datos del profesor
                                    teacherToUpdate.setFirstName(newFirstName);
                                    teacherToUpdate.setLastName(newLastName);
                                    teacherToUpdate.setEmail(newEmail);
                                    teacherToUpdate.setAddress(newAddress);

                                    // Guardar los cambios en el repositorio
                                    adminController.updateTeacher(teacherToUpdate);

                                    System.out.println("Teacher updated successfully.");
                                }

                                break;
                            case 5:
                                System.out.println("Delete a Teacher");


                                System.out.println("Enter the ID of the teacher to delete:");
                                int teacherIdToDelete = sc.nextInt();
                                sc.nextLine(); // Limpiar el buffer de entrada


                                Teacher teacherToDelete = adminController.getTeacherById(teacherIdToDelete);

                                if (teacherToDelete == null) {
                                    System.out.println("Teacher not found with ID: " + teacherIdToDelete);
                                } else {

                                    adminController.deleteTeacher(teacherToDelete);
                                    System.out.println("Teacher deleted successfully.");
                                }

                                break;
                            case 6:
                                System.out.println("6. Create a Student");
                                System.out.println("Enter teacher details:");
                                System.out.print("Username: ");
                                String studentUsername = sc.next();
                                System.out.print("Password: ");
                                String studentPassword = sc.next();
                                System.out.print("Email: ");
                                String studentEmail = sc.next();
                                System.out.print("First Name: ");
                                String studentFirstName = sc.next();
                                System.out.print("Last Name: ");
                                String studentLastName = sc.next();
                                System.out.print("Address: ");
                                String studentAddress = sc.next();
                                Student student = new Student(studentUsername, studentPassword, studentEmail, studentFirstName, studentLastName, studentAddress);
                                adminController.addStudent(student);
                                break;
                            case 7:
                                ArrayList<Student> students = adminController.getAllStudents();
                                System.out.println("7. Query a Student");
                                if (students.isEmpty()) {
                                    System.out.println("Sorry, this list don´t have any students!!");
                                } else {
                                    for (int i = 0; i < students.size(); i++) {
                                        System.out.println(students.get(i));
                                    }
                                }
                                break;
                            case 8:
                                ArrayList<Student> studentss = adminController.getAllStudents();
                                System.out.println("8. Generate a Report of the Students");

                                if (studentss.isEmpty()) {
                                    System.out.println("Sorry, this list doesn't have any Students!!");
                                } else {
                                    System.out.println("Send report to your email? yes/no");
                                    boolean sendEmail = sc.next().equalsIgnoreCase("yes");
                                    for (Student st : studentss) {
                                        studentController.generateStudentReport(new Course(),st, sendEmail);
                                    }
                                    System.out.println("Students reports generated successfully.");
                                }

                                break;
                            case 9:
                                System.out.println("9. Update a Student");

                                System.out.println("Enter the ID of the teacher to update:");
                                int studentIdToUpdate = sc.nextInt();
                                sc.nextLine(); // Limpiar el buffer de entrada


                                Student studentToUpdate = adminController.getStudentById(studentIdToUpdate);


                                if (studentToUpdate == null) {
                                    System.out.println("Teacher not found with ID: " + studentIdToUpdate);
                                } else {

                                    System.out.println("Enter the new first name:");
                                    String newFirstName = sc.nextLine();

                                    System.out.println("Enter the new last name:");
                                    String newLastName = sc.nextLine();

                                    System.out.println("Enter the new email:");
                                    String newEmail = sc.nextLine();

                                    System.out.println("Enter the new address:");
                                    String newAddress = sc.nextLine();

                                    // Actualizar los datos del profesor
                                    studentToUpdate.setFirstName(newFirstName);
                                    studentToUpdate.setLastName(newLastName);
                                    studentToUpdate.setEmail(newEmail);
                                    studentToUpdate.setAddress(newAddress);

                                    // Guardar los cambios en el repositorio
                                    adminController.updateStudent(studentToUpdate);

                                    System.out.println("Teacher updated successfully.");
                                }
                                break;
                            case 10:
                                System.out.println("10. Delete a Student");


                                System.out.println("Enter the ID of the student to delete:");
                                int studentIdToDelete = sc.nextInt();
                                sc.nextLine(); // Limpiar el buffer de entrada


                                Student studentToDelete = adminController.getStudentById(studentIdToDelete);

                                if (studentToDelete  == null) {
                                    System.out.println("Student not found with ID: " + studentIdToDelete);
                                } else {

                                    adminController.deleteStudent(studentToDelete);
                                    System.out.println("Sudent deleted successfully.");
                                }

                                break;
                            case 11:
                                System.out.println("11. Assign a course to a teacher and students");
                                break;
                            case 12:
                                System.out.println("12. Add University grades");
                                break;
                            case 13:
                                System.out.println(" 13. Query University grades");
                                break;
                            case 14:
                                System.out.println("14. Update University grades");
                                break;
                            case 15:
                                System.out.println("15. Generate a Report of the University grades ");
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






