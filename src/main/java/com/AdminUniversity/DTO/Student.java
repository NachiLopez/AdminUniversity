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

    public Student(String user, String password, String email, String firstName, String lastName, String address) {
        super(user, password, email, firstName, lastName, address);
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