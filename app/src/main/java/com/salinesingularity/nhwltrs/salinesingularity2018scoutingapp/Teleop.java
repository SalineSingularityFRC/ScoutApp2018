package com.salinesingularity.nhwltrs.salinesingularity2018scoutingapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Teleop extends Fragment {
    private static final String TAG = "Teleop";
    int allianceSwitchCounterLevel = 0;
    int scaleCounterLevel = 0;
    int opponentsSwitchCounterLevel = 0;
    int portalCounterLevel = 0;
    int vaultCounterLevel = 0;
    MatchData parent;

    @SuppressLint("ValidFragment")
    public Teleop(MatchData matchData) {
        parent = matchData;
    }

    public Teleop(){
        Log.e("7G7","Ouch, I'm not supposed to be here.");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.teleop, container, false);

        Button allianceSwitchMinus = (Button) view.findViewById(R.id.teleopAllianceSwitchMinusButton);
        Button allianceSwitchAdd = (Button) view.findViewById(R.id.teleopAllianceSwitchAddButton);
        final TextView allianceSwitchCounter = (TextView) view.findViewById(R.id.teleopAllianceSwitchCounterTextView);
        Button scaleMinus = (Button) view.findViewById(R.id.teleopScaleMinusButton);
        Button scaleAdd = (Button) view.findViewById(R.id.teleopScaleAddButton);
        final TextView scaleCounter = (TextView) view.findViewById(R.id.teleopScaleCounterTextView);
        Button opponentSwitchMinus = (Button) view.findViewById(R.id.teleopOpponentsSwitchMinusButton);
        Button opponentSwitchAdd = (Button) view.findViewById(R.id.teleopOpponentsSwitchAddButton);
        final TextView opponentSwitchCounter = (TextView) view.findViewById(R.id.teleopOpponentsSwitchCounterTextView);
        Button portalMinus = (Button) view.findViewById(R.id.teleopPortalMinusButton);
        Button portalAdd = (Button) view.findViewById(R.id.teleopPortalAddButton);
        final TextView portalCounter = (TextView) view.findViewById(R.id.teleopPortalCounterTextView);
        Button vaultMinus = (Button) view.findViewById(R.id.teleopVaultMinusButton);
        Button vaultAdd = (Button) view.findViewById(R.id.teleopVaultAddButton);
        final TextView vaultCounter = (TextView) view.findViewById(R.id.teleopVaultCounterTextView);

        allianceSwitchCounter.setText(allianceSwitchCounterLevel + "");
        opponentSwitchCounter.setText(opponentsSwitchCounterLevel + "");
        scaleCounter.setText(scaleCounterLevel + "");
        portalCounter.setText(portalCounterLevel + "");
        vaultCounter.setText(vaultCounterLevel + "");

        allianceSwitchMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (allianceSwitchCounterLevel > 0) {
                    allianceSwitchCounterLevel--;
                    allianceSwitchCounter.setText(allianceSwitchCounterLevel + "");
                    DatabaseGrant.removeSwitchFriendly();
                }
            }
        });

        allianceSwitchAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allianceSwitchCounterLevel++;
                allianceSwitchCounter.setText(allianceSwitchCounterLevel + "");
                DatabaseGrant.addSwitchFriendly("teleop", parent.getTimer());
            }
        });

        scaleAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scaleCounterLevel++;
                scaleCounter.setText(scaleCounterLevel + "");
                DatabaseGrant.addScale("teleop", parent.getTimer());
            }
        });

        scaleMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (scaleCounterLevel> 0) {
                    scaleCounterLevel--;
                    scaleCounter.setText(scaleCounterLevel + "");
                    DatabaseGrant.removeScale();
                }
            }
        });

        opponentSwitchMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (opponentsSwitchCounterLevel > 0) {
                    opponentsSwitchCounterLevel--;
                    opponentSwitchCounter.setText(opponentsSwitchCounterLevel + "");
                    DatabaseGrant.removeSwitchEnemy();
                }
            }
        });

        opponentSwitchAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opponentsSwitchCounterLevel++;
                opponentSwitchCounter.setText(opponentsSwitchCounterLevel + "");
                DatabaseGrant.addSwitchEnemy("teleop", parent.getTimer());
            }
        });

        portalAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                portalCounterLevel++;
                portalCounter.setText(portalCounterLevel + "");
                DatabaseGrant.addPortal("teleop", parent.getTimer());
            }
        });

        portalMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (portalCounterLevel > 0) {
                    portalCounterLevel--;
                    portalCounter.setText(portalCounterLevel + "");
                    DatabaseGrant.removePortal();
                }
            }
        });

        vaultAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vaultCounterLevel++;
                vaultCounter.setText(vaultCounterLevel + "");
                DatabaseGrant.addVault("teleop", parent.getTimer());
            }
        });

        vaultMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (vaultCounterLevel > 0) {
                    vaultCounterLevel--;
                    vaultCounter.setText(vaultCounterLevel + "");
                    DatabaseGrant.removeVault();
                }
            }
        });

        return view;
    }
}
