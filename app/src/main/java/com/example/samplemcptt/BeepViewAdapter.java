package com.example.samplemcptt;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BeepViewAdapter  extends RecyclerView.Adapter{

    private final String LOG_TAG = BeepViewAdapter.class.getSimpleName();
    private final List<String> beeplist;
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
        this.beeplist = beepList;
        this.mContext = context;
        total_types = beepList.size();
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.displaymoretext,parent,false);
        return new BeepViewAdapter.TextTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((BeepViewAdapter.TextTypeViewHolder)holder).beep_name.setText(beeplist.get(position));
    }

    @Override
    public int getItemCount() {
        return this.beeplist.size();
    }




}
