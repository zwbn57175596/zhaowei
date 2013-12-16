package com.zhaowei.HelloNN;

import java.util.List;

import com.zhaowei.HelloNN.DB.DBManager;
import com.zhaowei.HelloNN.DB.pojo.Fruit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class HelloAndroidActivity extends Activity {

    private DBManager mgr = null;
    private EditText user_Input = null;
    @SuppressWarnings("unused")
    private Button search = null;
    private TextView result = null;

//    public void queryFruit(View view) {
//        Cursor c = mgr.queryTheCursor();
//        startManagingCursor(c); // 托付给activity根据自己的生命周期去管理Cursor的生命周期
//        CursorWrapper cursorWrapper = new CursorWrapper(c) {
//            @Override
//            public String getString(int columnIndex) {
//                // 将简介前加上年龄
//                if (getColumnName(columnIndex).equals("info")) {
//                    int age = getInt(getColumnIndex("age"));
//                    return age + " years old, " + super.getString(columnIndex);
//                }
//                return super.getString(columnIndex);
//            }
//        };
//        // 确保查询结果中有"_id"列
//        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cursorWrapper,
//                new String[] { "name", "info" }, new int[] { android.R.id.text1, android.R.id.text2 });
//        ListView listView = (ListView) findViewById(R.id.listView1);
    
//        listView.setAdapter(adapter);
//    }
    
    public void searchFruit(View view) {
        Log.d("SearchFruit", "enter search");
        String keyword = user_Input.getText().toString();
        Log.d("SearchFruit  user input is ", keyword);
        List<Fruit> fruits = mgr.searchFruit(keyword);
        Log.d("Search after", String.valueOf(fruits.size()));
        if (fruits.size() <= 0) {
            result.setText("亲，偶木有见过这个水果哦。是不是打错字了？");
        } else if (fruits.size() >= 1) {
            result.setText(fruits.get(0).getName() + "     " + fruits.get(0).getDesc());
        } else {
            result.setText("亲，这种水果有点多哈");
        }
        
        for (Fruit f : mgr.query()) {
            Log.d("change", f.getId().intValue() + "-" + f.getName() + "-" + f.getDesc());
        }
//        for (Fruit f : mgr.queryNoChange()) {
//            Log.d("no_change", f.getId().intValue() + "-" + f.getName() + "-" + f.getDesc());
//        }
//        result.clearComposingText();
    }

//    public void query(View view) {
//        Log.d(Constant.LOG_TAG, "enterquery");
//        List<Fruit> fruits = mgr.query();
//        ArrayList<Map<String, String>> list = new ArrayList<Map<String, String>>();
//        for (Fruit fruit : fruits) {
//            Log.d("fruit_Data", fruit.getName() + "||" + fruit.getPinyin() + "||" + fruit.getDesc());
//
//            HashMap<String, String> map = new HashMap<String, String>();
//            map.put("name", fruit.getName() + "(" + fruit.getPinyin() + ")");
//            map.put("info", fruit.getDesc());
//            list.add(map);
//        }
//        Log.d(Constant.LOG_TAG, String.valueOf(list.size()));
//        SimpleAdapter adapter = new SimpleAdapter(this, list, android.R.layout.simple_list_item_2, new String[] {
//                "name", "info" }, new int[] { android.R.id.text1, android.R.id.text2 });
//        listView.setAdapter(adapter);
//    }

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
        user_Input = (EditText) findViewById(R.id.editText1);
        search = (Button) findViewById(R.id.button1);
        result = (TextView) findViewById(R.id.textview1);
//        listView = (ListView) findViewById(R.id.listView1);
        /*
         * Button button = (Button) findViewById(R.id.button1);
         * button.setOnClickListener(new OnClickListener() {
         * 
         * @Override public void onClick(View arg0) { TextView textView =
         * (TextView) findViewById(R.id.textView1);
         * textView.setText("you click me"); } });
         */
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
    protected void onDestroy() {
        mgr.closeDB();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(com.zhaowei.HelloNN.R.menu.main, menu);
        return true;
    }

}
