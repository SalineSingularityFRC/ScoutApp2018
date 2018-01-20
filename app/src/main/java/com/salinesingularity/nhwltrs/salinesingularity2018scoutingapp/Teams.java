package com.salinesingularity.nhwltrs.salinesingularity2018scoutingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class Teams extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams);

        Button newTeam = (Button)findViewById(R.id.newTeamButton);
        ListView list = (ListView)findViewById(R.id.teamsListView);
        Bundle extras = getIntent().getExtras();

        final ArrayList<String> arrayList = new ArrayList<String>();
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        list.setAdapter(arrayAdapter);

        //arrayList.add(extras.getString("Team Name"));
        //arrayAdapter.notifyDataSetChanged();

        newTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newTeam = new Intent(getApplicationContext(), NewTeam.class);
                startActivity(newTeam);
            }
        });
    }
}
