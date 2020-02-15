package com.example.baity.Activities.Profile_edit;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

import com.example.baity.Activities.Profile.MyProfile;
import com.example.baity.Utils.Constant;
import com.example.baity.Model.EditProfile_request;
import com.example.baity.Model.EditProfile_response;
import com.example.baity.Network.NetworkUtil;
import com.example.baity.R;
import com.example.baity.Utils.Preferences;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class Edit_profile_presenter {
    com.example.baity.Activities.Profile.profileInterface profileInterface;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();
    private Context context;
    AlertDialog alertDialog;
    Preferences preferences;

    public Edit_profile_presenter( Context context) {
        this.context = context;
    }

    void EditProfile(String image,String Phone,String username,String email) {
        showLoadingDialog();
        EditProfile_request editProfile_request = new EditProfile_request(image,Phone,username,email);
        String Token = preferences.GetToken(context);
        mSubscriptions.add(NetworkUtil.getRetrofitByToken(Token)
                .EditProfile(editProfile_request)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));
    }

    private void handleResponse(EditProfile_response editProfile_response) {
        alertDialog.dismiss();
        Intent intent = new Intent(context, MyProfile.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
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
