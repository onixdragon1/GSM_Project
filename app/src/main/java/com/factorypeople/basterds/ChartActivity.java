package com.factorypeople.basterds;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;

public class ChartActivity extends AppCompatActivity {
    private PieChart pieChart;
    String pId;
    BigDecimal matchCount, winCount, operate_result;
    TextView playerIdTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        pieChart = (PieChart)findViewById(R.id.winRate_chart);
        playerIdTv = (TextView)findViewById(R.id.playerId_chart);

        Intent intent = getIntent();
        pId = intent.getStringExtra("id");
        matchCount = BigDecimal.valueOf(intent.getIntExtra("MatchCount", 0));
        winCount = BigDecimal.valueOf(intent.getIntExtra("WinCount", 0));
        Log.i("matchCount", matchCount+"");
        Log.i("winCount", winCount+"");
        playerIdTv.setText(pId);

        PieDataSet pieDataSet = new PieDataSet(getData(), "Win-Lose Rate");
        pieDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.animateXY(500, 500);
        pieChart.invalidate();
    }

    private ArrayList getData(){
        ArrayList<PieEntry> entries = new ArrayList<>();
        operate_result = winCount.divide(matchCount);
        entries.add(new PieEntry((float)operate_result.doubleValue()*100, "Win"));
        entries.add(new PieEntry((1-(float)operate_result.doubleValue())*100, "Lose"));
        Log.i("rate_win", (float)operate_result.doubleValue()+"");
        return entries;
    }
}
