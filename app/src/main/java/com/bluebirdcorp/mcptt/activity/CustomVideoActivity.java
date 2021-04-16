package com.bluebirdcorp.mcptt.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.bluebirdcorp.mcptt.R;

public class CustomVideoActivity extends AppCompatActivity {

    VideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_video);
        ActionBar actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(getResources().getColor(R.color.bb_grey));
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(colorDrawable);
        mVideoView = this.findViewById(R.id.customVideoView);//FIXME removed casting
        MediaController mediaController = new MediaController(this);
        mVideoView.setMediaController(mediaController);
        Intent intent = getIntent();
        String filename = intent.getStringExtra("MESSAGE");
        setTitle(filename);
        mVideoView.setVideoPath("content://com.bluebirdcorp.mcptt/"+filename);
        mVideoView.start();
    }


}