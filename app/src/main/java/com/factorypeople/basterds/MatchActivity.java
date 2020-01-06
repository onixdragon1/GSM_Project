package com.factorypeople.basterds;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MatchActivity extends AppCompatActivity {
    String pId, playedAs, status, score, playtime, turn_count, spawned, killed, damage, createAt, terminatedAt;
    TextView playerIdTv, playedAsTv, statusTv, scoreTv, playtimeTv, turnCountTv, spawnedTv, killedTv, damageTv, createdAtTv, terminatedAtTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        playerIdTv = (TextView)findViewById(R.id.playerId_match);

    }

    public void sendRequest(){
        // RequestQueue를 새로 만들어준다.
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        // Request를 요청 할 URL
        String url ="http://donote.co:8000/api/v1/" + pId + "/match/";
        JsonObjectRequest jsonObjectRequest =new JsonObjectRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray obj = new JSONArray();
                            pId = response.getString("player");
                            obj = response.getJSONArray("data");
                            playedAs = obj.getJSONObject(0).getString("played_as");
                            status = obj.getJSONObject(0).getString("status");
                            score = obj.getJSONObject(0).getString("score");
                            playtime = obj.getJSONObject(0).getString("playtime");
                            turn_count = obj.getJSONObject(0).getString("turn_count");
                            spawned = obj.getJSONObject(0).getString("spawned");
                            killed = obj.getJSONObject(0).getString("killed");
                            damage = obj.getJSONObject(0).getString("damage");
                            createAt = obj.getJSONObject(0).getString("create_at");
                            terminatedAt = obj.getJSONObject(0).getString("terminated_at");
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "That didn't work!", Toast.LENGTH_SHORT).show();
            }
        });
        // queue에 Request를 추가해준다.
        queue.add(jsonObjectRequest);
    }
}