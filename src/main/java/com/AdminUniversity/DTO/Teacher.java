package com.AdminUniversity.DTO;

import com.AdminUniversity.DAO.InterfaceTeacher;

public class Teacher extends AbstractUser implements InterfaceTeacher {
    private static int nextIdTeacher = 1;
    private int idTeacher;

    public Teacher(String email, String firstName, String lastName, String adress) {
        super(email, firstName, lastName, adress);
        this.idTeacher = nextIdTeacher++;
    }

    @Override
    public void generateTeacherReport() {

    }

    @Override
    public void setCourse(Course course) {

    }
}
