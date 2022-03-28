package com.saya.coco;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.saya.coco.db.AppDatabase;
import com.saya.coco.db.EventModel;
import com.saya.coco.events.Event;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import timber.log.Timber;

public class AddRedXEvent extends AppCompatActivity {
    Button btn1, btn2, btn3,addEventBtn;
    CardView cv;
    EditText event_Title, event_Location;
    private static final int GET_VALUES_REQUEST_ID = 1;
    public static final String FIRST_VALUE_ID = "first_value_id";
    private DatePickerDialog.OnDateSetListener dateSetListener;
    private TimePickerDialog.OnTimeSetListener timeSetListener;
    private TimePickerDialog.OnTimeSetListener setListener;

    DateFormat dateFormat;
    Calendar c, c1, c2;
    TextView tv, tv1, tv3, tv4;


    FirebaseAuth mAuth;
    FirebaseUser currentUser;

    public static String club;
    public static String title;
    public static String location;
    public static String date;
    public static String startTime;
    public static String endTime;
    public static String frequency;
    public static String emailAcc;
    public static Intent i5 = new Intent();


    boolean valid;

    public int count;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_red_xevent);

        getWindow().setStatusBarColor(Color.parseColor("#6f5f90"));

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        btn1 = findViewById(R.id.eventDay);
        btn2 = findViewById(R.id.startTime);
        btn3 = findViewById(R.id.endTime);

        //Background color of the buttons
        btn1.setBackgroundColor(Color.parseColor("#e0e0e0"));
        btn2.setBackgroundColor(Color.parseColor("#e0e0e0"));
        btn3.setBackgroundColor(Color.parseColor("#e0e0e0"));

        //Show today's date in button as default text
        c = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("dd MM yyy");
        dateFormat = SimpleDateFormat.getDateInstance(SimpleDateFormat.MEDIUM);
        String date1 = dateFormat.format(c.getTime());
        btn1.setText(date1);


        //Show today's time as default text in button
        c1 = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("hh mm");
        dateFormat = SimpleDateFormat.getTimeInstance(SimpleDateFormat.SHORT);
        String time1 = dateFormat.format(c1.getTime());
        btn2.setText(time1);


        //Show today's time as default text in button
        c2 = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("hh mm");
        dateFormat = SimpleDateFormat.getTimeInstance(SimpleDateFormat.SHORT);
        String time2 = dateFormat.format(c2.getTime());
        btn3.setText(time2);


        //User to choose date for event using datepicker dialog
        btn1.setOnClickListener(view -> {
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog = new DatePickerDialog(
                    AddRedXEvent.this, R.style.MySpinnerDatePickerStyle,
                    dateSetListener, year, month, day);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        });

        dateSetListener = (datePicker, year, month, day) -> {

            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day);
            Date d = calendar.getTime();

            //Below format for showing month name
            dateFormat = new SimpleDateFormat("dd mm yyy");
            dateFormat = SimpleDateFormat.getDateInstance(SimpleDateFormat.MEDIUM, Locale.UK);
            date = dateFormat.format(d);

            btn1.setText(date);


        };


        //User to choose start time for event
        btn2.setOnClickListener(view -> {
            Calendar cal2 = Calendar.getInstance();
            int hour = cal2.get(Calendar.HOUR_OF_DAY);
            int minute = cal2.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(
                    AddRedXEvent.this, R.style.MySpinnerTimePickerStyle,
                    timeSetListener, hour, minute, true);
            timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            timePickerDialog.show();
        });

        timeSetListener = (timePicker, hour, minute) -> {
            Timber.d("onTimeSet: hh mm: " + hour + minute);

            startTime = hour + ":" + minute;
            btn2.setText(startTime);

        };


        //User to choose end time for event
        btn3.setOnClickListener(view -> {
            Calendar cal3 = Calendar.getInstance();
            int hour = cal3.get(Calendar.HOUR_OF_DAY);
            int minute = cal3.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog1 = new TimePickerDialog(
                    AddRedXEvent.this, R.style.MySpinnerTimePickerStyle,
                    setListener, hour, minute, true);
            timePickerDialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            timePickerDialog1.show();
        });

        setListener = (timePicker, hour, minute) -> {
            Timber.d("onTimeSet: hh mm: " + hour + minute);


            endTime = hour + ":" + minute;
            btn3.setText(endTime);


        };


//            tv = findViewById(R.id.calAccount);


        //Cardview Repeat to open EventRepeat class for user to choose event frequency
        cv = findViewById(R.id.cardRepeat);
        cv.setOnClickListener(view -> {
            Intent i;
            i = new Intent(AddRedXEvent.this, EventRepeat.class);
            startActivityForResult(i, GET_VALUES_REQUEST_ID);
            startActivity(i);
        });



        //Frequency of the event repetition
        tv1 = findViewById(R.id.textviewFrequency);
        tv1.setText(R.string.never);

        Intent i = getIntent();
        frequency = i.getStringExtra("radiobuttontext");
        tv1.setText(frequency);

        //Textview Cancel to take user to Event class on click
        tv4 = findViewById(R.id.cancel);
        tv4.setOnClickListener(view -> {
            Intent i4;
            i4 = new Intent(AddRedXEvent.this, Event.class);
            startActivity(i4);
        });



        //Save data to database using the key in putExtra method
        Intent intent = getIntent();
        club = intent.getStringExtra("clubnametext");
        title = intent.getStringExtra("eventTitle");
        location = intent.getStringExtra("eventLocation");
        date = intent.getStringExtra("eventDay");
        startTime = intent.getStringExtra("eventStartTime");
        endTime = intent.getStringExtra("eventEndTime");
        frequency = intent.getStringExtra("radiobuttontext");

        valid = true;



        addEventBtn = findViewById(R.id.btnAddEvent);
        String club = "RedCross";
        final EditText eventNameInput = findViewById(R.id.eventTitle);
        final EditText eventLocationInput = findViewById(R.id.eventLocation);
        final Button eventDayInput = findViewById(R.id.eventDay);
        final Button startTimeInput = findViewById(R.id.startTime);
        final Button endTimeInput = findViewById(R.id.endTime);
        final TextView repeat = findViewById(R.id.textviewFrequency);
        final TextView mailAcc = findViewById(R.id.calendarAccount);

        addEventBtn.setOnClickListener(view -> {
            saveEvent(club,eventNameInput.getText().toString(),eventLocationInput.getText().toString(),eventDayInput.getText().toString(),startTimeInput.getText().toString(),endTimeInput.getText().toString(), repeat.getText().toString(),mailAcc.getText().toString());
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case GET_VALUES_REQUEST_ID: {
                if (Activity.RESULT_OK == resultCode) {
                    tv1.setText(data.getStringExtra(FIRST_VALUE_ID));
                }
                break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void saveEvent(String club, String eventTitle, String eventLocation, String eventDay, String startTime, String endTime, String frequency, String emailAcc) {
        AppDatabase db  = AppDatabase.getDbInstance(getApplicationContext());
        EventModel event = new EventModel();
        event.eventTitle = eventTitle;
        event.eventLocation = eventLocation;
        event.eventDay = eventDay;
        event.startTime = startTime;
        event.endTime = endTime;
        event.frequency = frequency;
        event.emailAcc = emailAcc;
        event.clubName = club;
        db.redXEventDao().insertEvent(event);
        finish();
    }
}

