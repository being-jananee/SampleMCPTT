package com.example.samplemcptt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
//
//@Data
////@Builder
//@AllArgsConstructor
public class SetupNotificationsTemplate {

    public final  static int TEXT_TYPE=0;
    public final static int SWITCH_TYPE=1;
    public int type;
    public int data;
    public String fieldName;
    public String fieldValue;

    public SetupNotificationsTemplate(int type, int data, String fieldName, String fieldValue) {
        this.type = type;
        this.data = data;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
