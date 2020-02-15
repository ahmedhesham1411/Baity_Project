package com.example.baity.Model;

import com.google.gson.annotations.SerializedName;

public class EditProfile_request {
    @SerializedName("image")
    String image;

    @SerializedName("Phone")
    String Phone;

    @SerializedName("username")
    String username;

    @SerializedName("Email")
    String Email;

    public EditProfile_request(String image, String phone, String username, String email) {
        this.image = image;
        Phone = phone;
        this.username = username;
        Email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
