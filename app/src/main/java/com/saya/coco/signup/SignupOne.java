package com.saya.coco.signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.saya.coco.LogIn;
import com.saya.coco.MainActivity;
import com.saya.coco.R;
import com.saya.coco.User;
import com.saya.coco.UserHelper;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupOne extends AppCompatActivity {

    //Variables
    CardView nextBtn;

    //Get data variables
    EditText fName, lName, mail, phone, schoolID;
    boolean valid;

    TextView login;

    public static String first;
    public static String last;
    public static String email;
    public static String studentID;
    public static String num;

    public static Intent i = new Intent();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#e0e0e0"));
        setContentView(R.layout.activity_signup_one);


        nextBtn = findViewById(R.id.next1_cardView);
        fName = findViewById(R.id.firstName);
        lName = findViewById(R.id.lastName);
        mail = findViewById(R.id.email_signup);
        phone = findViewById(R.id.contact);
        schoolID = findViewById(R.id.studentID);

        valid = true;


        login = findViewById(R.id.login1);
        login.setOnClickListener(view -> {
            Intent i = new Intent(SignupOne.this, LogIn.class);
            startActivity(i);
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validation()) {
                    first = fName.getText().toString().trim();
                    last = lName.getText().toString().trim();
                    studentID = schoolID.getText().toString().trim();
                    email = mail.getText().toString().trim();
                    num = phone.getText().toString().trim();;

                    i = new Intent(SignupOne.this, SignupTwo.class);
                    i.putExtra("fName", first);
                    i.putExtra("lName", last);
                    i.putExtra("mail", email);
                    i.putExtra("number", num);
                    i.putExtra("schoolID", studentID);
                    startActivity(i);

                }
            }

            private boolean validation() {


                String first = fName.getText().toString();
                String alphabetsOnly = "[a-zA-Z]+";

                String last = lName.getText().toString();

                String email = mail.getText().toString();
                String schoolEmail = "[a-zA-Z.a-zA-Z]+@riarauniversity.ac.ke";
                Pattern pattern = Pattern.compile(schoolEmail, Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(email);

                String studentNo = schoolID.getText().toString();
                String idPattern = "[0-9]{2}[a-zA-Z]{3}[0-9]{6}";

                String num = phone.getText().toString();

                if (first.isEmpty()) {
                    fName.setError("First name is required");
                    valid = false;
                } else if (!first.matches(alphabetsOnly)) {
                    fName.setError("Enter only alphabetical character");
                    valid = false;
                } else {
                    fName.setError(null);
                }


                if (last.isEmpty()) {
                    lName.setError("Last name is required");
                    valid = false;
                } else if (!last.matches(alphabetsOnly)) {
                    lName.setError("Enter only alphabetical character");
                    valid = false;
                } else {
                    lName.setError(null);
                }


                if (email.isEmpty()) {
                    mail.setError("Your school email is required");
                    valid = false;
                } else if (!email.matches(schoolEmail)) {
                    mail.setError("Enter valid school email (xxxxxx@riarauniversity.ac.ke)");
                    valid = false;
                } else {
                    mail.setError(null);
                    return matcher.matches();
                }

                if (studentNo.isEmpty()) {
                    schoolID.setError("Student ID is required");
                    valid = false;
                } else if (!studentNo.matches(idPattern)) {
                    schoolID.setError("Use the format 11ZAD123456");
                    valid = false;
                } else {
                    schoolID.setError(null);
                }


                if (num.isEmpty()) {
                    phone.setError("Enter your phone number");
                    valid = false;
                } else if (!num.matches("^[+][0-9](10,13)$")) {
                    phone.setError("Enter valid phone number in the format with country code(e.g. +254)");
                } else if (!(num.length() == 10)) {
                    phone.setError("Enter a valid phone number");
                    valid = false;
                } else {
                    phone.setError(null);
                }

                return valid;

            }


        });
    }
}
