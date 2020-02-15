package com.example.baity.Model;

public class RegisterationResponse {
    String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "RegisterationResponse{" +
                "token='" + token + '\'' +
                '}';
    }
}
