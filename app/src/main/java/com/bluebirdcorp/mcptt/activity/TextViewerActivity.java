package com.bluebirdcorp.mcptt.activity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.bluebirdcorp.mcptt.R;

import java.io.IOException;
import java.io.InputStream;

public class TextViewerActivity extends AppCompatActivity {

    private final String TAG = TextViewerActivity.class.getSimpleName();

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_viewer);

        ActionBar actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(getResources().getColor(R.color.bb_grey));
        actionBar.setBackgroundDrawable(colorDrawable);
        textView=findViewById(R.id.article_subheading);
        String filename=getIntent().getStringExtra("MESSAGE");
        setTitle(filename);
        String string = loadData(filename);
        textView.setText(string);
    }

    public String loadData(String inFile) {
        String content = "";
        try {
            InputStream stream = getAssets().open(inFile);
            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            content = new String(buffer);
        } catch (IOException e) {
            Log.e(TAG, "loadData: "+e.getLocalizedMessage() );
        }
        return content;

    }

}