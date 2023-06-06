package com.AdminUniversity.DTO;


import lombok.ToString;

@ToString
public class Admin extends AbstractUser {

    /*
     * applying the singleton pattern, which restric use a unique object in the class
     * */

    private static Admin instanceAdmin = null;
    static  int cont = 0;
    private Admin(){

    }
    public static synchronized Admin getInstanceAdmin() {
        if (instanceAdmin == null) {
            instanceAdmin = new Admin();
            cont++;
            System.out.println(cont);
        }
        return instanceAdmin;
    }


    public void addStudent(Student student) {

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

    }


    public void addCourse(Course course) {

    }


    public void updateCourse(Course course) {

    }


    public void deleteCourse(Course course) {

    }
}
