package com.saya.coco.menu;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


import com.chaos.view.PinView;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.saya.coco.MainActivity;
import com.saya.coco.R;
import com.saya.coco.User;
import com.saya.coco.UserHelper;
import com.saya.coco.signup.ResetPassword;
import com.saya.coco.signup.ResetPassword2;

import java.util.concurrent.TimeUnit;


public class VerifyPhone extends AppCompatActivity {
    FirebaseUser user;
    DatabaseReference reference;
    String userID;
    CardView c;
    Button b;
    PinView pin;

    private FirebaseAuth auth;
    String codeBySystem;

    public static Intent i = new Intent();

    public static String num;

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#ebf5f0"));
        setContentView(R.layout.activity_verify_phone);



        b = findViewById(R.id.close_icon);
        b.setOnClickListener(view -> {

            //Go back to navigation drawer on click of close icon
            /*Intent i = new Intent(VerifyPhone.this, MainActivity.class);
            startActivity(i);*/
        });

        auth = FirebaseAuth.getInstance();
        pin = findViewById(R.id.pinView);


        tv = findViewById(R.id.tv_sendCode);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = FirebaseAuth.getInstance().getCurrentUser();
                reference = FirebaseDatabase.getInstance().getReference("riara");
                userID = user.getUid();

                reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        UserHelper userProfile = snapshot.getValue(UserHelper.class);

                        if (userProfile != null){
                                String phoneNumber = userProfile.getNum();

                                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                        phoneNumber,60, TimeUnit.SECONDS,
                                        VerifyPhone.this,
                                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){

                                            @Override
                                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                                            }

                                            @Override
                                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                                Toast.makeText(VerifyPhone.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                                            }

                                            @Override
                                            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
//                                                i1.putExtra("phoneNumber",phoneNumber);
//                                                i1.putExtra("emailAcc",emailAcc);
//                                                i1.putExtra("verificationId",verificationId);
                                                c = findViewById(R.id.verifyCardView);
                                                c.setOnClickListener(view -> {
                                                    Intent i1 = new Intent(VerifyPhone.this, MainActivity.class);
                                                    startActivity(i1);
                                                    Toast.makeText(VerifyPhone.this,"Phone Verified",Toast.LENGTH_SHORT).show();
                                                });
                                            }
                                        }
                                );
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }


        });

    }

}
