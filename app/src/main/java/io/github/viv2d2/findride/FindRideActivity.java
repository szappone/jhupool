package io.github.viv2d2.findride;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FindRideActivity extends AppCompatActivity {

    private String from;
    private String to;
    private String date;
    private String time;
    private int numRiders;
    private String notes;
    private String notesPreview;

    private TextView input_from;
    private TextView input_to;

    private TextView input_date;
    private TextView input_time;
    private SimpleDateFormat sdf_date;
    private SimpleDateFormat sdf_time;

    private TextView input_riders;

    private TextView input_notes;

    private int pickerHour = 0;
    private int pickerMin = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_ride);

        // Initialize variables
        input_from = (TextView) findViewById(R.id.from_input);
        input_to = (TextView) findViewById(R.id.to_input);
        input_date = (TextView) findViewById(R.id.date_input);
        input_time = (TextView) findViewById(R.id.time_input);
        input_riders = (TextView) findViewById(R.id.riders_input);
        input_notes = (TextView) findViewById(R.id.notes_input);

        // Date, time formats
        sdf_date = new SimpleDateFormat("EEE MMM dd yyyy");
        sdf_time = new SimpleDateFormat("hh:mm a");


        // Set initial to/from
        from = "Homewood";
        to = "BWI";
        input_from.setText(from);
        input_to.setText(to);

        // Set initial date/time
        // IS TIME WORKING PROPERLY? --viv
        Calendar cal = Calendar.getInstance();
        time = sdf_time.format(cal.getTime());
        input_time.setText(time);
        date = sdf_date.format(cal.getTime());
        input_date.setText(date);

        // Set riders
        numRiders = 1;
        input_riders.setText(String.valueOf(numRiders));

        // Set notes
        notes = "";
        notesPreview = "";

        /*final Button button = (Button) findViewById(R.id.find_ride_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(FindRideActivity.this, FindRideResultsActivity.class);
                /*intent.putExtra("from", input_from.getText().toString());
                intent.putExtra("to", input_to.getText().toString());
                intent.putExtra("date", input_date.getText().toString());
                intent.putExtra("time", input_time.getText().toString());
                intent.putExtra("riders", input_riders.getText().toString());
                intent.putExtra("notes", input_notes.getText().toString());
                startActivity(intent);
            }
        });*/
    }

    public void from(View view) {
        // create new intent
        Intent intent = new Intent(this, SelectLocActivity.class);
        // "from" direction
        intent.putExtra("direction", "FROM");
        // "from" location
        intent.putExtra("location", from);
        // SelectLocActivity == 1
        startActivityForResult(intent, 1);
    }

    public void to(View view) {
        // create new intent
        Intent intent = new Intent(this, SelectLocActivity.class);
        // "to" direction
        intent.putExtra("direction", "TO");
        // "to" location
        intent.putExtra("location", to);
        // SelectLocActivity == 1
        startActivityForResult(intent, 1);
    }

    public void date(View view) {
        // create new intent
        Intent intent = new Intent(this, SelectDateActivity.class);
        startActivityForResult(intent, 2);
        //code=2
    }

    public void time(View view) {
        // create new intent
        Intent intent = new Intent(this, SelectTimeActivity.class);
        startActivityForResult(intent, 3);
        //code=3
    }

    public void incrementRiders(View view) {
        if (numRiders != 6) { // max riders = 6
            numRiders++;
            input_riders.setText(String.valueOf(numRiders));
        }
    }

    public void decrementRiders(View view) {
        if (numRiders != 1) { // min riders = 1
            numRiders--;
            input_riders.setText(String.valueOf(numRiders));
        }
    }

    public void notes(View view) {
        // create new intent
        Intent intent = new Intent(this, NotesActivity.class);
        intent.putExtra("preview", notesPreview);
        intent.putExtra("notes", notes);
        // SelectLocActivity == 4
        startActivityForResult(intent, 4);
    }

    public void launchResults(View v) {
        Intent intent = new Intent(FindRideActivity.this, FindRideResultsActivity.class);
                intent.putExtra("from", input_from.getText().toString());
                intent.putExtra("to", input_to.getText().toString());
                intent.putExtra("date", input_date.getText().toString());
                intent.putExtra("time", input_time.getText().toString());
                intent.putExtra("riders", input_riders.getText().toString());
                intent.putExtra("notes", input_notes.getText().toString());
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // SelectLocActivity
        if (requestCode == 1) {
            // make sure the request was successful
            if (resultCode == RESULT_OK) {
                if (data.getStringExtra("direction").equals("FROM")) {
                    // update "from" location
                    from = data.getStringExtra("location");
                    input_from.setText(from);

                    // make sure one location is Homewood
                    if (!from.equals("Homewood")) {
                        to = "Homewood";
                        input_to.setText(to);
                    }
                }
                if (data.getStringExtra("direction").equals("TO")) {
                    // update "to" location
                    to = data.getStringExtra("location");
                    input_to.setText(to);

                    // make sure one location is Homewood
                    if (!to.equals("Homewood")) {
                        from = "Homewood";
                        input_from.setText(from);
                    }
                }
            }
        }

        // SelectDateActivity
        if (requestCode == 2) {
            // make sure the request was successful
            if (resultCode == RESULT_OK) {
                input_date.setText(data.getStringExtra("selected_date"));
            }
        }

        // SelectTimeActivity
        if (requestCode == 3) {
            // make sure the request was successful
            if (resultCode == RESULT_OK) {
                input_time.setText(data.getStringExtra("updated_time"));
            }
        }

        // NotesActivity
        if (requestCode == 4) {
            // make sure the request was successful
            if (resultCode == RESULT_OK) {
                notes = data.getStringExtra("notes");
                notesPreview = data.getStringExtra("preview");
                input_notes.setText(notesPreview);
            }
        }

    }

}

