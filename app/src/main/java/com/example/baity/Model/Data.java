package com.example.baity.Model;

public class Data {
    private Date date;

    private Meta meta;

    private Timings timings;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Timings getTimings() {
        return timings;
    }

    public void setTimings(Timings timings) {
        this.timings = timings;
    }

    @Override
    public String toString() {
        return "Data{" +
                "date=" + date +
                ", meta=" + meta +
                ", timings=" + timings +
                '}';
    }
}
