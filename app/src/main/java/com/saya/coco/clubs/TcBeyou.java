package com.saya.coco.clubs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.saya.coco.db.AppDatabase;
import com.saya.coco.db.Club;
import com.saya.coco.MainActivity;
import com.saya.coco.R;
import com.saya.coco.db.EventModel;
import com.saya.coco.db.NotificationModel;

public class TcBeyou extends AppCompatActivity {

    CheckBox cb;
    Button b1, b2;

    public static String clubName;
    public static String ClubID;
    public static int noOfStudents;

    FirebaseUser user;

    public static Intent myIntent;

    public static Intent ii = new Intent();

    int count;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#e3e3e3"));
        setContentView(R.layout.activity_tc_beyou);

        //Background color of checkbox button
        cb = findViewById(R.id.cbAgree);
        cb.setOnCheckedChangeListener((compoundButton, b) -> {
            if (compoundButton.isChecked()) {
                cb.setButtonTintList(ColorStateList.valueOf(getApplicationContext().getColor(R.color.checkYes)));
            } else {
                cb.setButtonTintList(ColorStateList.valueOf(getApplicationContext().getColor(R.color.checkNo)));
            }
        });


        //Go back to the card view position, in this case 0, when b1 is clicked.
        b1 = findViewById(R.id.cancelButton);
        b2 = findViewById(R.id.joinButton);

        b1.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class );
            startActivity(intent);
        });
        b2.setOnClickListener(view -> {
            long mLastClickTime = 0;
            if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                if (cb.isChecked()) {
                    String club = "Be You Club";
                    String notificationMessage = "Thank you for joining BeYou Club";
                    Toast.makeText(this, notificationMessage, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    saveNotification(notificationMessage, club);
                    startActivity(intent);
                } else {
                    //Student should accept the T&C first before joining any club
                    Toast.makeText(this, "Please accept the Terms and Conditions", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(this, "Already Joined The Be You Club", Toast.LENGTH_SHORT).show();
            }
        });

        //noOfStudents to show the number of students in a particular club
        count = 0;
        noOfStudents = count++;

        //Data passed from Adapter class.
        myIntent = getIntent();
        clubName = myIntent.getStringExtra("cName");
        noOfStudents = myIntent.getIntExtra("noOfStudents", 0);

    }

    private void saveNotification(String notification, String clubName) {
        AppDatabase db  = AppDatabase.getDbInstance(getApplicationContext());
//        Adding Notification
        NotificationModel noty = new NotificationModel();
        noty.notification = notification;
        db.notificationsDao().insertEvent(noty);

//        Adding ClubName
        Club club = new Club();
        club.clubName= clubName;
        db.clubDao().insertClub(club);

        finish();
    }

    //A club should not be registered twice
//    private void storeNewClubData() {
//
//        FirebaseDatabase db = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = db.getReference("clubs");
//
//        ClubID = user.getUid();
//
//        Club addNewClub = new Club(clubName, noOfStudents, ClubID);
//
//        myRef.child(ClubID).setValue(addNewClub);
//
//    }
}