package com.factorypeople.basterds;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import me.relex.circleindicator.CircleIndicator;

public class InfoShowerActivity extends AppCompatActivity {

    FragmentPagerAdapter adapterViewPager;
    static TextView category;
    static String tempStr;
    ImageButton btnBack;
    String pId, score, playtime, turn_count, match_count, win_count, spawned_alias, killed_alias, killed_hostiles, damage, heal, most_played;

    JSONObject total, insomnia, orangefamily, overhit, meisterboi;
    ViewPager vpPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_search);

        btnBack = findViewById(R.id.backBtn);

        vpPager = (ViewPager) findViewById(R.id.vpPager);
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);

        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(vpPager);

        category = findViewById(R.id.categoryTextView);
        Intent intent = getIntent();
        tempStr = intent.getStringExtra("playerName");
        category.setText(tempStr + "\'s Record");

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 5;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    LifetimeFragment frg_lifetime = new LifetimeFragment();
                    return frg_lifetime;
                case 1:
                    InsomniaFragment frg_insomnia = new InsomniaFragment();
                    return frg_insomnia;
                case 2:
                    OrangefamilyFragment frg_orangefamily = new OrangefamilyFragment();
                    return frg_orangefamily;
                case 3:
                    OverhitFragment frg_overhit = new OverhitFragment();
                    return frg_overhit;
                case 4:
                    MeisterboiFragment frg_meisterboi = new MeisterboiFragment();
                    return frg_meisterboi;
                default:
                    return null;
            }
        }
    }
}
