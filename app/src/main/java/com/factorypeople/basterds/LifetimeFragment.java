package com.factorypeople.basterds;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class LifetimeFragment extends Fragment{
    // Store instance variables
    TextView playerIdTv, scoreTv, playtimeTv, turnCountTv, matchCountTv, winCountTv, spawnedAliasTv, killedAliasTv, killedHostilesTv, damageTv, healTv, most_playedTv;
    // newInstance constructor for creating fragment with arguments
    public static LifetimeFragment newInstance() {
        LifetimeFragment fragment = new LifetimeFragment();
        return fragment;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lifetime, container, false);
        most_playedTv = view.findViewById(R.id.most_playedTv);
        playerIdTv = view.findViewById(R.id.playeridTv);
        scoreTv = view.findViewById(R.id.scoreTv);
        playtimeTv = view.findViewById(R.id.playtimeTv);
        turnCountTv = view.findViewById(R.id.turnCountTv);
        matchCountTv = view.findViewById(R.id.matchCountTv);
        winCountTv = view.findViewById(R.id.winCountTv);
        winCountTv = view.findViewById(R.id.winCountTv);
        spawnedAliasTv = view.findViewById(R.id.spawnedAliasTv);
        killedAliasTv = view.findViewById(R.id.killedAliasTv);
        killedHostilesTv = view.findViewById(R.id.killedHostilesTv);
        damageTv = view.findViewById(R.id.damageTv);
        healTv = view.findViewById(R.id.healTv);
        ((InfoShowerActivity)getActivity()).sendRequest(1);

        most_playedTv.setText(((InfoShowerActivity)getActivity()).most_played);
        playerIdTv.setText(((InfoShowerActivity)getActivity()).pId);
        scoreTv.setText(((InfoShowerActivity)getActivity()).score);
        playtimeTv.setText(((InfoShowerActivity)getActivity()).playtime);
        turnCountTv.setText(((InfoShowerActivity)getActivity()).turn_count);
        matchCountTv.setText(((InfoShowerActivity)getActivity()).match_count);
        winCountTv.setText(((InfoShowerActivity)getActivity()).win_count);
        spawnedAliasTv.setText(((InfoShowerActivity)getActivity()).spawned_alias);
        killedAliasTv.setText(((InfoShowerActivity)getActivity()).killed_alias);
        killedHostilesTv.setText(((InfoShowerActivity)getActivity()).killed_hostiles);
        damageTv.setText(((InfoShowerActivity)getActivity()).damage);
        healTv.setText(((InfoShowerActivity)getActivity()).heal);
        return view;
    }
}
