package com.byted.camp.todolist;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.byted.camp.todolist.beans.State;
import com.byted.camp.todolist.operation.db.AppDatabase;
import com.byted.camp.todolist.operation.db.entity.Noteentity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NoteActivity extends AppCompatActivity {

    private EditText editText;
    private Button addBtn;
    private SeekBar selector;
    String Tag = "NoteActivityhj";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        setTitle(R.string.take_a_note);
        selector = findViewById(R.id.selector);
        editText = findViewById(R.id.edit_text);
        editText.setFocusable(true);
        editText.requestFocus();

        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManager != null) {
            inputManager.showSoftInput(editText, 0);
        }

        addBtn = findViewById(R.id.btn_add);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence content = editText.getText();
                if (TextUtils.isEmpty(content)) {
                    Toast.makeText(NoteActivity.this,
                            "No content to add", Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean succeed = saveNote2Database(content.toString().trim());
                if (succeed) {
                    Toast.makeText(NoteActivity.this,
                            "Note added", Toast.LENGTH_SHORT).show();
                    setResult(Activity.RESULT_OK);
                } else {
                    Toast.makeText(NoteActivity.this,
                            "Error", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private boolean saveNote2Database(String content) {
        // TODO 插入一条新数据，返回是否插入成功
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        Noteentity newnote = new Noteentity();
        newnote.date = date;
        newnote.content = editText.getText().toString();
        newnote.state = State.TODO;
        newnote.id = MainActivity.total + 1;
        newnote.priority = selector.getProgress();



        Log.d(Tag, "Mainactivity.total:" + MainActivity.total);
        try {
            AppDatabase.getInstance(this).noteDao().insertAll(newnote);
            Log.d(Tag, "数据插入完毕,优先级为"+newnote.priority);
            MainActivity.total++;
            Log.d(Tag, "Mainactivity.total:" + MainActivity.total);
            return true;
        }
        catch (Exception e){
            Log.d(Tag, "出错"+e);
            return false;
        }

    }
}
