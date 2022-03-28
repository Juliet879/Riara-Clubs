package com.saya.coco;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;

import java.util.ArrayList;

public class EventRepeat extends AppCompatActivity {

    RadioButton rb_never, rb_biweekly, rb_monthly, rb_annually;
    ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#6f5f90"));
        setContentView(R.layout.activity_event_repeat);

        //Event frequency. Either Never, Biweekly, Monthly, or Annually.
        rb_never = findViewById(R.id.never);
        backBtn = findViewById(R.id.backBtn);

        rb_never.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                rb_never.setTextColor(Color.parseColor("#635581"));
            } else
                rb_never.setTextColor(Color.BLACK);
        });

        rb_biweekly = findViewById(R.id.biweekly);
        rb_biweekly.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                rb_biweekly.setTextColor(Color.parseColor("#635581"));
            } else
                rb_biweekly.setTextColor(Color.BLACK);
        });

        rb_monthly = findViewById(R.id.monthly);
        rb_monthly.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                rb_monthly.setTextColor(Color.parseColor("#635581"));
            } else
                rb_monthly.setTextColor(Color.BLACK);
        });

        rb_annually = findViewById(R.id.yearly);
        rb_annually.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                rb_annually.setTextColor(Color.parseColor("#635581"));
            } else
                rb_annually.setTextColor(Color.BLACK);
        });


        ContentValues values = new ContentValues();




        ArrayList<RadioButton> frequencies = new ArrayList<RadioButton>();
        frequencies.add(rb_annually);
        frequencies.add(rb_biweekly);
        frequencies.add(rb_never);
        frequencies.add(rb_monthly);


        backBtn.setOnClickListener( v ->{
            for (RadioButton frequency : frequencies) {
                if (frequency.isChecked()) {
                    if (frequency == rb_annually) {
                        Intent i1 = new Intent(EventRepeat.this, AddBeYouEvent.class);
                        i1.putExtra(AddBeYouEvent.FIRST_VALUE_ID, "Yearly");
                        setResult(Activity.RESULT_OK,i1);
                        finish();
                    }
                    if (frequency == rb_never) {
                        Intent i1 = new Intent(EventRepeat.this, AddBeYouEvent.class);
                        i1.putExtra(AddBeYouEvent.FIRST_VALUE_ID, "Never");
                        setResult(Activity.RESULT_OK,i1);
                        finish();
                    }
                    if (frequency == rb_biweekly) {
                        Intent i1 = new Intent(EventRepeat.this, AddBeYouEvent.class);
                        i1.putExtra(AddBeYouEvent.FIRST_VALUE_ID, "Biweekly");
                        setResult(Activity.RESULT_OK,i1);
                        finish();
                    }
                    if (frequency == rb_monthly) {
                        Intent i1 = new Intent(EventRepeat.this, AddBeYouEvent.class);
                        i1.putExtra(AddBeYouEvent.FIRST_VALUE_ID, "Monthly");
                        setResult(Activity.RESULT_OK,i1);
                        finish();
                    }
                }
            }
        });

    }

    public void onRadioButtonClick(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.never:
                if (checked)
                    break;

            case R.id.biweekly:
                if (checked)
                    break;

            case R.id.monthly:
                if (checked)
                    break;

            case R.id.yearly:
                if (checked)
                    break;
        }
    }
}