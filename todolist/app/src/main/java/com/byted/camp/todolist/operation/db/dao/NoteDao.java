package com.byted.camp.todolist.operation.db.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.byted.camp.todolist.operation.db.entity.Noteentity;

import java.util.List;

@Dao
public interface NoteDao {

    @Query("select * from note")
    List<Noteentity> getall();

    @Insert
    void insertAll(Noteentity... notes);

    @Insert
    long insertNote(Noteentity note);

    @Delete
    void deletall(Noteentity note);

    @Update
    void update(Noteentity note);
}
