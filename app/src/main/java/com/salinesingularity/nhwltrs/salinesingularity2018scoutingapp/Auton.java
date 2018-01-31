package com.salinesingularity.nhwltrs.salinesingularity2018scoutingapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

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
        final Spinner startingPosition = (Spinner) view.findViewById(R.id.startingPositionSpinner);

        List<String> list = new ArrayList<String>();
        list.add("Away");
        list.add("Middle");
        list.add("Closest");
        list.add("");
        final int listsize = list.size() - 1;

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, list) {
            @Override
            public int getCount() {
                return (listsize);
            }
        };

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        startingPosition.setAdapter(dataAdapter);
        startingPosition.setSelection(listsize);

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
