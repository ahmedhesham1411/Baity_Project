package com.example.baity.Model;

public class Params {
    private String Isha;

    private String Fajr;

    public String getIsha() {
        return Isha;
    }

    public void setIsha(String isha) {
        Isha = isha;
    }

    public String getFajr() {
        return Fajr;
    }

    public void setFajr(String fajr) {
        Fajr = fajr;
    }

    @Override
    public String toString() {
        return "Params{" +
                "Isha='" + Isha + '\'' +
                ", Fajr='" + Fajr + '\'' +
                '}';
    }
}
