package com.example.girisekran.Fragments;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.girisekran.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileActivityBasariHikayemFragment extends Fragment {
    BarChart barChart;

/*
    ProgressBar progressBar1;
    ProgressBar progressBar2;
    ProgressBar progressBar3;
    ProgressBar progressBar4;
    ProgressBar progressBar5;
    static String progressBar1Deger = "30";
    static String progressBar2Deger = "40";
    static String progressBar3Deger = "10";
    static String progressBar4Deger = "65";
    static String progressBar5Deger = "100";
    TextView tv1, tv2, tv3, tv4, tv5;
*/

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile_activity_basari_hikayem, container, false);

       /* progressBar1 = rootView.findViewById(R.id.progresbar1);
        progressBar2 = rootView.findViewById(R.id.progresbar2);
        progressBar3 = rootView.findViewById(R.id.progresbar3);
        progressBar4 = rootView.findViewById(R.id.progresbar4);
        progressBar5 = rootView.findViewById(R.id.progresbar5);

        tv1 = rootView.findViewById(R.id.tvProgresbar1);
        tv2 = rootView.findViewById(R.id.tvProgresbar2);
        tv3 = rootView.findViewById(R.id.tvProgresbar3);
        tv4 = rootView.findViewById(R.id.tvProgresbar4);
        tv5 = rootView.findViewById(R.id.tvProgresbar5);


        int degerInt1 = Integer.parseInt(progressBar1Deger);
        int degerInt2 = Integer.parseInt(progressBar2Deger);
        int degerInt3 = Integer.parseInt(progressBar3Deger);
        int degerInt4 = Integer.parseInt(progressBar4Deger);
        int degerInt5 = Integer.parseInt(progressBar5Deger);
        progressBar1.setProgress(10);
        progressBar2.setProgress(degerInt2);
        progressBar3.setProgress(degerInt3);
        progressBar4.setProgress(degerInt4);
        progressBar5.setProgress(degerInt5);
        tv1.setText(progressBar1Deger + "kg");
        tv2.setText(progressBar2Deger + "kg");
        tv3.setText(progressBar3Deger + "kg");
        tv4.setText(progressBar4Deger + "kg");
        tv5.setText(progressBar5Deger + "kg");*/
        barChart = rootView.findViewById(R.id.barChart);
        BarDataSet barDataSet1 = new BarDataSet(dataValues1(), "Kilo");
        barDataSet1.setColors(ColorTemplate.MATERIAL_COLORS);
        BarData barData = new BarData();
        barData.addDataSet(barDataSet1);
        barChart.setData(barData);
        barChart.invalidate();
        return rootView;
    }
    private ArrayList<BarEntry> dataValues1() {
        ArrayList<BarEntry> dataVals = new ArrayList<>();
        dataVals.add(new BarEntry(1f, 98));
        dataVals.add(new BarEntry(2f, 96));
        dataVals.add(new BarEntry(3f, 92));
        dataVals.add(new BarEntry(4f, 88));
        dataVals.add(new BarEntry(5f, 87));
        dataVals.add(new BarEntry(6f, 86));
        dataVals.add(new BarEntry(7f, 86));
        dataVals.add(new BarEntry(8f, 87));
        dataVals.add(new BarEntry(9f, 90));
        dataVals.add(new BarEntry(10f, 87));
        return dataVals;

    }
}
