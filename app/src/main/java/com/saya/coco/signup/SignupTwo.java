package com.saya.coco.signup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.saya.coco.LogIn;
import com.saya.coco.R;

import java.util.HashMap;
import java.util.Map;

public class SignupTwo extends AppCompatActivity {

    ImageView backArrow;

    public CardView c;

    DatabaseReference reference;

    RadioButton rb1, rb2, rb3, rb4, rb5, rb6, rb7, rb8, rb9, rb10;

    RadioGroup radioGroup1, radioGroup2, radioGroup3, radioGroupYear;

    boolean valid;

    public static String first;
    public static String last;
    public static String email;
    public static String studentID;
    public static String num;

    public static String school;
    public static String yr;

    public static Intent i = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#e0e0e0"));
        setContentView(R.layout.activity_signup_two);

        // TODO : Save data entered in SignupOne activity form so that when a student clicks on this arrow they do not have to fill in the form again.
        //TODO : Do the same for SignupThree and SignupLast activities
        backArrow = findViewById(R.id.back1);
        backArrow.setOnClickListener(view -> {
            Intent i = new Intent(SignupTwo.this, SignupOne.class);
            startActivity(i);
        });


        //Log in textview to take user to LogIn activity if they have already registered
        final TextView tv = findViewById(R.id.login2);
        tv.setOnClickListener(view -> {
            Intent i2 = new Intent(SignupTwo.this, LogIn.class);
            startActivity(i2);
        });


        radioGroup1 = findViewById(R.id.rg1);
        radioGroup2 = findViewById(R.id.rg2);
        radioGroup3 = findViewById(R.id.rg3);
        radioGroupYear = findViewById(R.id.rg_year);

        valid = true;


        //Only select one radiobutton.
        //Used this format(Line 71 - 150) cause of using 3 different radiogroups inside one main one.
        rb1 = findViewById(R.id.rb_SOB);
        rb1.setOnClickListener(v -> {
            rb1.setChecked(true);
            rb2.setChecked(false);
            rb3.setChecked(false);
            rb4.setChecked(false);
            rb5.setChecked(false);
            rb6.setChecked(false);

            //If rb1 is checked set value school as Business.
            //Pass this data using outExtra to next activity, SignupThree
            i.putExtra("faculty", school);
            school = "Business";
        });

        rb2 = findViewById(R.id.rb_SCS);
        rb2.setOnClickListener(v -> {
            rb1.setChecked(false);
            rb2.setChecked(true);
            rb3.setChecked(false);
            rb4.setChecked(false);
            rb5.setChecked(false);
            rb6.setChecked(false);


            i.putExtra("faculty", school);
            school = "Computing Science";
        });

        rb3 = findViewById(R.id.rb_SOE);
        rb3.setOnClickListener(v -> {
            rb1.setChecked(false);
            rb2.setChecked(false);
            rb3.setChecked(true);
            rb4.setChecked(false);
            rb5.setChecked(false);
            rb6.setChecked(false);

            i.putExtra("faculty", school);
            school = "Education";
        });

        rb4 = findViewById(R.id.rb_SIR);
        rb4.setOnClickListener(v -> {
            rb1.setChecked(false);
            rb2.setChecked(false);
            rb3.setChecked(false);
            rb4.setChecked(true);
            rb5.setChecked(false);
            rb6.setChecked(false);

            i.putExtra("faculty", school);
            school = "International Relations & Diplomacy";
        });

        rb5 = findViewById(R.id.rb_SOJ);
        rb5.setOnClickListener(v -> {
            rb1.setChecked(false);
            rb2.setChecked(false);
            rb3.setChecked(false);
            rb4.setChecked(false);
            rb5.setChecked(true);
            rb6.setChecked(false);

            i.putExtra("faculty", school);
            school = "Multimedia & Journalism";
        });

        rb6 = findViewById(R.id.rb_RLS);
        rb6.setOnClickListener(v -> {
            rb1.setChecked(false);
            rb2.setChecked(false);
            rb3.setChecked(false);
            rb4.setChecked(false);
            rb5.setChecked(false);
            rb6.setChecked(true);


            i.putExtra("faculty", school);
            school = "Law";
        });


        //Radiobuttons for choosing student year
        rb7 = findViewById(R.id.rb_One);
        rb8 = findViewById(R.id.rb_Two);
        rb9 = findViewById(R.id.rb_Three);
        rb10 = findViewById(R.id.rb_Four);


        //Cardview to go to SignupThree activity
        c = findViewById(R.id.next2_cardView);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateFacultyAndYear()) {
                    i = new Intent(SignupTwo.this, SignupThree.class);
                    i.putExtra("fName", first);
                    i.putExtra("lName", last);
                    i.putExtra("mail", email);
                    i.putExtra("schoolID", studentID);
                    i.putExtra("number", num);
                    i.putExtra("faculty", school);
                    i.putExtra("year", yr);
                    startActivity(i);

//                 Updating the database with year and faculty
//                    String userId = getIntent().getStringExtra("userID");
//                    reference = FirebaseDatabase.getInstance().getReference("users");
//                    DatabaseReference ref = reference.child(userId);
//
//                    Map<String, Object> childUpdates = new HashMap<>();
//                    childUpdates.put("faculty", school);
//                    childUpdates.put("year", yr);
//                    ref.updateChildren(childUpdates);
//                    //Get student data and store it under key(name)
//                    i = new Intent(SignupTwo.this, SignupThree.class);
//                    i.putExtra("userID", userId);
//                    startActivity(i);
                }
            }

            private boolean validateFacultyAndYear() {
                //Check if only one radio group is selected
                if (radioGroup1.getCheckedRadioButtonId() == -1 &&
                        radioGroup2.getCheckedRadioButtonId() == -1 &&
                        radioGroup3.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(SignupTwo.this, "Please select your faculty", Toast.LENGTH_SHORT).show();
                    valid = false;
                }

                if (radioGroupYear.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(SignupTwo.this, "Please select your year", Toast.LENGTH_SHORT).show();
                    valid = false;
                }

                return valid;

            }
        });

        //Get data passed from SignupOne activity
        Intent i = getIntent();
        first = i.getStringExtra("fName");
        last = i.getStringExtra("lName");
        email = i.getStringExtra("mail");
        studentID = i.getStringExtra("schoolID");
        num = i.getStringExtra("number");
    }


    public void onYearClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.rb_One:
                if (checked)
                    yr = "One";
                break;

            case R.id.rb_Two:
                if (checked)
                    yr = "Two";
                break;

            case R.id.rb_Three:
                if (checked)
                    yr = "Three";
                break;

            case R.id.rb_Four:
                if (checked)
                    yr = "Four";
                break;

        }
    }
}