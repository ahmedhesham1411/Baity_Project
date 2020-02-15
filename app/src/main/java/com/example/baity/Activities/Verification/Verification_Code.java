package com.example.baity.Activities.Verification;

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
import com.example.baity.Activities.Forget_password.Forget_password;
import com.example.baity.Activities.Reset_password.Reset_password;
import com.example.baity.Designs.MyButtonBold;
import com.example.baity.Designs.MyEditTextBold;
import com.example.baity.R;
import com.example.baity.Utils.Preferences;

import static android.graphics.Color.RED;
import static com.basgeekball.awesomevalidation.ValidationStyle.COLORATION;


public class Verification_Code extends BaseActivity {
    Preferences preferences;
    MyButtonBold btnVerify;
    MyButtonBold btnReset;
    AppCompatImageView backBtn;
    AwesomeValidation awesomeValidation;
    MyEditTextBold one,two,three,four,five,six;
    String verCode,getEmail;
    Ver_presenter ver_presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification__code);

        ver_presenter = new Ver_presenter(this);

        awesomeValidation = new AwesomeValidation(COLORATION);
        awesomeValidation.setColor(RED);
        addValidationToViews();

        one = findViewById(R.id.first_vCode);
        two = findViewById(R.id.second_vCode);
        three = findViewById(R.id.third_vCode);
        four = findViewById(R.id.fourth_vCode);
        five = findViewById(R.id.fifth_vCode);
        six = findViewById(R.id.six_vCode);
        btnVerify = findViewById(R.id.btnVerify);
        backBtn = findViewById(R.id.btnBack);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Verification_Code.this, Forget_password.class);
                startActivity(intent);
            }
        });

        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitForm();
            }
        });

    }

    private void addValidationToViews() {
        awesomeValidation.addValidation(this, R.id.first_vCode, RegexTemplate.NOT_EMPTY, R.string.nameerror);
        awesomeValidation.addValidation(this, R.id.second_vCode, RegexTemplate.NOT_EMPTY, R.string.nameerror);
        awesomeValidation.addValidation(this, R.id.third_vCode, RegexTemplate.NOT_EMPTY, R.string.nameerror);
        awesomeValidation.addValidation(this, R.id.fourth_vCode, RegexTemplate.NOT_EMPTY, R.string.nameerror);
        awesomeValidation.addValidation(this, R.id.fifth_vCode, RegexTemplate.NOT_EMPTY, R.string.nameerror);
        awesomeValidation.addValidation(this, R.id.six_vCode, RegexTemplate.NOT_EMPTY, R.string.nameerror);
    }

    private void showCustomDialog() {
         ViewGroup viewGroup = findViewById(android.R.id.content);
         final View dialogView = LayoutInflater.from(this).inflate(R.layout.verification_dialog, viewGroup, false);
         final AlertDialog.Builder builder = new AlertDialog.Builder(this);
         builder.setView(dialogView);
         final AlertDialog alertDialog = builder.create();
         alertDialog.show();
         btnReset = dialogView.findViewById(R.id.btnReset);
         btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                Intent intent = new Intent(getApplicationContext(), Reset_password.class);
                startActivity(intent);
                finish();
            }
         });
    }

    private void submitForm() {
        if (awesomeValidation.validate()) {
            verCode = one.getText().toString() +
                    two.getText().toString() +
                    three.getText().toString() +
                    four.getText().toString() +
                    five.getText().toString() +
                    six.getText().toString();

            preferences.SaveVerificationCode(this,verCode);
            getEmail = preferences.GetForgetPasswordEmail(this);
            ver_presenter.Veriify(getEmail,verCode);
        }
    }
}
