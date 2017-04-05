package io.github.viv2d2.findride;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FindRideActivity extends AppCompatActivity {

    private String from;
    private String to;
    private String date;
    private String time;
    private TextView input_from;
    private TextView input_to;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_ride);

        // Initialize variables
        from = "Homewood";
        to = "BWI";
        input_from = (TextView) findViewById(R.id.from_input);
        input_from.setText(from);
        input_to = (TextView) findViewById(R.id.to_input);
        input_to.setText(to);

    }

    public void from(View view) {
        // create new intent
        Intent intent = new Intent(this, SelectLocActivity.class);
        // "from" direction
        intent.putExtra("direction", "FROM");
        // "from" location
        intent.putExtra("location", from);
        // SelectLocActivity == 1
        startActivityForResult(intent, 1);
    }

    public void to(View view) {
        // create new intent
        Intent intent = new Intent(this, SelectLocActivity.class);
        // "to" direction
        intent.putExtra("direction", "TO");
        // "to" location
        intent.putExtra("location", to);
        // SelectLocActivity == 1
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // SelectLocActivity
        if (requestCode == 1) {
            // make sure the request was successful
            if (resultCode == RESULT_OK) {
                if (data.getStringExtra("direction").equals("FROM")) {
                    // update "from" location
                    from = data.getStringExtra("location");
                    input_from.setText(from);

                    // make sure one location is Homewood
                    if (!from.equals("Homewood")) {
                        to = "Homewood";
                        input_to.setText(to);
                    }
                }
                if (data.getStringExtra("direction").equals("TO")) {
                    // update "to" location
                    to = data.getStringExtra("location");
                    input_to.setText(to);

                    // make sure one location is Homewood
                    if (!to.equals("Homewood")) {
                        from = "Homewood";
                        input_from.setText(from);
                    }
                }
            }
        }

        // SelectDateActivity
        if (requestCode == 2) {
            // make sure the request was successful
            if (resultCode == RESULT_OK) {
            }
        }

        // SelectTimeActivity
        if (requestCode == 3) {
            // make sure the request was successful
            if (resultCode == RESULT_OK) {
            }
        }

        // NotesActivity
        if (requestCode == 4) {
            // make sure the request was successful
            if (resultCode == RESULT_OK) {
            }
        }

    }
}
