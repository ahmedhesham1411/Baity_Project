package com.example.baity.Model;

import com.google.gson.annotations.SerializedName;

public class change_password_response {
    @SerializedName("Code")
    String Code;

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }
}
