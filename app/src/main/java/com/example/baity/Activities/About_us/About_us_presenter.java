package com.example.baity.Activities.About_us;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

import com.example.baity.Activities.Child_resault.Child_resault_interface;
import com.example.baity.Model.About_us_response;
import com.example.baity.Model.AdModel;
import com.example.baity.Model.Child_resault_model;
import com.example.baity.Network.NetworkUtil;
import com.example.baity.R;
import com.example.baity.Utils.Constant;
import com.example.baity.Utils.Preferences;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class About_us_presenter {
    About_us_interface about_us_interface;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();
    private Context context;
    AlertDialog alertDialog;
    Preferences preferences;

    public About_us_presenter(About_us_interface about_us_interface, Context context) {
        this.about_us_interface = about_us_interface;
        this.context = context;
    }

    void GetAboutDes() {
        showLoadingDialog();
        String Token = preferences.GetToken(context);
        mSubscriptions.add(NetworkUtil.getRetrofitByToken(Token)
                .GetAboutDes()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseAbout, this::handleErrorAbout));
    }

    private void handleResponseAbout(About_us_response about_us_response) {
        alertDialog.dismiss();
        about_us_interface.sendDes(about_us_response);
    }

    private void handleErrorAbout(Throwable throwable) {
        alertDialog.dismiss();
        Constant.handleErrors(throwable,context);
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
