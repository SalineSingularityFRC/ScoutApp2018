package com.salinesingularity.nhwltrs.salinesingularity2018scoutingapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewTeam extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_team);

        Button createTeam = (Button) findViewById(R.id.createTeamButton);
        final EditText teamName = (EditText) findViewById(R.id.teamNameEditText);
        final EditText teamNumber = (EditText) findViewById(R.id.teamNumberEditText);

        createTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String checkTeamName = teamName.getText().toString();
                String checkTeamNumber = teamNumber.getText().toString();

                if (checkTeamName.matches("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(NewTeam.this);

                    builder.setCancelable(false);
                    builder.setTitle("");
                    builder.setMessage("Please input a team name");

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            return;
                        }
                    });
                    builder.show();
                    return;
                }

                if(checkTeamNumber.matches("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(NewTeam.this);

                    builder.setCancelable(false);
                    builder.setTitle("");
                    builder.setMessage("Please input a team number");

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            return;
                        }
                    });

                    builder.show();
                    return;
                }

                //Intent teams = new Intent(getApplicationContext(), Teams.class);
                //teams.putExtra("Team Name", checkTeamName);
                //teams.putExtra("Team Number", checkTeamNumber);
                //startActivity(teams);
            }
        });
    }
}
