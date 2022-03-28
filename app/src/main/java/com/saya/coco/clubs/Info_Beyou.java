package com.saya.coco.clubs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;

import com.saya.coco.MainActivity;
import com.saya.coco.R;

public class Info_Beyou extends AppCompatActivity {

    Button b1;

    //About us information for Be You Club
    //Activity that opens on click of Info textview and icon in Be You Club cardview in Main Activity.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#e3e3e3"));
        setContentView(R.layout.activity_info_beyou);

        b1 = findViewById(R.id.infoBackBtn);
        b1.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            //Go back to Be You Card view in view pager Main Activity
        });
    }
}