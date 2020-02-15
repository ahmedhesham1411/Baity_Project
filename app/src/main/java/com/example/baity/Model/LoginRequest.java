package com.example.baity.Model;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {
    @SerializedName("UserMail")
    String UserMail;

    @SerializedName("Password")
    String Password;

    public LoginRequest(String userMail, String password) {
        UserMail = userMail;
        Password = password;
    }

    public String getEmail() {
        return UserMail;
    }

    public void setEmail(String email) {
        UserMail = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
