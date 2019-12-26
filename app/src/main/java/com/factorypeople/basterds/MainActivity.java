package com.factorypeople.basterds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    EditText id_input, pw_input;
    Button LoginBtn;

    String clientId = "3LSLqFwtkBMMyio3eRKHfvvX4hOisF0Aoc7ySPMpYrK4Bzw2pk261MFcLmLs0LLG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id_input = findViewById(R.id.inputId);
        pw_input = findViewById(R.id.inputPw);
        LoginBtn = findViewById(R.id.btnLogin);

        final RequestQueue queue = Volley.newRequestQueue(this);

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (id_input.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "ID를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (pw_input.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                String url = "http://donote.co:8000/api/auth/session/";

                HashMap<String, String> data = new HashMap<>();
                data.put("clientid", clientId);
                data.put("id", id_input.getText().toString());
                data.put("pw", pw_input.getText().toString());

                // Request a string response from the provided URL.
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(data),
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.e("response", response.toString());
                                Intent intent = new Intent(MainActivity.this, searchActivity.class);
                                try {
                                    intent.putExtra("sessid", response.getString("sessid"));
                                    intent.putExtra("pid", response.getString("pid"));
                                    intent.putExtra("nickname", response.getString("nickname"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                startActivity(intent);
                                MainActivity.this.finish();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Server Not Available.", Toast.LENGTH_SHORT).show();
                        Log.e("failed", error.toString());
                    }
                });

                // Add the request to the RequestQueue.
                queue.add(request);
            }
        });
    }
}