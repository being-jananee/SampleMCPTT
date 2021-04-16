package com.bluebirdcorp.mcptt.enums;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.bluebirdcorp.mcptt.R;
import com.bluebirdcorp.mcptt.activity.CustomVideoActivity;
import com.bluebirdcorp.mcptt.activity.ImageViewerActivity;
import com.bluebirdcorp.mcptt.activity.TextViewerActivity;

import java.util.LinkedList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public enum ContentArchiveEnum {
    AUDIO_FILE(".mp3", R.drawable.ic_outline_volume_up_24, null),
    VIDEO_FILE(".mp4", R.drawable.ic_outline_videocam_24, CustomVideoActivity.class),
    TEXT_FILE(".txt", R.drawable.ic_outline_save_24,TextViewerActivity.class),
    IMAGE_FILE(".png", R.drawable.ic_outline_collections_24, ImageViewerActivity.class),
    VOICE_RECORDING_FILE(".ogg", R.drawable.ic_outline_keyboard_voice_24,null);

    public String extension;
    public int icon;
    public Class<? extends AppCompatActivity> classType;


    public static int getIconFromEnum(String extension) {
        for (ContentArchiveEnum value : values()) {
            if(value.extension.equals(extension)){
                return value.icon;
            }
        }
        return -1;
    }

    public static Class<? extends AppCompatActivity> getClassTypefromExtension(String extension) {
        for (ContentArchiveEnum value : values()) {
            if(value.extension.equals(extension)){
                return value.classType;
            }
        }
        return null;
    }

    public static List<String> getExtensions() {
        List<String> extensions=new LinkedList<>();
        for (ContentArchiveEnum value : values()) {
            extensions.add(value.extension);
        }
        return extensions;
    }
}
