package com.example.baity.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baity.Activities.Change_password.Change_password;
import com.example.baity.Activities.Login.LogIn;
import com.example.baity.Activities.MyFavourite.My_favourite;
import com.example.baity.Activities.Profile.MyProfile;
import com.example.baity.Designs.MyButtonBold;
import com.example.baity.Designs.MyTextViewBold;
import com.example.baity.R;
import com.example.baity.Utils.Preferences;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Locale;

import static com.example.baity.Activities.LocaleManeger.ARABIC;

public class Setting extends BaseActivity {
    BottomSheetDialog bottomSheetDialog;
    AppCompatImageView backBtn;
    MyTextViewBold myProfile,aboutUs,contactUs,changeLanguage,changePassword,terms_and_conditions,myFavourite,logOut;
    LinearLayoutCompat layout_popup;
    MyButtonBold btnYes,btnNo;
    Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        backBtn = findViewById(R.id.btnBack);
        myProfile = findViewById(R.id.myProfile);
        aboutUs = findViewById(R.id.about_us);
        contactUs = findViewById(R.id.contact_us);
        changeLanguage = findViewById(R.id.change_language);
        layout_popup = findViewById(R.id.layout_popup);
        changePassword = findViewById(R.id.change_password);
        terms_and_conditions = findViewById(R.id.terms_and_conditions);
        myFavourite = findViewById(R.id.my_favourirte);
        logOut = findViewById(R.id.logOut);

        String userState = preferences.Get_User_State(this);
        if (userState.equals("oUser")){
            myProfile.setVisibility(View.GONE);
            changePassword.setVisibility(View.GONE);
        }

        changeLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.show();

            }
        });

        myProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MyProfile.class);
                startActivity(intent);
            }
        });

        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),About_us.class);
                startActivity(intent);
            }
        });

        contactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Contact_Us.class);
                startActivity(intent);
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });



        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Change_password.class);
                startActivity(intent);
            }
        });

        terms_and_conditions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Terms_and_conditions.class);
                startActivity(intent);
            }
        });

        myFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), My_favourite.class);
                startActivity(intent);
            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog();
            }
        });

        //Bottom sheet
        bottomSheetDialog = new BottomSheetDialog(this);
        final View bottomSheetDialogView = getLayoutInflater().inflate(R.layout.popup_button, null);
        bottomSheetDialog.setContentView(bottomSheetDialogView);
        View popArabic = bottomSheetDialogView.findViewById(R.id.btnArabic);
        View popEnglish = bottomSheetDialogView.findViewById(R.id.btnEnglish);

        if (Locale.getDefault().toString().equals("ar")){
            popEnglish.setVisibility(View.VISIBLE);
        }
        else if (Locale.getDefault().toString().equals("en")){
            popArabic.setVisibility(View.VISIBLE);
        }
        popArabic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNewLocale(Setting.this,ARABIC);
                Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });

        popEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNewLocale(getApplicationContext(),LocaleManeger.ENGLISH);
                Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });

    }

    private void showCustomDialog() {
        ViewGroup viewGroup = findViewById(android.R.id.content);
        final View dialogView = LayoutInflater.from(this).inflate(R.layout.log_out_dialog, viewGroup, false);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        btnYes = dialogView.findViewById(R.id.btnYes);
        btnNo= dialogView.findViewById(R.id.btnNo);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                preferences.Clear(getApplicationContext());
                Intent intent = new Intent(getApplicationContext(), LogIn.class);
                startActivity(intent);
                finish();
            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }



    private void setNewLocale(Context mContext, @LocaleManeger.LocaleDef String language) {
        LocaleManeger.setNewLocale(getApplicationContext(), language);
        Intent intent = this.getIntent();
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
    }
}
