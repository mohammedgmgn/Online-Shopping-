package com.mahmoud.mohammed.onlineshopping.models;

/**
 * Created by mohammed on 28/11/2017.
 */

public class User {
    private String email, name, birthdate;

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public static User createNewUser(String email, String name, String birthdate) {
        User user = new User();
        user.setBirthdate(email);
        user.setBirthdate(birthdate);
        user.setName(name);
        return user;
    }
}
