package com.salinesingularity.nhwltrs.salinesingularity2018scoutingapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Endgame extends Fragment {
    private static final String TAG = "Endgame";
    int allianceSwitchLevel = 0;
    int scaleLevel = 0;
    int opponentsSwitchLevel = 0;
    int portalLevel = 0;
    int vaultLevel = 0;

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

        allianceSwitchAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allianceSwitchLevel++;
                allianceSwitchCounter.setText(allianceSwitchLevel + "");
            }
        });

        allianceSwitchMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (allianceSwitchLevel>0) {
                    allianceSwitchLevel--;
                    allianceSwitchCounter.setText(allianceSwitchLevel + "");
                }
            }
        });

        scaleAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scaleLevel++;
                scaleCounter.setText(scaleLevel + "");
            }
        });

        scaleMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (scaleLevel>0) {
                    scaleLevel--;
                    scaleCounter.setText(scaleLevel + "");
                }
            }
        });

        opponentsSwitchAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opponentsSwitchLevel++;
                opponentsSwitchCounter.setText(opponentsSwitchLevel + "");
            }
        });

        opponentsSwitchMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (opponentsSwitchLevel>0) {
                    opponentsSwitchLevel--;
                    opponentsSwitchCounter.setText(opponentsSwitchLevel + "");
                }
            }
        });

        portalAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                portalLevel++;
                portalCounter.setText(portalLevel + "");
            }
        });

        portalMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (portalLevel > 0) {
                    portalLevel--;
                    portalCounter.setText(portalLevel + "");
                }
            }
        });

        vaultAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vaultLevel++;
                vaultCounter.setText(vaultLevel + "");
            }
        });

        vaultMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (vaultLevel > 0) {
                    vaultLevel--;
                    vaultCounter.setText(vaultLevel + "");
                }
            }
        });

        return view;
    }
}
