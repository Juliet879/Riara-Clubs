package com.saya.coco.events;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.saya.coco.R;
import com.saya.coco.db.AppDatabase;
import com.saya.coco.db.EventModel;

import java.util.ArrayList;
import java.util.List;

public class MusicEvents extends AppCompatActivity  {

    MusicRecyclerViewList adapter;
    ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list);

//        ArrayList<String> animalNames = new ArrayList<>();
//        animalNames.add("Horse");
//        animalNames.add("Cow");
//        animalNames.add("Camel");
//        animalNames.add("Sheep");
//        animalNames.add("Goat");

        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        List<EventModel> eventsList = db.musicEventDao().getAllEvents();

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.musicRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MusicRecyclerViewList(this, eventsList);
        recyclerView.setAdapter(adapter);

        backBtn = findViewById(R.id.backBtn);

        backBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, Event.class);
            intent.putExtra("musicCounter", eventsList.size());
            startActivity(intent);
            finish();
        });
    }

 }