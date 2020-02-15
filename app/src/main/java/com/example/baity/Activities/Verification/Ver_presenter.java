package com.example.baity.Activities.Verification;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import com.example.baity.Activities.Reset_password.Reset_password;
import com.example.baity.Utils.Constant;
import com.example.baity.Designs.MyButtonBold;
import com.example.baity.Designs.MyTextViewBold;
import com.example.baity.Model.Verify_request;
import com.example.baity.Model.Verify_response;
import com.example.baity.Network.NetworkUtil;
import com.example.baity.R;
import com.example.baity.Utils.Preferences;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class Ver_presenter {
    private CompositeSubscription mSubscriptions = new CompositeSubscription();
    private Context context;
    AlertDialog alertDialog,alertDialog1;
    Verify_request verify_request;
    Preferences preferences;
    String getEmail;
    MyButtonBold btnReset;
    MyTextViewBold error;

    public Ver_presenter(Context context) {
        this.context = context;
    }

    void Veriify(String email,String verificationCode) {
        showLoadingDialog();
        verify_request = new Verify_request(email,verificationCode);
        mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                .Verify(verify_request)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));
    }

    private void handleResponse(Verify_response verify_response) {
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
        View view = layoutInflater.inflate(R.layout.verification_dialog, null, false);
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);
        alertDialog1 = builder.create();
        alertDialog1.show();
        btnReset = view.findViewById(R.id.btnReset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog1.dismiss();
                Intent intent = new Intent(context, Reset_password.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(intent);
            }
        });
    }

}
