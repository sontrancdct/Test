package com.example.test.model;

public class NguoiDung {

    private int id;
    private String name;
    private String Phone;

    public NguoiDung() {
    }

    public NguoiDung(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        Phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}
