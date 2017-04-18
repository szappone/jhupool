package io.github.viv2d2.findride;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * User's rides.
 */
public class MyRidesFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Declare your first fragment here
        return inflater.inflate(R.layout.fragment_all_rides, container, false);
    }
}
