package com.example.samplemcptt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static android.widget.LinearLayout.HORIZONTAL;
import static android.widget.LinearLayout.VERTICAL;

public class  Setupnotifications  extends AppCompatActivity implements MultiViewTypeAdapter.OnSetupListener {

    private final String LOG_TAG = Setupnotifications.class.getSimpleName();
    private String chosenRingtone;
    List<SetupNotificationsTemplate> list = new ArrayList<SetupNotificationsTemplate>();
    MultiViewTypeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setupnotifications);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
        setTitle("Notification Settings");
        ColorDrawable colorDrawable
                = new ColorDrawable(getResources().getColor(R.color.mycolour));

        // Set BackgroundDrawable
        ab.setBackgroundDrawable(colorDrawable);
        list.add(new SetupNotificationsTemplate(SetupNotificationsTemplate.TEXT_TYPE,0,"Radio Start Beep","Beep 1"));
        list.add(new SetupNotificationsTemplate(SetupNotificationsTemplate.SWITCH_TYPE,R.id.switch1,"Radio Shutdown notification",null));
        list.add(new SetupNotificationsTemplate(SetupNotificationsTemplate.TEXT_TYPE,0,"Sub-channel radio notification","Sound"));
        list.add(new SetupNotificationsTemplate(SetupNotificationsTemplate.SWITCH_TYPE,R.id.switch1,"Message Notification",null));
        list.add(new SetupNotificationsTemplate(SetupNotificationsTemplate.SWITCH_TYPE,R.id.switch1,"Screen turns on when receiving radio",null));
        adapter = new MultiViewTypeAdapter(list,this,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        DividerItemDecoration itemDecor = new DividerItemDecoration(this,VERTICAL);
        mRecyclerView.addItemDecoration(itemDecor);
        mRecyclerView.setLayoutManager(linearLayoutManager);//later
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());//later
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(resultCode==8)
        {

            String ChosenSound=data.getStringExtra("MESSAGE");
            Log.d(LOG_TAG,"CHOSEN:"+ChosenSound);

            SetupNotificationsTemplate obj = new SetupNotificationsTemplate(SetupNotificationsTemplate.TEXT_TYPE,0,"Radio Start Beep",ChosenSound);
            list.set(0,obj);
            adapter.notifyItemChanged(0);
        }
        if(resultCode==9)
        {

            String ChosenSound=data.getStringExtra("MESSAGE");
            Log.d(LOG_TAG,"CHOSEN:"+ChosenSound);

            SetupNotificationsTemplate obj = new SetupNotificationsTemplate(SetupNotificationsTemplate.TEXT_TYPE,0,"Sub-channel radio notification",ChosenSound);
            list.set(2,obj);
            adapter.notifyItemChanged(2);
        }
    }

public void onSetupClick(int position){
    switch(position){
        case 0:
            Intent intent = new Intent(this, BeepView.class);
            startActivityForResult(intent,8);
            break;
        case 2:

            Log.d(LOG_TAG, "Setup: HERE");
             intent = new Intent(this, SubchannelSoundSettings.class);
            startActivityForResult(intent,9);
            break;

    }
}

}



