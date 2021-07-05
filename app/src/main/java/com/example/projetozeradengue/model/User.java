package com.example.projetozeradengue.model;

import com.example.projetozeradengue.controller.ICrud;

public class User {
   private String nameUser , email, password, dob ;
   private int id;




    //CONSTRUTOR
    public User(String nameUser, String email, String password, String dob, int id) {
        this.id = id;
        this.nameUser = nameUser;
        this.email = email;
        this.password = password;
        this.dob = dob;
    }

    public User() {

    }

    //GETTERES E SETTERES
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;}

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
