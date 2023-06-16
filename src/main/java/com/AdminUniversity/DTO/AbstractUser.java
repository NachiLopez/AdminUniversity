package com.AdminUniversity.DTO;

import com.AdminUniversity.Controller.AdminController;
import com.AdminUniversity.Controller.CourseController;
import com.AdminUniversity.Controller.StudentController;
import com.AdminUniversity.Controller.TeacherController;
import com.AdminUniversity.repository.CourseRepository;
import com.AdminUniversity.repository.Repositories;
import lombok.*;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


@Getter
@Setter
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
    private Set<Course> courses = new HashSet<>();


    public AbstractUser(String user, String password, String email, String firstName, String lastName, String address) {
        this.user = user;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    public void addCourse(Course course) {
        courses.add(course);
    }


    public static void loginSystem(AdminController adminController) {
        Scanner sc = new Scanner(System.in);

        ArrayList<AbstractUser> users = new ArrayList<>();
        users.addAll(Repositories.getInstance().getAdminRepository().getDB());
        users.addAll(Repositories.getInstance().getStudentRepository().getDB());
        users.addAll(Repositories.getInstance().getTeacherRepository().getDB());
        TeacherController teacherController = new TeacherController();
        StudentController studentController = new StudentController();
        CourseController courseController = new CourseController();
        adminController.setTeacherController(teacherController);


        System.out.println("ENTER CREDENTIALS!! ");
        System.out.println("Enter the username: " );
        String username = sc.nextLine();
        System.out.println("Enter Password: ");
        String password = sc.nextLine();

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
                        sc.nextLine();

                        switch (option) {
                            case 1:
                                System.out.println("1. Create a Teacher");
                                System.out.println("Enter teacher details:");
                                System.out.print("Username: ");
                                String teacherUsername = sc.nextLine();
                                System.out.print("Password: ");
                                String teacherPassword = sc.nextLine();
                                System.out.print("Email: ");
                                String teacherEmail = sc.nextLine();
                                System.out.print("First Name: ");
                                String teacherFirstName = sc.nextLine();
                                System.out.print("Last Name: ");
                                String teacherLastName = sc.nextLine();
                                System.out.print("Address: ");
                                String teacherAddress = sc.nextLine();
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
                                    boolean sendEmail = sc.nextLine().equalsIgnoreCase("yes");
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
                                String studentUsername = sc.nextLine();
                                System.out.print("Password: ");
                                String studentPassword = sc.nextLine();
                                System.out.print("Email: ");
                                String studentEmail = sc.nextLine();
                                System.out.print("First Name: ");
                                String studentFirstName = sc.nextLine();
                                System.out.print("Last Name: ");
                                String studentLastName = sc.nextLine();
                                System.out.print("Address: ");
                                String studentAddress = sc.nextLine();
                                Student student = new Student(studentUsername, studentPassword, studentEmail, studentFirstName, studentLastName, studentAddress);
                                adminController.addStudent(student);
                                break;
                            case 7:
                                ArrayList<Student> students = adminController.getAllStudents();
                                System.out.println("7. Query a Student");
                                if (students.isEmpty()) {
                                    System.out.println("Sorry, this list don´t have any students!!");
                                } else {
                                    for (Student stud : Repositories.getInstance().getStudentRepository().getDB()) {
                                        System.out.println(
                                                "-----------------------" +
                                                "ID: " + stud.getId() +
                                                "Name: " + stud.getFirstName() + " " + stud.getLastName() +
                                                "User: " + stud.getUser() +
                                                "Email: " + stud.getEmail() +
                                                "Address: " + stud.getAddress() +
                                                "Courses subscribed: "
                                        );
                                        for(StudentCourse courses : stud.getCoursesSubscribed()){
                                            System.out.println("Course ID and Name:" + courses.getIdCourse() + " " + courses.getNameCourse());
                                        }
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
                                    boolean sendEmail = sc.nextLine().equalsIgnoreCase("yes");
                                    for (Student st : studentss) {
                                        for (StudentCourse studentCourse : st.getCoursesSubscribed()) {
                                            Course course = Repositories.getInstance().getCourseRepository().getById(studentCourse.getIdCourse());
                                            studentController.generateStudentReport(course,st, sendEmail);
                                        }
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
                                    System.out.println("Student not found with ID: " + studentIdToUpdate);
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
                                System.out.println("11. Assign a teacher to a course");
                                int teacherToAssignId = 0, courseToAssignId = 0;
                                Course courseToAssign = null;
                                Teacher teacherToAssign = null;

                                if(!Repositories.getInstance().getCourseRepository().getDB().isEmpty()){
                                    boolean courseValid = false;
                                    while(!courseValid) {
                                        System.out.println("Enter the ID of the course:");
                                        courseToAssignId = sc.nextInt();
                                        sc.nextLine();
                                        courseToAssign = adminController.getCourseById(courseToAssignId);
                                        if (courseToAssign == null) {
                                            System.out.println("Invalid ID");
                                        } else {
                                            courseValid = true;
                                        }
                                    }
                                } else {
                                    System.out.println("ERROR: There isn't any course in the database");
                                    break;
                                }
                                if(!Repositories.getInstance().getTeacherRepository().getDB().isEmpty()){
                                    boolean teacherValid = false;
                                    while(!teacherValid) {
                                        System.out.println("Enter the ID of the teacher:");
                                        teacherToAssignId = sc.nextInt();
                                        sc.nextLine();
                                        teacherToAssign = adminController.getTeacherById(teacherToAssignId);
                                        if (teacherToAssign == null) {
                                            System.out.println("Invalid ID");
                                        } else {
                                            teacherValid = true;
                                        }
                                    }
                                }else {
                                    System.out.println("ERROR: There isn't any teacher in the database");
                                    break;
                                }

                                courseToAssign.setTeacher(teacherToAssign);

                                System.out.println("The teacher with ID " + teacherToAssign.getId() + " was assigned to the course with ID " + courseToAssign.getId() + ".");
                                break;
                            case 12:
                                System.out.println("12. Add University course");
                                Course courseCreated = new Course("");

                                System.out.println("Enter the name of the course: ");
                                String courseName = sc.nextLine();
                                courseCreated.setName(courseName);

                                System.out.println("You want set a teacher in this course? yes/no");
                                String response = sc.nextLine();

                                if(response.equalsIgnoreCase("yes")){
                                    if (!Repositories.getInstance().getTeacherRepository().getDB().isEmpty()){
                                        System.out.println("Ok, there are the teachers in the database");
                                        for (Teacher allTeachers : Repositories.getInstance().getTeacherRepository().getDB()){
                                            System.out.println("Teacher ID and Name: " + allTeachers.getId() + ", " + allTeachers.getFirstName() + " " + allTeachers.getLastName());
                                        }
                                        boolean teacherValid = false;
                                        while(!teacherValid) {
                                            System.out.println("\n Please, enter the ID of the teacher:");
                                            teacherToAssignId = sc.nextInt();
                                            sc.nextLine();
                                            teacherToAssign = adminController.getTeacherById(teacherToAssignId);
                                            if (teacherToAssign == null) {
                                                System.out.println("Invalid ID");
                                            } else {
                                                teacherValid = true;
                                                courseCreated.setTeacher(teacherToAssign);
                                            }
                                        }
                                    } else{
                                        System.out.println("ERROR: There aren't any teacher in the database.");
                                        break;
                                    }
                                } else if (response.equalsIgnoreCase("no")){
                                    System.out.println("When you need it, you can assign a teacher to the course\n");
                                } else {
                                    System.out.println("Invalid option, after assign a teacher.");
                                }

                                System.out.println("Okay, the course was created and the ID is " + courseCreated.getId());
                                Repositories.getInstance().getCourseRepository().save(courseCreated);
                                adminController.addCourse(courseCreated);
                                break;
                            case 13:
                                System.out.println(" 13. Generate a report of a University course");
                                Course courseToReport = null;
                                boolean sendMail = false;
                                if (!Repositories.getInstance().getCourseRepository().getDB().isEmpty()){
                                    System.out.println("Select the course you want to generate a report.");
                                    for (Course courseToUpdate : Repositories.getInstance().getCourseRepository().getDB()){
                                        System.out.println("Course ID and Name: " + courseToUpdate.getId() + ", " + courseToUpdate.getName());
                                    }
                                    boolean courseValid = false;
                                    while(!courseValid) {
                                        System.out.println("\n Please, enter the course ID:");
                                        courseToAssignId = sc.nextInt();
                                        sc.nextLine();
                                        courseToReport = adminController.getCourseById(courseToAssignId);
                                        if (courseToReport == null) {
                                            System.out.println("Invalid ID");
                                        } else {
                                            courseValid = true;
                                            System.out.println("Do you want to receive a mail with the report? yes/no");
                                            sendMail = sc.nextLine().equalsIgnoreCase("yes");
                                        }
                                    }
                                    courseController.generateCourseReport(courseToReport, sendMail);
                                }else {
                                    System.out.println("There aren't any course in the database.");
                                }

                                break;
                            case 14:
                                System.out.println("14. Update University course");

                                if (!Repositories.getInstance().getCourseRepository().getDB().isEmpty()){
                                    System.out.println("Select the course you want to update.");
                                    for (Course courseToUpdate : Repositories.getInstance().getCourseRepository().getDB()){
                                        System.out.println("Course ID and Name: " + courseToUpdate.getId() + ", " + courseToUpdate.getName());
                                    }
                                    boolean courseValid = false;
                                    while(!courseValid) {
                                        System.out.println("\n Please, enter the course ID:");
                                        courseToAssignId = sc.nextInt();
                                        sc.nextLine();
                                        courseToAssign = adminController.getCourseById(courseToAssignId);
                                        if (courseToAssign == null) {
                                            System.out.println("Invalid ID");
                                        } else {
                                            courseValid = true;
                                            System.out.println("Enter the new name course: ");
                                            courseName = sc.nextLine();
                                            courseToAssign.setName(courseName);

                                            System.out.println("Do you want change the teacher? yes/no");
                                            response = sc.nextLine();
                                            if(response.equalsIgnoreCase("yes")){
                                                if (!Repositories.getInstance().getTeacherRepository().getDB().isEmpty()){
                                                    System.out.println("Ok, there are the teachers in the database");
                                                    for (Teacher allTeachers : Repositories.getInstance().getTeacherRepository().getDB()){
                                                        System.out.println("Teacher ID and Name: " + allTeachers.getId() + ", " + allTeachers.getFirstName() + " " + allTeachers.getLastName());
                                                    }
                                                    boolean teacherValid = false;
                                                    while(!teacherValid) {
                                                        System.out.println("\n Please, enter the ID of the teacher:");
                                                        teacherToAssignId = sc.nextInt();
                                                        sc.nextLine();
                                                        teacherToAssign = adminController.getTeacherById(teacherToAssignId);
                                                        if (teacherToAssign == null) {
                                                            System.out.println("Invalid ID");
                                                        } else {
                                                            teacherValid = true;
                                                            courseToAssign.setTeacher(teacherToAssign);
                                                        }
                                                    }
                                                } else{
                                                    System.out.println("ERROR: There aren't any teacher in the database.");
                                                }
                                            } else if(response.equalsIgnoreCase("no")) {
                                                System.out.println("When you need it, you can set a teacher in this course.");
                                            } else {
                                                System.out.println("Invalid option, after assign a teacher.");
                                            }
                                        }
                                    }

                                    System.out.println("Course updated.");
                                } else {
                                    System.out.println("There aren't any course in the database.");
                                }
                                break;
                            case 15:
                                System.out.println("15. Generate a Report of the University courses.");

                                if (!Repositories.getInstance().getCourseRepository().getDB().isEmpty()){
                                    System.out.println("Do you want to receive a mail with the report? yes/no");
                                    sendMail = sc.nextLine().equalsIgnoreCase("yes");
                                    for (Course coursesForReport : Repositories.getInstance().getCourseRepository().getDB()){
                                        courseController.generateCourseReport(coursesForReport, sendMail);
                                    }
                                    System.out.println("All courses report are generated.");
                                } else {
                                    System.out.println("There aren't any course in the database.");
                                }

                                break;
                            case 16:
                                System.out.println("16. Assign a student to course.");

                                if(!Repositories.getInstance().getCourseRepository().getDB().isEmpty()){
                                    int studentToAssignId = 0;
                                    courseToAssign = null;
                                    Student studentToAssign = null;
                                    boolean courseValid = false;
                                    while(!courseValid) {
                                        System.out.println("Enter the ID of the course:\n");
                                        courseToAssignId = sc.nextInt();
                                        sc.nextLine();
                                        courseToAssign = adminController.getCourseById(courseToAssignId);
                                        if (courseToAssign == null) {
                                            System.out.println("Invalid ID");
                                        } else {
                                            courseValid = true;
                                        }
                                    }
                                    boolean studentValid = false;
                                    while(!studentValid) {
                                        System.out.println("\n Please, enter the ID of the student:");
                                        studentToAssignId = sc.nextInt();
                                        sc.nextLine();
                                        studentToAssign = adminController.getStudentById(studentToAssignId);
                                        if (studentToAssign == null) {
                                            System.out.println("Invalid ID");
                                        } else {
                                            studentValid = true;
                                        }
                                    }
                                    studentController.suscribeCourse(courseToAssign, studentToAssign);
                                    System.out.println("The student with ID " + studentToAssign.getId() + " was subscribed to course with ID " + courseToAssign.getId());
                                } else{
                                    System.out.println("ERROR: There aren't any course in the database.");
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
                        "        11. Assign a course to a teacher\n" +
                        "        12. Add University course \n" +
                        "        13. Generate a Report of a University course \n" +
                        "        14. Update University course \n" +
                        "        15. Generate a Report of all University courses \n" +
                        "        16. Assign a student to course\n" +

                        "        0. Salir\n" +
                        "        ==============================");
    }


}






