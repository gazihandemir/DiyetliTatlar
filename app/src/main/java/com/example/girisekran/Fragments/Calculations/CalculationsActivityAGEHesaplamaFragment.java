package com.example.girisekran.Fragments.Calculations;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.girisekran.R;


public class CalculationsActivityAGEHesaplamaFragment extends Fragment {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_calculations_activity_a_g_e_hesaplama, container, false);
        btnHesapla = rootView.findViewById(R.id.btnCalculationsFragmentAGEHesapla);
        rb1 = rootView.findViewById(R.id.rbAgeFragment1);
        rb2 = rootView.findViewById(R.id.rbAgeFragment2);
        rb3 = rootView.findViewById(R.id.rbAgeFragment3);
        rb4 = rootView.findViewById(R.id.rbAgeFragment4);
        rb5 = rootView.findViewById(R.id.rbAgeFragment5);
        edKilo = rootView.findViewById(R.id.edCalculationsFragmentAGEKio);
        edCinsiyet = rootView.findViewById(R.id.edCalculationsFragmentAGECinsiyet);
        tvCevap = rootView.findViewById(R.id.tvCalculationsFragmentAGECevap);
        tvCevap.setVisibility(View.INVISIBLE);
        radioButtonTiklanmaKontrolü();
        hesapla();
        return rootView;
    }

    public void radioButtonTiklanmaKontrolü() {
        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aktiflikDegeri = 1.1;
                rb2.setVisibility(View.INVISIBLE);
                rb3.setVisibility(View.INVISIBLE);
                rb4.setVisibility(View.INVISIBLE);
                rb5.setVisibility(View.INVISIBLE);
            }
        });
        rb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aktiflikDegeri = 1.2;
                rb1.setVisibility(View.INVISIBLE);
                rb3.setVisibility(View.INVISIBLE);
                rb4.setVisibility(View.INVISIBLE);
                rb5.setVisibility(View.INVISIBLE);
            }
        });
        rb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aktiflikDegeri = 1.3;
                rb2.setVisibility(View.INVISIBLE);
                rb1.setVisibility(View.INVISIBLE);
                rb4.setVisibility(View.INVISIBLE);
                rb5.setVisibility(View.INVISIBLE);
            }
        });
        rb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aktiflikDegeri = 1.4;
                rb2.setVisibility(View.INVISIBLE);
                rb3.setVisibility(View.INVISIBLE);
                rb1.setVisibility(View.INVISIBLE);
                rb5.setVisibility(View.INVISIBLE);
            }
        });
        rb5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aktiflikDegeri = 1.1;
                rb2.setVisibility(View.INVISIBLE);
                rb3.setVisibility(View.INVISIBLE);
                rb4.setVisibility(View.INVISIBLE);
                rb1.setVisibility(View.INVISIBLE);
            }
        });
    }

    public void hesapla() {
        btnHesapla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                    Toast.makeText(getActivity(), "Lütfen Cinsiyetinizi kadın yada erkek olarak giriniz ! ", Toast.LENGTH_SHORT).show();
                }
                bmhHesabı = 24 * oran * kilo;
                ageHesabı = bmhHesabı * aktiflikDegeri;
                String stringAgeHesabı = String.valueOf(ageHesabı);
                tvCevap.setVisibility(View.VISIBLE);
                tvCevap.setText(stringAgeHesabı);
            }
        });
    }
}
