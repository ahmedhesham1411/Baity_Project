package com.example.baity.Model;

import com.google.gson.annotations.SerializedName;

public class Review_user_item {
    @SerializedName("image")
    String image;

    @SerializedName("name")
    String name;

    @SerializedName("count")
    double rate;

    public Review_user_item(String image, String name, double rate) {
        this.image = image;
        this.name = name;
        this.rate = rate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
