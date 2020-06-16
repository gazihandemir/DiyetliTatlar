package com.example.girisekran.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.girisekran.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AkisAdapter extends RecyclerView.Adapter<AkisAdapter.AkisHolder> {
    private ArrayList<String> userEmailList;
    private ArrayList<String> userCommentList;
    private ArrayList<String> userImageList;

    public AkisAdapter(ArrayList<String> userEmailList, ArrayList<String> userCommentList, ArrayList<String> userImageList) {
        this.userCommentList = userCommentList;
        this.userEmailList = userEmailList;
        this.userImageList = userImageList;
    }

    @NonNull
    @Override
    public AkisHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.akis_activity_tek_layout, parent, false);
        return new AkisHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AkisHolder holder, int position) {
        holder.tvEmailText.setText(userEmailList.get(position));
        holder.tvCommentText.setText(userCommentList.get(position));
        Picasso.get().load(userImageList.get(position)).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return userEmailList.size();
    }

    public class AkisHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvEmailText;
        TextView tvCommentText;

        public AkisHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgAkisActLayoutImage);
            tvCommentText = itemView.findViewById(R.id.tvAkisActTekLayoutComment);
            tvEmailText = itemView.findViewById(R.id.tvAkisActTekLayoutEmail);
        }
    }
}
