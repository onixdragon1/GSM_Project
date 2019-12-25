package com.factorypeople.basterds;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class MeisterboiFragment extends Fragment{
    // Store instance variables
    TextView playerIdTv, scoreTv, playtimeTv, turnCountTv, matchCountTv, winCountTv, spawnedAliasTv, killedAliasTv, killedHostilesTv, damageTv, healTv;
    // newInstance constructor for creating fragment with arguments
    public static MeisterboiFragment newInstance() {
        MeisterboiFragment fragment = new MeisterboiFragment();
        return fragment;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meisterboi, container, false);
        ((InfoShowerActivity)getActivity()).sendRequest(1);

        return view;
    }
}
