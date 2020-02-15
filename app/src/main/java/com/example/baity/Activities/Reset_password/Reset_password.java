package com.example.baity.Activities.Reset_password;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.baity.Activities.BaseActivity;
import com.example.baity.Activities.Login.LogIn;
import com.example.baity.Designs.MyButtonBold;
import com.example.baity.Designs.MyEditTextBold;
import com.example.baity.R;
import com.example.baity.Utils.Preferences;

import static android.graphics.Color.RED;
import static com.basgeekball.awesomevalidation.ValidationStyle.COLORATION;

public class Reset_password extends BaseActivity {
    Reset_password_presenter reset_password_presenter;
    MyButtonBold btnSubmit,btnDone;
    MyEditTextBold edtNewPassword,edtConfirmNewPassword;
    AwesomeValidation awesomeValidation;
    Preferences preferences;
    String getEmail,getCode;
    AppCompatImageView btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        reset_password_presenter = new Reset_password_presenter(this);

        awesomeValidation = new AwesomeValidation(COLORATION);
        awesomeValidation.setColor(RED);
        addValidationToViews();

        btnBack = findViewById(R.id.btnBack);
        btnSubmit = findViewById(R.id.btnSubmit);
        edtNewPassword = findViewById(R.id.reset_new_password);
        edtConfirmNewPassword = findViewById(R.id.reset_confirm_password);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitForm();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });
    }


    private void showCustomDialog() {
        ViewGroup viewGroup = findViewById(android.R.id.content);
        final View dialogView = LayoutInflater.from(this).inflate(R.layout.reset_password_dialog, viewGroup, false);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        btnDone = dialogView.findViewById(R.id.btnDone);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                Intent intent = new Intent(getApplicationContext(), LogIn.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void addValidationToViews() {

        awesomeValidation.addValidation(this, R.id.reset_new_password, RegexTemplate.NOT_EMPTY, R.string.passworderror);
        awesomeValidation.addValidation(this, R.id.reset_confirm_password, RegexTemplate.NOT_EMPTY, R.string.passworderror);

        String regexPassword = ".{6,}";
        awesomeValidation.addValidation(this, R.id.reset_new_password, regexPassword, R.string.passworderror);
        awesomeValidation.addValidation(this, R.id.reset_confirm_password, regexPassword, R.string.passworderror);

        if (edtNewPassword != edtConfirmNewPassword){
            awesomeValidation.addValidation(this, R.id.change_Confirm_password,R.id.change_new_password, R.string.match_password);
        }
    }

    private void submitForm() {
        if (awesomeValidation.validate()) {
            getEmail = preferences.GetForgetPasswordEmail(this);
            getCode = preferences.GetCode(this);
            reset_password_presenter.reset_password(getEmail,
                   getCode,
                    edtNewPassword.getText().toString(),
                    edtConfirmNewPassword.getText().toString());
        }
    }

}
