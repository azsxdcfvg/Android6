package com.byted.camp.todolist.db;

import android.provider.BaseColumns;

/**
 * Created on 2019/1/22.
 *
 * @author xuyingyi@bytedance.com (Yingyi Xu)
 */
public final class TodoContract {

    // TODO 定义表结构和 SQL 语句常量

    private TodoContract() {
    }

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TodoContract.TodoEntry.TABLE_NAME + " (" +
                    TodoContract.TodoEntry.ID + " INTEGER PRIMARY KEY," +
                    TodoContract.TodoEntry.DATE + " DATE," +
                    TodoContract.TodoEntry.CONTENT + " TEXT," +
                    TodoContract.TodoEntry.STATE + " INTEGER)";


    public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TodoContract.TodoEntry.TABLE_NAME;


    /* Inner class that defines the table contents */
    public static class TodoEntry implements BaseColumns {

        public static final String ID = "id";

        public static final String TABLE_NAME = "note";

        public static final String DATE = "date";

        public static final String STATE = "state";

        public static final String CONTENT = "content";
    }
}
