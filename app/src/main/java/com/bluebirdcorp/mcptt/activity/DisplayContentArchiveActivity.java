package com.bluebirdcorp.mcptt.activity;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bluebirdcorp.mcptt.R;
import com.bluebirdcorp.mcptt.adapter.ContentArchiveAdapter;
import com.bluebirdcorp.mcptt.constants.MCPTTConstants;
import com.bluebirdcorp.mcptt.entity.ContentArchiveTemplate;
import com.bluebirdcorp.mcptt.enums.ContentArchiveEnum;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lombok.SneakyThrows;

import static android.widget.LinearLayout.VERTICAL;

public class DisplayContentArchiveActivity extends AppCompatActivity implements ContentArchiveAdapter.OnContentListener {

    private final String TAG = DisplayContentArchiveActivity.class.getSimpleName();
    List<ContentArchiveTemplate> list = new ArrayList<>();
    ContentArchiveAdapter adapter;

    @SneakyThrows
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_content_archive);
        setTitle(MCPTTConstants.CONTENT_STORAGE_BOX);
        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        ColorDrawable colorDrawable
                = new ColorDrawable(getResources().getColor(R.color.bb_grey));
        actionBar.setBackgroundDrawable(colorDrawable);

        readChatMediaDirectory();

        adapter = new ContentArchiveAdapter(list, this, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        DividerItemDecoration itemDecor = new DividerItemDecoration(this, VERTICAL);
        mRecyclerView.addItemDecoration(itemDecor);
        mRecyclerView.setLayoutManager(linearLayoutManager);//later
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());//later
        mRecyclerView.setAdapter(adapter);
    }

    void readChatMediaDirectory() {
        String[] mediaFiles;
        String extension;
        int fileIcon = 0;
        try {
            mediaFiles = getAssets().list("chatmedia");
            if (mediaFiles.length > 0) {
                for (String file : mediaFiles) {
                    Log.d(TAG, "Reading from : " + file);
                    extension = file.substring(file.lastIndexOf("."));

                    if (ContentArchiveEnum.getExtensions().contains(extension)) {
                        fileIcon = ContentArchiveEnum.getIconFromEnum(extension);
                    }
                    list.add(new ContentArchiveTemplate(fileIcon, file, "This is sample file info", "January 1st 11.13.15", extension));//FIXME remove hard coding
                }
            }
        } catch (IOException e) {
            Log.e(TAG, "readChatMediaDirectory: Error occurred while reading file.");
        }
    }

    @Override
    public void onContentClick(String fileName) throws IOException {
        String extension = fileName.substring(fileName.lastIndexOf("."));
        MediaPlayer mediaPlayer = new MediaPlayer();
        fileName = "chatmedia/" + fileName;
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.reset();
        }

        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        if (extension.equals(ContentArchiveEnum.AUDIO_FILE.extension) || extension.equals(ContentArchiveEnum.VOICE_RECORDING_FILE.extension)) {

            AssetFileDescriptor assetFileDescriptor = getAssets().openFd(fileName);
            mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
            mediaPlayer.prepare();
            mediaPlayer.start();
            assetFileDescriptor.close();

        } else  {
            Class<? extends AppCompatActivity> classType = ContentArchiveEnum.getClassTypefromExtension(extension);
            Intent intent = new Intent(this, classType);
            intent.putExtra("MESSAGE", fileName);
            startActivity(intent);
        }

    }

}


