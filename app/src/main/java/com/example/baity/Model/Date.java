package com.example.baity.Model;

public class Date {

    private String readable;

    private Hijri hijri;

    private Gregorian gregorian;

    private String timestamp;

    public String getReadable() {
        return readable;
    }

    public void setReadable(String readable) {
        this.readable = readable;
    }

    public Hijri getHijri() {
        return hijri;
    }

    public void setHijri(Hijri hijri) {
        this.hijri = hijri;
    }

    public Gregorian getGregorian() {
        return gregorian;
    }

    public void setGregorian(Gregorian gregorian) {
        this.gregorian = gregorian;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Date{" +
                "readable='" + readable + '\'' +
                ", hijri=" + hijri +
                ", gregorian=" + gregorian +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
