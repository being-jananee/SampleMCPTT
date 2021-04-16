package com.example.samplemcptt;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

public class ImageViewerActivity extends AppCompatActivity {
    ImageView mImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer);
        ActionBar ab = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(getResources().getColor(R.color.mycolour));
        ab.setBackgroundDrawable(colorDrawable);
        mImage = (ImageView)findViewById(R.id.assetImageViewer);
        String filename=getIntent().getStringExtra("MESSAGE");
        setTitle(filename);
        loadImageFromAsset(filename);
    }
    public void loadImageFromAsset(String filename) {
        try {
            // get input stream
            InputStream ims = getAssets().open(filename);
            // load image as Drawable
            Drawable d = Drawable.createFromStream(ims, null);
            // set image to ImageView
            mImage.setImageDrawable(d);
        }
        catch(IOException ex) {
            return;
        }

    }
}