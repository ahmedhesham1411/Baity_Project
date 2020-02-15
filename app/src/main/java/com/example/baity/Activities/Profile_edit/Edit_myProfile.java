package com.example.baity.Activities.Profile_edit;

import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Patterns;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.baity.Activities.BaseActivity;
import com.example.baity.Activities.Profile.MyProfile;
import com.example.baity.Designs.MyButtonBold;
import com.example.baity.Designs.MyEditTextBold;
import com.example.baity.Designs.MyEditTextNormal;
import com.example.baity.R;
import com.example.baity.Utils.Preferences;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.graphics.Color.RED;
import static com.basgeekball.awesomevalidation.ValidationStyle.COLORATION;

public class Edit_myProfile extends BaseActivity {
    Edit_profile_presenter edit_profile_presenter;
    FrameLayout uploadImage;
    Uri imageUri;
    CircleImageView profileImage;
    AppCompatImageView btnBack;
    MyButtonBold btnSave;
    Preferences preferences;
    String username,email,phone,image;
    BottomSheetDialog bottomSheetDialog;
    MyEditTextNormal edtname,edtmail,edtphone;
    String myImage;
    AwesomeValidation awesomeValidation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_myprofile);

        edit_profile_presenter = new Edit_profile_presenter(this);
        awesomeValidation = new AwesomeValidation(COLORATION);
        awesomeValidation.setColor(RED);
        addValidationToViews();
        btnBack = findViewById(R.id.btnBack);
        uploadImage = findViewById(R.id.uploadImage);
        profileImage = findViewById(R.id.profile_image);
        btnSave = findViewById(R.id.btnSave);
        edtname = findViewById(R.id.edtUname);
        edtmail = findViewById(R.id.edtUemail);
        edtphone = findViewById(R.id.edtUphone);

        bottomSheetDialog = new BottomSheetDialog(this,R.style.SheetDialog);
        final View bottomSheetDialogView = getLayoutInflater().inflate(R.layout.choose_picture, null);
        bottomSheetDialog.setContentView(bottomSheetDialogView);

        View btnGallery = bottomSheetDialogView.findViewById(R.id.btnGallery);
        View btnCamera = bottomSheetDialogView.findViewById(R.id.btnCamera);
        View btnCancel = bottomSheetDialogView.findViewById(R.id.btnCancel);

        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(gallery, "Select Picture"), 1);
                bottomSheetDialog.dismiss();
            }
        });
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,0);
                bottomSheetDialog.dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });

        username = preferences.GetUname(this);
        email = preferences.GetUmail(this);
        phone = preferences.GetUmobile(this);
        image = preferences.GetUimage(this);

        edtname.setText(username);
        edtmail.setText(email);
        edtphone.setText(phone);

        Bitmap decodeTxt=decodeBase64(image);
        profileImage.setImageBitmap(decodeTxt);

        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.show();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitForm();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch(requestCode) {
            case 0:
                if(resultCode == RESULT_OK){
                    Bitmap bitmap = (Bitmap)imageReturnedIntent.getExtras().get("data");
                    profileImage.setImageBitmap(bitmap);

                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                    byte[] imageBytes = byteArrayOutputStream.toByteArray();
                    String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                    Preferences.saveImageToPreference(getApplicationContext(),imageString);
                }

                break;
            case 1:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    profileImage.setImageURI(selectedImage);

                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                        byte[] imageBytes = byteArrayOutputStream.toByteArray();
                        String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                        Preferences.saveImageToPreference(getApplicationContext(),imageString);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    public static Bitmap decodeBase64(String input)
    {
        byte[] decodedBytes = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }
    private void submitForm() {
        if (awesomeValidation.validate()) {

            myImage = Preferences.getImageToPreference(this);
            edit_profile_presenter.EditProfile(myImage,
                    edtphone.getText().toString(),
                    edtname.getText().toString(),
                    edtmail.getText().toString());
        }
    }

    private void addValidationToViews() {

        awesomeValidation.addValidation(this, R.id.edtUname, RegexTemplate.NOT_EMPTY, R.string.nameerror);
        awesomeValidation.addValidation(this, R.id.edtUemail, Patterns.EMAIL_ADDRESS, R.string.emailerror);
        awesomeValidation.addValidation(this, R.id.edtUphone, "^[+]?[0-9]{10,13}$", R.string.mobileerror);

    }

}
