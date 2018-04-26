package com.mrd.sealmachine.ui.bean;

/**
 * Created by Administrator on 2018/4/16.
 */

public class Message {
    private String date;
    private String title;

    public  Message()
    {

    }

    public Message(String title,String date)
    {
        this.title=title;
        this.date =date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
