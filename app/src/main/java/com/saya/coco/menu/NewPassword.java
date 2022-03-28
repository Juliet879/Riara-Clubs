package com.saya.coco.menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.textfield.TextInputLayout;
import com.saya.coco.R;

public class NewPassword extends AppCompatActivity {

    TextInputLayout newPass, confirmNewPass;

    public CardView c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#ebf5f0"));
        setContentView(R.layout.activity_new_password);

        newPass = findViewById(R.id.new_pass);
        confirmNewPass = findViewById(R.id.confirm_new_pass);

        c = findViewById(R.id.ok_cardView);
        c.setOnClickListener(view -> {
            //Put code for saving updated password in firebase database upon getting user ID.
        });

    }
}