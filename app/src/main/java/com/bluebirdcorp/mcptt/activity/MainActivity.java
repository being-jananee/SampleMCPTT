package com.bluebirdcorp.mcptt.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.bluebirdcorp.mcptt.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ShowList(View view){
        Intent intent = new Intent(this, ViewMoreActivity.class);
        startActivity(intent);
    }
}