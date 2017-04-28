package io.github.viv2d2.findride;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Switch;
import android.widget.TextView;

/**
 * Settings page.
 */

public class SettingsActivity extends AppCompatActivity {

    Switch notifications;
    private TextView input_jhed;
    private TextView input_facebook;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle("Settings");
        notifications = (Switch) findViewById(R.id.switch_notifications);

        // Set up JHED, facebook
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        String jhed = settings.getString("JHED_ID", "");
        String facebook = settings.getString("Facebook_ID", "");

        input_jhed = (TextView) findViewById(R.id.jhed_input);
        input_jhed.setText(jhed);

        input_facebook = (TextView) findViewById(R.id.facebook_input);
        input_facebook.setText(facebook);
    }
}
