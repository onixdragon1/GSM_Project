package com.factorypeople.basterds;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.gson.JsonIOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class ChartActivity extends AppCompatActivity {
    private PieChart pieChart;
    private LineChart lineChart;
    String pId, killed;
    BigDecimal matchCount, winCount, operate_result;
    TextView playerIdTv_pie, playerIdTv_line;
    ArrayList<Entry> lineEntries = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        lineChart = (LineChart)findViewById(R.id.killRate_chart);
        pieChart = (PieChart)findViewById(R.id.winRate_chart);
        playerIdTv_pie = (TextView)findViewById(R.id.playerId_pieChart);
        playerIdTv_line = (TextView)findViewById(R.id.playerId_lineChart);

        Intent intent = getIntent();
        pId = intent.getStringExtra("id");
        matchCount = BigDecimal.valueOf(intent.getIntExtra("MatchCount", 0));
        winCount = BigDecimal.valueOf(intent.getIntExtra("WinCount", 0));
        Log.i("matchCount", matchCount+"");
        Log.i("winCount", winCount+"");
        playerIdTv_pie.setText(pId);
        playerIdTv_line.setText(pId);

        sendRequest();
        make_PieChart();
    }

    private void make_PieChart(){
        PieDataSet pieDataSet = new PieDataSet(getPieData(), "Win-Lose Rate");
        pieDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.animateXY(500, 500);
        pieChart.invalidate();
    }

    private void make_LineChart(){
        LineDataSet lineDataSet = new LineDataSet(lineEntries, "Chart_KillRate");
        lineDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);

        YAxis yAxisRight = lineChart.getAxisRight();
        yAxisRight.setEnabled(false);

        YAxis yAxisLeft = lineChart.getAxisLeft();
        yAxisLeft.setGranularity(1f);

        LineData data = new LineData(lineDataSet);
        lineChart.setData(data);
        lineChart.animateX(500);
        lineChart.invalidate();
    }

    private ArrayList<PieEntry> getPieData(){
        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        operate_result = winCount.divide(matchCount, MathContext.DECIMAL64);
        pieEntries.add(new PieEntry((float)operate_result.doubleValue()*100, "Win"));
        pieEntries.add(new PieEntry((1-(float)operate_result.doubleValue())*100, "Lose"));
        return pieEntries;
    }

    public void sendRequest(){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url ="http://donote.co:8000/api/v1/" + pId + "/match/";
        JsonObjectRequest jsonObjectRequest =new JsonObjectRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray dataObj = response.getJSONArray("data");
                            for(int i=0;i<dataObj.length();i++) {
                                JSONObject obj = dataObj.getJSONObject(i);
                                Log.i("parsed Object", obj + "");
                                killed = obj.getString("killed");
                                Log.i("Parsed kill Record ", killed + "");
                                lineEntries.add(new Entry((float)i+1, BigDecimal.valueOf(Integer.parseInt(killed)).floatValue()));
                                make_LineChart();
                            }
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        // queue에 Request를 추가해준다.
        queue.add(jsonObjectRequest);
    }
}
