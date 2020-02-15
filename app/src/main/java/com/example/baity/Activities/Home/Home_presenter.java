package com.example.baity.Activities.Home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

import com.example.baity.Activities.Profile.profileInterface;
import com.example.baity.Model.AdModel;
import com.example.baity.Model.Get_profile_response;
import com.example.baity.Model.Home_model;
import com.example.baity.Model.Home_model_response;
import com.example.baity.Model.Home_slider_model;
import com.example.baity.Model.Search_model;
import com.example.baity.Network.NetworkUtil;
import com.example.baity.R;
import com.example.baity.Utils.Constant;
import com.example.baity.Utils.Preferences;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class Home_presenter {
    Home_interface home_interface;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();
    private Context context;
    AlertDialog alertDialog;
    Preferences preferences;

    public Home_presenter(Home_interface home_interface, Context context) {
        this.home_interface = home_interface;
        this.context = context;
    }

    void GetMarqee(String token) {
        mSubscriptions.add(NetworkUtil.getRetrofitByToken(token)
                .GetMarqee()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponsee, this::handleError));
    }

    private void handleResponsee(List<String> strings) {
        home_interface.sendMarqee(strings);
    }

    void GetSearchList() {
        mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                .GetListOfSearch()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseee, this::handleErrorr));
    }


    private void handleResponseee(List<Search_model> search_models) {
        home_interface.sendSearchList(search_models);
    }

    void GetAdsSlider() {
        showLoadingDialog();
        String token = preferences.GetToken(context);
        mSubscriptions.add(NetworkUtil.getRetrofitByToken(token)
                .GetAdsSlider()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseSlider, this::handleErrorrSlider));
    }

    private void handleErrorrSlider(Throwable throwable) {
        alertDialog.dismiss();
        Constant.handleErrors(throwable,context);
    }

    private void handleResponseSlider(List<Home_slider_model> home_slider_models) {
        alertDialog.dismiss();
        home_interface.sendAdsSlider(home_slider_models);
    }


    private void handleErrorr(Throwable throwable) {

    }

    void GetData(String token) {
        //showLoadingDialog();
        mSubscriptions.add(NetworkUtil.getRetrofitByToken(token)
                .GetHomeData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));
    }

    private void handleResponse(List<Home_model> home_models) {
        //alertDialog.dismiss();
        home_interface.sendData(home_models);
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
        home_interface.sendAd(adModel);
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