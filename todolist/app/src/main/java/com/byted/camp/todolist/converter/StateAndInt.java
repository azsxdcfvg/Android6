package com.byted.camp.todolist.converter;

import android.arch.persistence.room.TypeConverter;

import com.byted.camp.todolist.beans.State;

import java.util.Date;

public class StateAndInt {
    @TypeConverter
    public static State fromTimestamp(int value) {
        State state = State.from(value);
        return value == -1 ? null :state;
    }

    @TypeConverter
    public static int dateToTimestamp(State state) {
        return state == null ? null : state.intValue;
    }
}