package com.example.baity.Model;


import com.google.gson.annotations.SerializedName;

public class RegistrationRequest {
    @SerializedName("FirstName")
    String FirstName;

    @SerializedName("LastName")
    String LastName;

    @SerializedName("Phone")
    String Phone;

    @SerializedName("Email")
    String Email;

    @SerializedName("Password")
    String Password;

    @SerializedName("ConfirmPassword")
    String ConfirmPassword;

    @SerializedName("image")
    String image;

    public RegistrationRequest(String firstName, String lastName, String phone, String email, String password, String confirmPassword, String image) {
        FirstName = firstName;
        LastName = lastName;
        Phone = phone;
        Email = email;
        Password = password;
        ConfirmPassword = confirmPassword;
        this.image = image;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getConfirmPassword() {
        return ConfirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        ConfirmPassword = confirmPassword;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
