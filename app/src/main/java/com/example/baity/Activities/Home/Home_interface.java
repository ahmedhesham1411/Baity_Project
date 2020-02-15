package com.example.baity.Activities.Home;

import com.example.baity.Model.AdModel;
import com.example.baity.Model.Home_model;
import com.example.baity.Model.Home_model_response;
import com.example.baity.Model.Home_slider_model;
import com.example.baity.Model.Search_model;

import java.util.List;

public interface Home_interface {

    void sendData(List<Home_model> home_models);
    void sendMarqee(List<String> list);
    void sendAd(AdModel adModel);
    void sendSearchList(List<Search_model> search_models);
    void sendAdsSlider(List<Home_slider_model> home_slider_models);


}
