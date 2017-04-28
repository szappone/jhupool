package io.github.viv2d2.findride;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
    private TextView action_button;

    private ListView ridersView;
    private ArrayList<Rider> r;
    protected static RiderAdapter riderAdapter;

    private ListView notesView;
    protected static NotesAdapter notesAdapter;

    private ArrayList<Rider> riderObjects;

    private Rider r1;

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
        action_button = (TextView) findViewById(R.id.action_button);

        // Get info
        action = getIntent().getIntExtra("action", 0);
        from = getIntent().getStringExtra("from");
        to = getIntent().getStringExtra("to");
        date = getIntent().getStringExtra("date");
        time = getIntent().getStringExtra("time");
        riders = getIntent().getStringExtra("riders");
        riderObjects = (ArrayList<Rider>) getIntent().getSerializableExtra("riderObjects");

        // Set fields
        input_from.setText(from);
        input_to.setText(to);
        input_date.setText(date);
        input_time.setText(time);
        input_riders.setText(riders);

        if (action != 0) {
            // Set up rider adapter
            ridersView = (ListView) findViewById(R.id.riders);
            /* r = new ArrayList<Rider>();
            Rider r1 = new Rider("vtsai5", "Vivian", 2, "it was the best of times");
            Rider r2 = new Rider("szappon1", "Sarah", 1, "it was the worst of times");
            Rider r3 = new Rider("wmattes2", "Will", 1, "it was the age of wisdom");
            r.add(r1);
            r.add(r2);
            r.add(r3); */
            riderAdapter = new RiderAdapter(ViewRideActivity.this, R.layout.rider_view, riderObjects);
            ridersView.setAdapter(riderAdapter);

            // Set up notes adapter
            notesView = (ListView) findViewById(R.id.notes);
            notesAdapter = new NotesAdapter(ViewRideActivity.this, R.layout.notes_view, riderObjects);
            notesView.setAdapter(notesAdapter);
        }

        if (action == 0) {
            // Set up rider adapter
            ridersView = (ListView) findViewById(R.id.riders);
            r = new ArrayList<Rider>();
            SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
            String jhed = settings.getString("JHED_ID", "");
            String facebook = settings.getString("Facebook_ID", "");

            // Use YOU instead of Facebook name for user
            r1 = new Rider(jhed, facebook, Integer.parseInt(riders), notes);
            r.add(r1);

            riderAdapter = new RiderAdapter(ViewRideActivity.this, R.layout.rider_view, r);
            ridersView.setAdapter(riderAdapter);

            // Set up notes adapter
            notesView = (ListView) findViewById(R.id.notes);
            notesAdapter = new NotesAdapter(ViewRideActivity.this, R.layout.notes_view, r);
            notesView.setAdapter(notesAdapter);
        }

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

    }

    public void action(View view) {
        Intent intent = new Intent(ViewRideActivity.this, MainActivity.class);
        startActivity(intent);

        if (action == 0) {
            // create new car
            // ADD DIALOG TO CONFIRM RIDERS, NOTES

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myDB = database.getReference();
            /*Ride ride = new Ride("Homewood", "BWI", "Sun April 20, 2017", "8:30 AM", 3, new Rider("Viv",1,"hello"));*/

            Ride ride = new Ride(from, to, date, time, r1);
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
