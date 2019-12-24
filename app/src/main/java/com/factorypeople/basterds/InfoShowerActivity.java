package com.factorypeople.basterds;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import me.relex.circleindicator.CircleIndicator;

public class InfoShowerActivity extends AppCompatActivity {

    FragmentPagerAdapter adapterViewPager;
    static TextView category;
    static String tempStr;
    ImageButton btnBack;

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

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 6;

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
                    return LifetimeFragment.newInstance(0, "Page # 1");
                case 1:
                    return InsomniaFragment.newInstance(1, "Page # 2");
                case 2:
                    return OrangefamilyFragment.newInstance(2, "Page # 3");
                case 3:
                    return OverhitFragment.newInstance(3, "Page # 4");
                case 4:
                    return MeisterboiFragment.newInstance(4, "Page # 5");
                case 5:
                    return StatisticFragment.newInstance(5, "Page # 6");
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
}
