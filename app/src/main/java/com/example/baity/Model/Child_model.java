package com.example.baity.Model;

import com.google.gson.annotations.SerializedName;

public class Child_model {

    @SerializedName("id")
    int id;

    @SerializedName("NameA")
    String NameA;

    @SerializedName("NameE")
    String NameE;

    public Child_model(int id, String nameA, String nameE) {
        this.id = id;
        NameA = nameA;
        NameE = nameE;
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
