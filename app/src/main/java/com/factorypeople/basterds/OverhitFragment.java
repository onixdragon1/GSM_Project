package com.factorypeople.basterds;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class OverhitFragment extends Fragment{
    String pId, score, playtime, turn_count, match_count, win_count, spawned_alias, killed_alias, killed_hostiles, damage, heal;
    JSONObject overhit;
    // Store instance variables
    TextView playerIdTv, scoreTv, playtimeTv, turnCountTv, matchCountTv, winCountTv, spawnedAliasTv, killedAliasTv, killedHostilesTv, damageTv, healTv;

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_overhit, container, false);
        playerIdTv = view.findViewById(R.id.playeridTv);
        scoreTv = view.findViewById(R.id.scoreTv);
        playtimeTv = view.findViewById(R.id.playtimeTv);
        turnCountTv = view.findViewById(R.id.turnCountTv);
        matchCountTv = view.findViewById(R.id.matchCountTv);
        winCountTv = view.findViewById(R.id.winCountTv);
        spawnedAliasTv = view.findViewById(R.id.spawnedAliasTv);
        killedAliasTv = view.findViewById(R.id.killedAliasTv);
        killedHostilesTv = view.findViewById(R.id.killedHostilesTv);
        damageTv = view.findViewById(R.id.damageTv);
        healTv = view.findViewById(R.id.healTv);

        sendRequest();
        return view;
    }

    public void sendRequest(){
        // RequestQueue를 새로 만들어준다.
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        // Request를 요청 할 URL
        String url ="http://donote.co:8000/api/v1/12/summary/";
        JsonObjectRequest jsonObjectRequest =new JsonObjectRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject obj = new JSONObject();
                            pId = response.getString("pid");
                            overhit = response.getJSONObject("overhit");
                            obj = overhit;
                            score = obj.getString("score");
                            playtime = obj.getString("playtime");
                            turn_count = obj.getString("turn_count");
                            match_count = obj.getString("match_count");
                            win_count = obj.getString("win_count");
                            spawned_alias = obj.getString("spawned_alias");
                            killed_alias = obj.getString("killed_alias");
                            killed_hostiles = obj.getString("killed_hostiles");
                            damage = obj.getString("damage");
                            heal = obj.getString("heal");

                            //setting all cardview with data
                            playerIdTv.setText(pId);
                            scoreTv.setText(score);
                            playtimeTv.setText(playtime);
                            turnCountTv.setText(turn_count);
                            matchCountTv.setText(match_count);
                            winCountTv.setText(win_count);
                            spawnedAliasTv.setText(spawned_alias);
                            killedAliasTv.setText(killed_alias);
                            killedHostilesTv.setText(killed_hostiles);
                            damageTv.setText(damage);
                            healTv.setText(heal);
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity().getApplicationContext(), "That didn't work!", Toast.LENGTH_SHORT).show();
            }
        });
        // queue에 Request를 추가해준다.
        queue.add(jsonObjectRequest);
    }
}
