package com.example.baity.Model;

import com.google.gson.annotations.SerializedName;

public class About_us_response {

    @SerializedName("id")
    int id;

    @SerializedName("description")
    String description;

    public About_us_response(int id, String description) {
        this.id = id;
        this.description = description;
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
}
