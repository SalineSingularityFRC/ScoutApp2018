package com.salinesingularity.nhwltrs.salinesingularity2018scoutingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONException;

public class BeginningScreen extends AppCompatActivity {

    public BluetoothGrant bluetooth=new BluetoothGrant(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beginning_screen);

        Button newMatch = (Button)findViewById(R.id.inputDataButton);
        Button analyzeData = (Button)findViewById(R.id.analyzeDataButton);

        Button grantTest = (Button)findViewById(R.id.grantTestButton);

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

        grantTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Grant's app crashing code goes here
                Log.i("7G7 NOOO!","AWOOOGAH! AWOOOGAH! YOU PRESSED THE BUTTON! NOOO!");
                try {
                    DatabaseGrant.createRobotMatch(5066,"Q00",true);
                    DatabaseGrant.addPortal("endgame",90367);
                    DatabaseGrant.finishMatch();
                } catch (JSONException e) {
                    Log.e("7G7 NOOO!", "LOL, GET REKT!!!");
                    e.printStackTrace();
                    finish();
                }


            }
        });

        DatabaseGrant.setup(bluetooth);
    }

    @Override
    protected void onStart() {
        super.onStart();

        bluetooth.setup();
        //bluetooth.send("Hello world!");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        bluetooth.end();
    }
}
