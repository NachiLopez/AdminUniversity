package com.AdminUniversity.DTO;

import com.AdminUniversity.DAO.InterfaceTeacher;

public class Teacher extends AbstractUser  {
    private static int nextIdTeacher = 1;

    @Deprecated
    private int idTeacher;

    public Teacher(String user, String password, String email, String firstName, String lastName, String address) {
        this.idTeacher = nextIdTeacher++;
    }

}
