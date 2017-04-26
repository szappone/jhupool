package io.github.viv2d2.findride;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * ArrayAdapter for rides.
 */

public class RideAdapter extends ArrayAdapter<Ride> {

    int resource;

    public RideAdapter(Context ctx, int r, List<Ride> rides)  {
        super(ctx, r, rides);
        this.resource = r;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout rideView;
        Ride ride = getItem(position);

        if (convertView == null) {
            rideView = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(inflater);
            vi.inflate(this.resource, rideView, true);
        } else {
            rideView = (LinearLayout) convertView;
        }

        ImageView input_category = (ImageView) rideView.findViewById(R.id.category);
        TextView input_destination = (TextView) rideView.findViewById(R.id.destination);
        TextView input_date = (TextView) rideView.findViewById(R.id.date);
        TextView input_riders = (TextView) rideView.findViewById(R.id.riders);

        // Set up date
        String date_input = ride.getDate() + ", " + ride.getTime();
        input_date.setText(date_input);

        // Set up destination
        String destination_input = ride.getFrom() + " -> " + ride.getTo();
        input_destination.setText(destination_input);

        // Set up numRiders
        int numRiders = ride.getNumRiders();
        String riders_input;
        if (numRiders == 1) {
            riders_input = numRiders + " rider";
        } else {
            riders_input = numRiders + " riders";
        }
        input_riders.setText(riders_input);

        // Set up image based on category
        String category = ride.getCategory();
        if (category.equals("travel")) {
            input_category.setImageResource(R.drawable.travel);
        } else if (category.equals("groceries")) {
            input_category.setImageResource(R.drawable.grocery);
        } else {
            input_category.setImageResource(R.drawable.launch_icon);
        }

        return rideView;
    }
}
