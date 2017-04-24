package io.github.viv2d2.findride;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Switch;

/**
 * Settings page.
 */

public class SettingsActivity extends AppCompatActivity {

    Switch notifications;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle("Settings");
        notifications = (Switch) findViewById(R.id.switch_notifications);
    }
}
