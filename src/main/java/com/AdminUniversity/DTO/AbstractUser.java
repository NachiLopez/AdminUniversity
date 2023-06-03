package com.AdminUniversity.DTO;

import lombok.*;


import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public abstract class AbstractUser {
    private String email;
    private String firstName;
    private String lastName;
    private String adress;
    private Set<Course> courses;

    public AbstractUser(String email, String firstName, String lastName, String adress) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.adress = adress;
    }
}

