package com.zhaowei.HelloNN;

import com.zhaowei.HelloNN.DB.DBManager;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class HelloAndroidActivity extends Activity {

    private DBManager mgr = null;

    public void queryFruit(View view) {
        Cursor c = mgr.queryTheCursor();
        startManagingCursor(c); // 托付给activity根据自己的生命周期去管理Cursor的生命周期
        CursorWrapper cursorWrapper = new CursorWrapper(c) {
            @Override
            public String getString(int columnIndex) {
                // 将简介前加上年龄
                if (getColumnName(columnIndex).equals("info")) {
                    int age = getInt(getColumnIndex("age"));
                    return age + " years old, " + super.getString(columnIndex);
                }
                return super.getString(columnIndex);
            }
        };
        // 确保查询结果中有"_id"列
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cursorWrapper,
                new String[] { "name", "info" }, new int[] { android.R.id.text1, android.R.id.text2 });
        ListView listView = (ListView) findViewById(R.id.listView1);
        listView.setAdapter(adapter);
    }

    /**
     * Called when the activity is first created.
     * 
     * @param savedInstanceState
     *            If the activity is being re-initialized after previously being
     *            shut down then this Bundle contains the data it most recently
     *            supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it
     *            is null.</b>
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mgr = new DBManager(this);

        Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                TextView textView = (TextView) findViewById(R.id.textView1);
                textView.setText("you click me");
            }
        });
        findViewById(R.id.button2).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent();
                intent.setClass(arg0.getContext(), Display.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(com.zhaowei.HelloNN.R.menu.main, menu);
        return true;
    }

}
