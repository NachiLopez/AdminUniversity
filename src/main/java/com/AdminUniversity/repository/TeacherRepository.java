package com.AdminUniversity.repository;

import com.AdminUniversity.DTO.Teacher;
import java.util.ArrayList;
import java.util.List;

public class TeacherRepository extends AbstractRepository<Teacher> {
    public Teacher findById(int id) {
        List<Teacher> teachers = getDB();
        for (Teacher teacher : teachers) {
            if (teacher.getId() == id) {
                return teacher;
            }
        }
        return null;
    }
}
