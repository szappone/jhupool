package io.github.viv2d2.findride;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

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


    private static boolean fbLoggedin;
    private static boolean jhedLoggedin;


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
        final SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);


        fbIn = login.getBoolean("Facebook",false);
        JHEDIn= login.getBoolean("JHED",false);
        //could do some stuff w/ auto populating the field?





        loginButton = (LoginButton) findViewById(R.id.login_button);

        jhedInput = (EditText) findViewById(R.id.jhedInput);
        jhedInput.getBackground().mutate().setColorFilter(getResources().getColor(R.color.com_facebook_blue), PorterDuff.Mode.SRC_ATOP);



        JHEDS = new ArrayList<>();
        // real JHEDs
        JHEDS.add("wmattes2"); JHEDS.add("vtsai5"); JHEDS.add("szappon1"); JHEDS.add("rkinney4");
        // fun JHEDs
        JHEDS.add("adent42"); JHEDS.add("rwease1"); JHEDS.add("hsolo1");

        //textView = (TextView) findViewById(R.id.textView2);
        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //textView.setText("Login Sucess \n" +
                //loginResult.getAccessToken().getUserId() +
                  //      "\n" + loginResult.getAccessToken().getToken()
                //);

                //boolean that checks that facebook login is true
                SharedPreferences.Editor edit = login.edit();
                fbIn = true;
                edit.putBoolean("Facebook",true);
                edit.commit();

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
                if(!(JHEDIn))
                    JHEDIn=JHEDS.contains(jhedInput.getText().toString());

                if (JHEDIn){
                    SharedPreferences.Editor edit = login.edit();
                    edit.putBoolean("JHED",true);
                    edit.commit();
                    if (fbIn) {
                        // save for settings
                        // NOT FINISHED YET
                        SharedPreferences.Editor settings_edit = settings.edit();
                        // how do you get your Facebook name here?
                        // TEMP IF/ELSE
                        if (jhedInput.getText().equals("szappon1")) {
                            settings_edit.putString("Facebook_ID","Sarah");
                        } else if (jhedInput.getText().equals("wmattes2")) {
                            settings_edit.putString("Facebook_ID","Will");
                        } else if (jhedInput.getText().equals("vtsai5")) {
                            settings_edit.putString("Facebook_ID","Vivian");
                        } else if (jhedInput.getText().equals("rkinney4")) {
                            settings_edit.putString("Facebook_ID","Rachel");
                        } else if (jhedInput.getText().equals("rwease1")) {
                            settings_edit.putString("Facebook_ID","Ron");
                        } else if (jhedInput.getText().equals("adent42")) {
                            settings_edit.putString("Facebook_ID","Arthur");
                        } else {
                            settings_edit.putString("Facebook_ID","Han");
                        }

                        // Put in JHED
                        settings_edit.putString("JHED_ID", jhedInput.getText().toString());
                        settings_edit.commit();

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode, data);
    }

}
