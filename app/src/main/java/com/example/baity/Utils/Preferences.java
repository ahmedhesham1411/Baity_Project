package com.example.baity.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.baity.Model.Sub_slider_model;

import java.util.ArrayList;
import java.util.List;

public class Preferences {

    public static void saveImageToPreference(Context context, String image){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("image", image);
        editor.commit();
    }

    public static String getImageToPreference(Context context){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        return pref.getString("image","default");
    }

    public static void saveToken(Context context, String Token){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("Token", Token);
        editor.commit();
    }

    public static String GetToken(Context context){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        return pref.getString("Token","default");
    }

    public static void SaveForgetPasswordEmail(Context context, String Email){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("Email", Email);
        editor.commit();
    }

    public static String GetForgetPasswordEmail(Context context){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        return pref.getString("Email","default email");
    }

    public static void SaveVerificationCode(Context context, String Ver_code){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("Code", Ver_code);
        editor.commit();
    }

    public static String GetCode(Context context){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        return pref.getString("Code","default Code");
    }

    public static void SaveUname(Context context, String Uname){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("Uname", Uname);
        editor.commit();
    }

    public static String GetUname(Context context){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        return pref.getString("Uname","default name");
    }

    public static void SaveUmail(Context context, String Pass){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("Umail", Pass);
        editor.commit();
    }

    public static String GetUmail(Context context){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        return pref.getString("Umail","default pass");
    }

    public static void SaveUmobile(Context context, String mobile){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("Umobile", mobile);
        editor.commit();
    }

    public static String GetUmobile(Context context){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        return pref.getString("Umobile","default mobile");
    }

    public static void SaveUimage(Context context, String image){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("Uimage", image);
        editor.commit();
    }

    public static String GetUimage(Context context){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        return pref.getString("Uimage","default mobile");
    }

    public static void SaveMainCat_id(Context context, int id){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("MainCat_id", id);
        editor.commit();
    }

    public static Integer GetMainCaId(Context context){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        return pref.getInt("MainCat_id",1);
    }

    public static void SaveMainCat_name(Context context, String name){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("MainCat_name", name);
        editor.commit();
    }

    public static String GetMainCaName(Context context){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        return pref.getString("MainCat_name","a");
    }

    public static void SaveMainCat_image(Context context, String image){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("MainCat_image", image);
        editor.commit();
    }

    public static String GetMainCaImage(Context context){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        return pref.getString("MainCat_image","");
    }

    public static void Clear(Context context)
    {
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
    }

    public static void Save_image1(Context context, String image){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("image1", image);
        editor.commit();
    }

    public static String Get_image1(Context context){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        return pref.getString("image1","");
    }
    public static void Save_image2(Context context, String image){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("image2", image);
        editor.commit();
    }

    public static String Get_image2(Context context){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        return pref.getString("image2","");
    }

    public static void Save_Sub_name(Context context, String name){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("subnamee", name);
        editor.commit();
    }

    public static String Get_sub_name(Context context){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        return pref.getString("subnamee","");
    }

    public static void Save_Sub_id(Context context, int id){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("subidd", id);
        editor.commit();
    }

    public static int Get_sub_id(Context context){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        return pref.getInt("subidd",0);
    }

    public static void Save_Child_id(Context context, int id){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("childidd", id);
        editor.commit();
    }

    public static int Get_Child_id(Context context){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        return pref.getInt("childidd",0);
    }

    public static void Save_Child_name(Context context, String name){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("childnamee", name);
        editor.commit();
    }

    public static String Get_Child_name(Context context){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        return pref.getString("childnamee","a");
    }

    public static void Save_company_id(Context context, int id){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("companyid", id);
        editor.commit();
    }

    public static int Get_company_id(Context context){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        return pref.getInt("companyid",0);
    }

    public static void Save_listt(Context context,String list){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("sliderimages", list);
        editor.commit();
    }

    public static String Get_listt(Context context){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        return pref.getString("sliderimages","a");
    }

    public static void Save_User_State(Context context,String state){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("Ustate", state);
        editor.commit();
    }

    public static String Get_User_State(Context context){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        return pref.getString("Ustate","");
    }
}
