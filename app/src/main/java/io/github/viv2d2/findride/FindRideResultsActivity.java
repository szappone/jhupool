package io.github.viv2d2.findride;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

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

    protected static ArrayList<Ride> rideResults;
    protected static RideAdapter resultAdapter;
    private ListView resultsView;
    private String jhed;

    private String facebook;
    private int THREE_HOURS = 10800000;

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

        // Set up image for create car
        ImageView newCar = (ImageView) findViewById(R.id.create_category);
        if (user_from.equals("BWI") || user_to.equals("BWI")) {
            newCar.setImageResource(R.drawable.travel);
        } else if (user_from.equals("Hampdenfest") || user_to.equals("Hampdenfest")) {
            newCar.setImageResource(R.drawable.globe);
        } else {
            newCar.setImageResource(R.drawable.grocery);
        }

        // Set up preview for search
        TextView preview_fromTo = (TextView) findViewById(R.id.create_fromTo);
        TextView preview_dateTime = (TextView) findViewById(R.id.create_dateTime);
        TextView preview_riders = (TextView) findViewById(R.id.create_riders);
        preview_fromTo.setText(user_from + " -> " + user_to);
        preview_dateTime.setText(user_date + ", " + user_time);
        if (Integer.parseInt(user_riders) == 1) {
            preview_riders.setText(user_riders + " rider");
        } else {
            preview_riders.setText(user_riders + " riders");
        }

        // Populate results
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        jhed = settings.getString("JHED_ID", "");
        facebook = settings.getString("Facebook_ID", "");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myDB = database.getReference();
        rideResults = new ArrayList<Ride>();

         myDB.child("Drive_Feed").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                rideResults = new ArrayList<Ride>();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Ride ride = child.getValue(Ride.class);
                    rideResults = runMatchingAlgorithm(ride);
                }
                resultAdapter = new RideAdapter(FindRideResultsActivity.this, R.layout.ride_preview, rideResults);
                resultsView.setAdapter(resultAdapter);
            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {

            }
        });

        resultsView = (ListView) findViewById(R.id.results);

        // Set up adapter
        resultAdapter = new RideAdapter(this, R.layout.ride_preview, rideResults);
        resultsView.setAdapter(resultAdapter);

        // View a result
        resultsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object temp = resultsView.getItemAtPosition(position);
                Ride currRide = (Ride) temp;

                Intent intent = new Intent(FindRideResultsActivity.this, ViewRideActivity.class);
                intent.putExtra("action", 1); // join car
                intent.putExtra("from", currRide.getFrom());
                intent.putExtra("to", currRide.getTo());
                intent.putExtra("date", currRide.getDate());
                intent.putExtra("time", currRide.getTime());
                intent.putExtra("riders", Integer.toString(currRide.getNumRiders()));
                intent.putExtra("riderObjects", currRide.getRiders());
                intent.putExtra("r", currRide);
                final SharedPreferences login = getDefaultSharedPreferences(getApplicationContext());

                Rider user = new Rider(jhed, facebook, Integer.parseInt(user_riders), user_notes, login.getString("fbID", ""));
                intent.putExtra("user", user);

                startActivity(intent);
            }
        });
    }

    public ArrayList<Ride> runMatchingAlgorithm(Ride ride) {
        if (user_date.equals(ride.getDate())) {
            if (user_from.equals(ride.getFrom()) && user_to.equals(ride.getTo())) {
                if (checkWithinTimeFrame(user_time, ride.getTime()) && !ride.inCar(jhed)) {
                        rideResults.add(ride);
                }
            }
        }
        return rideResults;
    }

    public boolean checkWithinTimeFrame(String requested, String possible) {
        SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
        Date date1 = null;
        Date date2 = null;
        try {
            date1 = parseFormat.parse(requested);
            date2 = parseFormat.parse(possible);
        } catch (ParseException p) {
        }
        return Math.abs(date1.getTime() - date2.getTime()) <= THREE_HOURS;
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