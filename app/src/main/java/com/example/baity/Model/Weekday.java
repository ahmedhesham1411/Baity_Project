package com.example.baity.Model;

public class Weekday {
    private String ar;

    private String en;

    public String getAr() {
        return ar;
    }

    public void setAr(String ar) {
        this.ar = ar;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    @Override
    public String toString() {
        return "Weekday{" +
                "ar='" + ar + '\'' +
                ", en='" + en + '\'' +
                '}';
    }
}
