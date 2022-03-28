package com.saya.coco.signup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.saya.coco.LogIn;
import com.saya.coco.R;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class SignupThree extends AppCompatActivity {
    DatabaseReference reference;

    ImageView backArrow;
    public CardView c;

    RadioButton rb_m, rb_f;

    RadioGroup radioGroup;

    DatePicker datePicker;

    boolean valid;

    public static String first;
    public static String last;
    public static String email;
    public static String studentID;
    public static String num;

    public static String school;
    public static String yr;

    public static String sex;
    public static int age;

    public static Intent i = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#e0e0e0"));
        setContentView(R.layout.activity_signup_three);

        //Back arrow to go back to the 2nd sign up screen
        backArrow = findViewById(R.id.back2);
        backArrow.setOnClickListener(view -> {
            Intent i;
            i = new Intent(SignupThree.this, SignupTwo.class);
            startActivity(i);
        });

        final TextView tv = findViewById(R.id.login3);
        tv.setOnClickListener(view -> {
            Intent i2;
            i2 = new Intent(SignupThree.this, LogIn.class);
            startActivity(i2);
        });

        //Change color of text female to white when radio button is checked
        rb_f = findViewById(R.id.rb_Female);
        rb_f.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (isChecked) {
                rb_f.setTextColor(Color.WHITE);
            } else
                rb_f.setTextColor(Color.BLACK);
        });

        //Change color of text male to white when radio button is checked
        rb_m = findViewById(R.id.rb_Male);
        rb_m.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (isChecked) {
                rb_m.setTextColor(Color.WHITE);
            } else
                rb_m.setTextColor(Color.BLACK);
        });

        radioGroup = findViewById(R.id.rg_gender);

        datePicker = findViewById(R.id.datePicker);

        valid = true;

        //next card view to open the last sign up screen
        //check whether user has selected gender and correct age
        c = findViewById(R.id.next3_cardView);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateGenderAndDate()) {
                    i = new Intent(SignupThree.this, SignupLast.class);
                    i.putExtra("fName", first);
                    i.putExtra("lName", last);
                    i.putExtra("mail", email);
                    i.putExtra("schoolID", studentID);
                    i.putExtra("number", num);
                    i.putExtra("faculty", school);
                    i.putExtra("year", yr);
                    i.putExtra("gender", sex);
                    i.putExtra("userAge", age);
                    startActivity(i);
//                 Updating the database with year and faculty
//                    String userId = getIntent().getStringExtra("userID");
//                    reference = FirebaseDatabase.getInstance().getReference("users");
//                    DatabaseReference ref = reference.child(userId);
//
//                    Map<String, Object> childUpdates = new HashMap<>();
//                    childUpdates.put("userAge", age);
//                    childUpdates.put("gender", sex);
//                    ref.updateChildren(childUpdates);
//                    //Get student data and store it under key(name)
//                    i = new Intent(SignupThree.this, SignupLast.class);
//                    i.putExtra("userID", userId);
//                    startActivity(i);
                }
            }

            private boolean validateGenderAndDate() {
                //Check if at least one radio button is selected
                if (radioGroup.getCheckedRadioButtonId() == -1 ) {
                    Toast.makeText(SignupThree.this, "Please select gender", Toast.LENGTH_SHORT).show();
                    valid = false;
                } else {
                    valid = true;
                }

                //Get student's age and by getting dob of student and calculating student's age and storing the calculated age in database
                int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                int userAge = datePicker.getYear();
                age = currentYear - userAge;

                if (age < 14 || age > 50) {
                    Toast.makeText(SignupThree.this, "Please select validate date", Toast.LENGTH_SHORT).show();
                    valid = false;
                } else {
                    valid = true;
                }

                return valid;
            }
        });

        Intent i = getIntent();
        first = i.getStringExtra("fName");
        last = i.getStringExtra("lName");
        email = i.getStringExtra("mail");
        studentID = i.getStringExtra("schoolID");
        num = i.getStringExtra("number");
        school = i.getStringExtra("faculty");
        yr = i.getStringExtra("year");

    }

    public void onGenderClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.rb_Female:
                if (checked)
                    sex = "Female";
                break;

            case R.id.rb_Male:
                if (checked)
                    sex = "Male";
                break;
        }
    }
}