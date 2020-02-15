package com.example.baity.Model;

import com.google.gson.annotations.SerializedName;

public class Facebook_response {
    String token;
    String Message;

    public Facebook_response(String token, String message) {
        this.token = token;
        this.Message = message;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "token='" + token + '\'' +
                '}';
    }


}
