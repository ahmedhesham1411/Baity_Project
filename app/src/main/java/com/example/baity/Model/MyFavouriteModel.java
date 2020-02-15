package com.example.baity.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyFavouriteModel {

    @SerializedName("id")
    int id;

    @SerializedName("NameA")
    String NameA;

    @SerializedName("NameE")
    String NameE;

    @SerializedName("adsVMs")
    List<Favourite_Slider_model> adsVMs;

    public MyFavouriteModel(int id, String nameA, String nameE, List<Favourite_Slider_model> adsVMs) {
        this.id = id;
        NameA = nameA;
        NameE = nameE;
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

    public List<Favourite_Slider_model> getAdsVMs() {
        return adsVMs;
    }

    public void setAdsVMs(List<Favourite_Slider_model> adsVMs) {
        this.adsVMs = adsVMs;
    }
}
