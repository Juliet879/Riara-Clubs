package com.saya.coco.events;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.saya.coco.MainActivity;
import com.saya.coco.R;


public class EventsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        CardView c;


        //Set status bar color using method in MainActivity
        ((MainActivity) requireActivity()).updateStatusBarColorEvents("#6f5f90");
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_events, container, false);

        //Cardview to open Event class using intent
        c = v.findViewById(R.id.card4);
        c.setOnClickListener(view -> {
            Intent i;
            i = new Intent(getActivity(), Event.class);
            startActivity(i);

//            Button btn=getView().findViewById(R.id.btnViewEvent);
//            btn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent=new Intent(getActivity(),Event.class);
//                    startActivity(intent);
//                }
//            });
        });


        return v;


    }

    }