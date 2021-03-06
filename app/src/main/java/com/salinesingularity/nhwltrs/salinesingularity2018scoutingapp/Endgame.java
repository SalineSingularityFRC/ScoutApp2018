package com.salinesingularity.nhwltrs.salinesingularity2018scoutingapp;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Endgame extends Fragment {
    private static final String TAG = "Endgame";
    int allianceSwitchLevel = 0;
    int scaleLevel = 0;
    int opponentsSwitchLevel = 0;
    int portalLevel = 0;
    int vaultLevel = 0;
    long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L ;
    Handler handler;
    int Seconds, Minutes, MilliSeconds ;
    TextView climbTimer;
    boolean started = false;
    MatchData parent;

    @SuppressLint("ValidFragment")
    public Endgame(MatchData matchData) {
        parent = matchData;
    }

    public Endgame(){
        Log.e("7G7","Ouch, I'm not supposed to be here.");}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.endgame, container, false);

        Button allianceSwitchAdd = (Button) view.findViewById(R.id.endgameAllianceSwitchAddButton);
        Button allianceSwitchMinus = (Button) view.findViewById(R.id.endgameAllianceSwitchMinusButton);
        final TextView allianceSwitchCounter = (TextView) view.findViewById(R.id.endgameAllianceSwitchCounterTextView);
        Button scaleAdd = (Button) view.findViewById(R.id.endgameScaleAddButton);
        Button scaleMinus = (Button) view.findViewById(R.id.endgameScaleMinusButton);
        final TextView scaleCounter = (TextView) view.findViewById(R.id.endgameScaleCounterTextView2);
        Button opponentsSwitchAdd = (Button) view.findViewById(R.id.endgameOpponentsSwitchAddButton);
        Button opponentsSwitchMinus = (Button) view.findViewById(R.id.endgameOpponentsSwitchMinusButton2);
        final TextView opponentsSwitchCounter = (TextView) view.findViewById(R.id.endgameOpponentsSwitchCounterTextView);
        Button portalAdd = (Button) view.findViewById(R.id.endgamePortalAddButton);
        Button portalMinus = (Button) view.findViewById(R.id.endgamePortalMinusButton);
        final TextView portalCounter = (TextView) view.findViewById(R.id.endgamePortalCounterTextView);
        Button vaultAdd = (Button) view.findViewById(R.id.endgameVaultAddButton);
        Button vaultMinus = (Button) view.findViewById(R.id.endgameVaultMinusButton);
        final TextView vaultCounter = (TextView) view.findViewById(R.id.endgameVaultCounterTextView);
        Button endMatch = (Button) view.findViewById(R.id.endMatchButton);
        climbTimer = (TextView) view.findViewById(R.id.climbTimerTextView);
        final Button startStopButton = (Button) view.findViewById(R.id.climbStartStopButton);
        Switch park = (Switch) view.findViewById(R.id.parkSwitch);

        handler = new Handler();

        DatabaseGrant.setClimbSkill(0);
        allianceSwitchCounter.setText(allianceSwitchLevel + "");
        opponentsSwitchCounter.setText(opponentsSwitchLevel + "");
        scaleCounter.setText(scaleLevel + "");
        portalCounter.setText(portalLevel + "");
        vaultCounter.setText(vaultLevel + "");

        startStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (parent.started) {
                    if (started) {
                        TimeBuff += MillisecondTime;
                        handler.removeCallbacks(runnable);
                        startStopButton.setText("Start");
                        started = false;
                        DatabaseGrant.setClimbSkill(2);
                    }
                    else {
                        StartTime = SystemClock.uptimeMillis();
                        handler.postDelayed(runnable, 0);
                        started = true;
                        startStopButton.setText("Stop");
                    }
                }
            }
        });

        allianceSwitchAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (parent.started) {
                    allianceSwitchLevel++;
                    allianceSwitchCounter.setText(allianceSwitchLevel + "");
                    DatabaseGrant.addSwitchFriendly("endgame", parent.getTimer());
                }
            }
        });

        allianceSwitchMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (parent.started) {
                    if (allianceSwitchLevel>0) {
                        allianceSwitchLevel--;
                        allianceSwitchCounter.setText(allianceSwitchLevel + "");
                        DatabaseGrant.removeSwitchFriendly();
                    }
                }
            }
        });

        scaleAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (parent.started) {
                    scaleLevel++;
                    scaleCounter.setText(scaleLevel + "");
                    DatabaseGrant.addScale("endgame", parent.getTimer());
                }
            }
        });

        scaleMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (parent.started) {
                    if (scaleLevel>0) {
                        scaleLevel--;
                        scaleCounter.setText(scaleLevel + "");
                        DatabaseGrant.removeScale();
                    }
                }
            }
        });

        opponentsSwitchAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (parent.started) {
                    opponentsSwitchLevel++;
                    opponentsSwitchCounter.setText(opponentsSwitchLevel + "");
                    DatabaseGrant.addSwitchEnemy("endgame", parent.getTimer());
                }
            }
        });

        opponentsSwitchMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (parent.started) {
                    if (opponentsSwitchLevel>0) {
                        opponentsSwitchLevel--;
                        opponentsSwitchCounter.setText(opponentsSwitchLevel + "");
                        DatabaseGrant.removeSwitchEnemy();
                    }
                }
            }
        });

        portalAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (parent.started) {
                    portalLevel++;
                    portalCounter.setText(portalLevel + "");
                    DatabaseGrant.addPortal("endgame", parent.getTimer());
                }
            }
        });

        portalMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (parent.started) {
                    if (portalLevel > 0) {
                        portalLevel--;
                        portalCounter.setText(portalLevel + "");
                        DatabaseGrant.removePortal();
                    }
                }
            }
        });

        vaultAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (parent.started) {
                    vaultLevel++;
                    vaultCounter.setText(vaultLevel + "");
                    DatabaseGrant.addVault("endgame", parent.getTimer());
                }
            }
        });

        vaultMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (parent.started) {
                    if (vaultLevel > 0) {
                        vaultLevel--;
                        vaultCounter.setText(vaultLevel + "");
                        DatabaseGrant.removeVault();
                    }
                }
            }
        });

        park.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (parent.started) {
                    if (isChecked) {
                        DatabaseGrant.setClimbSkill(1);
                    }
                    else {
                        if (climbTimer.getText().toString() == "00:000") {
                            DatabaseGrant.setClimbSkill(0);
                        }
                        else {
                            DatabaseGrant.setClimbSkill(2);
                        }
                    }
                }
            }
        });

        //Need onclick listener for starting intent that takes you back to the beginning screen
        endMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (parent.started) {
                    DatabaseGrant.finishMatch();
                    parent.finish();
                }
            }
        });

        return view;
    }

    public Runnable runnable = new Runnable() {

        public void run() {

            MillisecondTime = SystemClock.uptimeMillis() - StartTime;

            UpdateTime = TimeBuff + MillisecondTime;

            Seconds = (int) (UpdateTime / 1000);

            Minutes = Seconds / 60;

            Seconds = Seconds % 60;

            MilliSeconds = (int) (UpdateTime % 1000);

            climbTimer.setText(String.format("%02d", Seconds) + "."
                    + String.format("%03d", MilliSeconds));

            handler.postDelayed(this, 0);
        }

    };
}
