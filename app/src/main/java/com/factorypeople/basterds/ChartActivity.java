package com.factorypeople.basterds;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ChartActivity extends AppCompatActivity {
    String pId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        Intent intent = getIntent();
        pId = intent.getStringExtra("id");
    }

}
