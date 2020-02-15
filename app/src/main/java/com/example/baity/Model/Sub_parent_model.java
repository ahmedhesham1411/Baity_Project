package com.example.baity.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Sub_parent_model {

    @SerializedName("id")
    int id;

    @SerializedName("NameA")
    String NameA;

    @SerializedName("NameE")
    String NameE;

    @SerializedName("adsVMs")
    List<Sub_slider_model> adsVMs;

    public Sub_parent_model(int id, String nameA, String nameE,List<Sub_slider_model> adsVMs) {
        this.id = id;
        NameA = nameA;
        NameE = nameE;
        this.adsVMs = adsVMs;
    }

    public List<Sub_slider_model> getSub_slider_models() {
        return adsVMs;
    }

    public void setSub_slider_models(List<Sub_slider_model> adsVMs) {
        this.adsVMs = adsVMs;
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
}
