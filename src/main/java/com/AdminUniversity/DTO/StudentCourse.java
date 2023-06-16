package com.AdminUniversity.DTO;

import lombok.Getter;
import lombok.Setter;

// Create the StudentCourse class for relate a specific course with a specific student

@Getter
@Setter
public class StudentCourse {
    private int idStudent;
    private int idCourse;
    private String nameCourse;
    private float firstNote = 0;
    private float secondNote = 0;
    private float thirdNote = 0;
    private float finalNote = 0;

    public StudentCourse(int idStudent, int idCourse, String nameCourse) {
        this.idStudent = idStudent;
        this.idCourse = idCourse;
        this.nameCourse = nameCourse;
    }
}
