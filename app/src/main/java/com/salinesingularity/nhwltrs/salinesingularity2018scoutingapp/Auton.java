package com.salinesingularity.nhwltrs.salinesingularity2018scoutingapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class Auton extends Fragment {
    private static final String TAG = "Auton";

    int AllianceSwitchCounter = 0;
    int ScaleCounter = 0;
    boolean blockInSwitch = false;
    boolean autoRun = false;
    boolean blockInScale = false;

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
        Switch autoRunSwitch = (Switch) view.findViewById(R.id.autoRunSwitch);
        CheckBox allianceSwitchWrongSide = (CheckBox) view.findViewById(R.id.allianceSwitchWrongSideCheckBox);
        CheckBox scaleWrongSide = (CheckBox) view.findViewById(R.id.scaleWrongSideCheckBox);

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

        autoRunSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    autoRun = true;
                    try {
                        DatabaseGrant.setAutonSkill(1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    autoRun = false;
                    try {
                        DatabaseGrant.setAutonSkill(0);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        autonAllianceSwitchMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (AllianceSwitchCounter > 0) {
                    AllianceSwitchCounter--;
                    allianceSwitchCounterTextView.setText(AllianceSwitchCounter + "");
                    if (AllianceSwitchCounter == 0) {
                        blockInSwitch = false;
                        if (autoRun) {
                            try {
                                DatabaseGrant.setAutonSkill(1);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        else {
                            try {
                                DatabaseGrant.setAutonSkill(0);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });

        autonAllianceSwitchAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AllianceSwitchCounter++;
                allianceSwitchCounterTextView.setText(AllianceSwitchCounter + "");
                blockInSwitch = true;
                try {
                    DatabaseGrant.setAutonSkill(2);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        allianceSwitchWrongSide.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    try {
                        DatabaseGrant.setAutonSkill(-2);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    if (AllianceSwitchCounter > 0) {
                        try {
                            DatabaseGrant.setAutonSkill(2);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    else if (autoRun) {
                        try {
                            DatabaseGrant.setAutonSkill(1);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    else {
                        try {
                            DatabaseGrant.setAutonSkill(0);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        autonScaleAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ScaleCounter++;
                scaleCounterTextView.setText(ScaleCounter + "");
                blockInScale = true;
                try {
                    DatabaseGrant.setAutonSkill(3);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        autonScaleMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ScaleCounter > 0) {
                    ScaleCounter--;
                    scaleCounterTextView.setText(ScaleCounter + "");
                    if (ScaleCounter == 0) {
                        blockInScale = false;
                        if (autoRun) {
                            try {
                                DatabaseGrant.setAutonSkill(1);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        else {
                            try {
                                DatabaseGrant.setAutonSkill(0);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });

        scaleWrongSide.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked && ScaleCounter>0) {
                    try {
                        DatabaseGrant.setAutonSkill(-3);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    if (ScaleCounter>0) {
                        try {
                            DatabaseGrant.setAutonSkill(3);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    else if (autoRun) {
                        try {
                            DatabaseGrant.setAutonSkill(1);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    else {
                        try {
                            DatabaseGrant.setAutonSkill(0);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        try {
            DatabaseGrant.setAutonSkill(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return view;
    }
}
