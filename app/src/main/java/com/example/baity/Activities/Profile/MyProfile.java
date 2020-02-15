package com.example.baity.Activities.Profile;

import androidx.appcompat.widget.AppCompatImageView;
import android.util.Base64;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;

import com.example.baity.Activities.BaseActivity;
import com.example.baity.Activities.Profile_edit.Edit_myProfile;
import com.example.baity.Activities.Setting;
import com.example.baity.Designs.MyButtonBold;
import com.example.baity.Designs.MyTextViewBold;
import com.example.baity.Model.Get_profile_response;
import com.example.baity.R;
import com.example.baity.Utils.Preferences;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyProfile extends BaseActivity implements profileInterface {
    Myprofile_presenter myprofile_presenter;
    MyTextViewBold userName,email,mobilePhone;
    MyButtonBold btnEdit;
    AppCompatImageView btnBack;
    String token;
    Preferences preferences;
    String profileUsername,profileEmail,profilePhone,profileImage;
    CircleImageView getProfileImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        myprofile_presenter = new Myprofile_presenter(this,this);

        userName = findViewById(R.id.profileUserName);
        email = findViewById(R.id.profileEmail);
        mobilePhone = findViewById(R.id.profilePhone);
        btnEdit = findViewById(R.id.btnEdit);
        btnBack = findViewById(R.id.btnBack);
        getProfileImage = findViewById(R.id.get_profile_image);

        token = preferences.GetToken(this);
        myprofile_presenter.GetProfile(token);


        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Edit_myProfile.class);
                startActivity(intent);
                finish();
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
    public static Bitmap decodeBase64(String input)
    {
        byte[] decodedBytes = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }

    @Override
    public void sendData(Get_profile_response get_profile_response) {
        profileUsername = get_profile_response.getUsername();
        profileEmail = get_profile_response.getEmail();
        profilePhone = get_profile_response.getPhone();
        profileImage = get_profile_response.getImage();

        preferences.SaveUname(this,profileUsername);
        preferences.SaveUmail(this,profileEmail);
        preferences.SaveUmobile(this,profilePhone);
        preferences.SaveUimage(this,profileImage);

        userName.setText(profileUsername);
        email.setText(profileEmail);
        mobilePhone.setText(profilePhone);

        if (profileImage.equals("")){
            getProfileImage.setImageResource(R.drawable.backg);
        }
        else{
            Bitmap decodeTxt=decodeBase64(profileImage);
            getProfileImage.setImageBitmap(decodeTxt);
        }
    }
}
