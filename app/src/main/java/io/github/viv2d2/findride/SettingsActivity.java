package io.github.viv2d2.findride;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

/**
 * Settings page.
 */

public class SettingsActivity extends AppCompatActivity {

    Switch notifications;
    private TextView input_jhed;
    private TextView input_facebook;
    SharedPreferences login;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle("Settings");
        notifications = (Switch) findViewById(R.id.switch_notifications);

        // Set up JHED, facebook
        final SharedPreferences login = getDefaultSharedPreferences(getApplicationContext());
        String jhed = login.getString("JHED_ID", "");
        String facebook = login.getString("Facebook_Name", "");

        input_jhed = (TextView) findViewById(R.id.jhed_input);
        input_jhed.setText(jhed);

        input_facebook = (TextView) findViewById(R.id.facebook_input);
        input_facebook.setText(facebook);
    }

    public void logOut(View view) {
        final SharedPreferences login = getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor edit = login.edit();
        edit.putBoolean("JHED",false);
        edit.putBoolean("Facebook",false);
        edit.putBoolean("FBLogout",true);
        edit.commit();
        Intent logout = new Intent(SettingsActivity.this, LoginActivity.class);
        startActivity(logout);
    }
}
