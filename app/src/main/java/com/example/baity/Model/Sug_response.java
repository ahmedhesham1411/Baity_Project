package com.example.baity.Model;

import com.google.gson.annotations.SerializedName;

public class Sug_response {

    @SerializedName("SUCCESS")
    String SUCCESS;

    public String getSUCCESS() {
        return SUCCESS;
    }

    public void setSUCCESS(String SUCCESS) {
        this.SUCCESS = SUCCESS;
    }
}
