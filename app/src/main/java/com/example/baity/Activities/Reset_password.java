package com.example.baity.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baity.Designs.MyButtonBold;
import com.example.baity.Designs.MyEditTextBold;
import com.example.baity.R;

public class Reset_password extends BaseActivity {
    MyButtonBold btnSubmit,btnDone;
    MyEditTextBold edtNewPassword,edtConfirmNewPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        btnSubmit = findViewById(R.id.btnSubmit);
        edtNewPassword = findViewById(R.id.new_password);
        edtConfirmNewPassword = findViewById(R.id.confirm_password);

        String newPassword = edtNewPassword.getText().toString();
        String confirmNewPassword = edtConfirmNewPassword.getText().toString();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog();
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
                Intent intent = new Intent(getApplicationContext(),LogIn.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
