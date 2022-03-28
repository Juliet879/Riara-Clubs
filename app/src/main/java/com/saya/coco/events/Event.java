package com.saya.coco.events;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.saya.coco.AddBeYouEvent;
import com.saya.coco.AddMusicEvent;
import com.saya.coco.AddRedXEvent;
import com.saya.coco.Counter;
import com.saya.coco.MainActivity;
import com.saya.coco.R;
import com.saya.coco.UserHelper;
import com.saya.coco.db.AppDatabase;
import com.saya.coco.db.CounterDao;
import com.saya.coco.db.EventModel;

import java.util.List;

public class Event extends AppCompatActivity {
    private static final int GET_VALUES_REQUEST_ID = 1;
    public static final String FIRST_VALUE_ID = "first_value_id";
    public static final String SECOND_VALUE_ID = "second_value_id";
    private static final int DEFAULT_VALUE = 0;
    FirebaseUser counter;
    DatabaseReference reference;
    String counterID;
    public CardView c1, c2, c3;

    TextView tv1, tv2, tv3, tv4, redCrossCount, musicCount, beYouCount;

    ImageView iv, backArrow, addMusicIcon, addBeYouIcon, addRedXIcon;

    CardView redxCardView, musicCardView, beYouCardView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#bfb8da"));
        setContentView(R.layout.activity_event);

        backArrow = findViewById(R.id.back2);
        addMusicIcon = findViewById(R.id.addMusicIcon);
        addBeYouIcon = findViewById(R.id.addBeYouIcon);
        addRedXIcon = findViewById(R.id.addRedXIcon);
        redxCardView = findViewById(R.id.redCrossEvent_cardView);
        musicCardView = findViewById(R.id.musicEvent_cardView);
        beYouCardView = findViewById(R.id.musicEvent_cardView);


        redCrossCount = findViewById(R.id.tv_redCrossCount);
        musicCount = findViewById(R.id.tv_musicCount);
        beYouCount = findViewById(R.id.tv_beYouCount);


        backArrow.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });


//        Red Cross count upon card clicking
        redxCardView.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), RedCrossEvents.class);
            startActivity(intent);
        });
        Intent rIntent = getIntent();
        int redxEvents = rIntent.getIntExtra("redCounter", 0);
        redCrossCount.setText(String.valueOf(redxEvents));


//        Music count upon card clicking
        musicCardView.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MusicEvents.class);
            startActivity(intent);
        });
        Intent mIntent = getIntent();
        int musicEvents = mIntent.getIntExtra("musicCounter", 0);
        musicCount.setText(String.valueOf(musicEvents));


//        Be You count upon card clicking
        beYouCardView.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), BeYouEvents.class);
//            startActivityForResult(intent, GET_VALUES_REQUEST_ID);
            startActivity(intent);
        });
        Intent bIntent = getIntent();
        int beYouEvents = bIntent.getIntExtra("beYouCounter", 0);
        beYouCount.setText(String.valueOf(beYouEvents));


        int totalEvents = redxEvents + musicEvents + beYouEvents;
        Toast.makeText(this,"You have " + totalEvents + " events",Toast.LENGTH_SHORT).show();




        //Set text of Club in Add Event form as clubnametext if c1, c2, or c3 is chosen
        //Pass the value to Add Event class and store it in tv3 in same class
        c1 = findViewById(R.id.beYouEvent_cardView);
        c1.setOnClickListener(view -> {

            Intent i1 = new Intent(this, BeYouEvents.class);
            i1.putExtra("clubnametext", "Be You Club");
            startActivity(i1);

        });


        c2 = findViewById(R.id.musicEvent_cardView);
        c2.setOnClickListener(view -> {

            Intent i2 = new Intent(this, MusicEvents.class);
//            i2.putExtra("clubnametext", "Music Club");
            startActivity(i2);

        });


        c3 = findViewById(R.id.redCrossEvent_cardView);
        c3.setOnClickListener(view -> {

            Intent i3 = new Intent(this, RedCrossEvents.class);
            i3.putExtra("clubnametext", "Red Cross Club");
            startActivity(i3);
        });


//   Saving counter Number
        counter = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("eventsNum");
        counterID = counter.getUid();

        reference.child(counterID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Counter counterNum = snapshot.getValue(Counter.class);

                if (counterNum != null){
                    redCrossCount.setText(counterNum.getEventsNumber());
                    musicCount.setText(counterNum.getEventsNumber());
                    beYouCount.setText(counterNum.getEventsNumber());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        addBeYouIcon.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddBeYouEvent.class);
            startActivity(intent);
        });
        addMusicIcon.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddMusicEvent.class);
            startActivity(intent);
        });
        addRedXIcon.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddRedXEvent.class);
            startActivity(intent);
        });

    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//        switch (requestCode) {
//            case GET_VALUES_REQUEST_ID: {
//                if (Activity.RESULT_OK == resultCode) {
//                    beYouCount.setText(data.getIntExtra(FIRST_VALUE_ID,DEFAULT_VALUE));
//                }
//                break;
//            }
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }
}