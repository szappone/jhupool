package io.github.viv2d2.findride;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Shows results (from matching algorithm) and user's car (from search).
 */

public class FindRideResultsActivity extends AppCompatActivity {

    private String user_from;
    private String user_to;
    private String user_date;
    private String user_time;
    private String user_riders;
    private String user_notes;

    private TextView preview_fromTo;
    private TextView preview_dateTime;
    private TextView preview_riders;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_ride_results);


        // User's car (from search)
        user_from = getIntent().getStringExtra("from");
        user_to = getIntent().getStringExtra("to");
        user_date = getIntent().getStringExtra("date");
        user_time = getIntent().getStringExtra("time");
        user_riders = getIntent().getStringExtra("riders");
        user_notes = getIntent().getStringExtra("notes");

        // Set up preview
        preview_fromTo = (TextView) findViewById(R.id.create_fromTo);
        preview_dateTime = (TextView) findViewById(R.id.create_dateTime);
        preview_riders = (TextView) findViewById(R.id.create_riders);

        preview_fromTo.setText(user_from + " -> " + user_to);
        preview_dateTime.setText(user_date + ", " + user_time);
        preview_riders.setText(user_riders + " riders");
    }

    public void viewRide(View view) {
        Intent intent = new Intent(FindRideResultsActivity.this, ViewRideActivity.class);
        intent.putExtra("action", 1);
        intent.putExtra("from", "Homewood");
        intent.putExtra("to", "BWI");
        intent.putExtra("date", "Wed Apr 18 2017");
        intent.putExtra("time", "7:00 PM");
        intent.putExtra("riders", "2");
        intent.putExtra("notes", "calling an Uber");
        startActivity(intent);
    }

    public void createCar(View view) {
        Intent intent = new Intent(FindRideResultsActivity.this, ViewRideActivity.class);
        // code = 0 for creating new car (1 = join, 2 = leave)
        intent.putExtra("action", 0);
        intent.putExtra("from", user_from);
        intent.putExtra("to", user_to);
        intent.putExtra("date", user_date);
        intent.putExtra("time", user_time);
        intent.putExtra("riders", user_riders);
        intent.putExtra("notes", user_notes);
        startActivity(intent);
    }
}
