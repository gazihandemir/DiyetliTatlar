package com.example.girisekran.Hesaplamalar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.girisekran.R;

public class BMHActivity extends AppCompatActivity {
    EditText edCinsiyet, edKilo;
    TextView tvCevap;
    static private String cinsiyet = "";
    static private double kilo = 0;
    static private double bmhHesabı = 0;
    static private double oran = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmh);
        edCinsiyet = findViewById(R.id.edBmhActCinsiyet);
        edKilo = findViewById(R.id.edBmhActKilo);
        tvCevap = findViewById(R.id.tvBmhActCevap);
        tvCevap.setVisibility(View.INVISIBLE);


    }

    public void hesaplaBmh(View view) {

        cinsiyet = edCinsiyet.getText().toString();
        kilo = Double.parseDouble(edKilo.getText().toString());

        if (cinsiyet.equalsIgnoreCase("erkek")) {
            oran = 1;
        } else if (cinsiyet.equalsIgnoreCase("kadın")) {
            oran = 0.95;
        } else {
            Toast.makeText(this, "Lütfen Cinsiyetinizi kadın yada erkek olarak giriniz ! ", Toast.LENGTH_SHORT).show();
        }
        bmhHesabı = 24 * oran * kilo;
        String stringMmhHesabı = String.valueOf(bmhHesabı);
        tvCevap.setVisibility(View.VISIBLE);
        tvCevap.setText(stringMmhHesabı);

    }
}
