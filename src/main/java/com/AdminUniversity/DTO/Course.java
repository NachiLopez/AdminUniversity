package com.AdminUniversity.DTO;

import com.AdminUniversity.DAO.InterfaceCourse;
import lombok.*;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Course extends Identifiable implements InterfaceCourse {

    private static int nextId = 1;
    private String name;
    private Teacher teacher;
    private Set<Student> students;

    public Course() {
        this.id = nextId++;
    }


}

