package com.example.girisekran.Fragments.Profile;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.girisekran.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileActivityKafamKarisiyorFragment extends Fragment {


    public ProfileActivityKafamKarisiyorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_activity_kafam_karisiyor, container, false);
    }

}
