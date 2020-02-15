package com.example.baity.Activities.Profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import androidx.appcompat.app.AlertDialog;

import com.example.baity.Utils.Constant;
import com.example.baity.Model.Get_profile_response;
import com.example.baity.Network.NetworkUtil;
import com.example.baity.R;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class Myprofile_presenter {
    profileInterface profileInterface;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();
    private Context context;
    AlertDialog alertDialog;

    public Myprofile_presenter(profileInterface profileInterface, Context context) {
        this.profileInterface = profileInterface;
        this.context = context;
    }

    void GetProfile(String token) {
        showLoadingDialog();
        mSubscriptions.add(NetworkUtil.getRetrofitByToken(token)
                .GetProfileData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));
    }

    public void handleResponse(Get_profile_response get_profile_response) {
        alertDialog.dismiss();
        profileInterface.sendData(get_profile_response);
    }


    private void handleError(Throwable throwable) {
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
