package com.salinesingularity.nhwltrs.salinesingularity2018scoutingapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Teleop extends Fragment {
    private static final String TAG = "Teleop";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.teleop, container, false);
        return view;
    }
}
