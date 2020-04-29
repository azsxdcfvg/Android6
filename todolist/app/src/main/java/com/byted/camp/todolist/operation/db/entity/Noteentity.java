package com.byted.camp.todolist.operation.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.byted.camp.todolist.beans.State;

import java.util.Date;

@Entity(tableName = "note")
public class Noteentity {

    @PrimaryKey
    public long id;

    @ColumnInfo
    public Date date;

    @ColumnInfo
    public State state;

    @ColumnInfo
    public String content;

    @ColumnInfo
    public int priority;
}
