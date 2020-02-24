package com.example.baity.Activities.Contact_us;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

import com.example.baity.Activities.About_us.About_us_interface;
import com.example.baity.Activities.Home.Home;
import com.example.baity.Designs.MyButtonBold;
import com.example.baity.Model.About_us_response;
import com.example.baity.Model.Contact_us_response;
import com.example.baity.Model.Sug_response;
import com.example.baity.Model.Suggestion_request;
import com.example.baity.Network.NetworkUtil;
import com.example.baity.R;
import com.example.baity.Utils.Constant;
import com.example.baity.Utils.Preferences;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static com.example.baity.Utils.Constant.alertDialog1;

public class Contact_us_presenter {
    Contact_us_interface contact_us_interface;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();
    private Context context;
    AlertDialog alertDialog,alertDialog1;
    Preferences preferences;
    MyButtonBold btnDone;

    public Contact_us_presenter(Contact_us_interface contact_us_interface, Context context) {
        this.contact_us_interface = contact_us_interface;
        this.context = context;
    }

    void GetContactUs() {
        showLoadingDialog();
        String Token = preferences.GetToken(context);
        mSubscriptions.add(NetworkUtil.getRetrofitByToken(Token)
                .GetContactUs()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseContact, this::handleErrorContact));
    }

    private void handleResponseContact(Contact_us_response contact_us_response) {
        alertDialog.dismiss();
        contact_us_interface.sendContacts(contact_us_response);
    }

    private void handleErrorContact(Throwable throwable) {
        alertDialog.dismiss();
        Constant.handleErrors(throwable,context);
    }

    void SaveSugg(String suggestion) {
        showLoadingDialog();
        String Token = preferences.GetToken(context);
        Suggestion_request suggestion_request = new Suggestion_request(suggestion);
        mSubscriptions.add(NetworkUtil.getRetrofitByToken(Token)
                .SaveSug(suggestion_request)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseSug, this::handleErrorSug));
    }

    private void handleResponseSug(Sug_response sug_response) {
        alertDialog.dismiss();
        showCustomDialog();
    }

    private void handleErrorSug(Throwable throwable) {
        alertDialog.dismiss();
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
        View view = layoutInflater.inflate(R.layout.sug_dialog, null, false);
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);
        alertDialog1 = builder.create();
        alertDialog1.show();
        btnDone = view.findViewById(R.id.btnDone);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog1.dismiss();
            }
        });
    }
}
