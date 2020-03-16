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
import com.example.girisekran.R;

import java.util.ArrayList;
import java.util.List;

public class BesinCaloriesAdapter extends RecyclerView.Adapter<BesinCaloriesAdapter.ViewHolder> implements Filterable {
    Context context;
    List<BesinCalories> list;
    List<BesinCalories> arananList;
    Activity activity;
    String name, porsiyon, kjal;

    public BesinCaloriesAdapter(Context context, List<BesinCalories> list, Activity activity) {
        this.context = context;
        this.list = list;
        this.activity = activity;
        this.arananList = list;

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
        BesinCalories besinCalories = arananList.get(position);
        holder.setData(besinCalories, position);
       /* holder.tvName.setText(list.get(position).toString());
        holder.tvPorsiyon.setText(list.get(position).toString());
        holder.tvKjal.setText(list.get(position).toString());*/
    }

    @Override
    public int getItemCount() {
        return arananList.size();
    }

    @Override
    public Filter getFilter() {


        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String key = charSequence.toString();
                if (key.isEmpty()) {
                    arananList = list;

                } else {
                    List<BesinCalories> listFiltered = new ArrayList<>();
                    for (BesinCalories row : list) {
                        if (row.getName().toLowerCase().contains(key.toLowerCase())) {
                            listFiltered.add(row);

                        }

                    }
                    arananList = listFiltered;

                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = arananList;
                return filterResults;

            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                arananList = (List<BesinCalories>) filterResults.values;
                notifyDataSetChanged();
            }
        };


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


}
