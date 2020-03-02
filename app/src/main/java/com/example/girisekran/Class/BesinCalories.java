package com.example.girisekran.Class;

public class BesinCalories {
    private String name,porsiyon,kcal;

    public BesinCalories(String name, String porsiyon, String kcal) {
        this.name = name;
        this.porsiyon = porsiyon;
        this.kcal = kcal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPorsiyon() {
        return porsiyon;
    }

    public void setPorsiyon(String porsiyon) {
        this.porsiyon = porsiyon;
    }

    public String getKcal() {
        return kcal;
    }

    public void setKcal(String kcal) {
        this.kcal = kcal;
    }

    @Override
    public String toString() {
        return "BesinCalories{" +
                "name='" + name + '\'' +
                ", porsiyon='" + porsiyon + '\'' +
                ", kcal='" + kcal + '\'' +
                '}';
    }
}
