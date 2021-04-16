package com.bluebirdcorp.mcptt.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bluebirdcorp.mcptt.R;
import com.bluebirdcorp.mcptt.entity.ViewMoreTemplate;

import java.util.List;

public class ViewMoreAdapter extends RecyclerView.Adapter {
    private final String LOG_TAG = MultiViewTypeAdapter.class.getSimpleName();
    private List<ViewMoreTemplate> dataset;
    Context mContext;
    int total_types;
    private OnViewListener mOnViewListener;

    public interface OnViewListener {
        void onViewClick(int position);
    }

    public static class TextTypeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView field_name;
      ImageView image;
      OnViewListener onViewListener;


        public TextTypeViewHolder(View itemView,OnViewListener onViewListener) {
            super(itemView);

            this.field_name= (TextView) itemView.findViewById(R.id.arrowText);
            this.image = (ImageView) itemView.findViewById(R.id.showArrow) ;
            this.onViewListener = onViewListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onViewListener.onViewClick(getAdapterPosition());
        }
    }
    public static class SeekerTypeViewHolder extends RecyclerView.ViewHolder {

        TextView txtType;
        SeekBar seekBar;

        public SeekerTypeViewHolder(View itemView) {
            super(itemView);

            this.txtType = (TextView) itemView.findViewById(R.id.sliderControllerText);
            this.seekBar = (SeekBar) itemView.findViewById(R.id.sliderControl);

        }
    }
    public static class VersionTypeViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView field_val;

        public VersionTypeViewHolder(View itemView, OnViewListener onViewListener)  {
            super(itemView);
            this.name= (TextView) itemView.findViewById(R.id.settings_text_view);
            this.field_val = (TextView) itemView.findViewById(R.id.settings_sub_text_view);
        }

    }
    public ViewMoreAdapter(List<ViewMoreTemplate> data,Context context,OnViewListener onViewListener) {
        this.dataset = data;
        this.mContext = context;
        total_types = data.size();
        this.mOnViewListener = onViewListener;
        Log.d(LOG_TAG, "MultiViewTypeAdapter: "+total_types);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        switch (viewType) {
            case ViewMoreTemplate.TEXT_ROW:
                Log.d(LOG_TAG, "onCreateViewHolder:CASE ONE ");
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_with_icon, parent, false);
                return new TextTypeViewHolder(view,mOnViewListener);
            case ViewMoreTemplate.SEEKBAR_ROW:
                Log.d(LOG_TAG, "onCreateViewHolder: CASE TWO");
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_with_seekbar, parent, false);
                return new SeekerTypeViewHolder(view);
            case ViewMoreTemplate.VERSION_ROW:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_with_text, parent, false);
                return new VersionTypeViewHolder(view,mOnViewListener);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewMoreTemplate obj = dataset.get(position);
        if(obj != null){
            switch (obj.type){
                case 0:
                    ((TextTypeViewHolder) holder).field_name.setText(obj.fieldName);
                    ((TextTypeViewHolder) holder).image.setId(R.id.showArrow);
                    break;
                case 1:
                    ((SeekerTypeViewHolder) holder).txtType.setText(obj.fieldName);
                    ((SeekerTypeViewHolder) holder).seekBar.setId(R.id.sliderControl);
                    break;
                case 2:
                    ((VersionTypeViewHolder) holder).name.setText(obj.fieldName);
                    ((VersionTypeViewHolder) holder).field_val.setText("         1.0.0");
                    break;
            }
        }
    }
    @Override
    public int getItemViewType(int position) {
        ViewMoreTemplate viewMoreTemplate=dataset.get(position);
        switch (viewMoreTemplate.type) {
            case 0:
                return viewMoreTemplate.TEXT_ROW;
            case 1:
                return ViewMoreTemplate.SEEKBAR_ROW;
            case 2:
                return ViewMoreTemplate.VERSION_ROW;
            default:
                return -1;
        }
    }
    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
