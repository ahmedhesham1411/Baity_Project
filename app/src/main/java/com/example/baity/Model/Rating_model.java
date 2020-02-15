package com.example.baity.Model;

import com.google.gson.annotations.SerializedName;

public class Rating_model {

    @SerializedName("company_id")
    int company_id;

    @SerializedName("count")
    double count;

    public Rating_model(int company_id, double count) {
        this.company_id = company_id;
        this.count = count;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }
}
