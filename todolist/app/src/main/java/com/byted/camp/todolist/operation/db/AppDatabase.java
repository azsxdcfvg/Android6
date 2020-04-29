package com.byted.camp.todolist.operation.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;

import com.byted.camp.todolist.converter.DateAndLong;
import com.byted.camp.todolist.converter.StateAndInt;
import com.byted.camp.todolist.operation.db.dao.NoteDao;
import com.byted.camp.todolist.operation.db.dao.UserDao;
import com.byted.camp.todolist.operation.db.entity.Noteentity;
import com.byted.camp.todolist.operation.db.entity.User;

/**
 * @author zhongshan
 * 2020-04-19.
 */
@Database(entities = {Noteentity.class}, version = 3, exportSchema = false)
@TypeConverters({DateAndLong.class,StateAndInt.class})
public abstract class AppDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "my_app.db";

    private static volatile AppDatabase sInstance;

    //public abstract UserDao userDao();
    public abstract NoteDao noteDao();
    public static AppDatabase getInstance(final Context context) {
        if (sInstance == null) {
            synchronized (AppDatabase.class) {
                if (sInstance == null) {
                    sInstance = buildDatabase(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }

    private static AppDatabase buildDatabase(Context appContext) {
        return Room.databaseBuilder(appContext, AppDatabase.class, DATABASE_NAME)
//                .addMigrations(MIGRATION_1_2)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }

    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {

        @Override
        public void migrate(SupportSQLiteDatabase database) {
            //
        }
    };
}
