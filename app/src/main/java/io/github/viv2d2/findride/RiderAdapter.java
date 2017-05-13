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
    private String fbID;
    int resource;
    Rider rider;

    public RiderAdapter(Context ctx, int r, List<Rider> riders)  {
        super(ctx, r, riders);
        this.resource = r;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout riderView;
        rider = getItem(position);

        if (convertView == null) {
            riderView = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(inflater);
            vi.inflate(this.resource, riderView, true);
        } else {
            riderView = (LinearLayout) convertView;
        }

        ImageView input_profile = (ImageView) riderView.findViewById(R.id.profile);
        //Set profile to rider picture

        // Actual code
        //ProfilePictureView fbProfPic = (ProfilePictureView) riderView.findViewById(R.id.profilePicture);
        //fbProfPic.setProfileId(rider.getID());

        if (rider.getJHED().equals("szappon1")) {
            input_profile.setImageResource(R.drawable.fb_sz);
        } else if (rider.getJHED().equals("wmattes2")) {
            input_profile.setImageResource(R.drawable.fb_wt);
        } else if (rider.getJHED().equals("vtsai5")) {
            input_profile.setImageResource(R.drawable.fb_vt);
        } else {
            input_profile.setImageResource(R.drawable.fb_icon);
        }

        TextView input_name = (TextView) riderView.findViewById(R.id.name);

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

        fbID=rider.getID();
        // Set up icon

        return riderView;
    }

    public void messenger(View view) {

        //view.getContext().startActivity(new Intent(Intent.ACTION_VIEW,
          //      Uri.parse("https://m.me/" + fbID)));

    }

}
