package com.example.baity.Activities.Child;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

import com.example.baity.Activities.Profile.profileInterface;
import com.example.baity.Model.AdModel;
import com.example.baity.Model.Child_model;
import com.example.baity.Model.Fav_message;
import com.example.baity.Model.Get_profile_response;
import com.example.baity.Network.NetworkUtil;
import com.example.baity.R;
import com.example.baity.Utils.Constant;
import com.example.baity.Utils.Preferences;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class Sub_category_presenter {
    Sub_child_interface sub_child_interface;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();
    private Context context;
    AlertDialog alertDialog;
    Preferences preferences;

    public Sub_category_presenter(Sub_child_interface sub_child_interface, Context context) {
        this.sub_child_interface = sub_child_interface;
        this.context = context;
    }

    void GetChild(String token) {
        showLoadingDialog();
        int id = preferences.Get_sub_id(context);
        mSubscriptions.add(NetworkUtil.getRetrofitByToken(token)
                .GetChlid(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));
    }

    private void handleResponse(List<Child_model> child_models) {
        alertDialog.dismiss();
        sub_child_interface.sendChildData(child_models);

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
        sub_child_interface.sendAd(adModel);
    }

    void SaveFavourite(int id) {
        showLoadingDialog();
        String token = preferences.GetToken(context);
        mSubscriptions.add(NetworkUtil.getRetrofitByToken(token)
                .SaveFavourite(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseFavourite, this::handleError));
    }

    private void handleResponseFavourite(Fav_message fav_message) {
        alertDialog.dismiss();
        Constant.FavouriteSuccess("Saved Successfully",context);
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
