package com.AdminUniversity.DTO;


import lombok.ToString;

@ToString
public class Admin extends AbstractUser {

    /*
     * applying the singleton pattern, which restric use a unique object in the class
     * */

    private static Admin instanceAdmin = null;
    static  int cont = 0;
    private Admin(){

    }
    public static synchronized Admin getInstanceAdmin() {
        if (instanceAdmin == null) {
            instanceAdmin = new Admin();
            cont++;
            System.out.println(cont);
        }
        return instanceAdmin;
    }

}
