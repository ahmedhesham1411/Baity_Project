package com.example.baity.Activities.MyFavourite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

import com.example.baity.Activities.Child.Sub_child_interface;
import com.example.baity.Model.Child_model;
import com.example.baity.Model.MyFavouriteModel;
import com.example.baity.Network.NetworkUtil;
import com.example.baity.R;
import com.example.baity.Utils.Constant;
import com.example.baity.Utils.Preferences;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class My_Favourite_Presenter {
    My_Favourite_Interface my_favourite_interface;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();
    private Context context;
    AlertDialog alertDialog;
    Preferences preferences;

    public My_Favourite_Presenter(My_Favourite_Interface my_favourite_interface, Context context) {
        this.my_favourite_interface = my_favourite_interface;
        this.context = context;
    }


    void GetFavourite() {
        String token = preferences.GetToken(context);
        showLoadingDialog();
        int id = preferences.Get_sub_id(context);
        mSubscriptions.add(NetworkUtil.getRetrofitByToken(token)
                .GetFavourite()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));
    }

    private void handleResponse(List<MyFavouriteModel> myFavouriteModels) {
        alertDialog.dismiss();
        my_favourite_interface.sendFavourite(myFavouriteModels);
    }

    private void handleError(Throwable throwable) {
        alertDialog.dismiss();
        //Constant.handleErrors(throwable,context);
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