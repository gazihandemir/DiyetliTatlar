package com.example.girisekran.Fragments.Calculations;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.girisekran.R;


public class CalculationsActivityBMHHesaplamaFragment extends Fragment {
    EditText edCinsiyet, edKilo;
    TextView tvCevap;
    Button btnHesapla;
    static private String cinsiyet = "";
    static private double kilo = 0;
    static private double bmhHesabı = 0;
    static private double oran = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_calculations_activity_b_m_h_hesaplama, container, false);
        edCinsiyet = rootView.findViewById(R.id.edBmhActCinsiyet);
        edKilo = rootView.findViewById(R.id.edBmhActKilo);
        tvCevap = rootView.findViewById(R.id.tvBmhActCevap);
        tvCevap.setVisibility(View.INVISIBLE);
        btnHesapla = rootView.findViewById(R.id.btnBmhActHesapla);

        hesaplaBmh();
        return rootView;
    }
    public void hesaplaBmh() {
btnHesapla.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
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
        String stringMmhHesabı = String.valueOf(bmhHesabı);
        tvCevap.setVisibility(View.VISIBLE);
        tvCevap.setText(stringMmhHesabı);

    }
});

    }
}
