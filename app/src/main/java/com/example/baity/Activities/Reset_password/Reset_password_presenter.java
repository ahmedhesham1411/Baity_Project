package com.example.baity.Activities.Reset_password;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

import com.example.baity.Activities.Login.LogIn;
import com.example.baity.Utils.Constant;
import com.example.baity.Designs.MyButtonBold;
import com.example.baity.Model.Ver_change_pass_request;
import com.example.baity.Model.Ver_change_pass_response;
import com.example.baity.Network.NetworkUtil;
import com.example.baity.R;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class Reset_password_presenter {
    private CompositeSubscription mSubscriptions = new CompositeSubscription();
    private Context context;
    AlertDialog alertDialog,alertDialog1;
    Ver_change_pass_request ver_change_pass_request;
    MyButtonBold btnDone;

    public Reset_password_presenter(Context context) {
        this.context = context;
    }

    void reset_password(String email,String oldPassword,String newPassword,String confirmPassword) {
        showLoadingDialog();
        ver_change_pass_request = new Ver_change_pass_request(email,oldPassword,newPassword,confirmPassword);
        mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                .Verify_change_password(ver_change_pass_request)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));
    }

    private void handleResponse(Ver_change_pass_response ver_change_pass_response) {
        alertDialog.dismiss();
        showCustomDialog();
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

    public void showCustomDialog() {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.reset_password_dialog, null, false);
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);
        alertDialog1 = builder.create();
        alertDialog1.show();
        btnDone = view.findViewById(R.id.btnDone);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog1.dismiss();
                Intent intent = new Intent(context, LogIn.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(intent);
            }
        });
    }

}
