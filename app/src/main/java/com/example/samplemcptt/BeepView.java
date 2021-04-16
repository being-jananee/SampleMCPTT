package com.example.samplemcptt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.widget.LinearLayout.VERTICAL;

public class BeepView extends AppCompatActivity {

    private final String LOG_TAG = BeepView.class.getSimpleName();
    MediaPlayer mediaPlayer;
    String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beep_view);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
        setTitle("Radio Start Beep");
        ColorDrawable colorDrawable
                = new ColorDrawable(getResources().getColor(R.color.mycolour));

        // Set BackgroundDrawable
        ab.setBackgroundDrawable(colorDrawable);
        List<String> BeepList = new ArrayList<>();
        BeepList.add("Beep 1");
        BeepList.add("Beep 2");
        BeepList.add("Beep 3");
        BeepList.add("Beep 4");
        BeepList.add("Beep 5");
        BeepList.add("Silent");

        BeepViewAdapter beepViewAdapter = new BeepViewAdapter(BeepList,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        DividerItemDecoration itemDecor = new DividerItemDecoration(this,VERTICAL);
        mRecyclerView.addItemDecoration(itemDecor);
        mRecyclerView.setLayoutManager(linearLayoutManager);//later
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());//later
        mRecyclerView.setAdapter(beepViewAdapter);
    }

    public void chooseAudio(View view){
        Log.d(LOG_TAG, "Chose audio");
        RadioButton radioButton = view.findViewById(R.id.radioButton);
         fileName = radioButton.getText().toString();
        Intent intent=new Intent();
        intent.putExtra("MESSAGE",fileName);
        setResult(8,intent);
        Log.d(LOG_TAG, "Audio TBplayed:"+fileName);

        int AudioFile = 0;
        if(fileName.equals("Beep 1"))
            AudioFile = R.raw.beep01;
        else if(fileName.equals("Beep 2"))
            AudioFile = R.raw.beep02;
        else if(fileName.equals("Beep 3"))
            AudioFile = R.raw.beep03;
        else if(fileName.equals("Beep 4"))
            AudioFile = R.raw.beep04;
        else if(fileName.equals("Beep 5"))
            AudioFile = R.raw.beep05;
        else
            AudioFile = -1;
        playSound(AudioFile);
    }
    public void playSound(int fileName){
        Log.d(LOG_TAG, "Audio TBplayed:"+fileName);
        if(fileName != -1) {
            mediaPlayer = MediaPlayer.create(getApplicationContext(), fileName);
            mediaPlayer.start();
        }

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                Log.e("home button", "pressed");
                Intent returnIntent = new Intent();
                returnIntent.putExtra("MESSAGE",fileName);
                setResult(8,returnIntent);
                finish();
                super.onBackPressed();
                return true;


        }

        return super.onOptionsItemSelected(item);
    }
}