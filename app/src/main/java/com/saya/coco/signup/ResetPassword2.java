package com.saya.coco.signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ImageView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.saya.coco.R;
import com.saya.coco.User;

public class ResetPassword2 extends AppCompatActivity {

    ImageView backArrow;

    public CardView c;
    PinView pinView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#e0e0e0"));
        setContentView(R.layout.activity_reset_password2);

//        get Phone number and email address
        String phoneNum = getIntent().getStringExtra("phoneNumber");
        String emailAdd = getIntent().getStringExtra("emailAcc");


        //Back arrow to go back to the ResetPassword screen
        backArrow = findViewById(R.id.backReset);
        backArrow.setOnClickListener(view -> {
            Intent i;
            i = new Intent(ResetPassword2.this, ResetPassword.class);
            startActivity(i);
        });

        c = findViewById(R.id.verifyCard);
        c.setOnClickListener(view -> {
            Toast.makeText(ResetPassword2.this,"Check Your Email for the password reset link",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(ResetPassword2.this, ResetPassword3.class);
            setUpOTPInputs();
            i.putExtra("emailAdd",emailAdd);
            startActivity(i);
        });
    }

    private void setUpOTPInputs(){
        pinView = findViewById(R.id.pinView);
        pinView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!pinView.toString().trim().isEmpty()){
                    pinView.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
}