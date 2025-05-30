package com.example.demo.view.model;

public class UserViewModel {
    private String username;
    private String email;
    private String registrationDate;

    public UserViewModel() {
    }

    public UserViewModel(String username, String email, String registrationDate) {
        this.username = username;
        this.email = email;
        this.registrationDate = registrationDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }
}