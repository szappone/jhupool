package io.github.viv2d2.findride;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.view.View;

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

    private TextView action_button;

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
        action_button = (TextView) findViewById(R.id.action_button);

        // Get info
        action = getIntent().getIntExtra("action", 0);
        from = getIntent().getStringExtra("from");
        to = getIntent().getStringExtra("to");
        date = getIntent().getStringExtra("date");
        time = getIntent().getStringExtra("time");
        riders = getIntent().getStringExtra("riders");
        notes = getIntent().getStringExtra("notes");

        // Set fields
        input_from.setText(from);
        input_to.setText(to);
        input_date.setText(date);
        input_time.setText(time);
        input_riders.setText(riders);
        input_notes.setText(notes);

        // Set action (based on 0,1,2)
        if (action == 0) {
            // create new car
            action_button.setText("Create Car");
        } else if (action == 1) {
            // join car
            action_button.setText("Join Car");
        } else { // action == 2
            // leave car
            action_button.setText("Leave Car");
        }

        // Set rider info
        riders_info.setText("Sarah                  24 mutual");

    }

    public void action(View view) {
        if (action == 0) {
            // create new car

            // ADD DIALOG TO CONFIRM RIDERS, NOTES
            // create new car

        } else if (action == 1) {
            // join car

            // ADD DIALOG TO CONFIRM RIDERS, NOTES
            // add user to car

        } else { // action == 2
            // leave car

            // remove user from car/delete car
        }
    }
}
