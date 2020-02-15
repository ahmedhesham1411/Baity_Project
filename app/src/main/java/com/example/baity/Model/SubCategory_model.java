package com.example.baity.Model;

import com.google.gson.annotations.SerializedName;

public class SubCategory_model {

    @SerializedName("id")
    Integer id;

    @SerializedName("NameA")
    String NameA;

    @SerializedName("NameE")
    String NameE;

    public SubCategory_model(Integer id, String nameA, String nameE) {
        this.id = id;
        NameA = nameA;
        NameE = nameE;
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
}
