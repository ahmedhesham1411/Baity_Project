package com.example.baity.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baity.Designs.MyButtonBold;
import com.example.baity.R;


public class Verification_Code extends BaseActivity {
    MyButtonBold btnVerify;
    MyButtonBold btnReset;
    AppCompatImageView backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification__code);

        btnVerify = findViewById(R.id.btnVerify);
        backBtn = findViewById(R.id.btnBack);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Verification_Code.this,Forget_password.class);
                startActivity(intent);
            }
        });

        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog();
            }
        });

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
                Intent intent = new Intent(getApplicationContext(),Reset_password.class);
                startActivity(intent);
                finish();
            }
         });
    }
}
