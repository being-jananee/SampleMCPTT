package com.example.samplemcptt;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import lombok.SneakyThrows;

import static android.widget.LinearLayout.VERTICAL;

public class DisplayContentArchive extends AppCompatActivity implements ContentArchiveAdapter.OnContentListener{

    private final String LOG_TAG = DisplayContentArchive.class.getSimpleName();
    List<ContentArchiveTemplate> list = new ArrayList<ContentArchiveTemplate>();
    ContentArchiveAdapter adapter;
    VideoView mVideoView;

    @SneakyThrows
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_content_archive);
        setTitle("Content Storage Box");
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ColorDrawable colorDrawable
                = new ColorDrawable(getResources().getColor(R.color.mycolour));
        ab.setBackgroundDrawable(colorDrawable);
        readChatMediaDirectory();

        adapter = new ContentArchiveAdapter(list, this,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        DividerItemDecoration itemDecor = new DividerItemDecoration(this, VERTICAL);
        mRecyclerView.addItemDecoration(itemDecor);
        mRecyclerView.setLayoutManager(linearLayoutManager);//later
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());//later
        mRecyclerView.setAdapter(adapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    void readChatMediaDirectory() throws IOException {
        String[] listofmediafiles;
        String extension;
        int fileicon = 0;
        try {
            listofmediafiles = getAssets().list("chatmedia");
            if (listofmediafiles.length > 0) {
                for (String file : listofmediafiles) {
                    Log.d(LOG_TAG, "Reading from:" + file);
                    extension = file.substring(file.lastIndexOf("."));

                    if(extension.equals(".mp3")){
                        fileicon = R.drawable.ic_outline_volume_up_24;
                    }
                    else if(extension.equals(".mp4")){
                        fileicon = R.drawable.ic_outline_videocam_24;
                    }
                    else if(extension.equals(".txt")){
                        fileicon = R.drawable.ic_outline_save_24;
                    }
                    else if(extension.equals(".png")){
                        fileicon =R.drawable.ic_outline_collections_24;
                    }
                    else if(extension.equals(".ogg")){
                        fileicon = R.drawable.ic_outline_keyboard_voice_24;
                    }
                    list.add(new ContentArchiveTemplate(fileicon,file,"This is sample file info","January 1st 11.13.15",extension));
                }
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "ERROR");
            e.printStackTrace();
        }
    }

    @Override
    public void onContentClick(String fileclicked) throws IOException {
        String extension = fileclicked.substring(fileclicked.lastIndexOf("."));
        MediaPlayer mediaPlayer = new MediaPlayer();
        fileclicked = "chatmedia/"+fileclicked;
        Uri file = Uri.parse(fileclicked);
        if(mediaPlayer.isPlaying()){
            mediaPlayer.reset();
        }

        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//        mediaPlayer.setDisplay(surfaceHolder);

        try {
            if (extension.equals(".mp3") || extension.equals(".ogg")) {

                AssetFileDescriptor afd = getAssets().openFd(fileclicked);
                mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                mediaPlayer.prepare();
                  mediaPlayer.start();
                afd.close();

            }


        else if(extension.equals(".txt")){
            Intent mintent = new Intent(this,TextViewerActivity.class);
                mintent.putExtra("MESSAGE",fileclicked);
                startActivity(mintent);
        }
        else if(extension.equals(".png")){
            Intent mintent = new Intent(this,ImageViewerActivity.class);
            mintent.putExtra("MESSAGE",fileclicked);
            startActivity(mintent);
        }
        else if( extension.equals(".mp4")){
                Intent nextActivity = new Intent(this, CustomVideoActivity.class);
                nextActivity.putExtra("MESSAGE",fileclicked);
                startActivity(nextActivity);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}


