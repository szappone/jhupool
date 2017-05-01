package io.github.viv2d2.findride;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * User's rides.
 */
public class MyRidesFragment extends Fragment {

    protected static ArrayList<Ride> myRides;
    protected static ArrayList<Ride> myTravelRides;
    protected static ArrayList<Ride> myGroceryRides;
    protected static RideAdapter rideAdapter;
    private ListView myRidesView;
    private View rootView;
    private String jhed;

    private ImageView travel;
    private ImageView all;
    private ImageView groceries;

    private Ride currRide;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Declare your first fragment here
        rootView =  inflater.inflate(R.layout.fragment_my_rides, container, false);
        myRidesView = (ListView) rootView.findViewById(R.id.my_rides);

        myRidesView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object temp = myRidesView.getItemAtPosition(position);
                Ride currRide = (Ride) temp;

                Intent intent = new Intent(getActivity(), ViewRideActivity.class);
                intent.putExtra("action", 2); // leave car
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

        // Set up bottom toolbar
        all = (ImageView) rootView.findViewById(R.id.global);
        all.setOnClickListener(allFilter);
        travel = (ImageView) rootView.findViewById(R.id.travel_only);
        travel.setOnClickListener(travelFilter);
        groceries = (ImageView) rootView.findViewById(R.id.grocery_only);
        groceries.setOnClickListener(groceryFilter);

        // Populate through Firebase
        // Shared pref
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getActivity());
        jhed = settings.getString("JHED_ID", "");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myDB = database.getReference();

        myDB.child("Drive_Feed").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                myRides = new ArrayList<Ride>();
                myTravelRides = new ArrayList<Ride>();
                myGroceryRides = new ArrayList<Ride>();

                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Ride ride = child.getValue(Ride.class);
                    if (ride.inCar(jhed)) {
                        myRides.add(ride);
                        if (ride.getCategory().equals("travel")) {
                            myTravelRides.add(ride);
                        } else if (ride.getCategory().equals("groceries")) {
                            myGroceryRides.add(ride);
                        }
                    }
                }

                rideAdapter = new RideAdapter(getActivity(), R.layout.ride_preview, myRides);
                myRidesView.setAdapter(rideAdapter);

            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {

            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        // Populate with rides
        myRides = new ArrayList<Ride>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myDB = database.getReference();

        myDB.child("Drive_Feed").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                myRides = new ArrayList<Ride>();
                myTravelRides = new ArrayList<Ride>();
                myGroceryRides = new ArrayList<Ride>();

                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Ride ride = child.getValue(Ride.class);
                    if (ride.inCar(jhed)) {
                        myRides.add(ride);
                        if (ride.getCategory().equals("travel")) {
                            myTravelRides.add(ride);
                        } else if (ride.getCategory().equals("groceries")) {
                            myGroceryRides.add(ride);
                        }
                    }
                }

                rideAdapter = new RideAdapter(getActivity(), R.layout.ride_preview, myRides);
                myRidesView.setAdapter(rideAdapter);

            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {

            }
        });


        // Set up adapter
        rideAdapter = new RideAdapter(getActivity(), R.layout.ride_preview, myRides);
        myRidesView.setAdapter(rideAdapter);

    }

    private View.OnClickListener travelFilter = new View.OnClickListener() {
        public void onClick(View v) {
            // Populate with travel rides only
            rideAdapter = new RideAdapter(getActivity(), R.layout.ride_preview, myTravelRides);
            myRidesView.setAdapter(rideAdapter);
        }
    };

    private View.OnClickListener allFilter = new View.OnClickListener() {
        public void onClick(View v) {
            // Populate with travel rides only
            rideAdapter = new RideAdapter(getActivity(), R.layout.ride_preview, myRides);
            myRidesView.setAdapter(rideAdapter);
        }
    };

    private View.OnClickListener groceryFilter = new View.OnClickListener() {
        public void onClick(View v) {
            // Populate with travel rides only
            rideAdapter = new RideAdapter(getActivity(), R.layout.ride_preview, myGroceryRides);
            myRidesView.setAdapter(rideAdapter);
        }
    };

}
