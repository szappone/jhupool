package io.github.viv2d2.findride;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

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
