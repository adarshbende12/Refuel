package com.example.refuel;

public class Users {
    String name;
    String u_city;
    String pin;
    String u_mobile;
    String u_email;
    String u_age;
    String user_name;
    String u_pass;

    public Users(String name, String u_city, String pin, String u_mobile, String u_email, String u_age, String user_name, String u_pass) {
        this.name = name;
        this.u_city = u_city;
        this.pin = pin;
        this.u_mobile = u_mobile;
        this.u_email = u_email;
        this.u_age = u_age;
        this.user_name = user_name;
        this.u_pass = u_pass;
    }

    public String getName() {
        return name;
    }

    public String getU_city() {
        return u_city;
    }

    public String getPin() {
        return pin;
    }

    public String getU_mobile() {
        return u_mobile;
    }

    public String getU_email() {
        return u_email;
    }

    public String getU_age() {
        return u_age;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getU_pass() {
        return u_pass;
    }
}
