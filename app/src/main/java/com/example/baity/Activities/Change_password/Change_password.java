package com.example.baity.Activities.Change_password;

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
import com.example.baity.Activities.Home.Home;
import com.example.baity.Activities.Setting;
import com.example.baity.Designs.MyButtonBold;
import com.example.baity.Designs.MyEditTextBold;
import com.example.baity.R;

import static android.graphics.Color.RED;
import static com.basgeekball.awesomevalidation.ValidationStyle.COLORATION;

public class Change_password extends BaseActivity {
    MyEditTextBold edtOldPassword,edtNewPassword,edtConfirmPassword;
    MyButtonBold btnSubmit,btnDone;
    AppCompatImageView btnBack;
    AwesomeValidation awesomeValidation;
    private Change_password_presenter change_password_presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        awesomeValidation = new AwesomeValidation(COLORATION);
        awesomeValidation.setColor(RED);

        change_password_presenter = new Change_password_presenter(this);

        edtOldPassword = findViewById(R.id.change_old_password);
        edtNewPassword = findViewById(R.id.change_new_password);
        edtConfirmPassword = findViewById(R.id.change_Confirm_password);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitForm();
                //showCustomDialog();
            }
        });

        addValidationToViews();
    }

    public void showCustomDialog() {
        ViewGroup viewGroup = findViewById(android.R.id.content);
        final View dialogView = LayoutInflater.from(this).inflate(R.layout.change_password_dialog, viewGroup, false);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        btnDone = dialogView.findViewById(R.id.btnDone);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void addValidationToViews() {

        awesomeValidation.addValidation(this, R.id.change_old_password, RegexTemplate.NOT_EMPTY, R.string.nameerror);
        awesomeValidation.addValidation(this, R.id.change_new_password, RegexTemplate.NOT_EMPTY, R.string.nameerror);
        awesomeValidation.addValidation(this, R.id.change_Confirm_password, RegexTemplate.NOT_EMPTY, R.string.nameerror);

        String regexPassword = ".{6,}";
        awesomeValidation.addValidation(this, R.id.change_old_password, regexPassword, R.string.passworderror);
        awesomeValidation.addValidation(this, R.id.change_new_password, regexPassword, R.string.passworderror);
        awesomeValidation.addValidation(this, R.id.change_Confirm_password, regexPassword, R.string.passworderror);


        if (edtNewPassword != edtConfirmPassword){
            awesomeValidation.addValidation(this, R.id.change_Confirm_password,R.id.change_new_password, R.string.match_password);
        }
    }

    private void submitForm() {
        if (awesomeValidation.validate()) {
                change_password_presenter.change(edtOldPassword.getText().toString(),
                        edtNewPassword.getText().toString(),
                        edtConfirmPassword.getText().toString());
        }
    }

}
