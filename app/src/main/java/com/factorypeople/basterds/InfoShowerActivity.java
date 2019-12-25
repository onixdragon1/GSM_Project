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
    String pId, score, playtime, turn_count, match_count, win_count, spawned_alias, killed_alias, killed_hostiles, damage, heal;
    JSONObject total, insomnia, orangefamily, overhit, meisterboi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_search);

        btnBack = findViewById(R.id.backBtn);

        ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);

        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(vpPager);

        category = findViewById(R.id.categoryTextView);
        Intent intent = getIntent();
        tempStr = intent.getStringExtra("playerName");
        category.setText(tempStr + "\'s Record");

        sendRequest(1);

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
                    return LifetimeFragment.newInstance();
                case 1:
                    return InsomniaFragment.newInstance();
                case 2:
                    return OrangefamilyFragment.newInstance();
                case 3:
                    return OverhitFragment.newInstance();
                case 4:
                    return MeisterboiFragment.newInstance();
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + position;
        }
    }

    public void sendRequest(final int frg_num){
        // RequestQueue를 새로 만들어준다.
        RequestQueue queue = Volley.newRequestQueue(this);
        // Request를 요청 할 URL
        String url ="http://donote.co:8000/api/v1/12/summary/";

        JsonObjectRequest jsonObjectRequest =new JsonObjectRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject obj = new JSONObject();
                            pId = response.getString("pid");
                            switch(frg_num){
                                case 1:
                                    total = response.getJSONObject("total");
                                    obj = total;
                                    break;
                                case 2:
                                    insomnia = response.getJSONObject("insomnia");
                                    obj = insomnia;
                                    break;
                                case 3:
                                    orangefamily = response.getJSONObject("orangefamily");
                                    obj = orangefamily;
                                    break;
                                case 4:
                                    overhit = response.getJSONObject("overhit");
                                    obj = overhit;
                                    break;
                                case 5:
                                    meisterboi = response.getJSONObject("meisterboi");
                                    obj = meisterboi;
                                    break;
                            }
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
                            Log.d("id", pId);
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
