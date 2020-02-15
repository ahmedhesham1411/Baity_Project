package com.example.baity.Model;

import com.google.gson.annotations.SerializedName;

public class Home_model {

    @SerializedName("id")
    Integer id;

    @SerializedName("NameA")
    String NameA;

    @SerializedName("NameE")
    String NameE;

    @SerializedName("imagepath")
    String imagepath;

    public Home_model(Integer id, String nameA, String nameE, String imagepath) {
        this.id = id;
        NameA = nameA;
        NameE = nameE;
        this.imagepath = imagepath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameA() {
        return NameA;
    }

    public void setNameA(String nameA) {
        NameA = nameA;
    }

    public String getNameE() {
        return NameE;
    }

    public void setNameE(String nameE) {
        NameE = nameE;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }
}

