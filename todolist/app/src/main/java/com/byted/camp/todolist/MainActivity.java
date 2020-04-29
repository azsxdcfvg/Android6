package com.byted.camp.todolist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.byted.camp.todolist.beans.Note;
import com.byted.camp.todolist.beans.State;
import com.byted.camp.todolist.operation.activity.DatabaseActivity;
import com.byted.camp.todolist.operation.activity.DebugActivity;
import com.byted.camp.todolist.operation.activity.SettingActivity;
import com.byted.camp.todolist.operation.db.AppDatabase;
import com.byted.camp.todolist.operation.db.entity.Noteentity;
import com.byted.camp.todolist.operation.db.entity.count;
import com.byted.camp.todolist.ui.NoteListAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_ADD = 1002;
    public static long total;
    private RecyclerView recyclerView;
    private NoteListAdapter notesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(
                        new Intent(MainActivity.this, NoteActivity.class),
                        REQUEST_CODE_ADD);
            }
        });

        recyclerView = findViewById(R.id.list_todo);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        notesAdapter = new NoteListAdapter(new NoteOperator() {
            @Override
            public void deleteNote(Note note) {
                MainActivity.this.deleteNote(note);
            }

            @Override
            public void updateNote(Note note) {
                MainActivity.this.updateNode(note);
            }
        });
        recyclerView.setAdapter(notesAdapter);

        notesAdapter.refresh(loadNotesFromDatabase());

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                startActivity(new Intent(this, SettingActivity.class));
                return true;
            case R.id.action_debug:
                startActivity(new Intent(this, DebugActivity.class));
                return true;
            case R.id.action_database:
                startActivity(new Intent(this, DatabaseActivity.class));
                return true;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD
                && resultCode == Activity.RESULT_OK) {
            notesAdapter.refresh(loadNotesFromDatabase());
        }
    }


    private List<Note> loadNotesFromDatabase() {
        // TODO 从数据库中查询数据，并转换成 JavaBeans

        List<Noteentity> a = AppDatabase.getInstance(this).noteDao().getall();
        List<Note> b = new ArrayList<>();
        Log.d("hj", String.valueOf(a.size()));
        total = a.size();
        for(int i=0;i<a.size();i++){
            Note temp = new Note(a.get(i).id);
            Log.d("hj", String.valueOf(a.get(i).id));
            temp.setState(a.get(i).state);
            temp.setDate(a.get(i).date);
            temp.setPriority(a.get(i).priority);
            temp.setContent(a.get(i).content);
            Log.d("hj", a.get(i).content);
            b.add(temp);
        }
        return b;
    }

    private void deleteNote(Note note) {
        // TODO 删除数据
        Noteentity temp = new Noteentity();
        temp.id = note.id;
        temp.date = note.getDate();
        temp.state = note.getState();
        temp.content = note.getContent();
        temp.priority = note.getPriority();
        AppDatabase.getInstance(this).noteDao().deletall(temp);
        notesAdapter.refresh(loadNotesFromDatabase());
    }

    private void updateNode(Note note) {
        // TODO 更新数据
        Noteentity temp = new Noteentity();
        temp.id = note.id;
        temp.date = note.getDate();
        temp.state = note.getState();
        temp.content = note.getContent();
        temp.priority = note.getPriority();
        AppDatabase.getInstance(this).noteDao().update(temp);
        notesAdapter.notifyDataSetChanged();
        //this.onCreate(null);

    }

}
