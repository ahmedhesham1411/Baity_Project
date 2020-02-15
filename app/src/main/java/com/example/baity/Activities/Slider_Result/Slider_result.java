package com.example.baity.Activities.Slider_Result;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.GridLayoutManager;
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
import com.bumptech.glide.Glide;
import com.example.baity.Activities.BaseActivity;
import com.example.baity.Activities.Reviews.Review_company;
import com.example.baity.Activities.Setting;
import com.example.baity.Adapter.ParentAdapter;
import com.example.baity.Designs.MyTextViewBold;
import com.example.baity.Model.AdModel;
import com.example.baity.Model.Sub_parent_model;
import com.example.baity.R;
import com.example.baity.Utils.Preferences;

import java.util.List;

public class Slider_result extends BaseActivity implements Sub_resault_interface{
    Slider_resault_presenter slider_resault_presenter;
    MyTextViewBold textViewDescription;
    AppCompatImageView settingBtn,backBtn,closeAdBtn;
    int id,company_id;
    RecyclerView recyclerView;
    //ArrayList<String> items = new ArrayList<>();
    Preferences preferences;
    String adImage,image;
    AppCompatImageView image_ad,MainImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider_result);

        slider_resault_presenter = new Slider_resault_presenter(this,this);
        slider_resault_presenter.GetAd();
        showAdDialog();
        textViewDescription = findViewById(R.id.slider_nameee);
        settingBtn = findViewById(R.id.settingBtn);
        backBtn = findViewById(R.id.btnBack);
        MainImage = findViewById(R.id.aaaaaaaaaaa);

        String name = preferences.GetMainCaName(this);
        image = preferences.GetMainCaImage(this);
        id = preferences.GetMainCaId(this);

        textViewDescription.setText(name);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });
        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Slider_result.this, Setting.class);
                startActivity(intent);
            }
        });

    }

    private void showAdDialog() {
        ViewGroup viewGroup = findViewById(android.R.id.content);
        Rect displayRectangle = new Rect();
        Window window = Slider_result.this.getWindow();
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
                Glide.with(getApplicationContext()).load(image).into(MainImage);
                alertDialog.dismiss();
                slider_resault_presenter.GetData(id);

            }
        });
    }

    @Override
    public void sendData(List<Sub_parent_model> sub_parent_models) {
        List<Sub_parent_model> sub_parent_models1 = sub_parent_models;
        recyclerView = findViewById(R.id.res);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setHasFixedSize(true);
        RecyclerView.Adapter adapter = new ParentAdapter(sub_parent_models,Slider_result.this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
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
