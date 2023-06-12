package com.AdminUniversity.DTO;

import com.AdminUniversity.DAO.InterfaceStudent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Student extends AbstractUser  {
    private static int nextIdStudent = 1;

    @Deprecated
    private int idStudent;

    public Student(String user, String password, String email, String firstName, String lastName, String address) {
        this.idStudent = nextIdStudent++;
    }

}