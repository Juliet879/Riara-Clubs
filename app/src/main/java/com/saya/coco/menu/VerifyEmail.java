package com.saya.coco.menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;

import com.saya.coco.R;

public class VerifyEmail extends AppCompatActivity {

    Button b;

    public CardView c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#ebf5f0"));
        setContentView(R.layout.activity_verify_email);

        c = findViewById(R.id.emailVerify);
        c.setOnClickListener(view -> {


            //Send an email with a verify button to student.
            //Upon clicking the verify button in email the student should be notified
            //that the email has been verified and should be automatically redirected to
            // the navigation drawer and show a tick alongside Verify Email to show email has been verified.

        });

        b = findViewById(R.id.close_icon);
        b.setOnClickListener(view -> {

            //Go back to navigation drawer on click of close icon
            /*Intent i = new Intent(VerifyPhone.this, MainActivity.class);
            startActivity(i);*/
        });


    }
}