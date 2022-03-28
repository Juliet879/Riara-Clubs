package com.saya.coco.signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.saya.coco.LogIn;
import com.saya.coco.R;
import com.saya.coco.User;
import com.saya.coco.UserHelper;
import com.saya.coco.db.AppDatabase;
import com.saya.coco.db.EventModel;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupLast extends AppCompatActivity {

    ImageView backArrow;
    public CardView c;

    TextInputLayout pass, confirmPass;

    boolean valid;

    private FirebaseAuth mAuth;
    FirebaseUser user;
    DatabaseReference reference;

    public static String first;
    public static String last;
    public static String email;
    public static String studentID;
    public static String num;

    public static String school;
    public static String yr;

    public static String sex;
    public static int age;

    public static String password;
    public static String confirmPassword;


    String userID;

    public static Intent i = new Intent();   //only way to pass choose faculty radio button text through the activities

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#e0e0e0"));
        setContentView(R.layout.activity_signup_last);

        mAuth = FirebaseAuth.getInstance();

        //Arrow to to third sign up activity
        backArrow = findViewById(R.id.back3);
        backArrow.setOnClickListener(view -> {
            Intent i;
            i = new Intent(SignupLast.this, SignupThree.class);
            startActivity(i);
        });


        //Log in textview to take user to LogIn activity if they have already registered
        final TextView tv = findViewById(R.id.login_last);
        tv.setOnClickListener(view -> {
            Intent i2 = new Intent(SignupLast.this, LogIn.class);
            startActivity(i2);
        });


        valid = true;


        pass = findViewById(R.id.pass_last_signup);
        confirmPass = findViewById(R.id.confirm_pass_last_signup);

        //Pass data from previous Signup(1,2,3) and current activities.
        //Store the data under students and use studentID as unique ID for student
        c = findViewById(R.id.go_cardView);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Check if form is not empty and is filled in correctly
                if (validatePass()) {

                    //Get user data
                    password = Objects.requireNonNull(pass.getEditText()).getText().toString();
                    confirmPassword = Objects.requireNonNull(confirmPass.getEditText()).getText().toString();
                   createUser(first, last, email, studentID, num, school, yr, sex, age, password, confirmPassword);
//                    AppDatabase db  = AppDatabase.getDbInstance(getApplicationContext());
//                    User user = new User();
//                    user.firstName = first;
//                    user.lastName = last;
//                    db.userDao().insertUser(user);
                }
            }


            private boolean validatePass() {

                password = Objects.requireNonNull(pass.getEditText()).getText().toString();
                confirmPassword = Objects.requireNonNull(confirmPass.getEditText()).getText().toString();
                String passwordPattern = "^" +
                        "(?=.*[0-9])" +
                        "(?=.*[a-z])" +
                        "(?=.*[A-Z])" +
                        "(?=.*[!@#$%*&])" +
                        "(?=\\S+$)" +
                        ".{7,20}" +
                        "$";
                Pattern pattern = Pattern.compile(passwordPattern, Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(password);

                if (password.isEmpty()) {
                    pass.setError("Password required");
                    valid = false;
                }
                else if (password.length() <= 7) {
                    pass.setError("Minimum 8 characters required");
                    valid = false;
                }
                else if (password.length() >= 19){
                    pass.setError("Exceeded maximum characters");
                    valid = false;
                }
                else if (!password.matches(passwordPattern)) {
                    pass.setError("Password must contain alphanumerics, capital and small letters and symbols");
                    valid = false;
                }
                else {
                    pass.setError(null);
                    return matcher.matches();
                }


                if (!confirmPassword.matches(password)) {
                    confirmPass.setError("Passwords should match");
                    valid = false;
                }
                else {
                    confirmPass.setError(null);
                }

                return  valid;
            }

            private void createUser(String first, String last, String email, String studentID, String num, String school, String yr, String sex, int age, String password, String confirmPassword) {
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                user = FirebaseAuth.getInstance().getCurrentUser();
                                reference = FirebaseDatabase.getInstance().getReference("riara");
                                userID = user.getUid();

                                UserHelper user = new UserHelper(first, last, email, studentID, num, school, yr, sex, age, password, confirmPassword);
                                FirebaseDatabase.getInstance().getReference("riara")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(SignupLast.this, "Success", Toast.LENGTH_SHORT).show();
                                            //Toast.makeText(SignupOne.this.getApplicationContext(), "SignUp Successful: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(SignupLast.this, LogIn.class);
                                            intent.putExtra("userID",userID);
                                            startActivity(intent);
                                        } else {
                                            Toast.makeText(SignupLast.this.getApplicationContext(), "SignUp unsuccessful: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            } else {
                                Toast.makeText(SignupLast.this.getApplicationContext(), "SignUp unsuccessful: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
           }
        });

        //Store data passed from previous activities( Signup 1,2,3) in the respective key
        Intent i = getIntent();
        first = i.getStringExtra("fName");
        last = i.getStringExtra("lName");
        email = i.getStringExtra("mail");
        studentID = i.getStringExtra("schoolID");
        num = i.getStringExtra("number");
        school = i.getStringExtra("faculty");
        yr= i.getStringExtra("year");
        sex = i.getStringExtra("gender");
        age = i.getIntExtra("userAge", 0);
        password = i.getStringExtra("pass");
        confirmPassword = i.getStringExtra("cpass");
    }

    //On click of c save data to firebase under user and generate user key
//    private void storeNewUserData() {
//
//        FirebaseDatabase db = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = db.getReference("user");
//
//        //generate user ID
//        ID = myRef.push().getKey();
//
//        UserHelper addNewUser = new UserHelper(
//                first, last, email, studentID, num, school,
//                yr, sex, age, password, confirmPassword
//        );
//
//        assert ID != null;
//        myRef.child(ID).setValue(addNewUser);

        //use studentId as unique ID for student

        //DatabaseReference myRef = db.getReference("student");

        //myRef.child(studentID).setValue(addNewUser);
}