package com.example.baity.Activities.Slider_Result;

import com.example.baity.Model.AdModel;
import com.example.baity.Model.Home_model;
import com.example.baity.Model.Sub_parent_model;

import java.util.List;

public interface Sub_resault_interface {
    void sendData(List<Sub_parent_model> sub_parent_models);
    void sendAd(AdModel adModel);

}
