package com.example.girisekran.Hesaplamalar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.girisekran.R;

public class IdealKiloHesaplama extends AppCompatActivity {
    Button btnHesapla;
    EditText edBoy, edYas;
    TextView tvCevap;
    static double boy = 0;
    static double yas = 0;
    static double idealbki = 0;
    static double idealKilo = 0;
    static String stringIdealKilo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ideal_kilo_hesaplama);
        btnHesapla = findViewById(R.id.btnHesaplaIdealAct);
        edBoy = findViewById(R.id.edIdealActBoy);
        tvCevap = findViewById(R.id.tvCevapIdealAct);
        tvCevap.setVisibility(View.INVISIBLE);
        edYas = findViewById(R.id.edIdealActYas);
    }

    public void hesaplaIdeal(View view) {
        boy = Double.parseDouble(edBoy.getText().toString());
        yas = Double.parseDouble(edYas.getText().toString());
        if (yas >= 19 && yas <= 24) {
            idealbki = 21;
        } else if (yas >= 25 && yas <= 34) {
            idealbki = 22;
        } else if (yas >= 35 && yas <= 44) {
            idealbki = 23;
        } else if (yas >= 45 && yas <= 54) {
            idealbki = 24;
        } else if (yas >= 55 && yas <= 64) {
            idealbki = 25;
        } else if (yas >= 65) {
            idealbki = 26;
        }
        idealKilo = idealbki * Math.pow(boy, 2);
        stringIdealKilo = String.valueOf(idealKilo);
        tvCevap.setVisibility(View.VISIBLE);
        tvCevap.setText(stringIdealKilo);
    }
}
