package com.griffith.assignment1.contactManager;

/**
 * Created by 42900 on 28/02/2017 for Assignment1.
 */

public class Contact {
    private int id;
    private String name;
    private String home_phone;
    private String mobile_phone;
    private String email;

    public Contact(String name) {
        this.name = name;
    }

    public Contact(int id, String name, String home_phone, String mobile_phone, String email) {
        this.id = id;
        this.name = name;
        this.home_phone = home_phone;
        this.mobile_phone = mobile_phone;
        this.email = email;
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

    public String getHome_phone() {
        return home_phone;
    }

    public void setHome_phone(String home_phone) {
        this.home_phone = home_phone;
    }

    public String getMobile_phone() {
        return mobile_phone;
    }

    public void setMobile_phone(String mobile_phone) {
        this.mobile_phone = mobile_phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", home_phone='" + home_phone + '\'' +
                ", mobile_phone='" + mobile_phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
