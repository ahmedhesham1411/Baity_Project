package com.example.baity.Model;

import com.google.gson.annotations.SerializedName;

public class AdModel {

    @SerializedName("id")
    int id;

    @SerializedName("imagepath")
    String imagepath;

    @SerializedName("status")
    String status;

    @SerializedName("description")
    String description;

    @SerializedName("company_id")
    int company_id;

    @SerializedName("company")
    String company;

    public AdModel(int id, String imagepath, String status, String description, int company_id, String company) {
        this.id = id;
        this.imagepath = imagepath;
        this.status = status;
        this.description = description;
        this.company_id = company_id;
        this.company = company;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
