package com.factorypeople.basterds;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

public class LifetimeFragment extends Fragment{
    String pId, score, playtime, turn_count, match_count, win_count, spawned, killed, damage, most_played, best_played, avg_score, pending;
    JSONObject lifetime;
    Button matchBtn, chartBtn;
    String player;
    // Store instance variables
    TextView playerIdTv, scoreTv, playtimeTv, turnCountTv, matchCountTv, winCountTv, spawnedTv, killedTv, damageTv, most_playedTv, best_playedTv, avg_scoreTv;

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lifetime, container, false);
        most_playedTv = view.findViewById(R.id.most_playedTv);
        best_playedTv = view.findViewById(R.id.best_playedTv);
        avg_scoreTv = view.findViewById(R.id.avg_scoreTv);
        playerIdTv = view.findViewById(R.id.playeridTv);
        scoreTv = view.findViewById(R.id.scoreTv);
        playtimeTv = view.findViewById(R.id.playtimeTv);
        turnCountTv = view.findViewById(R.id.turnCountTv);
        matchCountTv = view.findViewById(R.id.matchCountTv);
        winCountTv = view.findViewById(R.id.winCountTv);
        spawnedTv = view.findViewById(R.id.spawnedTv);
        killedTv = view.findViewById(R.id.killedTv);
        damageTv = view.findViewById(R.id.damageTv);

        matchBtn = view.findViewById(R.id.btnMatchDialog);
        chartBtn = view.findViewById(R.id.btnChartDialog);

        sendRequest();

        matchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MatchActivity.class);
                startActivity(intent);
            }
        });

        chartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChartActivity.class);
                intent.putExtra("id", playerIdTv.getText().toString());
                intent.putExtra("MatchCount", Integer.parseInt(matchCountTv.getText().toString()));
                intent.putExtra("WinCount", Integer.parseInt(winCountTv.getText().toString()));
                intent.putExtra("PendingCount", Integer.parseInt(pending));
                startActivity(intent);
            }
        });
        return view;
    }

    public void sendRequest(){
        // RequestQueue를 새로 만들어준다.
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        pId = ((InfoShowerActivity)getActivity()).tempStr;
        // Request를 요청 할 URL
        String url ="http://donote.co:8000/api/v1/" + pId + "/summary/";
        JsonObjectRequest jsonObjectRequest =new JsonObjectRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject obj;
                            pId = response.getString("pid");
                            lifetime = response.getJSONObject("total");
                            obj = lifetime;
                            avg_score = obj.getString("avg_score");
                            score = obj.getString("score");
                            playtime = obj.getString("playtime");
                            most_played = obj.getString("most_played");
                            best_played = obj.getString("best_played");
                            turn_count = obj.getString("turn_count");
                            pending = obj.getString("pending_count");
                            match_count = obj.getString("match_count");
                            win_count = obj.getString("win_count");
                            spawned = obj.getString("spawned");
                            killed = obj.getString("killed");
                            damage = obj.getString("damage");

                            //setting all cardview with data
                            playerIdTv.setText(pId);
                            most_playedTv.setText(most_played);
                            avg_scoreTv.setText(avg_score);
                            best_playedTv.setText(best_played);
                            scoreTv.setText(score);
                            playtimeTv.setText(playtime);
                            turnCountTv.setText(turn_count);
                            matchCountTv.setText(match_count);
                            winCountTv.setText(win_count);
                            spawnedTv.setText(spawned);
                            killedTv.setText(killed);
                            damageTv.setText(damage);
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
