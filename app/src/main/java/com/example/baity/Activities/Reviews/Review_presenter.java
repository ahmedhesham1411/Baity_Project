package com.example.baity.Activities.Reviews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

import com.example.baity.Activities.Home.Home_interface;
import com.example.baity.Model.AdModel;
import com.example.baity.Model.Home_model;
import com.example.baity.Model.Rate_response;
import com.example.baity.Model.Rating_model;
import com.example.baity.Model.Review_full_model;
import com.example.baity.Network.NetworkUtil;
import com.example.baity.R;
import com.example.baity.Utils.Constant;
import com.example.baity.Utils.Preferences;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class Review_presenter {
    Review_interface review_interface;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();
    private Context context;
    AlertDialog alertDialog;
    Preferences preferences;

    public Review_presenter(Review_interface review_interface, Context context) {
        this.review_interface = review_interface;
        this.context = context;
    }

    void SendRate(int company_id,double rate) {
        Rating_model rating_model = new Rating_model(company_id,rate);
        showLoadingDialog();
        String Token = preferences.GetToken(context);
        mSubscriptions.add(NetworkUtil.getRetrofitByToken(Token)
                .SendRate(rating_model)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponsee, this::handleError));
    }

    private void handleResponsee(Rate_response rate_response) {
        alertDialog.dismiss();
    }

    void GetReviews(int id) {
        showLoadingDialog();
        String Token = preferences.GetToken(context);
        mSubscriptions.add(NetworkUtil.getRetrofitByToken(Token)
                .GetReviews(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponsee, this::handleError));
    }

    private void handleResponsee(Review_full_model review_full_model) {
        alertDialog.dismiss();
        review_interface.sendReviews(review_full_model);
    }


    private void handleError(Throwable throwable) {
        alertDialog.dismiss();
        Constant.handleErrors(throwable,context);
    }

    void GetAd() {
        mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                .GetAd()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponsee, this::handleError));
    }

    private void handleResponsee(AdModel adModel) {
        review_interface.sendAd(adModel);
    }


    private void showLoadingDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.loading, null, false);
        final AlertDialog.Builder builder = new AlertDialog.Builder(context,R.style.SheetDialog);
        builder.setView(view);
        alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
    }
}
