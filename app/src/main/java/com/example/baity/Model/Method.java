package com.example.baity.Model;

public class Method {
    private String name;

    private String id;

    private Params params;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Params getParams() {
        return params;
    }

    public void setParams(Params params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "Method{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", params=" + params +
                '}';
    }
}
