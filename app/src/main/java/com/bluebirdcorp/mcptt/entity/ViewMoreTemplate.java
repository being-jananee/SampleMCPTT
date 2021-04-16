package com.bluebirdcorp.mcptt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public class ViewMoreTemplate {

    public final  static int TEXT_ROW=0;
    public final static int SEEKBAR_ROW=1;
    public final static int VERSION_ROW=2;

    public int type;
    public int data;
    public String fieldName;

}
