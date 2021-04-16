package com.bluebirdcorp.mcptt.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.CancellationSignal;
import android.util.Log;

import com.bluebirdcorp.mcptt.activity.BeepViewActivity;
import com.bluebirdcorp.mcptt.exceptions.MCPTTException;

import java.io.FileNotFoundException;
import java.io.IOException;

import lombok.extern.slf4j.Slf4j;

public class VideoProvider extends ContentProvider {
    private final String TAG = BeepViewActivity.class.getSimpleName();

    @Override
    public AssetFileDescriptor openAssetFile(Uri uri, String mode) throws FileNotFoundException {
        AssetFileDescriptor assetFileDescriptor = null;
        AssetManager assetManager = getContext().getAssets();
        String fileName = uri.getLastPathSegment();
        if(fileName == null) {
            Log.e(TAG, "openAssetFile: Requested video file name cannot be null");
            throw new MCPTTException("openAssetFile: Requested video file name cannot be null");
        }
        try {
            assetFileDescriptor = assetManager.openFd(fileName);
        } catch (IOException ioException) {
            Log.e(TAG, "openAssetFile: "+ioException.getLocalizedMessage());
        }
        return assetFileDescriptor;
    }

    @Override
    public String getType( Uri p1 )
    {
        return null;
    }

    @Override
    public int delete( Uri p1, String p2, String[] p3 )
    {
        return 0;
    }

    @Override
    public Cursor query(Uri p1, String[] p2, String p3, String[] p4, String p5 )
    {
        return null;
    }

    @Override
    public Cursor query( Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder, CancellationSignal cancellationSignal )
    {
        return super.query( uri, projection, selection, selectionArgs, sortOrder, cancellationSignal );
    }

    @Override
    public Uri insert( Uri p1, ContentValues p2 )
    {
        return null;
    }

    @Override
    public boolean onCreate( )
    {
        return false;
    }

    @Override
    public int update(Uri p1, ContentValues p2, String p3, String[] p4 )
    {
        return 0;
    }
}
