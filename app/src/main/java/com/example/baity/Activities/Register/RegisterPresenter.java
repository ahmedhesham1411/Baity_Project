package com.example.baity.Activities.Register;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

import com.example.baity.Activities.Home.Home;
import com.example.baity.Utils.Constant;
import com.example.baity.Model.RegisterationResponse;
import com.example.baity.Model.RegistrationRequest;
import com.example.baity.Network.NetworkUtil;
import com.example.baity.R;
import com.example.baity.Utils.Preferences;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class RegisterPresenter {
    private CompositeSubscription mSubscriptions = new CompositeSubscription();
    private Context context;
    AlertDialog alertDialog;
    Preferences preferences;

    public RegisterPresenter(Context context) {
        this.context = context;
    }

    void register(String FirstName, String LastName, String Phone, String Email, String pass, String ConfirmPass, String Image) {
        showLoadingDialog();
        RegistrationRequest registrationRequest = new RegistrationRequest(FirstName, LastName,Phone,Email,pass,ConfirmPass,Image);
        mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                .Register(registrationRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));

    }

    private void handleResponse(RegisterationResponse registerationResponse) {
        alertDialog.dismiss();
        Preferences.saveToken(context,registerationResponse.getToken());
        Intent intent = new Intent(context, Home.class);
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
        alertDialog.show();
        alertDialog.setCancelable(false);
    }

}
