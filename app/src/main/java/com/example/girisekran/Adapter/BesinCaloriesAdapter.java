package com.example.girisekran.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.girisekran.Class.BesinCalories;
import com.example.girisekran.Class.FilterHelper;
import com.example.girisekran.R;

import java.util.ArrayList;
import java.util.List;

public class BesinCaloriesAdapter extends RecyclerView.Adapter<BesinCaloriesAdapter.ViewHolder> implements Filterable {
    Context context;
    List<BesinCalories> list;
    Activity activity;
    String name, porsiyon, kjal;
    BesinCaloriesAdapter suankiAdapter;
    FilterHelper myFilter = new FilterHelper((ArrayList<BesinCalories>) list, suankiAdapter);

    public BesinCaloriesAdapter(Context context, List<BesinCalories> list, Activity activity) {
        this.context = context;
        this.list = list;
        this.activity = activity;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.calculations_activity_besinler_calories_tek_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        BesinCalories besinCalories = list.get(position);
        holder.setData(besinCalories, position);
       /* holder.tvName.setText(list.get(position).toString());
        holder.tvPorsiyon.setText(list.get(position).toString());
        holder.tvKjal.setText(list.get(position).toString());*/
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setFilter(Object values) {
        list = (List<BesinCalories>) values;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPorsiyon, tvKjal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvCalName);
            tvPorsiyon = itemView.findViewById(R.id.tvCalPorsiyon);
            tvKjal = itemView.findViewById(R.id.tvCalKjal);
        }

        public void setData(BesinCalories besinCalories, int position) {
            this.tvName.setText(besinCalories.getName());
            this.tvPorsiyon.setText(besinCalories.getPorsiyon());
            this.tvKjal.setText(besinCalories.getCalories());
        }
    }


    @Override
    public Filter getFilter() {
        return myFilter;
    }
}
