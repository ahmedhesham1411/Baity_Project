package com.example.baity.Model;

import com.google.gson.annotations.SerializedName;

public class Ver_change_pass_request {
    @SerializedName("Email")
    String Email;

    @SerializedName("OldPassword")
    String OldPassword;

    @SerializedName("NewPassword")
    String NewPassword;

    @SerializedName("ConfirmPassword")
    String ConfirmPassword;

    public Ver_change_pass_request(String email, String oldPassword, String newPassword, String confirmPassword) {
        Email = email;
        OldPassword = oldPassword;
        NewPassword = newPassword;
        ConfirmPassword = confirmPassword;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getOldPassword() {
        return OldPassword;
    }

    public void setOldPassword(String oldPassword) {
        OldPassword = oldPassword;
    }

    public String getNewPassword() {
        return NewPassword;
    }

    public void setNewPassword(String newPassword) {
        NewPassword = newPassword;
    }

    public String getConfirmPassword() {
        return ConfirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        ConfirmPassword = confirmPassword;
    }
}
