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

public class SubchannelAdapter extends RecyclerView.Adapter{
    private final String LOG_TAG = SubchannelAdapter.class.getSimpleName();
    private final List<String> subchannellist;
    Context mContext;
    int total_types;
    public  class TextTypeViewHolder extends RecyclerView.ViewHolder {

        TextView field_name;

        public TextTypeViewHolder(View itemView) {
            super(itemView);

            this.field_name= (TextView) itemView.findViewById(R.id.subchannelradioButton);
        }
    }
    public SubchannelAdapter(List<String> subchannellist, Context context) {
        this.subchannellist = subchannellist ;
        this.mContext = context;
        total_types = subchannellist.size();
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.displayradiobutton,parent,false);
        return new TextTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((TextTypeViewHolder)holder).field_name.setText(subchannellist.get(position));

    }

    @Override
    public int getItemCount() {
        return this.subchannellist.size();
    }
}
