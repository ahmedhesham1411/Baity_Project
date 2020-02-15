package com.example.baity.Utils;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

import com.example.baity.Designs.MyButtonBold;
import com.example.baity.Designs.MyTextViewBold;
import com.example.baity.R;

import org.json.JSONObject;

import java.util.Objects;

import retrofit2.HttpException;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class Constant {
    public static AlertDialog alertDialog1;
    public static String BASEURL = "http://baitykw.com/";


    public static void FavouriteSuccess(String errorMsg,Context context) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dialog_success, null, false);
        final AlertDialog.Builder builder = new AlertDialog.Builder(context,R.style.ErrorDialog);
        builder.setView(view);
        alertDialog1 = builder.create();
        alertDialog1.show();
        MyTextViewBold error = view.findViewById(R.id.error_message);
        error.setText(errorMsg);
        MyButtonBold ok;
        ok = view.findViewById(R.id.btn_error_ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog1.dismiss();
            }
        });
    }

    public static void showErrorDialog(String errorMsg,Context context) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.error_dialog, null, false);
        final AlertDialog.Builder builder = new AlertDialog.Builder(context,R.style.ErrorDialog);
        builder.setView(view);
        alertDialog1 = builder.create();
        alertDialog1.show();
        MyTextViewBold error = view.findViewById(R.id.error_message);
        error.setText(errorMsg);
        MyButtonBold ok;
        ok = view.findViewById(R.id.btn_error_ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog1.dismiss();
            }
        });
    }

    public static void handleErrors(Throwable throwable, Context context){
        try {
            JSONObject jsonObject = new JSONObject(Objects.requireNonNull(Objects.requireNonNull(((HttpException) throwable).response()).errorBody()).string());
            String response = jsonObject.getString("Message");
            switch (response) {

                //Register
                case "3":
                    showErrorDialog("Server is too busy",context);
                    Log.d(TAG, "handleErrors: user id is null");
                    break;
                case "1":
                    showErrorDialog("Not Valid Model",context);
                    Log.d(TAG, "handleErrors: password is null");
                    break;
                case "4":
                    showErrorDialog("Password lenght is small",context);
                    Log.d(TAG, "handleErrors: user id incorrect");
                    break;
                case "6":
                    showErrorDialog("password and confirm password doesn't match",context);
                    Log.d(TAG, "handleErrors: can't handle or parse your model to token say to user the server is busy or try login again or some thing like that");
                    break;
                case "2":
                    showErrorDialog("User already exists",context);
                    Log.d(TAG, "handleErrors: password incorrect");
                    break;

                    //Login
                case "11":
                    showErrorDialog("Model is not valid",context);
                    Log.d(TAG, "handleErrors: the old password incorrect");
                    break;
                case "12":
                    showErrorDialog("User name is empty",context);
                    Log.d(TAG, "handleErrors: the model you entered is incorrect as format");
                    break;
                case "13":
                    showErrorDialog("Password is empty",context);
                    Log.d(TAG, "handleErrors: token is null");
                    break;
                case "14":
                    showErrorDialog("User not exists",context);
                    Log.d(TAG, "handleErrors: your id code is null");
                    break;
                case "15":
                    showErrorDialog("Can't create token",context);
                    Log.d(TAG, "handleErrors: Can't create token");
                    break;
                case "16":
                    showErrorDialog("Password is incorrect",context);
                    Log.d(TAG, "handleErrors: Password is incorrect");
                    break;

                    //Change_password
                case "82":
                    showErrorDialog("Incorrect password",context);
                    Log.d(TAG, "handleErrors: Incorrect password");
                    break;
                case "70":
                    showErrorDialog("Can't generate token",context);
                    Log.d(TAG, "handleErrors: Can't generate token");
                    break;
                case "71":
                    showErrorDialog("Model is not valid",context);
                    Log.d(TAG, "handleErrors: Model is not valid");
                    break;

                    //Forget_password
                case "23":
                    showErrorDialog("Please check your connection",context);
                    Log.d(TAG, "handleErrors: Please check your connection");
                    break;
                case "78":
                    showErrorDialog("Can't reset password",context);
                    Log.d(TAG, "handleErrors: Can't reset password");
                    break;

                    //profile
                case "72":
                    showErrorDialog("Can't generate token",context);
                    Log.d(TAG, "handleErrors: Can't generate this token");
                    break;
                case "770":
                    showErrorDialog("User can't be found",context);
                    Log.d(TAG, "handleErrors: User can't be found");
                    break;

                default:
                    showErrorDialog("Something is wrong",context);
                    Log.d(TAG, "handleErrors: "+throwable.getMessage());
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
