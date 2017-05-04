package io.github.viv2d2.findride;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;


public class LoginActivity extends AppCompatActivity {
    LoginButton loginButton;
    TextView textView;
    CallbackManager callbackManager;
    Button toNext;
    public static int APP_REQUEST_CODE = 99;
    ArrayList<String> JHEDS;
    boolean fbIn;
    boolean JHEDIn;
    TextView showstuff;
    SharedPreferences.Editor edit;

    io.github.viv2d2.findride.ProfilePictureView profileImage;

    EditText jhedInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //getActionBar().hide();
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
        //setContentView(R.layout.activity_login);

        //checking if already logged in
        final SharedPreferences login = getDefaultSharedPreferences(getApplicationContext());
        //final SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);


        fbIn = login.getBoolean("Facebook",false);
        JHEDIn= login.getBoolean("JHED",false);
        //could do some stuff w/ auto populating the field?


        //must use this class to make sure it is circular
        //
        //profileImage = (io.github.viv2d2.findride.ProfilePictureView) findViewById(R.id.profilePicture);
        //profileImage.setPresetSize(profileImage.SMALL);

        loginButton = (LoginButton) findViewById(R.id.login_button);
        final SharedPreferences.Editor edit = login.edit();

        jhedInput = (EditText) findViewById(R.id.jhedInput);
        jhedInput.getBackground().mutate().setColorFilter(getResources().getColor(R.color.com_facebook_blue), PorterDuff.Mode.SRC_ATOP);

        if(login.getBoolean("FBLogout",true)) {
            LoginManager.getInstance().logOut();

            edit.putBoolean("FBLogout",false);
            edit.commit();


        }
        JHEDS = new ArrayList<>();
        // real JHEDs
        JHEDS.add("wmattes2"); JHEDS.add("vtsai5"); JHEDS.add("szappon1"); JHEDS.add("rkinney4");
        // fun JHEDs
        JHEDS.add("adent42"); JHEDS.add("rwease1"); JHEDS.add("hsolo1");

        //textView = (TextView) findViewById(R.id.textView2);
        loginButton.setReadPermissions("public_profile");
        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            private ProfileTracker mProfileTracker;


            @Override
            public void onSuccess(LoginResult loginResult) {
                fbIn = true;
                edit.putBoolean("Facebook",true);
                if(Profile.getCurrentProfile() == null) {
                mProfileTracker = new ProfileTracker() {
                    @Override
                    protected void onCurrentProfileChanged(Profile profile, Profile profile2) {
                        // profile2 is the new profile
                        Log.v("facebook - profile", profile2.getFirstName());
                        mProfileTracker.stopTracking();
                    }
                };
                // no need to call startTracking() on mProfileTracker
                // because it is called by its constructor, internally.

            }
            else {
                Profile profile = Profile.getCurrentProfile();
                    Log.v("facebook - profile", profile.getFirstName());

                }
                //mProfileTracker = new ProfileTra
                //mProfileTracker.startTracking();


                //textView.setText("Login Sucess \n" +
                //loginResult.getAccessToken().getUserId() +
                  //      "\n" + loginResult.getAccessToken().getToken()
                //);

                //boolean that checks that facebook login is true


                //userID to trigger messenger
                //need to make sure this is consistent in terms how storing
                //can both link to profile as well as generate image with id
                //edit.putString("userID","" + loginResult.getAccessToken().getUserId());

                //Profile userprof = Profile.getCurrentProfile();


            }

            @Override
            public void onCancel() {
                //textView.setText("Login Failed");
                fbIn = false;
            }

            @Override
            public void onError(FacebookException error) {
                fbIn = false;
            }
        });

        Button toNext = (Button)findViewById(R.id.nextScreen);

        toNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //take userid, can also grab other information from this
                //profileImage.setProfileId(myprof.getId());
                if(!(JHEDIn))
                    JHEDIn=JHEDS.contains(jhedInput.getText().toString());

                if (JHEDIn){
                    //SharedPreferences.Editor edit = login.edit();
                    edit.putBoolean("JHED",true);
                    edit.commit();
                    if (fbIn) {
                        Profile myprof = Profile.getCurrentProfile();
                        // save for settings
                        // NOT FINISHED YET


                        //Id = myprof.getId();
                        //Here is how you put first name on facebook
                        String name = myprof.getFirstName();
                        edit.putString("Facebook_Name",name);
                        edit.putString("fbID",myprof.getId());
                        // TEMP IF/ELSE
                        /*
                        if (jhedInput.getText().toString().equals("szappon1")) {
                            settings_edit.putString("Facebook_ID","Sarah");
                        } else if (jhedInput.getText().toString().equals("wmattes2")) {
                            settings_edit.putString("Facebook_ID","Will");
                        } else if (jhedInput.getText().toString().equals("vtsai5")) {
                            settings_edit.putString("Facebook_ID","Vivian");
                        } else if (jhedInput.getText().toString().equals("rkinney4")) {
                            settings_edit.putString("Facebook_ID","Rachel");
                        } else if (jhedInput.getText().toString().equals("rwease1")) {
                            settings_edit.putString("Facebook_ID","Ron");
                        } else if (jhedInput.getText().toString().equals("adent42")) {
                            settings_edit.putString("Facebook_ID","Arthur");
                        } else {
                            settings_edit.putString("Facebook_ID","Han");
                        }
                        */
                        // Put in JHED
                        edit.putString("JHED_ID", jhedInput.getText().toString());
                        edit.commit();

                        // Start MainActivity
                        Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
                        LoginActivity.this.startActivity(mainIntent);
                    }
                    else {
                    }
                }
                else {
                    jhedInput.setText("");
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // if you don't add following block,
        // your registered `FacebookCallback` won't be called
        if (callbackManager.onActivityResult(requestCode, resultCode, data)) {
            return;
        }


    }

}
