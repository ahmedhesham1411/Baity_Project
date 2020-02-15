package com.example.baity.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Review_full_model {

    @SerializedName("id")
    int id;

    @SerializedName("description")
    String description;

    @SerializedName("Imagepath")
    String Imagepath;

    @SerializedName("userreviewVMs")
    List<Review_user_item> userreviewVMs;

    public Review_full_model(int id, String description, String imagepath, List<Review_user_item> userreviewVMs) {
        this.id = id;
        this.description = description;
        Imagepath = imagepath;
        this.userreviewVMs = userreviewVMs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagepath() {
        return Imagepath;
    }

    public void setImagepath(String imagepath) {
        Imagepath = imagepath;
    }

    public List<Review_user_item> getUserreviewVMs() {
        return userreviewVMs;
    }

    public void setUserreviewVMs(List<Review_user_item> userreviewVMs) {
        this.userreviewVMs = userreviewVMs;
    }
}
