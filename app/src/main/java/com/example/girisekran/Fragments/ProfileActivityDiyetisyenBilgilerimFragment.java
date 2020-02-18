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
public class ProfileActivityDiyetisyenBilgilerimFragment extends Fragment {


    public ProfileActivityDiyetisyenBilgilerimFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_activity_diyetisyen_bilgilerim, container, false);
    }

}
