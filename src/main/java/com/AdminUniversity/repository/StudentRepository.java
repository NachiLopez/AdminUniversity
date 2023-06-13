package com.AdminUniversity.repository;

import com.AdminUniversity.DTO.Student;
import com.AdminUniversity.DTO.Teacher;

import java.util.List;

public class StudentRepository extends AbstractRepository<Student> {
    public Student findById(int id) {
        List<Student> students = getDB();
        for (Student st : students) {
            if (st.getId() == id) {
                return st;
            }
        }
        return null;
    }
}
