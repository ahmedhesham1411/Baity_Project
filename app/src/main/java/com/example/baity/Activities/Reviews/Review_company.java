package com.example.baity.Activities.Reviews;

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
import android.widget.RatingBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.baity.Activities.Child_resault.Sub_category_result;
import com.example.baity.Activities.Home.Home;
import com.example.baity.Adapter.Review_adapter;
import com.example.baity.Designs.MyButtonBold;
import com.example.baity.Designs.MyTextViewNormal;
import com.example.baity.Model.AdModel;
import com.example.baity.Model.Review_full_model;
import com.example.baity.Model.Review_model;
import com.example.baity.Model.Review_user_item;
import com.example.baity.R;
import com.example.baity.Utils.Preferences;

import java.util.List;

public class Review_company extends AppCompatActivity implements Review_interface{
    Review_presenter review_presenter;
    RecyclerView recyclerView;
    AppCompatImageView closeAdBtn,btnBack,companyImage;
    RatingBar ratingBar;
    MyButtonBold btnRate,btnNotNow;
    Preferences preferences;
    String adImage;
    AppCompatImageView image_ad;
    int company_id;
    MyTextViewNormal desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_company);

        review_presenter = new Review_presenter(this,this);
        review_presenter.GetAd();
        showAdDialog();

        companyImage = findViewById(R.id.company_image);
        String image = preferences.GetMainCaImage(this);
        Glide.with(this).load(image).into(companyImage);
        desc = findViewById(R.id.review_desc);
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRatingDialog();
            }
        });
    }


    private void showRatingDialog() {
        ViewGroup viewGroup = findViewById(android.R.id.content);
        final View dialogView = LayoutInflater.from(this).inflate(R.layout.rating_dialog, viewGroup, false);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        btnRate = dialogView.findViewById(R.id.btnRate);
        btnNotNow = dialogView.findViewById(R.id.btnNoRate);
        ratingBar = dialogView.findViewById(R.id.rate);

        btnRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                //String rating = "Rating is :" + ratingBar.getRating();
                float rate = ratingBar.getRating();
                company_id = preferences.Get_company_id(v.getContext());
                review_presenter.SendRate(company_id,rate);
                //Toast.makeText(getApplicationContext(), rating, Toast.LENGTH_LONG).show();
               /* Intent intent = new Intent(getApplicationContext(), Sub_category_result.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);*/
               onBackPressed();
               finish();
            }
        });

        btnNotNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                /*Intent intent = new Intent(getApplicationContext(), Sub_category_result.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);*/
                onBackPressed();
                finish();
            }
        });
    }
    private void showAdDialog() {
        ViewGroup viewGroup = findViewById(android.R.id.content);
        Rect displayRectangle = new Rect();
        Window window = Review_company.this.getWindow();
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

        closeAdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                company_id = preferences.Get_company_id(v.getContext());
                review_presenter.GetReviews(company_id);
            }
        });
    }


    public static Bitmap decodeBase64(String input)
    {
        byte[] decodedBytes = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }


    @Override
    public void sendAd(AdModel adModel) {
        AdModel adModel1 = adModel;
        adImage = adModel1.getImagepath();
        Bitmap decodeTxt=decodeBase64(adImage);
        image_ad.setImageBitmap(decodeTxt);
    }

    @Override
    public void sendReviews(Review_full_model review_full_model) {
        Review_full_model review_full_model1 = review_full_model;
        String des = review_full_model.getDescription();
        desc.setText(des);
        List<Review_user_item> review_user_items = review_full_model1.getUserreviewVMs();
        recyclerView = findViewById(R.id.reviews_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        Review_adapter review_adapter = new Review_adapter(review_user_items);
        recyclerView.setAdapter(review_adapter);
    }

}
