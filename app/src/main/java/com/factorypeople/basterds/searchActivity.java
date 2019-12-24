package com.factorypeople.basterds;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class searchActivity extends AppCompatActivity {

    ImageButton btnSearch;
    EditText searchTextView;
    String temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        btnSearch = findViewById(R.id.btnSearch);
        searchTextView = findViewById(R.id.inputSearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), InfoShowerActivity.class);
                temp = searchTextView.getText().toString();
                intent.putExtra("playerName", temp);
                startActivity(intent);
            }
        });
    }
}
