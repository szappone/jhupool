package io.github.viv2d2.findride;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
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


public class LoginActivity extends AppCompatActivity {
    LoginButton loginButton;
    TextView textView;
    CallbackManager callbackManager;
    Button toNext;
    public static int APP_REQUEST_CODE = 99;
    ArrayList<String> JHEDS;
    boolean fbIn;

    EditText jhedInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //getActionBar().hide();
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
        //setContentView(R.layout.activity_login);

        loginButton = (LoginButton) findViewById(R.id.login_button);

        jhedInput = (EditText) findViewById(R.id.jhedInput);


        JHEDS = new ArrayList<>();
        JHEDS.add("wmattes2"); JHEDS.add("vtsai5"); JHEDS.add("szappon1"); JHEDS.add("rkinney4");
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
                fbIn = true;
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

                if (JHEDS.contains(jhedInput.getText().toString())){
                    if (fbIn) {
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
