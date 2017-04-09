package io.github.viv2d2.findride;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Selects start/end location.
 */

public class SelectLocActivity extends AppCompatActivity {

    private final String check = "✔️";
    private String dir;
    private String loc;
    private TextView homewood;
    private TextView homewood_check;
    private TextView bwi;
    private TextView bwi_check;
    private TextView tj;
    private TextView tj_check;
    private TextView wf;
    private TextView wf_check;
    private TextView hm;
    private TextView hm_check;
    private TextView special;
    private TextView special_check;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_loc);

        // Get direction ("from" or "to")
        // set heading text based on direction?
        dir = getIntent().getStringExtra("direction");
        loc = getIntent().getStringExtra("location");

        // Initialize all variables
        homewood_check = (TextView) findViewById(R.id.homewood_check);
        homewood = (TextView) findViewById(R.id.homewood);
        bwi_check = (TextView) findViewById(R.id.bwi_check);
        bwi = (TextView) findViewById(R.id.bwi);
        tj_check = (TextView) findViewById(R.id.tj_check);
        tj = (TextView) findViewById(R.id.tj);
        wf_check = (TextView) findViewById(R.id.wf_check);
        wf = (TextView) findViewById(R.id.wf);
        hm_check = (TextView) findViewById(R.id.hm_check);
        hm = (TextView) findViewById(R.id.hm);
        special_check = (TextView) findViewById(R.id.special_check);
        special = (TextView) findViewById(R.id.special);

        // check next to selected loc
        if (loc.equals("Homewood")) {
            homewood_check.setText(check);
        } else if (loc.equals("BWI")) {
            bwi_check.setText(check);
        } else if (loc.equals("Trader Joe's")) {
            tj_check.setText(check);
        } else if (loc.equals("Whole Foods")) {
            wf_check.setText(check);
        } else if (loc.equals("HMart")) {
            hm_check.setText(check);
        } else { // special event
            special_check.setText(check);
        }
    }

    public void homewood(View view) {
        // update selection
        clearChecks();
        homewood_check.setText(check);
        // save selected location
        loc = homewood.getText().toString();
    }

    public void bwi(View view) {
        // update selection
        clearChecks();
        bwi_check.setText(check);
        // save selected location
        loc = bwi.getText().toString();
    }

    public void tj(View view) {
        // update selection
        clearChecks();
        tj_check.setText(check);
        // save selected location
        loc = tj.getText().toString();
    }

    public void wf(View view) {
        // update selection
        clearChecks();
        wf_check.setText(check);
        // save selected location
        loc = wf.getText().toString();
    }

    public void hm(View view) {
        // update selection
        clearChecks();
        hm_check.setText(check);
        // save selected location
        loc = hm.getText().toString();
    }

    public void special(View view) {
        // update selection
        clearChecks();
        special_check.setText(check);
        // save selected location
        loc = special.getText().toString();
    }

    /** Gets rid of all check marks. */
    public void clearChecks() {
        homewood_check.setText("");
        bwi_check.setText("");
        tj_check.setText("");
        wf_check.setText("");
        hm_check.setText("");
        special_check.setText("");

    }

    public void save(View view) {
        Intent intent = new Intent();
        // send direction back
        intent.putExtra("direction", dir);
        // send selected location back
        intent.putExtra("location", loc);
        setResult(RESULT_OK, intent);
        finish();
    }
}
