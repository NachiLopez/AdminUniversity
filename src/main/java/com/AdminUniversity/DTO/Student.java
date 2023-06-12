package com.AdminUniversity.DTO;

import com.AdminUniversity.DAO.InterfaceStudent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class Student extends AbstractUser  {
    private static int nextIdStudent = 1;

    @Deprecated
    private int idStudent;
    private Set<StudentCourse> coursesSubscribed = new HashSet<>();

    public Student(String user, String password, String email, String firstName, String lastName, String address) {
        this.idStudent = nextIdStudent++;
    }

    public StudentCourse getSpecificCourse(Course course, Student student) {
        StudentCourse scReturn = null;
        for (StudentCourse sc : student.getCoursesSubscribed()) {
            if (sc.getIdCourse() == course.getId()) {
                scReturn = sc;
            }
        }
        return scReturn;
    }

}