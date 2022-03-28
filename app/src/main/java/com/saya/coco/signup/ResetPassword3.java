package com.saya.coco.signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.saya.coco.LogIn;
import com.saya.coco.R;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResetPassword3 extends AppCompatActivity {

    FirebaseAuth auth;
    ImageView backArrow;

    public CardView c;

    boolean valid;

    TextInputLayout password, confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#e0e0e0"));
        setContentView(R.layout.activity_reset_password3);

        auth = FirebaseAuth.getInstance();
        password = findViewById(R.id.new_pass);
        confirmPassword = findViewById(R.id.confirm_new_pass);

        String emailAdd = getIntent().getStringExtra("emailAdd");

        //Back arrow to go back to the ResetPassword2 screen
        backArrow = findViewById(R.id.backReset2);
        backArrow.setOnClickListener(view -> {
            Intent i;
            i = new Intent(ResetPassword3.this, ResetPassword2.class);
            startActivity(i);
        });

        valid = true;


        c = findViewById(R.id.updateCard);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validatePassword()) {
                    auth.sendPasswordResetEmail(emailAdd).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(ResetPassword3.this,"Successfully Reset",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    Intent i = new Intent(ResetPassword3.this, LogIn.class);
                    ResetPassword3.this.startActivity(i);
                }
                //

            }

            private boolean validatePassword() {

                String pass = Objects.requireNonNull(password.getEditText()).getText().toString();
                String confirmPass = Objects.requireNonNull(confirmPassword.getEditText()).getText().toString();
                String passwordPattern = "^" +
                        "(?=.*[0-9])" +
                        "(?=.*[a-z])" +
                        "(?=.*[A-Z])" +
                        "(?=.*[!@#$%*&])" +
                        "(?=\\S+$)" +
                        ".{7,20}" +
                        "$";
                Pattern pattern = Pattern.compile(passwordPattern, Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(pass);

                if (pass.isEmpty()) {
                    password.setError("Password required");
                    valid = false;
                }
                else if (pass.length() <= 7) {
                    password.setError("Minimum 8 characters required");
                    valid = false;
                }
                else if (pass.length() >= 19){
                    password.setError("Exceeded maximum characters");
                    valid = false;
                }
                else if (!pass.matches(passwordPattern)) {
                    password.setError("Password must contain alphanumerics, capital and small letters and symbols");
                    valid = false;
                }
                else {
                    password.setError(null);
                    return matcher.matches();
                }


                if (!confirmPass.matches(pass)) {
                    confirmPassword.setError("Passwords should match");
                    valid = false;
                }
                else {
                    confirmPassword.setError(null);
                }

                return  valid;
            }

        });
    }
}