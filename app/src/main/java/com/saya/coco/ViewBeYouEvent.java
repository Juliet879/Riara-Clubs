package com.saya.coco;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.saya.coco.db.AppDatabase;
import com.saya.coco.db.MusicEventDao;
import com.saya.coco.db.EventModel;
import com.saya.coco.events.BeYouEvents;
import com.saya.coco.events.Event;

public class ViewBeYouEvent extends AppCompatActivity {
    FirebaseUser user;
    DatabaseReference reference;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_be_you_view_events);
        EditText eventTitleView = findViewById(R.id.eventTitle);
        EditText eventLocationView = findViewById(R.id.eventLocation);
        Button eventDayView = findViewById(R.id.eventDay);
        Button startTimeView = findViewById(R.id.startTime);
        Button endTimeView = findViewById(R.id.endTime);
        TextView tvFrequency = findViewById(R.id.textviewFrequency);
        TextView tvEmailAcc = findViewById(R.id.emailAcc);
        Button saveChangesBtn = findViewById(R.id.btnAddEvent);


        ImageView backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(view -> {
            Intent intent = new Intent(ViewBeYouEvent.this, BeYouEvents.class);
            startActivity(intent);
        });
        String eventTitle = getIntent().getExtras().getString("eventTitle");
        eventTitleView.setText(eventTitle);

        String eventLocation = getIntent().getExtras().getString("eventLocation");
        eventLocationView.setText(eventLocation);

        String eventDay = getIntent().getExtras().getString("eventDay");
        eventDayView.setText(eventDay);

        String startTime = getIntent().getExtras().getString("startTime");
        startTimeView.setText(startTime);

        String endTime = getIntent().getExtras().getString("endTime");
        endTimeView.setText(endTime);

        String frequency = getIntent().getExtras().getString("frequency");
        tvFrequency.setText(frequency);


        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("riara");
        userID = user.getUid();

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserHelper userProfile = snapshot.getValue(UserHelper.class);

                if (userProfile != null){
                    tvEmailAcc.setText(userProfile.getEmail());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        saveChangesBtn.setOnClickListener(view -> {
            String club = "Be You Club";
            saveChanges(club,eventTitle,eventLocation,eventDay,startTime,endTime, frequency,tvEmailAcc.getText().toString());
        });
    }
//
    private void saveChanges(String clubName,String eventTitle, String eventLocation, String eventDay, String startTime, String endTime, String repeat, String calendar) {
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        EventModel updatedEvent = new EventModel(clubName,eventTitle,eventLocation,eventDay, startTime, endTime, repeat, calendar);
        db.beYouEventDao().updateEvent(updatedEvent);
    }

//    clubName, eventTitle, eventLocation, eventDay, startTime, endTime
//    saveUpdate After Editing
}