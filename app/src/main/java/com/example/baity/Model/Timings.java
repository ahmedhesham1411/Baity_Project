package com.example.baity.Model;

public class Timings {

    private String Sunset;

    private String Asr;

    private String Isha;

    private String Fajr;

    private String Dhuhr;

    private String Maghrib;

    private String Sunrise;

    private String Midnight;

    private String Imsak;

    public String getSunset() {
        return Sunset;
    }

    public void setSunset(String sunset) {
        Sunset = sunset;
    }

    public String getAsr() {
        return Asr;
    }

    public void setAsr(String asr) {
        Asr = asr;
    }

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

    public String getDhuhr() {
        return Dhuhr;
    }

    public void setDhuhr(String dhuhr) {
        Dhuhr = dhuhr;
    }

    public String getMaghrib() {
        return Maghrib;
    }

    public void setMaghrib(String maghrib) {
        Maghrib = maghrib;
    }

    public String getSunrise() {
        return Sunrise;
    }

    public void setSunrise(String sunrise) {
        Sunrise = sunrise;
    }

    public String getMidnight() {
        return Midnight;
    }

    public void setMidnight(String midnight) {
        Midnight = midnight;
    }

    public String getImsak() {
        return Imsak;
    }

    public void setImsak(String imsak) {
        Imsak = imsak;
    }

    @Override
    public String toString() {
        return "Timings{" +
                "Sunset='" + Sunset + '\'' +
                ", Asr='" + Asr + '\'' +
                ", Isha='" + Isha + '\'' +
                ", Fajr='" + Fajr + '\'' +
                ", Dhuhr='" + Dhuhr + '\'' +
                ", Maghrib='" + Maghrib + '\'' +
                ", Sunrise='" + Sunrise + '\'' +
                ", Midnight='" + Midnight + '\'' +
                ", Imsak='" + Imsak + '\'' +
                '}';
    }
}
