package com.example.projetozeradengue.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class User {
   private String nameUser , email, password ;
   private String id;
   private Date dob;




    //CONSTRUTOR
    public User(String nameUser, String email, String password, Date dob, String id) {
        this.id = id;
        this.nameUser = nameUser;
        this.email = email;
        this.password = password;
        this.dob = dob;
    }

    public User() {

    }

    //GETTERES E SETTERES
    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    //database
    public void save(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("users").child(getId()).setValue(this);

    }
}
