package com.example.baity.Activities.Register;

import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.baity.Activities.BaseActivity;
import com.example.baity.Activities.Login.LogIn;
import com.example.baity.Designs.MyButtonBold;
import com.example.baity.Designs.MyEditTextNormal;
import com.example.baity.R;
import com.example.baity.Utils.Preferences;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.graphics.Color.RED;
import static com.basgeekball.awesomevalidation.ValidationStyle.COLORATION;

public class SignUp extends BaseActivity implements AdapterView.OnItemSelectedListener {
    private RegisterPresenter registerPresenter;
    AppCompatImageView btnBack;
    MyEditTextNormal firstName,lastName,email,phone,password,confirmPassword;
    FrameLayout uploadImage;
    CircleImageView profileImage;
    CheckBox checkBox;
    private AwesomeValidation awesomeValidation;
    MyButtonBold signUp;
    BottomSheetDialog bottomSheetDialog;
    String myImage,Spinnertext;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        registerPresenter =new RegisterPresenter(this);

        awesomeValidation = new AwesomeValidation(COLORATION);
        awesomeValidation.setColor(RED);

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
                bottomSheetDialog.show();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, LogIn.class);
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

        spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.numbers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    private void addValidationToViews() {

        awesomeValidation.addValidation(this, R.id.editFirstName, RegexTemplate.NOT_EMPTY, R.string.nameerror);
        awesomeValidation.addValidation(this, R.id.editLastName, RegexTemplate.NOT_EMPTY, R.string.nameerror);

        awesomeValidation.addValidation(this, R.id.editEmail, Patterns.EMAIL_ADDRESS, R.string.emailerror);

        String regexPassword = ".{6,}";
        awesomeValidation.addValidation(this, R.id.editPassword, regexPassword, R.string.passworderror);
        awesomeValidation.addValidation(this, R.id.editConfirmPassword, regexPassword, R.string.passworderror);

        if (password != confirmPassword){
            awesomeValidation.addValidation(this, R.id.editConfirmPassword,R.id.editPassword, R.string.match_password);
        }
        awesomeValidation.addValidation(this, R.id.editPhone, "^[+]?[0-9]{10,13}$", R.string.mobileerror);

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

    private void submitForm() {
        if (awesomeValidation.validate()) {

            if (!checkBox.isChecked()) {
                Toast.makeText(this, "You must agree to terms and conditions", Toast.LENGTH_SHORT).show();
            }
            else {
                myImage = Preferences.getImageToPreference(this);
                registerPresenter.register(firstName.getText().toString(),
                        lastName.getText().toString(),
                        Spinnertext+phone.getText().toString(),
                        email.getText().toString(),
                        password.getText().toString(),
                        confirmPassword.getText().toString(),
                        myImage);
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinnertext = parent.getItemAtPosition(position).toString();
        //Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
        setSpinText(spinner,Spinnertext);
        ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void setSpinText(Spinner spin, String text)
    {
        for(int i= 0; i < spin.getAdapter().getCount(); i++)
        {
            if(spin.getAdapter().getItem(i).toString().contains(text))
            {
                spin.setSelection(i);
            }
        }

    }
}


