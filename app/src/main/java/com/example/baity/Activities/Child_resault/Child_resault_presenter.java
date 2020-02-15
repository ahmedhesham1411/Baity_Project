package com.example.baity.Activities.Child_resault;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.baity.Activities.Child.Sub_child_interface;
import com.example.baity.Model.AdModel;
import com.example.baity.Model.Child_model;
import com.example.baity.Model.Child_resault_model;
import com.example.baity.Network.NetworkUtil;
import com.example.baity.R;
import com.example.baity.Utils.Constant;
import com.example.baity.Utils.Preferences;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class Child_resault_presenter {
    Child_resault_interface child_resault_interface;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();
    private Context context;
    AlertDialog alertDialog;
    Preferences preferences;

    public Child_resault_presenter(Child_resault_interface child_resault_interface, Context context) {
        this.child_resault_interface = child_resault_interface;
        this.context = context;
    }

    void GetChildresault(String token) {
        showLoadingDialog();
        int id = preferences.Get_Child_id(context);
        mSubscriptions.add(NetworkUtil.getRetrofitByToken(token)
                .GetChlidResault(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));
    }

    private void handleResponse(Child_resault_model child_resault_model) {
        alertDialog.dismiss();
        child_resault_interface.sendChildresault(child_resault_model);
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
        child_resault_interface.sendAd(adModel);
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
