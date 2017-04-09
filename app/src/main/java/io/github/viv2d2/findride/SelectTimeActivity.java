package io.github.viv2d2.findride;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import org.w3c.dom.Text;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Selects date.
 */
//SARAH

public class SelectTimeActivity extends AppCompatActivity {

    private TextView mTimeDisplay;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_time);
        mTimeDisplay = (TextView) findViewById(R.id.time_input);
        final int timeArray[] = new int[2];

        final TimePicker timePicker = (TimePicker) findViewById(R.id.time_picker); //initiate a time picker


        final Button button = (Button) findViewById(R.id.confirm_date);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = getIntent();
                intent.putExtra("updated_time", timePicker.getCurrentHour() + ":" + timePicker.getCurrentMinute());
                setResult(Activity.RESULT_OK, intent);
                finish();

            }
        });


    }

}
