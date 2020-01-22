package com.example.baity.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputType;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.baity.Designs.MyButtonBold;
import com.example.baity.Designs.MyEditTextNormal;
import com.example.baity.R;
import com.google.common.collect.Range;

import java.io.IOException;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.basgeekball.awesomevalidation.ValidationStyle.COLORATION;

public class SignUp extends BaseActivity {
    AppCompatImageView btnBack;
    MyEditTextNormal firstName,lastName,email,phone,password,confirmPassword;
    FrameLayout uploadImage;
    Uri imageUri;
    CircleImageView profileImage;
    CheckBox checkBox;
    private AwesomeValidation awesomeValidation;
    MyButtonBold signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        awesomeValidation = new AwesomeValidation(COLORATION);
        awesomeValidation.setColor(Color.RED);

        btnBack = findViewById(R.id.btnBack);
        uploadImage = findViewById(R.id.uploadImage);
        profileImage = findViewById(R.id.profile_image);
        firstName = findViewById(R.id.editFirstName);
        lastName = findViewById(R.id.editLastName);
        email = findViewById(R.id.editEmail);
        phone = findViewById(R.id.editPhone);
        password = findViewById(R.id.editPassword);
        confirmPassword = findViewById(R.id.editConfirmPassword);
        signUp = findViewById(R.id.btnsignup);
        checkBox = findViewById(R.id.checkBox);

        addValidationToViews();

        if (Locale.getDefault().toString().equals("ar")){
            password.setTextDirection(View.TEXT_DIRECTION_RTL);
        }


        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(gallery, "Select Picture"), 1);

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this,LogIn.class);
                startActivity(intent);
                finish();
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitForm();
            }
        });
    }

    private void addValidationToViews() {

        awesomeValidation.addValidation(this, R.id.editFirstName, RegexTemplate.NOT_EMPTY, R.string.nameerror);
        awesomeValidation.addValidation(this, R.id.editLastName, RegexTemplate.NOT_EMPTY, R.string.nameerror);

        awesomeValidation.addValidation(this, R.id.editEmail, Patterns.EMAIL_ADDRESS, R.string.emailerror);

        String regexPassword = ".{8,}";
        awesomeValidation.addValidation(this, R.id.editPassword, regexPassword, R.string.passworderror);
        awesomeValidation.addValidation(this, R.id.editConfirmPassword, regexPassword, R.string.passworderror);
/*        if (password != confirmPassword){
            awesomeValidation.addValidation(this, R.id.editConfirmPassword, regexPassword, R.string.match_password);
        }*/

        awesomeValidation.addValidation(this, R.id.editPhone, "^[+]?[0-9]{10,13}$", R.string.mobileerror);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
        }

        try
        {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
            profileImage.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void submitForm() {
        if (awesomeValidation.validate()) {
            if (password != confirmPassword){
                awesomeValidation.addValidation(this, R.id.editConfirmPassword,R.id.editPassword, R.string.match_password);
            }
            else if (!checkBox.isChecked()){
                Toast.makeText(this, "You must agree to terms", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Register Successfully", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),LogIn.class);
                startActivity(intent);
                finish();
            }
        }
        else {
/*
            Toast.makeText(this, "All fields required ", Toast.LENGTH_SHORT).show();
*/
        }
    }
}


