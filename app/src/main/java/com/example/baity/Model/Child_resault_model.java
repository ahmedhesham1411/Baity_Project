package com.example.baity.Model;

import com.google.gson.annotations.SerializedName;

public class Child_resault_model {

    @SerializedName("id")
    int id;

    @SerializedName("NameA")
    String NameA;

    @SerializedName("NameE")
    String NameE;

    @SerializedName("DescriptionA")
    String DescriptionA;

    @SerializedName("DescriptionE")
    String DescriptionE;

    @SerializedName("videopath")
    String videopath;

    public Child_resault_model(int id, String nameA, String nameE, String descriptionA, String descriptionE,String videopath) {
        this.id = id;
        NameA = nameA;
        NameE = nameE;
        DescriptionA = descriptionA;
        DescriptionE = descriptionE;
        this.videopath = videopath;
    }

    public String getVideopath() {
        return videopath;
    }

    public void setVideopath(String videopath) {
        this.videopath = videopath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getDescriptionA() {
        return DescriptionA;
    }

    public void setDescriptionA(String descriptionA) {
        DescriptionA = descriptionA;
    }

    public String getDescriptionE() {
        return DescriptionE;
    }

    public void setDescriptionE(String descriptionE) {
        DescriptionE = descriptionE;
    }
}
