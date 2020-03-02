package com.example.girisekran.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.girisekran.Adapter.BesinCaloriesAdapter;
import com.example.girisekran.Class.BesinCalories;
import com.example.girisekran.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CalculationsActivityBesinlerCaloriesFragment extends Fragment {
    RecyclerView recyclerView;
    BesinCaloriesAdapter besinCaloriesAdapter;
    List<BesinCalories> list;
    String name, porsiyon, kjal;

    public CalculationsActivityBesinlerCaloriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        BesinCalories besin = new BesinCalories("muz", "porsiyon", "50 kjal");
        BesinCalories besin2 = new BesinCalories("muz12", "porsiyon12", "50 kjal12");

        list = new ArrayList<>();
        list.add(besin);
        list.add(besin2);
        View rootView = inflater.inflate(R.layout.fragment_calculations_activity_besinler_calories, container, false);
        recyclerView = rootView.findViewById(R.id.calculationsActivitiyBesinlerCaloriesRecyclerView);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(layoutManager);
        besinCaloriesAdapter = new BesinCaloriesAdapter(getActivity(), list, getActivity(), name, porsiyon, kjal);
        recyclerView.setAdapter(besinCaloriesAdapter);
        besinCaloriesAdapter.notifyDataSetChanged();
        return rootView;
    }
}
