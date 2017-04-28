package io.github.viv2d2.findride;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

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
        Rider han = new Rider("Han", 2, "never tell me the odds");
        Ride r1 = new Ride("Homewood", "Whole Foods", "Sat May 6, 2017", "03:42 PM", han);
        myRides.add(r1);
        myRides.add(r1);
        myRides.add(r1);
        myRides.add(r1);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myDB = database.getReference();

        myDB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Ride ride = (Ride) dataSnapshot.getValue();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Ride ride = child.getValue(Ride.class);
                    myRides.add(ride);
                    System.out.println("VALUE: " + ride);
                    //dataSnapshot.getValue();
                    //myRides.add(ride);*/
                    // do your stuff here with value
                }

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
