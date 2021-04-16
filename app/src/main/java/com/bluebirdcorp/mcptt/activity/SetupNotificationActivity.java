package com.bluebirdcorp.mcptt.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bluebirdcorp.mcptt.adapter.MultiViewTypeAdapter;
import com.bluebirdcorp.mcptt.R;
import com.bluebirdcorp.mcptt.constants.MCPTTConstants;
import com.bluebirdcorp.mcptt.entity.SetupNotificationsTemplate;

import java.util.ArrayList;
import java.util.List;

import static android.widget.LinearLayout.VERTICAL;

public class SetupNotificationActivity extends AppCompatActivity implements MultiViewTypeAdapter.OnSetupListener {

    public static final int SUB_CHANNEL = 2;
    public static final int BEEP_VIEW = 0;

    private final String TAG = SetupNotificationActivity.class.getSimpleName();

    List<SetupNotificationsTemplate> notificationsTemplates = new ArrayList<>();
    MultiViewTypeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setupnotifications);
        setTitle(MCPTTConstants.NOTIFICATION_SETTINGS);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        ColorDrawable colorDrawable
                = new ColorDrawable(getResources().getColor(R.color.bb_grey));
        actionBar.setBackgroundDrawable(colorDrawable);

        notificationsTemplates.addAll(MCPTTConstants.createNotificationMenus());
        adapter = new MultiViewTypeAdapter(notificationsTemplates,this,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        DividerItemDecoration itemDecor = new DividerItemDecoration(this,VERTICAL);
        mRecyclerView.addItemDecoration(itemDecor);
        mRecyclerView.setLayoutManager(linearLayoutManager);//later
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());//later
        mRecyclerView.setAdapter(adapter);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(resultCode==8)
        {

            String ChosenSound=data.getStringExtra("MESSAGE");
            Log.d(TAG,"CHOSEN:"+ChosenSound);

            SetupNotificationsTemplate obj = new SetupNotificationsTemplate(SetupNotificationsTemplate.TEXT_TYPE,0,"Radio Start Beep",ChosenSound);
            notificationsTemplates.set(0,obj);
            adapter.notifyItemChanged(0);
        }
        if(resultCode==9)
        {

            String ChosenSound=data.getStringExtra("MESSAGE");
            Log.d(TAG,"CHOSEN:"+ChosenSound);

            SetupNotificationsTemplate obj = new SetupNotificationsTemplate(SetupNotificationsTemplate.TEXT_TYPE,0,"Sub-channel radio notification",ChosenSound);
            notificationsTemplates.set(2,obj);
            adapter.notifyItemChanged(2);
        }
    }

public void onSetupClick(int position){
    switch(position){
        case BEEP_VIEW:
            Intent intent = new Intent(this, BeepViewActivity.class);
            startActivityForResult(intent,8);
            break;
        case SUB_CHANNEL:
            intent = new Intent(this, SubChannelSoundSettingsActivity.class);
            startActivityForResult(intent,9);
            break;

    }
}

}



