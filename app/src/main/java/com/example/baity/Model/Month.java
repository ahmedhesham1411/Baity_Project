package com.example.baity.Model;

public class Month {
    private String number;

    private String ar;

    private String en;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

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
        return "Month{" +
                "number='" + number + '\'' +
                ", ar='" + ar + '\'' +
                ", en='" + en + '\'' +
                '}';
    }
}
