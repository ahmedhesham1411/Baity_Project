package com.example.baity.Activities.Slider_Result;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

import com.example.baity.Activities.Home.Home_interface;
import com.example.baity.Model.AdModel;
import com.example.baity.Model.Home_model;
import com.example.baity.Model.Sub_parent_model;
import com.example.baity.Network.NetworkUtil;
import com.example.baity.R;
import com.example.baity.Utils.Constant;
import com.example.baity.Utils.Preferences;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class Slider_resault_presenter {

    Sub_resault_interface sub_resault_interface;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();
    private Context context;
    AlertDialog alertDialog;
    Preferences preferences;

    public Slider_resault_presenter(Sub_resault_interface sub_resault_interface, Context context) {
        this.sub_resault_interface = sub_resault_interface;
        this.context = context;
    }

    void GetData(int id) {
        String token = preferences.GetToken(context);
        showLoadingDialog();
        mSubscriptions.add(NetworkUtil.getRetrofitByToken(token)
                .GetSlidersData(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));
    }

    private void handleResponse(List<Sub_parent_model> sub_parent_models) {
        alertDialog.dismiss();
        sub_resault_interface.sendData(sub_parent_models);
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
        sub_resault_interface.sendAd(adModel);
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
