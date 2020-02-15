package com.example.baity.Model;

import com.google.gson.annotations.SerializedName;

public class Forget_password_request {
    @SerializedName("email")
    String email;

    public Forget_password_request(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
