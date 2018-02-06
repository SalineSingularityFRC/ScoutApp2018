package com.salinesingularity.nhwltrs.salinesingularity2018scoutingapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import org.json.JSONException;

public class MatchInformation extends AppCompatActivity {

    private static RadioGroup radioGroup;
    private static RadioButton team;
    boolean blueTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_information);

        Button startMatchButton = (Button)findViewById(R.id.startMatchButton);
        final EditText matchNumberEditText = (EditText)findViewById(R.id.matchNumberEditText);
        final RadioButton allianceBlue = (RadioButton)findViewById(R.id.allianceBlue);
        final RadioButton allianceRed = (RadioButton)findViewById(R.id.allianceRed);

        startMatchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(allianceBlue.isChecked()) {
                    blueTeam = true;
                }

                else if (allianceRed.isChecked()) {
                    blueTeam = false;
                }

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

                if (getIntent().hasExtra("Team Number")) {
                    String teamNumberString = getIntent().getExtras().toString();
                    teamNumberString = teamNumberString.replace("Bundle[{Team Number=","");
                    teamNumberString = teamNumberString.replace("}]", "");
                    int teamNumber = Integer.parseInt(teamNumberString);
                    DatabaseGrant.createRobotMatch(teamNumber,checkInput, blueTeam);
                    Intent matchData = new Intent(getApplicationContext(), MatchData.class);
                    startActivity(matchData);
                }
            }
        });

    }
}
