package com.zhaowei.HelloNN;

import java.util.ArrayList;
import java.util.List;

import com.zhaowei.HelloNN.DB.DBManager;
import com.zhaowei.HelloNN.DB.pojo.Fruit;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class Display extends Activity {
    
    private DBManager mgr;
    
    private EditText name;
    private EditText pinyin;
    private EditText desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        mgr = new DBManager(this);
        name = (EditText) findViewById(R.id.editText1);
        pinyin = (EditText) findViewById(R.id.editText2);
        desc = (EditText) findViewById(R.id.editText3);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.display, menu);
        return true;
    }
    
    public void addFruit(View view) {
        List<Fruit> fruits  = new ArrayList<Fruit>();
        fruits.add(new Fruit(name.getText().toString(), desc.getText().toString(), pinyin.getText().toString()));
        mgr.add(fruits);
    }

    @Override
    protected void onDestroy() {
        mgr.closeDB();
        super.onDestroy();
    }
}
