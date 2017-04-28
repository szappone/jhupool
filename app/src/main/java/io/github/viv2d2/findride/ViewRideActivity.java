package io.github.viv2d2.findride;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

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

    private ListView ridersView;
    private ArrayList<Rider> r;
    protected static RiderAdapter riderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_ride);


        // Riders listview
        ridersView = (ListView) findViewById(R.id.riders);
        r = new ArrayList<Rider>();
        Rider r1 = new Rider("Vivian", 1, "it was the best of times");
        Rider r2 = new Rider("Sarah", 0, "it was the worst of times");
        Rider r3 = new Rider("Will", 0, "it was the age of wisdom");
        r.add(r1);
        r.add(r2);
        r.add(r3);

        // Set up adapter
        riderAdapter = new RiderAdapter(ViewRideActivity.this, R.layout.rider_view, r);
        ridersView.setAdapter(riderAdapter);

        // Initialize variables
        input_from = (TextView) findViewById(R.id.from_input);
        input_to = (TextView) findViewById(R.id.to_input);
        input_date = (TextView) findViewById(R.id.date_input);
        input_time = (TextView) findViewById(R.id.time_input);
        input_riders = (TextView) findViewById(R.id.riders_input);
        input_notes = (TextView) findViewById(R.id.notes_input);
        //riders_info = (TextView) findViewById(R.id.riders_info);
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
        //riders_info.setText("Sarah                  24 mutual");
    }

    public void action(View view) {
        Intent intent = new Intent(ViewRideActivity.this, MainActivity.class);
        startActivity(intent);

        if (action == 0) {
            // create new car
            // ADD DIALOG TO CONFIRM RIDERS, NOTES

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myDB = database.getReference();
            /*Ride ride = new Ride("Homewood", "BWI", "Sun April 20, 2017", "8:30 AM", 3, "riders", "Notes");*/
            //System.out.println(from + " " + to + " " + date + " " + time + " riders: " + riders + " notes: " +notes);
            Ride ride = new Ride(from, to, date, time, riders, notes);
            //myDB.child("Drive_Feed").child("Drive" + id).setValue(ride);

            ride.setID(myDB.child("Drive_Feed").push().getKey());
            myDB.child("Drive_Feed").child(ride.getID()).setValue(ride);
            finish();

            myDB.child("drives").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    List rides = new ArrayList<>();
                    for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                        Ride drive = noteDataSnapshot.getValue(Ride.class);
                        rides.add(drive);
                    }
                }
                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                }
            });


        } else if (action == 1) {

            // join car - add user to car
            // ADD DIALOG TO CONFIRM RIDERS, NOTES

        } else { // action == 2
            // leave car - remove user from car/delete car
        }
    }

}
