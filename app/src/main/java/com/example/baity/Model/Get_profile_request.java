package com.example.baity.Model;

public class Get_profile_request {
    String token;

    public Get_profile_request(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
/*        return "RegisterationResponse{" +
                "token='" + token + '\'' +
                '}';*/
        return token;
    }
}
