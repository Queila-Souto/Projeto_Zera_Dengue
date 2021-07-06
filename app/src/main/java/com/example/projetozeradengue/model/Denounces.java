package com.example.projetozeradengue.model;

import androidx.annotation.Nullable;

public class Denounces {
    private String a_Street;
    private String a_number;
    private String a_district;
    private String a_complement;
    private String a_city;
    private String a_coord;
    private String note;
    private String a_state;
    private int id, a_cep;
    private Integer userId;

  //CONSTRUTOR
    public Denounces(@Nullable Integer userId, int cep, String street, String number, @Nullable String complement, String district, String city, String state,String note ) {
        this.userId = userId;
        this.a_cep = cep;
        this.a_Street = street;
        this.a_number = number;
        this.a_complement = complement;
        this.a_district = district;
        this.a_city = city;
        this.a_state = state;
        this.note = note;
    }

    public Denounces() {

    }


    //GETTERES E SETTERES
    public String getA_state() {
        return a_state;
    }
    public void setA_state(String a_state) {
        this.a_state = a_state;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCep() {
        return a_cep;
    }

    public void setCep(int cep) {
        this.a_cep = cep;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(@Nullable Integer userId) {
        this.userId = userId;
    }

    public String getA_Street() {
        return a_Street;
    }

    public void setA_Street(String a_Street) {
        this.a_Street = a_Street;
    }

    public String getA_number() {
        return a_number;
    }

    public void setA_number(String a_number) {
        this.a_number = a_number;
    }

    public String getA_district() {
        return a_district;
    }

    public void setA_district(String a_district) {
        this.a_district = a_district;
    }

    public String getA_complement() {
        return a_complement;
    }

    public void setA_complement(String a_complement) {
        this.a_complement = a_complement;
    }

    public String getA_city() {
        return a_city;
    }

    public void setA_city(String a_city) {
        this.a_city = a_city;
    }

    public String getA_coord() {
        return a_coord;
    }

    public void setA_coord(String a_coord) {
        this.a_coord = a_coord;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
