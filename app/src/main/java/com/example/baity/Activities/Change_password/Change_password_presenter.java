package com.example.baity.Activities.Change_password;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

import com.example.baity.Activities.Home.Home;
import com.example.baity.Utils.Constant;
import com.example.baity.Designs.MyButtonBold;
import com.example.baity.Model.Change_password_request;
import com.example.baity.Model.change_password_response;
import com.example.baity.Network.NetworkUtil;
import com.example.baity.R;
import com.example.baity.Utils.Preferences;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class Change_password_presenter {
    private CompositeSubscription mSubscriptions = new CompositeSubscription();
    private Context context;
    AlertDialog alertDialog,alertDialog1;
    MyButtonBold btnDone;
    public Change_password_presenter(Context context) {
        this.context = context;
    }

    void change(String OldPassword, String NewPassword,String ConfirmPassword) {
        String Token = Preferences.GetToken(context);
        showLoadingDialog();
        Change_password_request change_password = new Change_password_request(OldPassword, NewPassword,ConfirmPassword);
        mSubscriptions.add(NetworkUtil.getRetrofitByToken(Token)
                .ChangePassword(change_password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));

    }

    private void handleResponse(change_password_response changepasswordresponse) {
        /*alertDialog.dismiss();
        Toast.makeText(context,response.body().toString() , Toast.LENGTH_SHORT).show();*/
        //showCustomDialog();
        alertDialog.dismiss();
        showCustomDialog();

    }

    private void handleError(Throwable throwable) {
        alertDialog.dismiss();
        Constant.handleErrors(throwable,context);
        //alertDialog.dismiss();
        //Constant.handleErrors(throwable,context);
    }

    public void showCustomDialog() {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.change_password_dialog, null, false);
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);
        alertDialog1 = builder.create();
        alertDialog1.show();
        btnDone = view.findViewById(R.id.btnDone);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog1.dismiss();
                Intent intent = new Intent(context, Home.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(intent);
            }
        });
    }


    private void showLoadingDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.loading, null, false);
        final AlertDialog.Builder builder = new AlertDialog.Builder(context,R.style.SheetDialog);
        builder.setView(view);
        alertDialog = builder.create();
        alertDialog.show();
        alertDialog.setCancelable(false);
    }

}