package com.salinesingularity.nhwltrs.salinesingularity2018scoutingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.salinesingularity.nhwltrs.salinesingularity2018scoutingapp.MatchInformation;
import com.salinesingularity.nhwltrs.salinesingularity2018scoutingapp.NewTeam;
import com.salinesingularity.nhwltrs.salinesingularity2018scoutingapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Teams extends AppCompatActivity {

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams);

        Button newTeam = (Button) findViewById(R.id.newTeamsButton);
        list = (ListView) findViewById(R.id.teamsListView);
        EditText search = (EditText) findViewById(R.id.searchTeams);



        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String teamNumber = ((TextView) view.findViewById(R.id.teamNumberTextView)).getText().toString();
                Intent matchInformation = new Intent(getApplicationContext(), MatchInformation.class);
                matchInformation.putExtra("Team Number", teamNumber);
                startActivity(matchInformation);
                finish();
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

    protected void onResume() {
        super.onResume();

        List<HashMap<String, String>> listItems = new ArrayList<>();
        SimpleAdapter adapter = new SimpleAdapter(this, listItems, R.layout.list_item, new String[]{"First Line", "Second Line"}, new int[]{R.id.teamNameTextView, R.id.teamNumberTextView});

        list.setAdapter(adapter);
        for(int i=0;i<DatabaseGrant.getTeamDatabaseLength();i++) {
            HashMap<String, String> resultsMap = new HashMap<>();
            resultsMap.put("First Line", DatabaseGrant.getTeamName(i));
            resultsMap.put("Second Line", String.valueOf(DatabaseGrant.getTeamNumber(i)));
            listItems.add(resultsMap);
        }

        for(int i=0;i<DatabaseGrant.getLocalTeamDatabaseLength();i++) {
            HashMap<String, String> resultsMap = new HashMap<>();
            resultsMap.put("First Line", DatabaseGrant.getLocalTeamName(i));
            resultsMap.put("Second Line", String.valueOf(DatabaseGrant.getLocalTeamNumber(i)));
            listItems.add(resultsMap);
        }

        adapter.notifyDataSetChanged();
    }
}
