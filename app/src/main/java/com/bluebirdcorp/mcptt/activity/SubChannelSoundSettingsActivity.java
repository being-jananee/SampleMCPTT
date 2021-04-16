package com.bluebirdcorp.mcptt.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bluebirdcorp.mcptt.R;
import com.bluebirdcorp.mcptt.adapter.SubchannelAdapter;
import com.bluebirdcorp.mcptt.constants.MCPTTConstants;

import java.util.ArrayList;
import java.util.List;

import static android.widget.LinearLayout.VERTICAL;

public class SubChannelSoundSettingsActivity extends AppCompatActivity {

    private final String TAG = SubChannelSoundSettingsActivity.class.getSimpleName();

    String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subchannel_sound_settings);
        setTitle(MCPTTConstants.SUB_CHANNEL_RADIO_NOTIFICATION);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        ColorDrawable colorDrawable
                = new ColorDrawable(getResources().getColor(R.color.bb_grey));
        actionBar.setBackgroundDrawable(colorDrawable);
        List<String> subChannelList = new ArrayList<>();
        subChannelList.add("vibration+sound");
        subChannelList.add("vibration");
        subChannelList.add("sound");
        subChannelList.add("Silent");
        SubchannelAdapter subchannelAdapter = new SubchannelAdapter(subChannelList,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        DividerItemDecoration itemDecor = new DividerItemDecoration(this,VERTICAL);
        mRecyclerView.addItemDecoration(itemDecor);
        mRecyclerView.setLayoutManager(linearLayoutManager);//later
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());//later
        mRecyclerView.setAdapter(subchannelAdapter);
    }

    public void chooseSubChannelSound(View view){
        Log.d(TAG, "chooseSubChannelSound: Chose audio for sub channel");
        RadioButton radioButton = view.findViewById(R.id.subchannelradioButton);
         fileName = radioButton.getText().toString();
        Intent intent=new Intent();
        intent.putExtra("MESSAGE",fileName);
        setResult(9,intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                Intent returnIntent = new Intent();
                returnIntent.putExtra("MESSAGE",fileName);
                setResult(9,returnIntent);
                finish();
                super.onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


