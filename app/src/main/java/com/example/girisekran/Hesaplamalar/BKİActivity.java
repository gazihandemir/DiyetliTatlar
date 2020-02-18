package com.example.girisekran.Hesaplamalar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.girisekran.R;

public class BKİActivity extends AppCompatActivity {
    Button btnHesapla;
    EditText edKilo, edBoy;
    TextView tvCevap;
    ImageView imgBki;
    static double hesap = 0;
    static double boy = 0;
    static double kilo = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bki);
        btnHesapla = findViewById(R.id.btnBkiActHesapla);
        edBoy = findViewById(R.id.edBkiActBoy);
        edKilo = findViewById(R.id.edBkiActKilo);
        tvCevap = findViewById(R.id.tvBkiActCevap);
        imgBki = findViewById(R.id.imgBkiAct);
        tvCevap.setVisibility(View.INVISIBLE);
        imgBki.setVisibility(View.INVISIBLE);
    }

    public void hesaplaBki(View view) {
        kilo = Double.parseDouble(edKilo.getText().toString());
        boy = Double.parseDouble(edBoy.getText().toString());
        hesap = ((kilo) / Math.pow(boy, 2));


        if (hesap <= 18.5){
            tvCevap.setVisibility(View.VISIBLE);
            String hesapString = String.valueOf(hesap);
            tvCevap.setText(hesapString);
            imgBki.setImageResource(R.drawable.zbki);
            imgBki.setVisibility(View.VISIBLE);
        }else if(hesap > 18.5 && hesap <= 24.9 ){
            tvCevap.setVisibility(View.VISIBLE);
            String hesapString = String.valueOf(hesap);
            tvCevap.setText(hesapString);
            imgBki.setImageResource(R.drawable.nbki);
            imgBki.setVisibility(View.VISIBLE);
        }else if (hesap > 24.9 && hesap <= 29.9){
            tvCevap.setVisibility(View.VISIBLE);
            String hesapString = String.valueOf(hesap);
            tvCevap.setText(hesapString);
            imgBki.setImageResource(R.drawable.kbki);
            imgBki.setVisibility(View.VISIBLE);
        }else if(hesap > 29.9 && hesap <= 34.9){
            tvCevap.setVisibility(View.VISIBLE);
            String hesapString = String.valueOf(hesap);
            tvCevap.setText(hesapString);
            imgBki.setImageResource(R.drawable.obki);
            imgBki.setVisibility(View.VISIBLE);
        }else if (hesap > 34.9 ){
            tvCevap.setVisibility(View.VISIBLE);
            String hesapString = String.valueOf(hesap);
            tvCevap.setText(hesapString);
            imgBki.setImageResource(R.drawable.aobki);
            imgBki.setVisibility(View.VISIBLE);
        }
    }
}
