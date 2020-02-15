package com.example.baity.Model;

import com.google.gson.annotations.SerializedName;

public class Favourite_Slider_model {

    @SerializedName("imagepath")
    String imagepath;

    public Favourite_Slider_model(String imagepath) {
        this.imagepath = imagepath;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }
}
