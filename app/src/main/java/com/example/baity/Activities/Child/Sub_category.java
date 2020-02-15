package com.example.baity.Activities.Child;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ListView;

import com.example.baity.Activities.Home.Home;
import com.example.baity.Activities.Reviews.Review_company;
import com.example.baity.Activities.Setting;
import com.example.baity.Activities.Slider_Result.Slider_result;
import com.example.baity.Adapter.Sub_cat_slider;
import com.example.baity.Adapter.Sub_items_adapter;
import com.example.baity.Designs.MyTextViewBold;
import com.example.baity.Model.AdModel;
import com.example.baity.Model.Child_model;
import com.example.baity.R;
import com.example.baity.Utils.Preferences;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.smarteist.autoimageslider.SliderView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class Sub_category extends AppCompatActivity implements Sub_child_interface {
    Sub_category_presenter sub_category_presenter;
    RecyclerView recyclerView;
    MyTextViewBold textViewDescription,text;
    AppCompatImageView btnBack,closeAdBtn;
    Preferences preferences;
    String Token;
    AppCompatImageView image_ad,favoriteBtn,settingBtn;
    String adImage;
    int company_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);

        sub_category_presenter = new Sub_category_presenter(this,this);
        sub_category_presenter.GetAd();
        showAdDialog();

        Gson gson = new Gson();
        String json = preferences.Get_listt(this);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        ArrayList<String> list11 = gson.fromJson(json, type);

        SliderView sliderView = findViewById(R.id.imageSliderrrrrrr);
        Sub_cat_slider adapterr = new Sub_cat_slider(this,list11);
        sliderView.setSliderAdapter(adapterr);

        String nameee = preferences.Get_sub_name(this);
        text = findViewById(R.id.slider_namee);
        text.setText(nameee);
        settingBtn = findViewById(R.id.settingBtn);
        favoriteBtn = findViewById(R.id.saveFavoriteBtn);
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });
        textViewDescription = findViewById(R.id.slider_name);

        favoriteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = preferences.Get_sub_id(v.getContext());
                sub_category_presenter.SaveFavourite(id);
            }
        });

        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Sub_category.this, Setting.class);
                startActivity(intent);
            }
        });
    }

    private void showAdDialog() {
        ViewGroup viewGroup = findViewById(android.R.id.content);
        Rect displayRectangle = new Rect();
        Window window = Sub_category.this.getWindow();
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
                Token = preferences.GetToken(v.getContext());
                sub_category_presenter.GetChild(Token);
            }
        });
    }

    @Override
    public void sendChildData(List<Child_model> child_models) {
        List<Child_model> child_modelss = child_models;
        recyclerView = findViewById(R.id.sub_items_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        Sub_items_adapter sub_cat_adapter = new Sub_items_adapter(child_modelss,this);
        recyclerView.setAdapter(sub_cat_adapter);
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
