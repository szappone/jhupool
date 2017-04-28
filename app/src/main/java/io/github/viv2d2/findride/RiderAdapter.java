package io.github.viv2d2.findride;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
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

public class RiderAdapter extends ArrayAdapter<Rider> {

    int resource;

    public RiderAdapter(Context ctx, int r, List<Rider> riders)  {
        super(ctx, r, riders);
        this.resource = r;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout riderView;
        Rider rider = getItem(position);

        if (convertView == null) {
            riderView = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(inflater);
            vi.inflate(this.resource, riderView, true);
        } else {
            riderView = (LinearLayout) convertView;
        }

        ImageView input_profile = (ImageView) riderView.findViewById(R.id.profile);
        TextView input_name = (TextView) riderView.findViewById(R.id.name);
        TextView input_mutual = (TextView) riderView.findViewById(R.id.mutual);

        Context context = parent.getContext();
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        String jhed = settings.getString("JHED_ID", "");

        // Set up name
        String name_input;
        if (rider.getJHED().equals(jhed)) {
            name_input = "You";
        } else {
            name_input = rider.getFacebook();
        }

        // Add guests if they exist
        int guests = rider.getNumGuests();
        if (guests > 0) {
            name_input += " +" + guests;
        }
        input_name.setText(name_input);

        // Set up mutual friends
        if (rider.getJHED().equals(jhed)) {
            input_mutual.setText("");
        } else {
            input_mutual.setText("42 mutual");
        }

        // Set up icon
        if (rider.getFacebook().equals("Sarah") || rider.getFacebook().equals("Ron")) {
            input_profile.setImageResource(R.drawable.pink);
        } else if (rider.getFacebook().equals("Will") || rider.getFacebook().equals("Arthur")) {
            input_profile.setImageResource(R.drawable.blue);
        } else {
            input_profile.setImageResource(R.drawable.green);
        }

        return riderView;
    }
}
