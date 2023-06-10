package com.AdminUniversity.DTO;

import lombok.*;


import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public abstract class AbstractUser {
    private Integer id;
    private String user;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String address;
    private Set<Course> courses;
}

