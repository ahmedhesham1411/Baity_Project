package com.example.baity.Model;

import com.google.gson.annotations.SerializedName;

public class Facebook_request {

    @SerializedName("Username")
    String Username;

    @SerializedName("email")
    String email;

    public Facebook_request(String username, String email) {
        Username = username;
        this.email = email;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
