package com.saya.coco.menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;

import com.saya.coco.MainActivity;
import com.saya.coco.NotificationRecyclerView;
import com.saya.coco.R;
import com.saya.coco.db.AppDatabase;
import com.saya.coco.db.EventModel;
import com.saya.coco.db.NotificationModel;

import java.util.ArrayList;
import java.util.List;

public class Notifications extends AppCompatActivity {
    NotificationRecyclerView adapter;
    ImageView backBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#e0e0e0"));
        setContentView(R.layout.activity_notifications);

//        String musicNotification = getIntent().getStringExtra("musicNotification");
//        String beYouNotification = getIntent().getStringExtra("beYouNotification");
//        String redXNotification = getIntent().getStringExtra("redXNotification");
//        ArrayList<String> notification = new ArrayList<String>();
//        notification.add(musicNotification);
//        notification.add(beYouNotification);
//        notification.add(redXNotification);

        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        List<NotificationModel> notification = db.notificationsDao().getAllNotifications();


        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.notificationsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NotificationRecyclerView(this, notification);
        recyclerView.setAdapter(adapter);

        backBtn = findViewById(R.id.backBtn);

        backBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }
}