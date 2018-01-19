package com.salinesingularity.nhwltrs.salinesingularity2018scoutingapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Teleop extends Fragment {
    private static final String TAG = "Teleop";
    int allianceSwitchCounterLevel = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.teleop, container, false);

        Button allianceSwitchMinus = (Button) view.findViewById(R.id.teleopAllianceSwitchMinusButton);
        Button allianceSwitchAdd = (Button) view.findViewById(R.id.teleopAllianceSwitchAddButton);
        final TextView allianceSwitchCounter = (TextView) view.findViewById(R.id.teleopAllianceSwitchCounterTextView);

        allianceSwitchMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (allianceSwitchCounterLevel > 0) {
                    allianceSwitchCounterLevel--;
                    allianceSwitchCounter.setText(allianceSwitchCounterLevel + "");
                }
            }
        });

        allianceSwitchAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allianceSwitchCounterLevel++;
                allianceSwitchCounter.setText(allianceSwitchCounterLevel + "");
            }
        });
        return view;
    }
}
