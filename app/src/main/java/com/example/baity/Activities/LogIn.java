package com.example.baity.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.baity.Designs.MyButtonBold;
import com.example.baity.Designs.MyEditTextBold;
import com.example.baity.Designs.MyTextViewBold;
import com.example.baity.R;

public class LogIn extends BaseActivity {
    MyEditTextBold editUserName,editPassword;
    MyTextViewBold openSignUpPage,forgetPassword;
    AppCompatImageView btnBack;
    MyButtonBold btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        editUserName = findViewById(R.id.editUserName);
        editPassword = findViewById(R.id.editPassword);
        openSignUpPage = findViewById(R.id.OpenSignUpPage);
        btnBack = findViewById(R.id.btnBack);
        forgetPassword = findViewById(R.id.forget_password);
        btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogIn.this,Home.class);
                startActivity(intent);
                finish();
            }
        });

        openSignUpPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogIn.this,SignUp.class);
                startActivity(intent);
                finish();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogIn.this,Language.class);
                startActivity(intent);
                finish();
            }
        });

        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogIn.this,Forget_password.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
