package com.saya.coco;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.saya.coco.signup.ResetPassword;
import com.saya.coco.signup.SignupOne;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LogIn extends AppCompatActivity {
    private FirebaseAuth mAuth;

    public CardView loginBtn;
    EditText mail, pass;

    TextView tvRegister;

    boolean valid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        mAuth = FirebaseAuth.getInstance();

        getWindow().setStatusBarColor(Color.parseColor("#9ee0e0e0"));
        //Set status background same as activity background instead of using the one on line 60
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //getWindow().setStatusBarColor(Color.TRANSPARENT);
        //Drawable background;
        //background = ContextCompat.getDrawable(this, R.drawable.marble);
        //getWindow().setBackgroundDrawable(background);


        //Forgot password textview to open reset password activity
        final TextView tv = findViewById(R.id.fgt_pass);

        tv.setOnClickListener(view -> {
            Intent intent = new Intent(LogIn.this,ResetPassword.class);
            startActivity(intent);
        });

        //Register textview to open sign up 1st activity
        final TextView tv1 = findViewById(R.id.tvRegister);

        tv1.setOnClickListener(view -> {
            Intent intent2 = new Intent(LogIn.this, SignupOne.class);
            startActivity(intent2);
        });

        //Hooks
        mail = findViewById(R.id.email_login);
        pass = findViewById(R.id.pass_login);

        valid = true;


        //sign in card view to open main activity/home fragment
        loginBtn = findViewById(R.id.login_card);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Validation()){
                    String email = mail.getText().toString().trim();
                    String password = pass.getText().toString().trim();
                    signInUser(email,password);
                }
            }

            private void signInUser(String email, String password){
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LogIn.this, new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
//Email Verification
//                        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
//                        assert currentUser != null;
//                        if (currentUser.isEmailVerified()){
                            Intent intent = new Intent(LogIn.this, MainActivity.class);
                            intent.putExtra("email", email);
                            startActivity(intent);
//                          }
//                          else {
//                              currentUser.sendEmailVerification();
//                              Toast.makeText(LogIn.this, "Check Your emailto verify your accountf ", Toast.LENGTH_SHORT).show();
//                          }

//                            Toast.makeText(LogIn.this, "Sucessfully logged in", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(LogIn.this, "Unucessfully logged in", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

            //Checks to see whether the form is empty
            private boolean Validation() {

                String email = mail.getText().toString().trim();
                String password = pass.getText().toString().trim();

                if (email.isEmpty()) {
                    mail.setError("Your school email is required");
                    valid = false;
                } else {
                    mail.setError(null);
//                    mail.setErrorEnabled(false);
                }

                if (password.isEmpty()) {
                    pass.setError("Password required");
                    valid = false;
                } else {
                    pass.setError(null);
//                    pass.setErrorEnabled(false);
                }

                return valid;

            }
        });

    }
}













//        Intent i = getIntent();
//        firstFromDB = i.getStringExtra("FirstName");
//        lastFromDB = i.getStringExtra("LastName");
//        emailFromDB = i.getStringExtra("Email");
//        studentIDFromDB = i.getStringExtra("ID");
//        numFromDB = i.getStringExtra("Contact");
//        schoolFromDB = i.getStringExtra("School");
//        yrFromDB = i.getStringExtra("Year");
//        sexFromDB = i.getStringExtra("Sex");
//        ageFromDB = i.getIntExtra("Age", 0);
//        passFromDB = i.getStringExtra("pass");
//        cpassFromDB = i.getStringExtra("cpass");
//TODO: Read to database to get students information upon checking whether student has registered or not.
//TODO: Code not working as I was unable to reference the studentID used as uniqueID for student in database
//private void IsUser() {

        /*
        //Get user input
        String userEnteredEmail = Objects.requireNonNull(mail.getEditText()).getText().toString();
        String userEnteredPassword = Objects.requireNonNull(pass.getEditText()).getText().toString();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://coco-2323-default-rtdb.firebaseio.com/").child(ID);
        ID = ref.push().getKey();

        //Read data in database under ref above
        Query checkUser = ref.orderByChild("email").equalTo(userEnteredEmail);

        checkUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.hasChildren()) {

                    passFromDB = (String) snapshot.child(ID).child("password").getValue();

                        if (Objects.requireNonNull(passFromDB).equals(userEnteredPassword)) {

                            ageFromDB = (int) snapshot.child(ID).child("age").getValue();
                            cpassFromDB = (String) snapshot.child(ID).child("confirmPassword").getValue();
                            emailFromDB = (String) snapshot.child(ID).child("email").getValue();
                            firstFromDB = (String) snapshot.child(ID).child("first").getValue();
                            lastFromDB = (String) snapshot.child(ID).child("last").getValue();
                            numFromDB = (String) snapshot.child(ID).child("num").getValue();
                            schoolFromDB = (String) snapshot.child(ID).child("school").getValue();
                            sexFromDB = (String) snapshot.child(ID).child("sex").getValue();
                            studentIDFromDB = (String) snapshot.child(ID).child("studentID").getValue();
                            yrFromDB = (String) snapshot.child(ID).child("yr").getValue();


                            //Pass data gotten from database and store it under the key
                            Intent i;
                            i = new Intent(LogIn.this, Profile.class);
                            i.putExtra("Age", ageFromDB);
                            i.putExtra("cpass", cpassFromDB);
                            i.putExtra("Email", emailFromDB);
                            i.putExtra("FirstName", firstFromDB);
                            i.putExtra("LastName", lastFromDB);
                            i.putExtra("Contact", numFromDB);
                            i.putExtra("pass", passFromDB);
                            i.putExtra("School", schoolFromDB);
                            i.putExtra("Sex", sexFromDB);
                            i.putExtra("ID", studentIDFromDB);
                            i.putExtra("Year", yrFromDB);
                            startActivity(i);
                        }

                        else {
                            pass.setError("Wrong Password");
                            pass.requestFocus();
                        }

                }
                else {
                    mail.setError("No such user exists");
                    mail.requestFocus();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }*/



//
