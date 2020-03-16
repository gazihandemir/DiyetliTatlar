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

/**
 * A simple {@link Fragment} subclass.
 */
public class CalculationsActivityIdealKiloHesaplamaFragment extends Fragment {
    private Button btnHesapla;
    private EditText edBoy, edYas;
    private TextView tvCevap;
    private static double boy = 0;
    private static double yas = 0;
    private static double idealbki = 0;
    private static double idealKilo = 0;
    private static String stringIdealKilo;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_calculations_activity_ideal_kilo_hesaplama, container, false);
        btnHesapla = rootView.findViewById(R.id.btnHesaplaIdealAct);
        edBoy = rootView.findViewById(R.id.edIdealActBoy);
        edYas = rootView.findViewById(R.id.edIdealActYas);
        tvCevap = rootView.findViewById(R.id.tvCevapIdealAct);
        tvCevap.setVisibility(View.INVISIBLE);

        hesaplaIdeal();
        return rootView;
    }

    private void hesaplaIdeal() {
        btnHesapla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        });

    }
}
