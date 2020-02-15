package com.example.baity.Activities.Reviews;

import com.example.baity.Model.AdModel;
import com.example.baity.Model.Review_full_model;

public interface Review_interface {
    void sendAd(AdModel adModel);
    void sendReviews(Review_full_model review_full_model);

}
