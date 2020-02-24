package com.example.baity.Model;

import java.util.List;

public class Prayer {
    private String code;

    private List<Data> data;

    private String status;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Prayer{" +
                "code='" + code + '\'' +
                ", data=" + data +
                ", status='" + status + '\'' +
                '}';
    }
}
