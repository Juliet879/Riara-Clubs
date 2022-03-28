package com.saya.coco;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PaymentsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Set status bar color
        ((MainActivity) requireActivity()).updateStatusBarColorPayments("#62809c");
        // Inflate the layout for this fragment

        CardView c;

        View v = inflater.inflate(R.layout.fragment_payments, container, false);

        c = v.findViewById(R.id.Mpesa_cardView);
        c.setOnClickListener(view -> {
            Intent i;
            i = new Intent(getActivity(), PaymentsActivity.class);
            startActivity(i);
        });

        return v;


    }
}