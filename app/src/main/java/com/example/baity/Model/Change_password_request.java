package com.example.baity.Model;

import com.google.gson.annotations.SerializedName;

public class Change_password_request {
    @SerializedName("OldPassword")
    String OldPassword;

    @SerializedName("NewPassword")
    String NewPassword;

    @SerializedName("ConfirmPassword")
    String ConfirmPassword;

    public Change_password_request(String oldPassword, String newPassword, String confirmPassword) {
        OldPassword = oldPassword;
        NewPassword = newPassword;
        ConfirmPassword = confirmPassword;
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
