package com.salinerobotics.salinesingularityscoutingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MatchInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_information);

        Button startMatchButton = (Button)findViewById(R.id.startMatchButton);

        startMatchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent matchData = new Intent(getApplicationContext(), MatchData.class);
                startActivity(matchData);
            }
        });
    }
}
