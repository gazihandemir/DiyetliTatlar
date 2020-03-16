package com.example.girisekran.Fragments.Calculations;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.girisekran.R;


public class CalculationsActivityBKIHesaplamaFragment extends Fragment {
    Button btnHesapla;
    EditText edKilo, edBoy;
    TextView tvCevap;
    ImageView imgBki;
    static double hesap = 0;
    static double boy = 0;
    static double kilo = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_calculations_activity_b_k_i_hesaplama, container, false);
        btnHesapla = rootView.findViewById(R.id.btnBkiActHesapla);
        edBoy = rootView.findViewById(R.id.edBkiActBoy);
        edKilo = rootView.findViewById(R.id.edBkiActKilo);
        tvCevap = rootView.findViewById(R.id.tvBkiActCevap);
        imgBki = rootView.findViewById(R.id.imgBkiAct);
        tvCevap.setVisibility(View.INVISIBLE);
        hesaplaBki();
        return rootView;
    }

    public void hesaplaBki() {
        btnHesapla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kilo = Double.parseDouble(edKilo.getText().toString());
                boy = Double.parseDouble(edBoy.getText().toString());
                hesap = ((kilo) / Math.pow(boy, 2));


                if (hesap <= 18.5) {
                    tvCevap.setVisibility(View.VISIBLE);
                    String hesapString = String.valueOf(hesap);
                    tvCevap.setText(hesapString);
                    imgBki.setImageResource(R.drawable.zbki);
                    imgBki.setVisibility(View.VISIBLE);
                } else if (hesap > 18.5 && hesap <= 24.9) {
                    tvCevap.setVisibility(View.VISIBLE);
                    String hesapString = String.valueOf(hesap);
                    tvCevap.setText(hesapString);
                    imgBki.setImageResource(R.drawable.nbki);
                    imgBki.setVisibility(View.VISIBLE);
                } else if (hesap > 24.9 && hesap <= 29.9) {
                    tvCevap.setVisibility(View.VISIBLE);
                    String hesapString = String.valueOf(hesap);
                    tvCevap.setText(hesapString);
                    imgBki.setImageResource(R.drawable.kbki);
                    imgBki.setVisibility(View.VISIBLE);
                } else if (hesap > 29.9 && hesap <= 34.9) {
                    tvCevap.setVisibility(View.VISIBLE);
                    String hesapString = String.valueOf(hesap);
                    tvCevap.setText(hesapString);
                    imgBki.setImageResource(R.drawable.obki);
                    imgBki.setVisibility(View.VISIBLE);
                } else if (hesap > 34.9) {
                    tvCevap.setVisibility(View.VISIBLE);
                    String hesapString = String.valueOf(hesap);
                    tvCevap.setText(hesapString);
                    imgBki.setImageResource(R.drawable.aobki);
                    imgBki.setVisibility(View.VISIBLE);
                }
            }
        });

    }
}
