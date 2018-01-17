package com.salinerobotics.salinesingularityscoutingapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Endgame extends Fragment {
    private static final String TAG = "Endgame";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.endgame, container, false);
        return view;
    }
}
