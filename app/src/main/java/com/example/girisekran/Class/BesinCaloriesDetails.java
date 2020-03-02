package com.example.girisekran.Class;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BesinCaloriesDetails {

    @SerializedName("besinler")
    @Expose
    private List<BesinCalories> besinler = null;

    public List<BesinCalories> getBesinler() {
        return besinler;
    }

    public void setBesinler(List<BesinCalories> besinler) {
        this.besinler = besinler;
    }

}