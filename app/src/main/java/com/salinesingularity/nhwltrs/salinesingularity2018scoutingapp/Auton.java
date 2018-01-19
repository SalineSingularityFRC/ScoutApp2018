package com.salinesingularity.nhwltrs.salinesingularity2018scoutingapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class Auton extends Fragment {
    private static final String TAG = "Auton";

    int AllianceSwitchCounter = 0;
    int ScaleCounter = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.auton, container, false);

        Button autonAllianceSwitchMinusButton = (Button) view.findViewById(R.id.autonAllianceSwitchMinusButton);
        Button autonAllianceSwitchAddButton = (Button) view.findViewById(R.id.autonAllianceSwitchAddButton);
        Button autonScaleAddButton = (Button) view.findViewById(R.id.autonScaleAddButton);
        Button autonScaleMinusButton = (Button) view.findViewById(R.id.autonScaleMinusButton);
        final TextView allianceSwitchCounterTextView = (TextView) view.findViewById(R.id.autonAllianceSwitchCounterTextView);
        final TextView scaleCounterTextView = (TextView) view.findViewById(R.id.autonScaleCounerTextView);
        final Spinner startingPositionSpinner = (Spinner) view.findViewById(R.id.startingPositionSpinner);

        autonAllianceSwitchMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (AllianceSwitchCounter > 0) {
                    AllianceSwitchCounter--;
                    allianceSwitchCounterTextView.setText(AllianceSwitchCounter + "");
                }
            }
        });

        autonAllianceSwitchAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AllianceSwitchCounter++;
                allianceSwitchCounterTextView.setText(AllianceSwitchCounter + "");
            }
        });

        autonScaleAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ScaleCounter++;
                scaleCounterTextView.setText(ScaleCounter + "");
            }
        });

        autonScaleMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ScaleCounter > 0) {
                    ScaleCounter--;
                    scaleCounterTextView.setText(ScaleCounter + "");
                }
            }
        });

        return view;
    }
}
