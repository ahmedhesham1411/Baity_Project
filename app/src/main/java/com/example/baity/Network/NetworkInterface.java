package com.example.baity.Network;

import com.example.baity.Model.About_us_response;
import com.example.baity.Model.AdModel;
import com.example.baity.Model.Change_password_request;
import com.example.baity.Model.Child_model;
import com.example.baity.Model.Child_resault_model;
import com.example.baity.Model.Contact_us_response;
import com.example.baity.Model.EditProfile_request;
import com.example.baity.Model.EditProfile_response;
import com.example.baity.Model.Facebook_request;
import com.example.baity.Model.Facebook_response;
import com.example.baity.Model.Fav_message;
import com.example.baity.Model.Forget_password_request;
import com.example.baity.Model.Forget_password_response;
import com.example.baity.Model.Get_profile_request;
import com.example.baity.Model.Get_profile_response;
import com.example.baity.Model.Google_request;
import com.example.baity.Model.Google_response;
import com.example.baity.Model.Home_model;
import com.example.baity.Model.Home_model_response;
import com.example.baity.Model.Home_slider_model;
import com.example.baity.Model.LoginRequest;
import com.example.baity.Model.LoginResponse;
import com.example.baity.Model.MyFavouriteModel;
import com.example.baity.Model.Prayer;
import com.example.baity.Model.Rate_response;
import com.example.baity.Model.Rating_model;
import com.example.baity.Model.Review_full_model;
import com.example.baity.Model.Search_model;
import com.example.baity.Model.Sub_parent_model;
import com.example.baity.Model.Sug_response;
import com.example.baity.Model.Suggestion_request;
import com.example.baity.Model.Ver_change_pass_request;
import com.example.baity.Model.Ver_change_pass_response;
import com.example.baity.Model.Verify_request;
import com.example.baity.Model.Verify_response;
import com.example.baity.Model.change_password_response;
import com.example.baity.Model.RegisterationResponse;
import com.example.baity.Model.RegistrationRequest;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface NetworkInterface {
    @POST("api/Account/register")
    Observable<RegisterationResponse> Register(@Body RegistrationRequest registrationRequest);

    @POST("api/Account/ChangePassword")
    Observable<change_password_response> ChangePassword(@Body Change_password_request change_password_request);

    @POST("api/Account/login")
    Observable<LoginResponse> Login(@Body LoginRequest loginRequest);

    @POST("api/Account/ForgotPassword")
    Observable<Forget_password_response> ForgetPassword(@Body Forget_password_request forget_password_request);

    @POST("api/Account/Verfiy")
    Observable<Verify_response> Verify(@Body Verify_request verify_request);

    @POST("api/Account/Verifybyemail")
    Observable<Ver_change_pass_response> Verify_change_password(@Body Ver_change_pass_request ver_change_pass_request);

    @GET("api/Account/profile")
    Observable<Get_profile_response> GetProfileData();

    @POST("api/Account/modifyprofile")
    Observable<EditProfile_response> EditProfile(@Body EditProfile_request editProfile_request);

    @GET("api/Main_Category")
    Observable<List<Home_model>> GetHomeData();

    @GET("api/sub_category")
    Observable<List<Sub_parent_model>> GetSlidersData(@Query("id") int id);

    @GET("myads")
    Observable<List<String>> GetMarqee();

    @GET("child_Cats")
    Observable<List<Child_model>> GetChlid(@Query("id") int id);

    @GET("childcat_details")
    Observable<Child_resault_model> GetChlidResault(@Query("id") int id);

    @GET("randomads")
    Observable<AdModel> GetAd();

    @GET("companyreview")
    Observable<Review_full_model> GetReviews(@Query("id") int id);

    @POST("review")
    Observable<Rate_response> SendRate(@Body Rating_model rating_model);

    @GET("listofsearch")
    Observable<List<Search_model>> GetListOfSearch();

    @GET("api/categoryads")
    Observable<List<Home_slider_model>> GetAdsSlider();

    @POST("favourite")
    Observable<Fav_message> SaveFavourite(@Query("id") int id);

    @GET("myfavourite")
    Observable<List<MyFavouriteModel>> GetFavourite();

    @POST("api/Account/TokenFromFacebook")
    Observable<Facebook_response> FacebookLoginn(@Body Facebook_request facebook_request);

    @POST("api/Account/TokenFromFacebook")
    Observable<Google_response> GoogleLoginn(@Body Google_request google_request);

    @GET("AboutUs")
    Observable<About_us_response> GetAboutDes();

    @GET("contactus")
    Observable<Contact_us_response> GetContactUs();

    @POST("suggest")
    Observable<Sug_response> SaveSug(@Body Suggestion_request suggestion_request);

    @GET("v1/calendar")
    Observable<Prayer> getTiming(@Query("latitude") Double latitude,
                                 @Query("longitude") Double longitude,
                                 @Query("method") Integer method,
                                 @Query("month") Integer month,
                                 @Query("year") Integer year);
}
