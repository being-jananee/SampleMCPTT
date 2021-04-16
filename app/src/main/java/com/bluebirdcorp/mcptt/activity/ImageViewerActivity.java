package com.bluebirdcorp.mcptt.activity;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.bluebirdcorp.mcptt.R;

import java.io.IOException;
import java.io.InputStream;

public class ImageViewerActivity extends AppCompatActivity {

    private final String TAG = ImageViewerActivity.class.getSimpleName();

    ImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer);
        ActionBar actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(getResources().getColor(R.color.bb_grey));
        actionBar.setBackgroundDrawable(colorDrawable);
        mImage = (ImageView)findViewById(R.id.assetImageViewer);
        String filename=getIntent().getStringExtra("MESSAGE");
        setTitle(filename);
        loadImageFromAsset(filename);
    }
    public void loadImageFromAsset(String filename) {
        try {
            InputStream inputStream = getAssets().open(filename);
            Drawable drawable = Drawable.createFromStream(inputStream, null);
            mImage.setImageDrawable(drawable);
        }
        catch(IOException exception) {
            Log.e(TAG, "loadImageFromAsset: "+exception.getLocalizedMessage() );
        }

    }
}