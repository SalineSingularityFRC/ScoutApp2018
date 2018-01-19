package com.salinesingularity.nhwltrs.salinesingularity2018scoutingapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

public class MatchInformation extends AppCompatActivity {

    boolean redTeam;
    boolean blueTeam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_information);

        Button startMatchButton = (Button)findViewById(R.id.startMatchButton);
        final Switch redAllianceSwitch = (Switch)findViewById(R.id.redAllianceColorSwitch);
        final Switch blueAllianceSwitch = (Switch)findViewById(R.id.blueAllianceColorSwitch);
        final EditText matchNumberEditText = (EditText)findViewById(R.id.matchNumberEditText);

        redAllianceSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    blueAllianceSwitch.setEnabled(false);
                    redTeam = true;
                } else {
                    blueAllianceSwitch.setEnabled(true);
                    redTeam = false;
                }
            }
        });

        blueAllianceSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    redAllianceSwitch.setEnabled(false);
                    blueTeam = true;
                } else {
                    redAllianceSwitch.setEnabled(true);
                    blueTeam = false;
                }
            }
        });
        startMatchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String checkInput = matchNumberEditText.getText().toString();
                if (checkInput.matches("")) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MatchInformation.this);

                        builder.setCancelable(false);
                        builder.setTitle("");
                        builder.setMessage("Please input what match number this is");

                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                return;
                            }
                        });
                        builder.show();
                        return;
                }
                if (!redTeam && !blueTeam) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MatchInformation.this);

                    builder.setCancelable(false);
                    builder.setTitle("");
                    builder.setMessage("Please select what alliance color your team is on");

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            return;
                        }
                    });
                    builder.show();
                    return;
                }
                Intent matchData = new Intent(getApplicationContext(), MatchData.class);
                startActivity(matchData);
            }
        });
    }
}
