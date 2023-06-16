package com.AdminUniversity.DTO;

import com.AdminUniversity.DAO.InterfaceCourse;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Course extends Identifiable {

    private String name;
    private Teacher teacher;
    private Set<Student> students;

    public Course() {
        students=new HashSet<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }


    public void removeStudent(Student student) {
        students.remove(student);
    }

}

