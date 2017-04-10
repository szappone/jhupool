package io.github.viv2d2.findride;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Details for a ride.
 */

public class ViewRideActivity extends AppCompatActivity {

    private String from;
    private String to;
    private String date;
    private String time;
    private String riders;
    private String notes;
    private int action;

    private TextView input_from;
    private TextView input_to;
    private TextView input_date;
    private TextView input_time;
    private TextView input_riders;
    private TextView input_notes;

    private TextView riders_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_ride);

        // Initialize variables
        input_from = (TextView) findViewById(R.id.from_input);
        input_to = (TextView) findViewById(R.id.to_input);
        input_date = (TextView) findViewById(R.id.date_input);
        input_time = (TextView) findViewById(R.id.time_input);
        input_riders = (TextView) findViewById(R.id.riders_input);
        input_notes = (TextView) findViewById(R.id.notes_input);
        riders_info = (TextView) findViewById(R.id.riders_info);

        // Get info
        action = getIntent().getIntExtra("action", 0);
        from = getIntent().getStringExtra("from");
        to = getIntent().getStringExtra("to");
        date = getIntent().getStringExtra("date");
        time = getIntent().getStringExtra("time");
        riders = getIntent().getStringExtra("notes");

        // Set fields
        input_from.setText(from);
        input_to.setText(to);
        input_date.setText(date);
        input_time.setText(time);
        input_riders.setText(riders);
        input_notes.setText(notes);

    }
}
