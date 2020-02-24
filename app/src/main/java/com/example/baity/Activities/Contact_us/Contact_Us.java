package com.example.baity.Activities.Contact_us;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.widget.ImageViewCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.baity.Activities.BaseActivity;
import com.example.baity.Designs.MyButtonBold;
import com.example.baity.Designs.MyEditTextNormal;
import com.example.baity.Designs.MyTextViewBold;
import com.example.baity.Model.Contact_us_response;
import com.example.baity.R;

import java.net.URLEncoder;

public class Contact_Us extends BaseActivity implements Contact_us_interface {
    Contact_us_presenter contact_us_presenter;
    AppCompatImageView backBtn;
    MyTextViewBold phonenumber,contactMail;
    String phonenum,website,facebookUrl,twitterUrl,WahtsupNumber,suggestionText;
    AppCompatImageView face,twitter,whatsapp;
    MyEditTextNormal suggestion;
    MyButtonBold Add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact__us);

        contact_us_presenter = new Contact_us_presenter(this,this);
        contact_us_presenter.GetContactUs();

        backBtn = findViewById(R.id.btnBack);
        phonenumber = findViewById(R.id.phonenm);
        contactMail = findViewById(R.id.contactmail);
        face = findViewById(R.id.facebook_imagee);
        twitter = findViewById(R.id.twitter_imagee);
        whatsapp = findViewById(R.id.whats_imagee);
        suggestion = findViewById(R.id.suggestion);
        Add = findViewById(R.id.btn_Add_sug);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onBackPressed();
               finish();
            }
        });

        face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(facebookUrl));
                startActivity(intent);
            }
        });

        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(twitterUrl));
                startActivity(intent);
            }
        });

        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWhatsApp(WahtsupNumber,"");
            }
        });

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suggestionText = suggestion.getText().toString();
                if (suggestionText.isEmpty()){
                }
                else {
                    contact_us_presenter.SaveSugg(suggestionText);
                }
            }
        });
    }

    @Override
    public void sendContacts(Contact_us_response contact_us_response) {
        phonenum = contact_us_response.getPhonenumber();
        website = contact_us_response.getWebsite();
        facebookUrl = contact_us_response.getFacebookUrl();
        twitterUrl = contact_us_response.getTwitterUrl();
        WahtsupNumber = contact_us_response.getWahtsupNumber();

        phonenumber.setText(phonenum);
        contactMail.setText(website);
    }

    private void openWhatsApp(String numero,String mensaje){

        try{
            PackageManager packageManager = getApplicationContext().getPackageManager();
            Intent i = new Intent(Intent.ACTION_VIEW);
            String url = "https://api.whatsapp.com/send?phone="+ numero +"&text=" + URLEncoder.encode(mensaje, "UTF-8");
            i.setPackage("com.whatsapp");
            i.setData(Uri.parse(url));
            if (i.resolveActivity(packageManager) != null) {
                startActivity(i);
            }else {

            }
        } catch(Exception e) {
            Log.e("ERROR WHATSAPP",e.toString());
        }

    }
}
