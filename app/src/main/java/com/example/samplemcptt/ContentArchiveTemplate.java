package com.example.samplemcptt;

public class ContentArchiveTemplate {

    int fileicon;
    String filename;
    String filedescription;
    String lastmodifiedat;
    String fileextension;

    public ContentArchiveTemplate(int fileicon, String filename, String filedescription, String lastmodifiedat, String fileextension) {
        this.fileicon = fileicon;
        this.filename = filename;
        this.filedescription = filedescription;
        this.lastmodifiedat = lastmodifiedat;
        this.fileextension = fileextension;
    }
}
