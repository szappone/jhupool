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
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * All rides.
 */
public class AllRidesFragment extends Fragment {

    protected static ArrayList<Ride> allRides;
    protected static RideAdapter rideAdapter;
    private ListView allRidesView;
    private View rootView;

    private Ride currRide;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Declare your first fragment here
        rootView =  inflater.inflate(R.layout.fragment_all_rides, container, false);
        allRidesView = (ListView) rootView.findViewById(R.id.all_rides);

        allRidesView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object temp = allRidesView.getItemAtPosition(position);
                Ride currRide = (Ride) temp;

                SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getActivity());
                String jhed = settings.getString("JHED_ID", "");

                Intent intent = new Intent(getActivity(), ViewRideActivity.class);
                if (currRide.inCar(jhed)) {
                    intent.putExtra("action", 2); // leave car
                } else {
                    intent.putExtra("action", 1); // join car
                }

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

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        // Populate with rides
        allRides = new ArrayList<Ride>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myDB = database.getReference();

        myDB.child("Drive_Feed").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                allRides = new ArrayList<Ride>();

                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Ride ride = child.getValue(Ride.class);
                    allRides.add(ride);
                }

                rideAdapter = new RideAdapter(getActivity(), R.layout.ride_preview, allRides);
                allRidesView.setAdapter(rideAdapter);

            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {

            }
        });


        // Set up adapter
        rideAdapter = new RideAdapter(getActivity(), R.layout.ride_preview, allRides);
        allRidesView.setAdapter(rideAdapter);

    }

}
