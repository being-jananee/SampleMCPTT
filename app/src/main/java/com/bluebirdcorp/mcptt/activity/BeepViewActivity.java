package com.bluebirdcorp.mcptt.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
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

import com.bluebirdcorp.mcptt.adapter.BeepViewAdapter;
import com.bluebirdcorp.mcptt.R;
import com.bluebirdcorp.mcptt.constants.MCPTTConstants;
import com.bluebirdcorp.mcptt.enums.BeepAudioEnum;

import java.util.ArrayList;
import java.util.List;

import static android.widget.LinearLayout.VERTICAL;

/**
 * BeepViewActivity used to bind radio beep screen and it's activity.
 *
 */
public class BeepViewActivity extends AppCompatActivity {

    private final String TAG = BeepViewActivity.class.getSimpleName();

    MediaPlayer mediaPlayer;
    String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beep_view);
        setTitle(MCPTTConstants.RADIO_START_BEEP);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        ColorDrawable colorDrawable
                = new ColorDrawable(getResources().getColor(R.color.bb_grey));
        actionBar.setBackgroundDrawable(colorDrawable);

        List<String> beepList = MCPTTConstants.STATIC_BEEP_LIST;

        BeepViewAdapter beepViewAdapter = new BeepViewAdapter(beepList,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        RecyclerView mRecyclerView = findViewById(R.id.recyclerView);//FIXME unwanted casting removed..
        DividerItemDecoration itemDecor = new DividerItemDecoration(this,VERTICAL);
        mRecyclerView.addItemDecoration(itemDecor);
        mRecyclerView.setLayoutManager(linearLayoutManager);//FIXME check usage
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());//FIXME check usage
        mRecyclerView.setAdapter(beepViewAdapter);
    }

    /**
     * chooseAudio gets selected sound file from view
     *
     * @param view
     */
    public void chooseAudio(View view){
        RadioButton radioButton = view.findViewById(R.id.radioButton);
         fileName = radioButton.getText().toString();
        Intent intent=new Intent();
        intent.putExtra("MESSAGE",fileName);
        setResult(8,intent);

        int audioFile = 0;
        if(BeepAudioEnum.getBeepNameList().contains(fileName)){
            audioFile = BeepAudioEnum.getBeepComponentIdFromEnum(fileName);
        }
        if(audioFile!=0)
        playSound(audioFile);
    }

    /**
     * playSound dispatches selected sound file to media controller
     * @param fileName
     */
    private void playSound(int fileName){
        if(MCPTTConstants.INVALID_FILE_NAME!=fileName) {
            mediaPlayer = MediaPlayer.create(getApplicationContext(), fileName);
            mediaPlayer.start();
        }else{
            Log.d(TAG, "playSound: Specified filename not found");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
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