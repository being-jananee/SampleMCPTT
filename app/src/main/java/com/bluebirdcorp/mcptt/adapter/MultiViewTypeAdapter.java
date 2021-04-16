package com.bluebirdcorp.mcptt.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bluebirdcorp.mcptt.R;
import com.bluebirdcorp.mcptt.entity.SetupNotificationsTemplate;

import java.util.List;

public class MultiViewTypeAdapter extends RecyclerView.Adapter {
    private final List<SetupNotificationsTemplate> setupNotificationsTemplates;
    Context mContext;
    int totalTypes;
    private OnSetupListener onSetupListener;
    public interface OnSetupListener
    {
        void onSetupClick(int position);
    }

    public static class TextTypeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView fieldName;
        TextView fieldvalue;
        OnSetupListener onSetupListener;

        public TextTypeViewHolder(View itemView,OnSetupListener onSetupListener)  {
            super(itemView);
            this.fieldName = (TextView) itemView.findViewById(R.id.settings_text_view);
            this.fieldvalue = (TextView) itemView.findViewById(R.id.settings_sub_text_view) ;
            this.onSetupListener = onSetupListener;
            itemView.setOnClickListener(this);//wat exactly is this here
        }

        @Override
        public void onClick(View v) {
            onSetupListener.onSetupClick(getAdapterPosition());
        }
    }

    public static class SwitchTypeViewHolder extends RecyclerView.ViewHolder {

        TextView switchCaption;
        Switch switchView;

        public SwitchTypeViewHolder(View itemView) {
            super(itemView);
            this.switchCaption = (TextView) itemView.findViewById(R.id.switchText);
            this.switchView = (Switch) itemView.findViewById(R.id.notificationToggle);
        }
    }

    public MultiViewTypeAdapter(List<SetupNotificationsTemplate> data, Context context,OnSetupListener onSetupListener) {
        this.setupNotificationsTemplates = data;
        this.mContext = context;
        this.totalTypes = data.size();
        this.onSetupListener =onSetupListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        switch (viewType) {
            case SetupNotificationsTemplate.TEXT_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_with_text, parent, false);
                return new TextTypeViewHolder(view, onSetupListener);
            case SetupNotificationsTemplate.SWITCH_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_with_switch, parent, false);
                return new SwitchTypeViewHolder(view);
                }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        SetupNotificationsTemplate obj = setupNotificationsTemplates.get(position);
        View itemView = null;
        if(obj != null){
            switch(obj.componentType){
                case 0:
                    ((TextTypeViewHolder) holder).fieldName.setText(obj.fieldName);
                    ((TextTypeViewHolder) holder).fieldvalue.setText(obj.fieldValue);
                    break;
                case 1:
                    ((SwitchTypeViewHolder)holder).switchCaption.setText(obj.fieldName);
                    ((SwitchTypeViewHolder)holder).switchView.setId(R.id.notificationToggle);
                    break;
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        SetupNotificationsTemplate setupnotificationstemplate= setupNotificationsTemplates.get(position);
        switch (setupnotificationstemplate.componentType) {
            case 0:
                return SetupNotificationsTemplate.TEXT_TYPE;
            case 1:
                return SetupNotificationsTemplate.SWITCH_TYPE;
            default:
                return -1;
        }
    }

    @Override
    public int getItemCount() {
        return this.setupNotificationsTemplates.size();
    }
}
