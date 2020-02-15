package com.example.baity.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Search_model {

    @SerializedName("id")
    int id;

    @SerializedName("NameA")
    String NameA;

    @SerializedName("NameE")
    String NameE;

    @SerializedName("imageVMs")
    List<Search_image_model> search_image_models;

    @SerializedName("find")
    String find;

    public Search_model(int id, String nameA, String nameE, String find,List<Search_image_model> search_image_models) {
        this.id = id;
        NameA = nameA;
        NameE = nameE;
        this.find = find;
        this.search_image_models = search_image_models;
    }

    public List<Search_image_model> getSearch_image_models() {
        return search_image_models;
    }

    public void setSearch_image_models(List<Search_image_model> search_image_models) {
        this.search_image_models = search_image_models;
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

    public String getFind() {
        return find;
    }

    public void setFind(String find) {
        this.find = find;
    }
}
