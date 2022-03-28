package com.saya.coco.clubs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.saya.coco.MainActivity;
import com.saya.coco.R;

public class InfoRedCross extends AppCompatActivity {

    Button b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_red_cross);

        b3 = findViewById(R.id.infoBackBtn);
        b3.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            //Go back to Red Cross Card view in view pager Main Activity
        });
    }
}