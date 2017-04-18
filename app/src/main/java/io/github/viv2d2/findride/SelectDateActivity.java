package io.github.viv2d2.findride;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Selects date.
 */
//SARAH

public class SelectDateActivity extends AppCompatActivity {

    private String dayOfWeek;
    private String monthStr;
    private int dayInt;
    private int yearInt;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_date);
        DatePicker datePicker = (DatePicker) findViewById(R.id.date_picker);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        // SET DATE PICKER TO WHATEVER WAS SENT IN FROM INTENT
        dayOfWeek = "";
        monthStr = "";
        dayInt = 0;
        yearInt = 0;

        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {

            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int dayOfMonth) {

                SimpleDateFormat simpledateformat = new SimpleDateFormat("EEEE");
                Date date = new Date(year, month, dayOfMonth-1);
                dayOfWeek = simpledateformat.format(date);
                monthStr = new DateFormatSymbols().getMonths()[month].toString().substring(0,3);
                dayInt = dayOfMonth;
                yearInt = year;

                //if (year >= Calendar.YEAR && month >= Calendar.MONTH && dayOfMonth >= Calendar.DAY_OF_MONTH) {
                /*Intent intent = getIntent();
                intent.putExtra("selected_date", dayOfWeek.substring(0, 3) + " " + monthStr + " " + dayOfMonth + " " + year);
                setResult(Activity.RESULT_OK, intent);
                finish();*/
                // Toast.makeText(getApplicationContext(), "You cannot schedule a ride in the past", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void save(View view) {
        Intent intent = new Intent();
        intent.putExtra("selected_date", dayOfWeek.substring(0, 3) + " " + monthStr + " " + dayInt + " " + yearInt);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
