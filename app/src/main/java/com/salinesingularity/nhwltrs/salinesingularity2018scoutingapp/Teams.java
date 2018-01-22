package com.salinesingularity.nhwltrs.salinesingularity2018scoutingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.salinesingularity.nhwltrs.salinesingularity2018scoutingapp.MatchInformation;
import com.salinesingularity.nhwltrs.salinesingularity2018scoutingapp.NewTeam;
import com.salinesingularity.nhwltrs.salinesingularity2018scoutingapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Teams extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams);

        Button newTeam = (Button)findViewById(R.id.newTeamsButton);
        ListView list = (ListView) findViewById(R.id.teamsListView);

        List<HashMap<String, String>> listItems = new ArrayList<>();
        SimpleAdapter adapter = new SimpleAdapter(this, listItems, R.layout.list_item, new String[]{"First Line", "Second Line"}, new int[]{R.id.teamNameTextView, R.id.teamNumberTextView});
        HashMap<String, String> resultsMap = new HashMap<>();
        list.setAdapter(adapter);
        if (getIntent().hasExtra("Team Name")) {
            resultsMap.put("First Line", getIntent().getExtras().getString("Team Name"));
            resultsMap.put("Second Line", getIntent().getExtras().getString("Team Number"));
            listItems.add(resultsMap);
            adapter.notifyDataSetChanged();
        }

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent matchInformation = new Intent (getApplicationContext(), MatchInformation.class);
                startActivity(matchInformation);
            }
        });

        newTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newTeam = new Intent(getApplicationContext(), NewTeam.class);
                startActivity(newTeam);
            }
        });
    }
}
