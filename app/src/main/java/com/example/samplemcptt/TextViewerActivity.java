package com.example.samplemcptt;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class TextViewerActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_viewer);

        ActionBar ab = getSupportActionBar();
         ColorDrawable colorDrawable
                = new ColorDrawable(getResources().getColor(R.color.mycolour));
        ab.setBackgroundDrawable(colorDrawable);
        textView=findViewById(R.id.article_subheading);
        String filename=getIntent().getStringExtra("MESSAGE");
        setTitle(filename);
        String string = loadData(filename);
        textView.setText(string);
    }

    public String loadData(String inFile) {
        String tContents = "";

        try {
            InputStream stream = getAssets().open(inFile);

            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            tContents = new String(buffer);
        } catch (IOException e) {
            // Handle exceptions here
        }

        return tContents;

    }

}