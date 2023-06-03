package com.AdminUniversity.DTO;

import com.AdminUniversity.DAO.InterfaceStudent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Student extends AbstractUser implements InterfaceStudent {
    private static int nextIdStudent = 1;
    private int idStudent;

    public Student(String email, String firstName, String lastName, String adress) {
        super(email, firstName, lastName, adress);
        this.idStudent = nextIdStudent++;
    }

    @Override
    public int average() {
        return 0;
    }

    @Override
    public void generateStudentReport() {

    }

    @Override
    public void suscribeCourse(Course course) {

    }

    @Override
    public void unsuscribeCourse(Course course) {

    }
}