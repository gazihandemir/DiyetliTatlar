package com.example.girisekran.Hesaplamalar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.girisekran.R;

import java.text.DecimalFormat;

public class GunlukSuMiktariHesaplama extends AppCompatActivity {
    EditText edKilo;
    TextView tvCevap;
    static private Double kilo = 0.0;
    static private Double hesap = 0.0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gunluk_su_miktari_hesaplama);
        edKilo = findViewById(R.id.edSuActKilo);
        tvCevap = findViewById(R.id.tvSuActCevap);
        tvCevap.setVisibility(View.INVISIBLE);


    }

    public void hesaplaSu(View view) {
        Double oran = 0.001;
        kilo = Double.parseDouble(edKilo.getText().toString());
        hesap = kilo * oran * 35;
        String formatHesap = new DecimalFormat("##.##").format(hesap).replace(',', '.');
        tvCevap.setVisibility(View.VISIBLE);
        tvCevap.setText(formatHesap + "\t Litre");

    }
}
