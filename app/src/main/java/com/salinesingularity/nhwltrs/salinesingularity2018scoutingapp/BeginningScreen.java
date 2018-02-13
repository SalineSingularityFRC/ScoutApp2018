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
    private boolean started=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beginning_screen);

        Button newMatch = (Button)findViewById(R.id.inputDataButton);

        newMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent teams = new Intent(getApplicationContext(), Teams.class);
                startActivity(teams);
            }
        });

        DatabaseGrant.setup(bluetooth);
    }

    @Override
    protected void onStart() {
        super.onStart();

        bluetooth.setup();

        if(!started){
            bluetooth.send("{\"teamData\":[],\"matchData\":[]}");
            started=true;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        bluetooth.end();
    }
}
