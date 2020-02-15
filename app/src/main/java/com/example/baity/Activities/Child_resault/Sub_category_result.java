package com.example.baity.Activities.Child_resault;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.baity.Activities.Reviews.Review_company;
import com.example.baity.Activities.Setting;
import com.example.baity.Designs.MyTextViewBold;
import com.example.baity.Designs.MyTextViewNormal;
import com.example.baity.Model.AdModel;
import com.example.baity.Model.Child_resault_model;
import com.example.baity.R;
import com.example.baity.Utils.Preferences;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Sub_category_result extends AppCompatActivity implements Child_resault_interface {
    Child_resault_presenter child_resault_presenter;
    MyTextViewBold textViewBold;
    AppCompatImageView btnBack,closeAdBtn,settingBtn;
    MyTextViewNormal descTxt;
    Preferences preferences;
    AppCompatImageView image_ad,saveFavoriteBtn;
    String adImage,Token;
    int company_id;
    private VideoView vv;
    private MediaController mediacontroller;
    private Uri uri;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_result);

        child_resault_presenter = new Child_resault_presenter(this,this);
        descTxt = findViewById(R.id.desc_child);
        child_resault_presenter.GetAd();
        showAdDialog();
        Token = preferences.GetToken(this);

        Gson gson = new Gson();
        String json = preferences.Get_listt(this);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        ArrayList<String> list11 = gson.fromJson(json, type);

        String name = preferences.Get_Child_name(this);
        textViewBold = findViewById(R.id.slider_sub_namee);
        btnBack = findViewById(R.id.btnBack);
        textViewBold.setText(name);
        settingBtn = findViewById(R.id.settingBtn);
        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Sub_category_result.this, Setting.class);
                v.getContext().startActivity(intent);
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

    private void showAdDialog() {
        ViewGroup viewGroup = findViewById(android.R.id.content);
        Rect displayRectangle = new Rect();
        Window window = Sub_category_result.this.getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);

        final View dialogView = LayoutInflater.from(this).inflate(R.layout.show_ad, viewGroup, false);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        closeAdBtn = dialogView.findViewById(R.id.closeAd);
        image_ad = dialogView.findViewById(R.id.imageAd);

        dialogView.setMinimumWidth((int)(displayRectangle.width() * 1f));
        dialogView.setMinimumHeight((int)(displayRectangle.height() * 0.8f));
        image_ad.setMinimumWidth((int)(displayRectangle.width() * 1f));
        image_ad.setMinimumHeight((int)(displayRectangle.height() * 0.8f));
        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        alertDialog.setCancelable(false);
        builder.setView(dialogView);
        image_ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferences.Save_company_id(v.getContext(),company_id);
                Intent intent = new Intent(v.getContext(), Review_company.class);
                v.getContext().startActivity(intent);
            }
        });
        closeAdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                child_resault_presenter.GetChildresault(Token);
            }
        });
    }

    @Override
    public void sendChildresault(Child_resault_model child_resault_model) {
        Child_resault_model child_resault_model1 = child_resault_model;
        String desc = child_resault_model1.getDescriptionA();
        descTxt.setText(desc);


        vv = (VideoView) findViewById(R.id.vv);

        mediacontroller = new MediaController(this);
        mediacontroller.setAnchorView(vv);
        String uriPath = "http://baitykw.com/Content/Images/" + child_resault_model.getVideopath();
        uri = Uri.parse(uriPath);
        vv.setMediaController(mediacontroller);
        vv.setVideoURI(uri);
        vv.requestFocus();
        vv.start();

    }

    @Override
    public void sendAd(AdModel adModel) {
        AdModel adModel1 = adModel;
        adImage = adModel1.getImagepath();
        Bitmap decodeTxt=decodeBase64(adImage);
        image_ad.setImageBitmap(decodeTxt);
        company_id = adModel.getCompany_id();

    }

    public static Bitmap decodeBase64(String input)
    {
        byte[] decodedBytes = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }
}
