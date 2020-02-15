package com.example.baity.Model;

public class Forget_password_response {
    String Message;

    public Forget_password_response(String message) {
        Message = message;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
