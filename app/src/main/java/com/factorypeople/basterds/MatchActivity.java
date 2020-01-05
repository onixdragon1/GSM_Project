package com.factorypeople.basterds;

import android.content.Intent;
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

import org.json.JSONException;
import org.json.JSONObject;

public class MatchActivity extends AppCompatActivity {
    String pId;
    TextView playerIdTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        playerIdTv = (TextView)findViewById(R.id.playerId_match);

        Intent intent = getIntent();
        pId = intent.getStringExtra("id");
        playerIdTv.setText(pId);
    }
}
