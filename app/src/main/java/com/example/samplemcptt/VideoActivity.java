package com.example.samplemcptt;

import android.content.Intent;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;


public class VideoActivity extends AppCompatActivity {

    VideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_video);

        mVideoView = (VideoView) this.findViewById(R.id.SampleVideoView);
        MediaController mediaController = new MediaController(this);
        mVideoView.setMediaController(mediaController);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String filename=data.getStringExtra("MESSAGE");

        mVideoView.setVideoPath("content://com.example.samplemcptt/"+filename);
        mVideoView.start();
    }
}