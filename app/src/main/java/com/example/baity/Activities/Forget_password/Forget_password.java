package com.example.baity.Activities.Forget_password;

import androidx.appcompat.widget.AppCompatImageView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.baity.Activities.BaseActivity;
import com.example.baity.Activities.Login.LogIn;
import com.example.baity.Designs.MyButtonBold;
import com.example.baity.Designs.MyEditTextBold;
import com.example.baity.R;
import static android.graphics.Color.RED;
import static com.basgeekball.awesomevalidation.ValidationStyle.COLORATION;

public class Forget_password extends BaseActivity {
    Forget_password_presenter forget_password_presenter;
    AppCompatImageView backBtn;
    MyEditTextBold edtEmail;
    MyButtonBold resetBtn;
    AwesomeValidation awesomeValidation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        forget_password_presenter = new Forget_password_presenter(this);

        awesomeValidation = new AwesomeValidation(COLORATION);
        awesomeValidation.setColor(RED);
        addValidationToViews();

        backBtn = findViewById(R.id.btnBack);
        edtEmail = findViewById(R.id.Forget_password_email);
        resetBtn = findViewById(R.id.btnReset);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Forget_password.this, LogIn.class);
                startActivity(intent);
                finish();
            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitForm();
            }
        });
    }

    private void addValidationToViews() {
        awesomeValidation.addValidation(this, R.id.Forget_password_email, RegexTemplate.NOT_EMPTY, R.string.nameerror);
        awesomeValidation.addValidation(this, R.id.Forget_password_email, Patterns.EMAIL_ADDRESS, R.string.emailerror);
    }

    private void submitForm() {
        if (awesomeValidation.validate()) {
            forget_password_presenter.Forget(edtEmail.getText().toString());
        }
    }
}
