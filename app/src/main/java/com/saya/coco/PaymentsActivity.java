package com.saya.coco;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.androidstudy.daraja.Daraja;
import com.androidstudy.daraja.DarajaListener;
import com.androidstudy.daraja.model.AccessToken;
import com.androidstudy.daraja.model.LNMExpress;
import com.androidstudy.daraja.model.LNMResult;
import com.androidstudy.daraja.util.TransactionType;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import timber.log.Timber;

public class PaymentsActivity extends AppCompatActivity {

    // TODO : Integrate mpesa with the app.
    //Not working on click of Cardview c.

    TextInputLayout mpesa_phone_no, amount;


    public CardView c;

    boolean valid;

    Daraja daraja;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payments);


        amount = findViewById(R.id.Amount);


        //Set true to enable logging, false to disable
        valid = true;


        c = findViewById(R.id.receiveMpesaRequest_cardView);
        c.setOnClickListener((View.OnClickListener) this);


        daraja = Daraja.with("OebmAarlthL7yhXZDSF0pA95hDrx18HG", "Ck5ImlHL5ym9BoVA", new DarajaListener<AccessToken>() {
            @Override
            public void onResult(@NonNull AccessToken accessToken) {
                Timber.i(accessToken.getAccess_token());
                Toast.makeText(PaymentsActivity.this, "TOKEN :" + accessToken.getAccess_token(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(String error) {
                Timber.e(error);

            }
        });


       c.setOnClickListener(view -> {
            String phoneNo = Objects.requireNonNull(mpesa_phone_no.getEditText()).toString().trim();
            String am = Objects.requireNonNull(amount.getEditText()).toString().trim();

            if (phoneNo.isEmpty()) {
                mpesa_phone_no.setError("Please provide phone number");
                valid = false;
            }
            else if (am.isEmpty()) {
                amount.setError("Please put an amount");
                valid = false;
            }

            LNMExpress lnmExpress = new LNMExpress(
                    "174379",       //Organization PayBill or Till Number
                    "AUa68oAALXdfpJ81GV49bd7H9117AUL4",
                    TransactionType.CustomerPayBillOnline,
                    am,                        //amount to be transacted
                    phoneNo,                   //phone number sending the money
                    "174379",           //organization receiving the funds
                    phoneNo,                  //phone number to receive STK Pin Prompt
                    "https:mydomain.com/path",
                    "Coco",
                    "Clubs Payment"
            );

            daraja.requestMPESAExpress(lnmExpress, new DarajaListener<LNMResult>() {
                @Override
                public void onResult(@NonNull LNMResult lnmResult) {
                    Timber.i(lnmResult.ResponseDescription);
                }

                @Override
                public void onError(String error) {
                    Timber.tag(PaymentsActivity.this.getClass().getSimpleName()).i(error);

                }
            });

        });

    }
}