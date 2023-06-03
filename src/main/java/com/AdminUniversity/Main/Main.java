package com.AdminUniversity.Main;


import com.AdminUniversity.DTO.Admin;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(Admin.getInstanceAdmin().toString());
            //Prueba
        }
    }
}
