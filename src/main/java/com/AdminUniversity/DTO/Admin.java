package com.AdminUniversity.DTO;


import com.AdminUniversity.DAO.InterfaceAdmin;
import lombok.ToString;

@ToString
public class Admin implements InterfaceAdmin {

    /*
     * Aplicando el patron singleton, el cual me restringe la creacion a unica instancia de la clase
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

    @Override
    public void addStudent(Student student) {

    }

    @Override
    public void updateStudent(Student student) {

    }

    @Override
    public void deleteStudent(Student student) {

    }

    @Override
    public void addTeacher(Teacher teacher) {

    }

    @Override
    public void updateTeacher(Teacher teacher) {

    }

    @Override
    public void deleteTeacher(Teacher teacher) {

    }

    @Override
    public void addCourse(Course course) {

    }

    @Override
    public void updateCourse(Course course) {

    }

    @Override
    public void deleteCourse(Course course) {

    }
}
