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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.widget.LinearLayout.VERTICAL;

public class ViewMore extends AppCompatActivity implements ViewMoreAdapter.OnViewListener{
    private final String LOG_TAG = ViewMore.class.getSimpleName();
    List<ViewMoreTemplate> list = new ArrayList<ViewMoreTemplate>();
    ViewMoreAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_more);
        setTitle("View More");

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        ColorDrawable colorDrawable
                = new ColorDrawable(getResources().getColor(R.color.mycolour));

        // Set BackgroundDrawable
        ab.setBackgroundDrawable(colorDrawable);

        list.add(new ViewMoreTemplate(ViewMoreTemplate.TEXT_ROW, R.id.imageView1, "View Device Information"));
        list.add(new ViewMoreTemplate(ViewMoreTemplate.TEXT_ROW, R.id.imageView1, "Content Archive"));
        list.add(new ViewMoreTemplate(ViewMoreTemplate.SEEKBAR_ROW, R.id.seekBar2, "Radio Volume"));
        list.add(new ViewMoreTemplate(ViewMoreTemplate.TEXT_ROW, R.id.imageView1, "Notification Settings"));
        list.add(new ViewMoreTemplate(ViewMoreTemplate.TEXT_ROW, R.id.imageView1, "Preferences"));
        list.add(new ViewMoreTemplate(ViewMoreTemplate.VERSION_ROW, 0, "Version Information"));
        adapter = new ViewMoreAdapter(list,this,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView1);
        DividerItemDecoration itemDecor = new DividerItemDecoration(this,VERTICAL);
        mRecyclerView.addItemDecoration(itemDecor);
        mRecyclerView.setLayoutManager(linearLayoutManager);//later
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());//later
        mRecyclerView.setAdapter(adapter);
        Log.d(LOG_TAG,"CALLING......");
    }

    @Override
    public void onViewClick(int position){
        switch(position){
            case 0:
                Intent intent = new Intent(this, DisplayTerminalInfo.class);
                startActivity(intent);
                break;
            case 1:
                Intent archiveIntent  = new Intent(this, DisplayContentArchive.class);
                startActivity(archiveIntent);
                break;
            case 3:
                Log.d(LOG_TAG, "Setup: HERE");
                Intent mintent = new Intent(this, Setupnotifications.class);
                startActivity(mintent);
                break;

        }
    }
}