package io.github.viv2d2.findride;

import android.content.Intent;
import android.os.Bundle;
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
 * User's rides.
 */
public class MyRidesFragment extends Fragment {

    protected static ArrayList<Ride> myRides;
    protected static RideAdapter rideAdapter;
    private ListView myRidesView;
    private View rootView;

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
                intent.putExtra("riders", currRide.getNumRiders());
                intent.putExtra("riderObjects", currRide.getRiders());

                startActivity(intent);

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

                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Ride ride = child.getValue(Ride.class);
                    myRides.add(ride);
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

}
