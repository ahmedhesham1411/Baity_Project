package com.example.baity.Model;

public class Gregorian {

    private String date;

    private Month month;

    private String year;

    private String format;

    private Weekday weekday;

    private Designation designation;

    private String day;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Weekday getWeekday() {
        return weekday;
    }

    public void setWeekday(Weekday weekday) {
        this.weekday = weekday;
    }

    public Designation getDesignation() {
        return designation;
    }

    public void setDesignation(Designation designation) {
        this.designation = designation;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "Gregorian{" +
                "date='" + date + '\'' +
                ", month=" + month +
                ", year='" + year + '\'' +
                ", format='" + format + '\'' +
                ", weekday=" + weekday +
                ", designation=" + designation +
                ", day='" + day + '\'' +
                '}';
    }
}
