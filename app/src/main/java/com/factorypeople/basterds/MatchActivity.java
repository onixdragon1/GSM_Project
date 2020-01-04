package com.factorypeople.basterds;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MatchActivity extends AppCompatActivity {
    String pId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        Intent intent = getIntent();
        pId = intent.getStringExtra("id");
    }

}
