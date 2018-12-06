package com.yet.spring.core.beans;

import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class Event {

    private static final AtomicInteger AUTO_ID = new AtomicInteger(0);
    private int id;
    private String msg;
    private Date date;
    private DateFormat dateFormat;

    public Event(Date date, DateFormat df){
        this.id = AUTO_ID.getAndIncrement();

        this.date = date;
        this.dateFormat = df;

    }

    public int getId() { return id; }
    public void setId() {  id = (int)(Math.random()*10000); }

    public String getMsg() { return msg; }
    public void setMsg(String msg) { this.msg = msg; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    @Override
    public String toString(){
        return "id:'" + id + "', msg:'" + msg + "', date:'" + dateFormat.format(date) + "'.";
    }




}
