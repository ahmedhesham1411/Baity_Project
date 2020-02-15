package com.example.baity.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Sub_slider_model {

    @SerializedName("id")
    int id;

    @SerializedName("imagepath")
    String imagepath;

    @SerializedName("description")
    String description;

    @SerializedName("company_id")
    int company_id;

    public Sub_slider_model(int id, String  imagepath, String description, int company_id) {
        this.id = id;
        this.imagepath = imagepath;
        this.description = description;
        this.company_id = company_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }
}
