package com.example.girisekran.Fragments.Calculations;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.girisekran.R;

import java.text.DecimalFormat;

public class CalculationsActivitySuHesaplamaFragment extends Fragment {
    EditText edKilo;
    TextView tvCevap;
    Button btnHesapla;
    static private Double kilo = 0.0;
    static private Double hesap = 0.0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_calculations_activity_su_hesaplama, container, false);
        edKilo = rootView.findViewById(R.id.edSuActKilo);
        tvCevap = rootView.findViewById(R.id.tvSuActCevap);
        tvCevap.setVisibility(View.INVISIBLE);
        hesaplaSu();
        return rootView;
    }

    public void hesaplaSu() {
        btnHesapla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double oran = 0.001;
                kilo = Double.parseDouble(edKilo.getText().toString());
                hesap = kilo * oran * 35;
                String formatHesap = new DecimalFormat("##.##").format(hesap).replace(',', '.');
                tvCevap.setVisibility(View.VISIBLE);
                tvCevap.setText(formatHesap + "\t Litre");
            }
        });


    }
}
