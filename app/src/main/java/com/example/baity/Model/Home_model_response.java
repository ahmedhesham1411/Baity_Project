package com.example.baity.Model;

import java.util.List;

import retrofit2.http.Body;

public class Home_model_response {

    public List<Home_model> home_models = null;

    public Home_model_response(List<Home_model> home_models) {
        this.home_models = home_models;
    }

    public List<Home_model> getHome_models() {
        return home_models;
    }

    public void setHome_models(List<Home_model> home_models) {
        this.home_models = home_models;
    }
}
