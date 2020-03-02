package com.example.girisekran.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.girisekran.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class CalculationsActivityBesinlerCaloriesFragment extends Fragment {

    public CalculationsActivityBesinlerCaloriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_calculations_activity_besinler_calories, container, false);
        return rootView;
    }
}
