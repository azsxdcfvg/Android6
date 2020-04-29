package com.byted.camp.todolist.beans;

import android.arch.persistence.room.Entity;

import java.util.Date;

/**
 * Created on 2019/1/23.
 *
 * @author xuyingyi@bytedance.com (Yingyi Xu)
 */


public class Note {

    public final long id;
    private Date date;
    private State state;
    private String content;
    private int priority;

    public Note(long id) {
        this.id = id;
    }

    public int getPriority(){return priority;}

    public void setPriority(int i){priority = i;}

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
