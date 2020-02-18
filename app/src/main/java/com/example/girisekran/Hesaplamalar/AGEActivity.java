package com.example.girisekran.Hesaplamalar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.girisekran.R;

public class AGEActivity extends AppCompatActivity {
    Button btnHesapla;
    RadioButton rb1, rb2, rb3, rb4, rb5;
    EditText edKilo, edCinsiyet;
    TextView tvCevap;
    static private double aktiflikDegeri = 0;
    static private String cinsiyet = "";
    static private double kilo = 0;
    static private double oran = 0;
    static private double bmhHesabı = 0;
    static private double ageHesabı = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age);
        rb1 = findViewById(R.id.rbAGEAct1);
        rb2 = findViewById(R.id.rbAGEAct2);
        rb3 = findViewById(R.id.rbAGEAct3);
        rb4 = findViewById(R.id.rbAGEAct4);
        rb5 = findViewById(R.id.rbAGEAct5);
        btnHesapla = findViewById(R.id.btnAGEActHesapla);
        edCinsiyet = findViewById(R.id.edAGEActCinsiyet);
        edKilo = findViewById(R.id.edAGEActivtyKilo);
        tvCevap = findViewById(R.id.tvAGEActCevap);
        tvCevap.setVisibility(View.INVISIBLE);
    }

    public void rb1(View view) {
        aktiflikDegeri = 1.1;
        rb2.setVisibility(View.INVISIBLE);
        rb3.setVisibility(View.INVISIBLE);
        rb4.setVisibility(View.INVISIBLE);
        rb5.setVisibility(View.INVISIBLE);
    }

    public void rb2(View view) {
        aktiflikDegeri = 1.2;
        rb1.setVisibility(View.INVISIBLE);
        rb3.setVisibility(View.INVISIBLE);
        rb4.setVisibility(View.INVISIBLE);
        rb5.setVisibility(View.INVISIBLE);
    }

    public void rb3(View view) {
        aktiflikDegeri = 1.3;
        rb2.setVisibility(View.INVISIBLE);
        rb1.setVisibility(View.INVISIBLE);
        rb4.setVisibility(View.INVISIBLE);
        rb5.setVisibility(View.INVISIBLE);
    }

    public void rb4(View view) {
        aktiflikDegeri = 1.4;
        rb2.setVisibility(View.INVISIBLE);
        rb3.setVisibility(View.INVISIBLE);
        rb1.setVisibility(View.INVISIBLE);
        rb5.setVisibility(View.INVISIBLE);
    }

    public void rb5(View view) {
        aktiflikDegeri = 1.5;
        rb2.setVisibility(View.INVISIBLE);
        rb3.setVisibility(View.INVISIBLE);
        rb4.setVisibility(View.INVISIBLE);
        rb1.setVisibility(View.INVISIBLE);
    }

    public void hesaplaAge(View view) {
        rb1.setVisibility(View.VISIBLE);
        rb2.setVisibility(View.VISIBLE);
        rb3.setVisibility(View.VISIBLE);
        rb4.setVisibility(View.VISIBLE);
        rb5.setVisibility(View.VISIBLE);

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
        ageHesabı = bmhHesabı * aktiflikDegeri;
        String stringAgeHesabı = String.valueOf(ageHesabı);
        tvCevap.setVisibility(View.VISIBLE);
        tvCevap.setText(stringAgeHesabı);
    }


}
