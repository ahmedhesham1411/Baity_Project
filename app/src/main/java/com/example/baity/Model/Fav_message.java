package com.example.baity.Model;

import com.google.gson.annotations.SerializedName;

public class Fav_message {
    @SerializedName("Message")
    String Message;

    public Fav_message(String message) {
        Message = message;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
