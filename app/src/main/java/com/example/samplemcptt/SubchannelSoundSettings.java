package com.example.samplemcptt;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.List;

import static android.widget.LinearLayout.VERTICAL;

public class SubchannelSoundSettings extends AppCompatActivity {

    private final String LOG_TAG = SubchannelSoundSettings.class.getSimpleName();
    String fileName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subchannel_sound_settings);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
        setTitle("Sub channel radio notification");
        ColorDrawable colorDrawable
                = new ColorDrawable(getResources().getColor(R.color.mycolour));

        // Set BackgroundDrawable
        ab.setBackgroundDrawable(colorDrawable);
        List<String> SubchannelList = new ArrayList<>();
        SubchannelList.add("vibration+sound");
        SubchannelList.add("vibration");
        SubchannelList.add("sound");
        SubchannelList.add("Silent");
        SubchannelAdapter subchannelAdapter = new SubchannelAdapter(SubchannelList,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        DividerItemDecoration itemDecor = new DividerItemDecoration(this,VERTICAL);
        mRecyclerView.addItemDecoration(itemDecor);
        mRecyclerView.setLayoutManager(linearLayoutManager);//later
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());//later
        mRecyclerView.setAdapter(subchannelAdapter);
    }

    public void choosesubchannelsound(View view){
        Log.d(LOG_TAG, "Chose audio");
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
                Log.e("home button", "pressed");
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


