package com.AdminUniversity.repository;

import lombok.Getter;

@Getter
public class Repositories {
    private static Repositories instance;
    private final AdminRepository adminRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    public Repositories() {
        adminRepository = new AdminRepository();
        courseRepository = new CourseRepository();
        studentRepository = new StudentRepository();
        teacherRepository = new TeacherRepository();
    }

    public static Repositories getInstance() {
        if (instance==null) instance=new Repositories();
        return instance;
    }
}
