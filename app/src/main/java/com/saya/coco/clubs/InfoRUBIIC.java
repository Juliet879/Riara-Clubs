package com.saya.coco.clubs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.saya.coco.MainActivity;
import com.saya.coco.R;

public class InfoRUBIIC extends AppCompatActivity {

    Button b4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_rubiic);

        b4 = findViewById(R.id.infoBackBtn);
        b4.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            //Go back to RUBIIC Card view in view pager Main Activity
        });
    }
}