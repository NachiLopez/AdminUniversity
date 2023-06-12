package com.AdminUniversity.DTO;


import com.AdminUniversity.repository.Repositories;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Scanner;

@ToString
public class Admin extends AbstractUser {

    /*
     * applying the singleton pattern, which restric use a unique object in the class
     * */

    private static Admin instanceAdmin = null;

    private Admin(String user, String password, String email, String firstName, String lastName, String address ){
        super(user, password, email, firstName, lastName, address);
    }
    public static synchronized Admin getInstanceAdmin(String user, String password, String email, String firstName, String lastName, String address) {
        if (instanceAdmin == null) {
            instanceAdmin = new Admin(user, password, email, firstName, lastName, address);
        }
        return instanceAdmin;
    }
}
