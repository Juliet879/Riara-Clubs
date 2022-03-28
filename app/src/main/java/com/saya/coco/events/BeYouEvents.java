package com.saya.coco.events;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.saya.coco.Counter;
import com.saya.coco.R;
import com.saya.coco.db.AppDatabase;
import com.saya.coco.db.EventModel;

import java.util.ArrayList;
import java.util.List;

public class BeYouEvents extends AppCompatActivity {

    BeYouRecyclerViewAdapter adapter;
    ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_be_you_events);

//        Loading the list from the database
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        List<EventModel> eventsList = db.beYouEventDao().getAllEvents();


        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.beYouRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BeYouRecyclerViewAdapter(this, eventsList);
        recyclerView.setAdapter(adapter);


        backBtn = findViewById(R.id.backBtn);

        backBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, Event.class);
            intent.putExtra("beYouCounter", eventsList.size());
            startActivity(intent);
            finish();
//            intent.putExtra(Event.FIRST_VALUE_ID,eventsList.size());
//            setResult(Activity.RESULT_OK,intent);
//            finish();
        });
    }
}