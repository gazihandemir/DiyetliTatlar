package com.example.girisekran.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.girisekran.Class.ChatModel;
import com.example.girisekran.R;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {


    Context context;
    List<ChatModel> list;
    Activity activity;
    String userName;
    Boolean state;
    int viewSend = 1, viewReceiver = 2;

    public ChatAdapter(Context context, List<ChatModel> list, Activity activity, String userName) {
        this.context = context;
        this.list = list;
        this.activity = activity;
        this.userName = userName;
        state = false;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == viewSend) {
            view = LayoutInflater.from(context).inflate(R.layout.send_message, parent, false);
            return new ViewHolder(view);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.received_layout, parent, false);
            return new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvMesaj.setText(list.get(position).getText().toString());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMesaj;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            if (state) {
                tvMesaj = itemView.findViewById(R.id.tvSend);
            } else {
                tvMesaj = itemView.findViewById(R.id.tvReceived);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getFrom().equals(userName)) {
            state = true;
            return viewSend;
        } else {
            state = false;
            return viewReceiver;
        }
    }
}
