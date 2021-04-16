package com.bluebirdcorp.mcptt.exceptions;

import android.util.Log;


public class MCPTTException extends RuntimeException{
    private final String TAG = MCPTTException.class.getSimpleName();

    public MCPTTException(String stringMsg){
        Log.e(TAG, "MCPTTException: Exception occurred : "+ stringMsg);
    }
}
