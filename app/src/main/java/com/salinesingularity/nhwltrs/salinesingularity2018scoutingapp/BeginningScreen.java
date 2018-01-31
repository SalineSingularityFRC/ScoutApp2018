package com.salinesingularity.nhwltrs.salinesingularity2018scoutingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class BeginningScreen extends AppCompatActivity {

    public BluetoothGrant bluetooth=new BluetoothGrant(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beginning_screen);

        Button newMatch = (Button)findViewById(R.id.inputDataButton);
        Button analyzeData = (Button)findViewById(R.id.analyzeDataButton);

        newMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent teams = new Intent(getApplicationContext(), Teams.class);
                startActivity(teams);
            }
        });

        analyzeData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent analyzeTeams = new Intent(getApplicationContext(), AnalyzeTeams.class);
                startActivity(analyzeTeams);
            }
        });

        DatabaseGrant.setup(bluetooth);
    }

    @Override
    protected void onStart() {
        super.onStart();

        bluetooth.setup();
        bluetooth.send("Hello world!");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        bluetooth.end();
    }
}
