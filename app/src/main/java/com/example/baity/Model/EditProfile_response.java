package com.example.baity.Model;

public class EditProfile_response {
    String Message;

    public EditProfile_response( String message) {
        this.Message = message;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

}
