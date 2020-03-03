package com.example.girisekran.Class;

import android.widget.Filter;

import com.example.girisekran.Adapter.BesinCaloriesAdapter;

import java.util.ArrayList;

public class FilterHelper extends Filter {
    private ArrayList<BesinCalories> suankiListe;
    private BesinCaloriesAdapter suankiAdapter = null;

    public FilterHelper(ArrayList<BesinCalories> suankiListe, BesinCaloriesAdapter suankiAdapter) {
        this.suankiListe = suankiListe;
        this.suankiAdapter = suankiAdapter;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults sonuc = new FilterResults();
        if (constraint != null && constraint.length() > 0) {
            String aranilanAd = constraint.toString().toLowerCase();
            ArrayList<BesinCalories> eslesenler = new ArrayList<BesinCalories>();
            for (BesinCalories besin : suankiListe) {
                String adi = besin.getName().toLowerCase();
                if (adi.contains(aranilanAd.toString())) {
                    eslesenler.add(besin);
                }
            }
            sonuc.values = eslesenler;
            sonuc.count = eslesenler.size();
        } else {
            sonuc.values = suankiListe;
            sonuc.count = suankiListe.size();
        }
        return sonuc;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        suankiAdapter.setFilter(results.values);
        suankiAdapter.notifyDataSetChanged();
    }
}
