package com.example.baity.Model;

import com.google.gson.annotations.SerializedName;

public class Rate_response {
    @SerializedName("Message")
    String message;

    public Rate_response(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
