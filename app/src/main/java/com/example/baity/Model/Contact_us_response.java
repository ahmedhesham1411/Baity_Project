package com.example.baity.Model;

import com.google.gson.annotations.SerializedName;

public class Contact_us_response {

    @SerializedName("id")
    int id;

    @SerializedName("phonenumber")
    String phonenumber;

    @SerializedName("website")
    String website;

    @SerializedName("facebookUrl")
    String facebookUrl;

    @SerializedName("twitterUrl")
    String twitterUrl;

    @SerializedName("WahtsupNumber")
    String WahtsupNumber;

    public Contact_us_response(int id, String phonenumber, String website, String facebookUrl, String twitterUrl, String wahtsupNumber) {
        this.id = id;
        this.phonenumber = phonenumber;
        this.website = website;
        this.facebookUrl = facebookUrl;
        this.twitterUrl = twitterUrl;
        WahtsupNumber = wahtsupNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getFacebookUrl() {
        return facebookUrl;
    }

    public void setFacebookUrl(String facebookUrl) {
        this.facebookUrl = facebookUrl;
    }

    public String getTwitterUrl() {
        return twitterUrl;
    }

    public void setTwitterUrl(String twitterUrl) {
        this.twitterUrl = twitterUrl;
    }

    public String getWahtsupNumber() {
        return WahtsupNumber;
    }

    public void setWahtsupNumber(String wahtsupNumber) {
        WahtsupNumber = wahtsupNumber;
    }
}
