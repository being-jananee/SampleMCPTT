package com.example.samplemcptt;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class CustomVideoActivity extends AppCompatActivity {

    VideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_video);
        ActionBar ab = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(getResources().getColor(R.color.mycolour));
        ab.setBackgroundDrawable(colorDrawable);
        mVideoView = (VideoView) this.findViewById(R.id.SampleVideoView);
        MediaController mediaController = new MediaController(this);
        mVideoView.setMediaController(mediaController);
        Intent intent = getIntent();
        String filename = intent.getStringExtra("MESSAGE");
        setTitle(filename);
        mVideoView.setVideoPath("content://com.example.samplemcptt/"+filename);
        mVideoView.start();

    }


}