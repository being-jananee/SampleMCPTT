package com.example.samplemcptt;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MultiViewTypeAdapter extends RecyclerView.Adapter {
    private final String LOG_TAG = MultiViewTypeAdapter.class.getSimpleName();
    private final List<SetupNotificationsTemplate> dataset;
    Context mContext;
    int total_types;
    private OnSetupListener mOnsetupListener;
    public interface OnSetupListener
    {
        void onSetupClick(int position);
    }

    public static class TextTypeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView field_name;
        TextView field_val;
        OnSetupListener onSetupListener;

        public TextTypeViewHolder(View itemView,OnSetupListener onSetupListener)  {
            super(itemView);

            this.field_name= (TextView) itemView.findViewById(R.id.settings_text_view1);
            this.field_val = (TextView) itemView.findViewById(R.id.settings_text_view1a) ;
            this.onSetupListener = onSetupListener;
            itemView.setOnClickListener(this);//wat exactly is this here
        }

        @Override
        public void onClick(View v) {
            onSetupListener.onSetupClick(getAdapterPosition());
        }
    }

    public static class SwitchTypeViewHolder extends RecyclerView.ViewHolder {

        TextView txtType;
        Switch mySwitch;
        Button bu;

        public SwitchTypeViewHolder(View itemView) {
            super(itemView);

            this.txtType = (TextView) itemView.findViewById(R.id.settings_text_view2);
            this.mySwitch = (Switch) itemView.findViewById(R.id.switch1);
           // this.bu = (Button)itemView.findViewById(R.id.switch1);
        }
    }
    public MultiViewTypeAdapter(List<SetupNotificationsTemplate> data, Context context,OnSetupListener onSetupListener) {
        this.dataset = data;
        this.mContext = context;
        total_types = data.size();
        this.mOnsetupListener=onSetupListener;
        Log.d(LOG_TAG, "MultiViewTypeAdapter: "+total_types);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        switch (viewType) {
            case SetupNotificationsTemplate.TEXT_TYPE:
                Log.d(LOG_TAG, "onCreateViewHolder:CASE ONE ");
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_with_text, parent, false);
                return new TextTypeViewHolder(view,mOnsetupListener);
            case SetupNotificationsTemplate.SWITCH_TYPE:
                Log.d(LOG_TAG, "onCreateViewHolder: CASE TWO");
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_with_switch, parent, false);
                return new SwitchTypeViewHolder(view);
                }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        SetupNotificationsTemplate obj = dataset.get(position);
        View itemView = null;
        if(obj != null){
            switch(obj.type){
                case 0:
                    ((TextTypeViewHolder) holder).field_name.setText(obj.fieldName);
                    ((TextTypeViewHolder) holder).field_val.setText(obj.fieldValue);
                    break;
                case 1:
                    ((SwitchTypeViewHolder)holder).txtType.setText(obj.fieldName);
                    //((SwitchTypeViewHolder)holder).mySwitch =  (Switch) itemView.findViewById(R.id.switch1);
                    ((SwitchTypeViewHolder)holder).mySwitch.setId(R.id.switch1);
//                    ((SwitchTypeViewHolder)holder).bu.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
////                            Toast t = Toast.makeText(this,"some text",Toast.LENGTH_LONG);
//                            Log.d(LOG_TAG, "onbind: HERE");
//                        }
//                    });
                    break;

            }
        }
    }
    @Override
    public int getItemViewType(int position) {
        SetupNotificationsTemplate setupnotificationstemplate=dataset.get(position);
        switch (setupnotificationstemplate.type) {
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
        return this.dataset.size();
    }
}
