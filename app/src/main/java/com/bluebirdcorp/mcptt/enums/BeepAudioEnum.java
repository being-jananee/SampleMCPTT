package com.bluebirdcorp.mcptt.enums;

import androidx.annotation.NonNull;

import com.bluebirdcorp.mcptt.R;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum BeepAudioEnum {
    BEEP_1("Beep 1", R.raw.beep01),
    BEEP_2("Beep 2", R.raw.beep02),
    BEEP_3("Beep 3", R.raw.beep03),
    BEEP_4("Beep 4", R.raw.beep04),
    BEEP_5("Beep 5", R.raw.beep05),
    SILENT("Silent", 0);


    String beepName;
    int beepComponentId;

    public static int getBeepComponentIdFromEnum(String beepName) {
        for (BeepAudioEnum value : values()) {
            if(value.beepName.equals(beepName)){
                return value.beepComponentId;
            }
        }
        return -1;
    }

    public static List<String> getBeepNameList() {
        List<String> beepList=new LinkedList<>();
        for (BeepAudioEnum value : values()) {
            beepList.add(value.beepName);
        }
        return beepList;
    }
}
