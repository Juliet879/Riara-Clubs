package com.saya.coco.menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.saya.coco.R;
import com.saya.coco.UserHelper;
import com.saya.coco.db.AppDatabase;
import com.saya.coco.db.Club;
import com.saya.coco.ClubRecyClerView;

import java.util.ArrayList;
import java.util.List;

public class Profile extends AppCompatActivity {

    FirebaseUser user;
    DatabaseReference reference;
    String userID;
    ClubRecyClerView adapter;
    TextView tvName, tvEmail, tvSchool, tvYr, tvID, tvGender, tvAge, tvContact;
    RecyclerView clubList;


     @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         getWindow().setStatusBarColor(Color.parseColor("#e0e0e0"));
         setContentView(R.layout.activity_profile);

         tvName = findViewById(R.id.studentName);
         tvEmail = findViewById(R.id.studentEmail);
         tvSchool = findViewById(R.id.studentSchool);
         tvYr = findViewById(R.id.studentYear);
         tvID = findViewById(R.id.studentID);
         tvGender = findViewById(R.id.studentGender);
         tvAge = findViewById(R.id.studentAge);
         tvContact = findViewById(R.id.studentContact);
         clubList = findViewById(R.id.clubsRecyclerView);


         AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
         List<Club> clubs = db.clubDao().getAllClubs();
         RecyclerView recyclerView = findViewById(R.id.clubsRecyclerView);
         recyclerView.setLayoutManager(new LinearLayoutManager(this));
         adapter = new ClubRecyClerView(this, clubs);
         recyclerView.setAdapter(adapter);


         user = FirebaseAuth.getInstance().getCurrentUser();
         reference = FirebaseDatabase.getInstance().getReference("riara");
         userID = user.getUid();

         reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot snapshot) {
                 UserHelper userProfile = snapshot.getValue(UserHelper.class);

                 if (userProfile != null){
                     tvName.setText(userProfile.getFirst());
                     tvEmail.setText(userProfile.getEmail());
                     tvID.setText(userProfile.getStudentID());
                     tvContact.setText(userProfile.getNum());
                     tvSchool.setText(userProfile.getSchool());
                     tvGender.setText(userProfile.getSex());
                     tvYr.setText(userProfile.getYr());

                 }
             }

             @Override
             public void onCancelled(@NonNull DatabaseError error) {

             }
         });


     }
}