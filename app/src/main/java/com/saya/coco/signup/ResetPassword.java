package com.saya.coco.signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.saya.coco.LogIn;
import com.saya.coco.R;
import com.saya.coco.User;
import com.saya.coco.UserHelper;

import java.util.concurrent.TimeUnit;

public class ResetPassword extends AppCompatActivity {

    FirebaseUser user;
    DatabaseReference reference;
    String userID;
    public CardView c;

    RadioButton rb_sms, rb_email;

    ImageView backArrow;

    RadioGroup radioGroup;

    boolean valid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#e0e0e0"));
        setContentView(R.layout.activity_reset_password);

        //Back arrow to go back to the LogIn screen
        backArrow = findViewById(R.id.backLogin);
        backArrow.setOnClickListener(view -> {
            Intent i;
            i = new Intent(ResetPassword.this, LogIn.class);
            startActivity(i);
        });

        //Change color of text female to white when radio button is checked
        rb_sms = findViewById(R.id.rb_smsSelection);
        rb_sms.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (isChecked) {
                rb_sms.setTextColor(Color.WHITE);
            } else
                rb_sms.setTextColor(Color.BLACK);
        });

        //Change color of text male to white when radio button is checked
        rb_email = findViewById(R.id.rb_emailSelection);
        rb_email.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (isChecked) {
                rb_email.setTextColor(Color.WHITE);
            } else
                rb_email.setTextColor(Color.BLACK);
        });

        radioGroup = findViewById(R.id.rg_verificationMethod);

        valid = true;

        //Send code to email or phone number depending on which radio button is clicked.
        c = findViewById(R.id.next_card);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateReceiptMethod()) {
                    user = FirebaseAuth.getInstance().getCurrentUser();
                    reference = FirebaseDatabase.getInstance().getReference("riara");
                    userID = user.getUid();

                    reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            UserHelper userProfile = snapshot.getValue(UserHelper.class);

                            if (userProfile != null){
                                String phoneNumber = userProfile.getNum();
                                String emailAcc = userProfile.getEmail();
                                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                        phoneNumber,60, TimeUnit.SECONDS,
                                        ResetPassword.this,
                                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){

                                            @Override
                                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                                            }

                                            @Override
                                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                                Toast.makeText(ResetPassword.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                                            }

                                            @Override
                                            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                                Toast.makeText(ResetPassword.this,"Code Sent",Toast.LENGTH_SHORT).show();
                                                Intent i1 = new Intent(ResetPassword.this, ResetPassword2.class);
                                                i1.putExtra("phoneNumber",phoneNumber);
                                                i1.putExtra("emailAcc",emailAcc);
                                                i1.putExtra("verificationId",verificationId);
                                                startActivity(i1);
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
            }


        });
    }

    private boolean validateReceiptMethod() {
        //Check if at least one radio button is selected
        if (radioGroup.getCheckedRadioButtonId() == -1 ) {
            Toast.makeText(ResetPassword.this, "Please select code receipt method", Toast.LENGTH_SHORT).show();
            valid = false;
        } else {
            valid = true;
        }

        return valid;
    }

    public void onVerificationMethodClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.rb_smsSelection:
                if (checked)
                break;

            case R.id.rb_emailSelection:
                if (checked)
                break;
        }
    }
}