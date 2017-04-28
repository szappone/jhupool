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

public class NotesAdapter extends ArrayAdapter<Rider> {

    int resource;

    public NotesAdapter(Context ctx, int r, List<Rider> riders)  {
        super(ctx, r, riders);
        this.resource = r;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout notesView;
        Rider rider = getItem(position);

        if (convertView == null) {
            notesView = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(inflater);
            vi.inflate(this.resource, notesView, true);
        } else {
            notesView = (LinearLayout) convertView;
        }

        ImageView input_profile = (ImageView) notesView.findViewById(R.id.profile);
        TextView input_notes = (TextView) notesView.findViewById(R.id.notes);

        // Set up notes
        String notes_input = rider.getNotes();
        input_notes.setText(notes_input);


        // Set up icon

        return notesView;
    }
}
