package com.byted.camp.todolist;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.byted.camp.todolist.beans.Note;
import com.byted.camp.todolist.operation.db.entity.Noteentity;

import java.util.List;

/**
 * Created on 2019/1/23.
 *
 * @author xuyingyi@bytedance.com (Yingyi Xu)
 */

public interface NoteOperator {

    void deleteNote(Note note);

    void updateNote(Note note);
}
