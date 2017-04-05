package io.github.viv2d2.findride;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Adds user's notes
 */

public class NotesActivity extends AppCompatActivity {

    private String notes;
    private String notesPreview;
    private TextView input_notes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);


        // Initialize all variables
        input_notes = (TextView) findViewById(R.id.notes);
        notes = "";
        notesPreview = "";

        // If notes already exist
        notes = getIntent().getStringExtra("notes");
        notesPreview = getIntent().getStringExtra("preview");

        if (notes.length() > 0) {
            input_notes.setText(notes);
        }
    }

    public void updateNotes(View view) {
        notes = input_notes.getText().toString();
        input_notes.setText(notes);
    }


    public void save(View view) {
        notes = input_notes.getText().toString();
        input_notes.setText(notes);

        if (notes.length() < 20) {
            notesPreview = notes.substring(0,notes.length());
        } else {
            notesPreview = notes.substring(0, 20) + "...";
        }

        Intent intent = new Intent();
        intent.putExtra("notes", notes);
        intent.putExtra("preview", notesPreview);
        setResult(RESULT_OK, intent);
        finish();
    }
}
