package com.example.baity.Activities.Forget_password;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import com.example.baity.Activities.Verification.Verification_Code;
import com.example.baity.Utils.Constant;
import com.example.baity.Model.Forget_password_request;
import com.example.baity.Model.Forget_password_response;
import com.example.baity.Network.NetworkUtil;
import com.example.baity.R;
import com.example.baity.Utils.Preferences;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class Forget_password_presenter {

    private CompositeSubscription mSubscriptions = new CompositeSubscription();
    private Context context;
    AlertDialog alertDialog;
    Forget_password_request forget_password_request;
    Preferences preferences;
    String saveEmail;

    public Forget_password_presenter(Context context) {
        this.context = context;
    }

    void Forget(String email) {
        saveEmail = email;
        showLoadingDialog();
        forget_password_request = new Forget_password_request(email);
        mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                .ForgetPassword(forget_password_request)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));
    }

    private void handleResponse(Forget_password_response forget_password_response) {
        alertDialog.dismiss();
        preferences.SaveForgetPasswordEmail(context,saveEmail);
        Intent intent = new Intent(context, Verification_Code.class);
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