package com.example.baity.Model;

public class Designation {

    private String expanded;

    private String abbreviated;

    public String getExpanded() {
        return expanded;
    }

    public void setExpanded(String expanded) {
        this.expanded = expanded;
    }

    public String getAbbreviated() {
        return abbreviated;
    }

    public void setAbbreviated(String abbreviated) {
        this.abbreviated = abbreviated;
    }

    @Override
    public String toString() {
        return "Designation{" +
                "expanded='" + expanded + '\'' +
                ", abbreviated='" + abbreviated + '\'' +
                '}';
    }
}
