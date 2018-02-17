package com.salinesingularity.nhwltrs.salinesingularity2018scoutingapp;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Auton extends Fragment {
    private static final String TAG = "Auton";

    int AllianceSwitchCounter = 0;
    int ScaleCounter = 0;
    boolean blockInSwitch = false;
    boolean autoRun = false;
    boolean blockInScale = false;

    public Auton() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.auton, container, false);

        final Spinner startingPosition = (Spinner) view.findViewById(R.id.startingPositionSpinner);
        RadioGroup autonAbilities = (RadioGroup)view.findViewById(R.id.autonAbilityRadioGroup);
        RadioButton autoRun = (RadioButton)view.findViewById(R.id.autoRunRadioButton);
        final RadioButton switchButton = (RadioButton)view.findViewById(R.id.switchRadioButton);
        final RadioButton scale = (RadioButton)view.findViewById(R.id.scaleRadioButton);
        final CheckBox wrongSide = (CheckBox)view.findViewById(R.id.wrongSideCheckBox);
        RadioButton noAuton = (RadioButton)view.findViewById(R.id.noAutonRadioButton);

        List<String> list = new ArrayList<String>();
        list.add("Away");
        list.add("Middle");
        list.add("Closest");
        final int listsize = list.size();
        list.add("Select Position"); //This is correct

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, list) {
            @Override
            public int getCount() {
                return (listsize);
            }
        };

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        startingPosition.setAdapter(dataAdapter);
        startingPosition.setSelection(listsize);


        startingPosition.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String startingPos = startingPosition.getSelectedItem().toString();
                switch(startingPos) {
                    case "Away":
                        DatabaseGrant.setStartingPos(2);
                        break;
                    case "Middle":
                        DatabaseGrant.setStartingPos(1);
                        break;
                    case "Closest":
                        DatabaseGrant.setStartingPos(0);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        DatabaseGrant.setAutonSkill(0);

        noAuton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseGrant.setAutonSkill(0);
            }
        });

        autoRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseGrant.setAutonSkill(1);
            }
        });

        switchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (wrongSide.isChecked()) {
                    DatabaseGrant.setAutonSkill(-2);
                }
                else {
                    DatabaseGrant.setAutonSkill(2);
                }
            }
        });

        scale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (wrongSide.isChecked()) {
                    DatabaseGrant.setAutonSkill(-3);
                }
                else {
                    DatabaseGrant.setAutonSkill(3);
                }
            }
        });

        wrongSide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (wrongSide.isChecked()) {
                    if (switchButton.isChecked()) {
                        DatabaseGrant.setAutonSkill(-2);
                    }
                    else if (scale.isChecked()) {
                        DatabaseGrant.setAutonSkill(-3);
                    }
                }
                else {
                    if (switchButton.isChecked()) {
                        DatabaseGrant.setAutonSkill(2);
                    }
                    else if (scale.isChecked()) {
                        DatabaseGrant.setAutonSkill(3);
                    }
                }
            }
        });

        return view;
    }
}
