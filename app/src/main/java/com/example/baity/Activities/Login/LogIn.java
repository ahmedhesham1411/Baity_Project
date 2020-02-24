package com.example.baity.Activities.Login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.baity.Activities.BaseActivity;
import com.example.baity.Activities.Forget_password.Forget_password;
import com.example.baity.Activities.Language;
import com.example.baity.Activities.Register.SignUp;
import com.example.baity.Designs.MyButtonBold;
import com.example.baity.Designs.MyEditTextBold;
import com.example.baity.Designs.MyTextViewBold;
import com.example.baity.R;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.Login;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import static android.graphics.Color.RED;
import static com.basgeekball.awesomevalidation.ValidationStyle.COLORATION;

public class LogIn extends BaseActivity {
    LoginPresenter loginPresenter;
    MyEditTextBold editUserName,editPassword;
    MyTextViewBold openSignUpPage,forgetPassword;
    AppCompatImageView btnBack,facebook_image,Google_image,twitter_image;
    MyButtonBold btn_login;
    AwesomeValidation awesomeValidation;
    //facebook
    LoginButton loginButton;
    CallbackManager callbackManager;
    String first_name,last_name,email,id,image_url;
    //google
    GoogleSignInClient mGoogleSignInClient;
    int RC_SIGN_IN = 0;
    SignInButton signInButton;
    Boolean faceClicked,twitterClicked = false;
    TwitterLoginButton twitterLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Twitter.initialize(this);
        setContentView(R.layout.activity_log_in);

        loginPresenter = new LoginPresenter(this);
        LoginManager.getInstance().logOut();

        awesomeValidation = new AwesomeValidation(COLORATION);
        awesomeValidation.setColor(RED);

        addValidationToViews();
        Google_image = findViewById(R.id.Google_image);
        facebook_image = findViewById(R.id.facebook_image);
        loginButton = findViewById(R.id.faceBtn);
        editUserName = findViewById(R.id.loginEmail);
        editPassword = findViewById(R.id.loginPassword);
        openSignUpPage = findViewById(R.id.OpenSignUpPage);
        btnBack = findViewById(R.id.btnBack);
        forgetPassword = findViewById(R.id.forget_password);
        btn_login = findViewById(R.id.btn_login);
        twitterLoginButton = (TwitterLoginButton) findViewById(R.id.login_button);
        twitter_image = findViewById(R.id.twitter_image);
        Google_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        facebook_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginButton.performClick();
                faceClicked = true;
                twitterClicked = false;
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               submitForm();
            }
        });

        openSignUpPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogIn.this, SignUp.class);
                startActivity(intent);
                finish();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogIn.this, Language.class);
                startActivity(intent);
                finish();
            }
        });

        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogIn.this, Forget_password.class);
                startActivity(intent);
                finish();
            }
        });


        callbackManager = CallbackManager.Factory.create();
        loginButton.setReadPermissions(Arrays.asList("email","public_profile"));

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });


        signInButton = findViewById(R.id.sign_in_button);


        twitterLoginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
                loginMethod(session);
            }

            @Override
            public void failure(TwitterException exception) {
                Toast.makeText(getApplicationContext(),"Login fail",Toast.LENGTH_LONG).show();
            }
        });

        twitter_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                faceClicked = false;
                twitterClicked = true;
                twitterLoginButton.performClick();
            }
        });
    }

    public void loginMethod(TwitterSession twitterSession){

        String userName=twitterSession.getUserName();
        String mail = "@Twitter.com";
        loginPresenter.Twitterlogin(userName+mail,userName+mail);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
        else if (faceClicked == true){
            callbackManager.onActivityResult(requestCode,resultCode,data);
        }
        else if (twitterClicked == true){
            twitterLoginButton.onActivityResult(requestCode, resultCode, data);
        }
    }

    AccessTokenTracker tokenTracker = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            if (currentAccessToken==null){
                /*txtEmail.setText("");
                txtUsername.setText("");
                imageView.setImageResource(0);
                Toast.makeText(MainActivity.this, "ssssssss", Toast.LENGTH_SHORT).show();*/
            }
            else {
                getUserProfile(currentAccessToken);
            }
        }
    };

    private void getUserProfile(AccessToken currentAccessToken){

        GraphRequest request = GraphRequest.newMeRequest(currentAccessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    first_name = object.getString("first_name");
                    last_name = object.getString("last_name");
                    email = object.getString("email");
                    id = object.getString("id");
                    image_url = "https://graph.facebook.com/" + id + "/picture?type=normal";

                    //editUserName.setText(email);
                    loginPresenter.Facebooklogin(email,email);
                    LoginManager.getInstance().logOut();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        Bundle parameters = new Bundle();
        parameters.putString("fields","first_name,last_name,email,id");
        request.setParameters(parameters);
        request.executeAsync();
    }


    private void addValidationToViews() {

        awesomeValidation.addValidation(this, R.id.loginEmail, RegexTemplate.NOT_EMPTY, R.string.nameerror);

        awesomeValidation.addValidation(this, R.id.loginEmail, Patterns.EMAIL_ADDRESS, R.string.emailerror);

        String regexPassword = ".{6,}";
        awesomeValidation.addValidation(this, R.id.loginPassword, regexPassword, R.string.passworderror);

    }

    private void submitForm() {
        if (awesomeValidation.validate()) {
                loginPresenter.login(editUserName.getText().toString(),
                        editPassword.getText().toString());
        }
    }


    @Override
    protected void onStart() {
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null) {
        }
        super.onStart();
    }
    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(LogIn.this, "Successfully signed out", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            // Signed in successfully, show authenticated UI.
            //startActivity(new Intent(MainActivity.this, Main2Activity.class));

            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .build();

            // Build a GoogleSignInClient with the options specified by gso.
            mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(LogIn.this);
            if (acct != null) {
                String personName = acct.getDisplayName();
                String personGivenName = acct.getGivenName();
                String personFamilyName = acct.getFamilyName();
                String personEmail = acct.getEmail();
                String personId = acct.getId();
                Uri personPhoto = acct.getPhotoUrl();

                //editUserName.setText(personName);
                loginPresenter.Googlelogin(personEmail,personEmail);
                LoginManager.getInstance().logOut();

            }

        } catch (ApiException e) {
            e.printStackTrace();
        }

    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


}
