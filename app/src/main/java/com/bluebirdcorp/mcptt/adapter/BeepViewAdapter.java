package com.bluebirdcorp.mcptt.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bluebirdcorp.mcptt.R;

import java.util.List;

public class BeepViewAdapter  extends RecyclerView.Adapter{

    private final List<String> beepList;

    Context mContext;
    int total_types;



    public  class TextTypeViewHolder extends RecyclerView.ViewHolder {
        TextView beep_name;
        public TextTypeViewHolder(View itemView) {
            super(itemView);
            this.beep_name= (TextView) itemView.findViewById(R.id.radioButton);
        }
    }

    public BeepViewAdapter(List<String> beepList, Context context) {
        this.beepList = beepList;
        this.mContext = context;
        total_types = beepList.size();
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.displaymoretext,parent,false);
        return new TextTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((TextTypeViewHolder)holder).beep_name.setText(beepList.get(position));
    }

    @Override
    public int getItemCount() {
        return this.beepList.size();
    }
}
