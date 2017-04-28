package io.github.viv2d2.findride;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

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

    protected static ArrayList<Ride> rideResults;
    protected static RideAdapter resultAdapter;
    private ListView resultsView;
    private View rootView;
    private String jhed;

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

        // Set up preview for search
        preview_fromTo = (TextView) findViewById(R.id.create_fromTo);
        preview_dateTime = (TextView) findViewById(R.id.create_dateTime);
        preview_riders = (TextView) findViewById(R.id.create_riders);
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

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myDB = database.getReference();
        rideResults = new ArrayList<Ride>();


         myDB.child("Drive_Feed").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Ride ride = child.getValue(Ride.class);


                    if (user_date.compareTo(ride.getDate()) == 0) {

                     /* System.out.println("from1: " + user_from + "from2: " + ride.getFrom());
                     System.out.println("to1: " + user_to + "to2: " + ride.getTo());
                        if (user_from.equals(ride.getFrom()) && user_to.equals(ride.getTo())) {*/
                            if (checkWithinTimeFrame(user_time, ride.getTime())) {
                                if (!ride.inCar(jhed)) {
                                    rideResults.add(ride);
                                }
                            }
                        //}
                    }
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

                startActivity(intent);

            }
        });
    }

    public boolean checkWithinTimeFrame(String requested, String possible) {
        System.out.println("Requested Time: " + requested);
        System.out.println("Possible Match: " + possible);

        return true;
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
