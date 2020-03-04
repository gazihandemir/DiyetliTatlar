package com.example.girisekran.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.girisekran.Adapter.BesinCaloriesAdapter;
import com.example.girisekran.Class.BesinCalories;
import com.example.girisekran.Class.BesinCaloriesDetails;
import com.example.girisekran.R;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class CalculationsActivityBesinlerCaloriesFragment extends Fragment {
    RecyclerView recyclerView;
    BesinCaloriesAdapter besinCaloriesAdapter;
    ArrayList<BesinCalories> list;
    BesinCalories besin;
    SearchView searchView;

    public CalculationsActivityBesinlerCaloriesFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        jsonOku();

        View rootView = inflater.inflate(R.layout.fragment_calculations_activity_besinler_calories, container, false);
        searchView = rootView.findViewById(R.id.calculationsActivitiySearchView);


        recyclerView = rootView.findViewById(R.id.calculationsActivitiyBesinlerCaloriesRecyclerView);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(layoutManager);
        besinCaloriesAdapter = new BesinCaloriesAdapter(getActivity(), list, getActivity());
        recyclerView.setAdapter(besinCaloriesAdapter);


        besinCaloriesAdapter.notifyDataSetChanged();
        return rootView;
    }

    public void jsonOku() {
        besin = new BesinCalories();

        BesinCaloriesDetails besinlerList = new BesinCaloriesDetails();
        try {
            // load file
            BufferedReader jsonReader = new BufferedReader(new InputStreamReader(this.getResources().openRawResource(R.raw.besinler)));
            StringBuilder jsonBuilder = new StringBuilder();
            for (String line = null; (line = jsonReader.readLine()) != null; ) {
                jsonBuilder.append(line).append("\n");
            }

            Gson gson = new Gson();
            besinlerList = gson.fromJson(jsonBuilder.toString(), BesinCaloriesDetails.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        list = new ArrayList<>();
        for (int i = 0; i < besinlerList.getBesinler().size(); i++) {
            list.add(besin = new BesinCalories(besinlerList.getBesinler().get(i).getName(), besinlerList.getBesinler().get(i).getPorsiyon()
                    , besinlerList.getBesinler().get(i).getCalories()));
        }
    }


}
