package com.example.samplemcptt;

public class ViewMoreTemplate {
    public final  static int TEXT_ROW=0;
    public final static int SEEKBAR_ROW=1;
    public final static int VERSION_ROW=2;
    public int type;
    public int data;
    public String fieldName;

    public ViewMoreTemplate(int type, int data, String fieldName) {
        this.type = type;
        this.data = data;
        this.fieldName = fieldName;
    }
}
