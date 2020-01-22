package com.example.baity.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.baity.Designs.MyButtonBold;
import com.example.baity.Designs.MyEditTextBold;
import com.example.baity.R;

public class Forget_password extends BaseActivity {
    AppCompatImageView backBtn;
    MyEditTextBold edtMobileNumber;
    MyButtonBold resetBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        backBtn = findViewById(R.id.btnBack);
        edtMobileNumber = findViewById(R.id.MobileNumber);
        resetBtn = findViewById(R.id.btnReset);

        String MobileNumber = edtMobileNumber.getText().toString();
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Forget_password.this,LogIn.class);
                startActivity(intent);
                finish();
            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Forget_password.this,Verification_Code.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
