package io.github.viv2d2.findride;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Declare your first fragment here
        rootView =  inflater.inflate(R.layout.fragment_my_rides, container, false);
        myRidesView = (ListView) rootView.findViewById(R.id.my_rides);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        // Populate with rides
        myRides = new ArrayList<Ride>();
        Rider ron = new Rider("rwease1", "Ron", 2, "mum always said midnight");
        Ride r1 = new Ride("Homewood", "Whole Foods", "Sat May 6, 2017", "03:42 PM", ron);


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
