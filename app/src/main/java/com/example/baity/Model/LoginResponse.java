package com.example.baity.Model;

public class LoginResponse {
    String token;
    String Message;

    public LoginResponse(String token, String message) {
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
