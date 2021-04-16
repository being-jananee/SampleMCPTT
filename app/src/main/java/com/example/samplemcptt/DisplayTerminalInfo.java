package com.example.samplemcptt;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayTerminalInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_terminal_info);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
        setTitle("View Terminal Information");
        ColorDrawable colorDrawable
                = new ColorDrawable(getResources().getColor(R.color.mycolour));

        // Set BackgroundDrawable
        ab.setBackgroundDrawable(colorDrawable);
    }
}