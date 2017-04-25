package io.github.viv2d2.findride;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.facebook.FacebookSdk;
import com.facebook.accountkit.AccountKit;

import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccessToken;




public class LoginActivity extends AppCompatActivity {
    Button toNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
        //setContentView(R.layout.activity_login);

        AccessToken accessToken = AccountKit.getCurrentAccessToken();

        if (accessToken != null) {
            //Handle Returning User
        } else {
            //Handle new or logged out user
        }


        Button toNext = (Button)findViewById(R.id.nextScreen);

        toNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
                LoginActivity.this.startActivity(mainIntent);
            }
        });
    }
}
