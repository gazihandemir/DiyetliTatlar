package com.example.girisekran.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.girisekran.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class DenemeFragment1 extends Fragment {


    public DenemeFragment1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_deneme_fragment1, container, false);

        return viewGroup;
    }

}
