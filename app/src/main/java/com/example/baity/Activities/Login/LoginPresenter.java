package com.example.baity.Activities.Login;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import com.example.baity.Activities.Home.Home;
import com.example.baity.Model.Facebook_request;
import com.example.baity.Model.Facebook_response;
import com.example.baity.Utils.Constant;
import com.example.baity.Model.LoginRequest;
import com.example.baity.Model.LoginResponse;
import com.example.baity.Network.NetworkUtil;
import com.example.baity.R;
import com.example.baity.Utils.Preferences;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class LoginPresenter {

    private CompositeSubscription mSubscriptions = new CompositeSubscription();
    private Context context;
    AlertDialog alertDialog;
    Preferences preferences;
    LoginRequest loginRequest;
    String userEmail,Password;

    public LoginPresenter(Context context) {
        this.context = context;
    }

    public void login(String UserMail, String Password) {
        showLoadingDialog();
        loginRequest = new LoginRequest(UserMail, Password);
        mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                .Login(loginRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));
    }

    private void handleResponse(LoginResponse loginResponse) {
        alertDialog.dismiss();
        String Token = loginResponse.getToken();
        preferences.saveToken(context,Token);
        preferences.Save_User_State(context,"mUser");
        Intent intent = new Intent(context, Home.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    private void handleError(Throwable throwable) {
        alertDialog.dismiss();
        Constant.handleErrors(throwable,context);
    }

    public void Facebooklogin(String Username, String email) {
        showLoadingDialog();
        Facebook_request facebook_request = new Facebook_request(Username, email);
        mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                .FacebookLoginn(facebook_request)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseFace, this::handleErrorFace));
    }


    private void handleResponseFace(Facebook_response facebook_response) {
        alertDialog.dismiss();
        String Token = facebook_response.getToken();
        preferences.saveToken(context,Token);
        preferences.Save_User_State(context,"oUser");
        Intent intent = new Intent(context, Home.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    private void handleErrorFace(Throwable throwable) {
        alertDialog.dismiss();
        Constant.handleErrors(throwable,context);
    }

    public void Twitterlogin(String Username, String email) {
        showLoadingDialog();
        Facebook_request facebook_request = new Facebook_request(Username, email);
        mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                .FacebookLoginn(facebook_request)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseTwitter, this::handleErrorTwitter));
    }

    private void handleErrorTwitter(Throwable throwable) {
        alertDialog.dismiss();
        Constant.handleErrors(throwable,context);
    }

    private void handleResponseTwitter(Facebook_response facebook_response) {
        alertDialog.dismiss();
        String Token = facebook_response.getToken();
        preferences.saveToken(context,Token);
        preferences.Save_User_State(context,"oUser");
        Intent intent = new Intent(context, Home.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }


    public void Googlelogin(String Username, String email) {
        showLoadingDialog();
        Facebook_request facebook_request = new Facebook_request(Username, email);
        mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                .FacebookLoginn(facebook_request)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseGoogle, this::handleErrorGoogle));
    }

    private void handleResponseGoogle(Facebook_response facebook_response) {
        alertDialog.dismiss();
        String Token = facebook_response.getToken();
        preferences.saveToken(context,Token);
        preferences.Save_User_State(context,"oUser");
        Intent intent = new Intent(context, Home.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    private void handleErrorGoogle(Throwable throwable) {
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
