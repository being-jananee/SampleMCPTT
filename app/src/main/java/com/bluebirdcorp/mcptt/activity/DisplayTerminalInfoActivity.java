package com.bluebirdcorp.mcptt.activity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.bluebirdcorp.mcptt.R;
import com.bluebirdcorp.mcptt.constants.MCPTTConstants;

public class DisplayTerminalInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_terminal_info);
        setTitle(MCPTTConstants.TERMINAL_INFORMATION);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        ColorDrawable colorDrawable
                = new ColorDrawable(getResources().getColor(R.color.bb_grey));
        actionBar.setBackgroundDrawable(colorDrawable);
    }
}