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

import com.bluebirdcorp.mcptt.R;
import com.bluebirdcorp.mcptt.adapter.ViewMoreAdapter;
import com.bluebirdcorp.mcptt.constants.MCPTTConstants;
import com.bluebirdcorp.mcptt.entity.ViewMoreTemplate;

import java.util.ArrayList;
import java.util.List;

import static android.widget.LinearLayout.VERTICAL;

public class ViewMoreActivity extends AppCompatActivity implements ViewMoreAdapter.OnViewListener {

    private final int DISPLAY_TERMINAL = 0;
    private final int DISPLAY_ARCHIVE = 1;
    private final int DISPLAY_NOTIFICATION = 3;

    private final String TAG = ViewMoreActivity.class.getSimpleName();

    List<ViewMoreTemplate> list = new ArrayList<>();
    ViewMoreAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_more);
        setTitle(MCPTTConstants.VIEW_MORE);

        ActionBar actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(getResources().getColor(R.color.bb_grey));
        actionBar.setBackgroundDrawable(colorDrawable);

        createViewMoreMenus();
        adapter = new ViewMoreAdapter(list,this,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.viewMoreRecyclerView);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this,VERTICAL);

        mRecyclerView.addItemDecoration(itemDecoration);
        mRecyclerView.setLayoutManager(linearLayoutManager);//later
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());//later
        mRecyclerView.setAdapter(adapter);
    }

    private void createViewMoreMenus() {
        list.add(new ViewMoreTemplate(ViewMoreTemplate.TEXT_ROW, R.id.showArrow, "View Device Information"));
        list.add(new ViewMoreTemplate(ViewMoreTemplate.TEXT_ROW, R.id.showArrow, "Content Archive"));
        list.add(new ViewMoreTemplate(ViewMoreTemplate.SEEKBAR_ROW, R.id.sliderControl, "Radio Volume"));
        list.add(new ViewMoreTemplate(ViewMoreTemplate.TEXT_ROW, R.id.showArrow, "Notification Settings"));
        list.add(new ViewMoreTemplate(ViewMoreTemplate.TEXT_ROW, R.id.showArrow, "Preferences"));
        list.add(new ViewMoreTemplate(ViewMoreTemplate.VERSION_ROW, 0, "Version Information"));
    }

    @Override
    public void onViewClick(int position){
        switch(position){
            case DISPLAY_TERMINAL:
                Intent terminalIntent = new Intent(this, DisplayTerminalInfoActivity.class);
                startActivity(terminalIntent);
                break;
            case DISPLAY_ARCHIVE:
                Intent archiveIntent  = new Intent(this, DisplayContentArchiveActivity.class);
                startActivity(archiveIntent);
                break;
            case DISPLAY_NOTIFICATION:
                Intent setupNotificationIntent = new Intent(this, SetupNotificationActivity.class);
                startActivity(setupNotificationIntent);
                break;
        }
    }
}