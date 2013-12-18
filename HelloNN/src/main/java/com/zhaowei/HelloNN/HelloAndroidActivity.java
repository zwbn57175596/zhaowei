package com.zhaowei.HelloNN;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class HelloAndroidActivity extends Activity {

    private EditText user_Input = null;
    @SuppressWarnings("unused")
    private Button search = null;

    public void searchFruit(View view) {
        Log.d("SearchFruit", "enter search");
        // TODO 发送参数并跳转
        String userInput = user_Input.getText().toString();
        Log.d("SearchFruit  user input is ", userInput);
        Intent i = new Intent();
        i.putExtra("userInput", userInput);
        i.setClass(this, FruitInfoActivity.class);
        startActivity(i);
    }
    
    // public void queryFruit(View view) {
    // Cursor c = mgr.queryTheCursor();
    // startManagingCursor(c); // 托付给activity根据自己的生命周期去管理Cursor的生命周期
    // CursorWrapper cursorWrapper = new CursorWrapper(c) {
    // @Override
    // public String getString(int columnIndex) {
    // // 将简介前加上年龄
    // if (getColumnName(columnIndex).equals("info")) {
    // int age = getInt(getColumnIndex("age"));
    // return age + " years old, " + super.getString(columnIndex);
    // }
    // return super.getString(columnIndex);
    // }
    // };
    // // 确保查询结果中有"_id"列
    // SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
    // android.R.layout.simple_list_item_2, cursorWrapper,
    // new String[] { "name", "info" }, new int[] { android.R.id.text1,
    // android.R.id.text2 });
    // ListView listView = (ListView) findViewById(R.id.listView1);

    // listView.setAdapter(adapter);
    // }

    // public void query(View view) {
    // Log.d(Constant.LOG_TAG, "enterquery");
    // List<Fruit> fruits = mgr.query();
    // ArrayList<Map<String, String>> list = new ArrayList<Map<String,
    // String>>();
    // for (Fruit fruit : fruits) {
    // Log.d("fruit_Data", fruit.getName() + "||" + fruit.getPinyin() + "||" +
    // fruit.getDesc());
    //
    // HashMap<String, String> map = new HashMap<String, String>();
    // map.put("name", fruit.getName() + "(" + fruit.getPinyin() + ")");
    // map.put("info", fruit.getDesc());
    // list.add(map);
    // }
    // Log.d(Constant.LOG_TAG, String.valueOf(list.size()));
    // SimpleAdapter adapter = new SimpleAdapter(this, list,
    // android.R.layout.simple_list_item_2, new String[] {
    // "name", "info" }, new int[] { android.R.id.text1, android.R.id.text2 });
    // listView.setAdapter(adapter);
    // }

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

        user_Input = (EditText) findViewById(R.id.editText1);
        search = (Button) findViewById(R.id.button1);

        // listView = (ListView) findViewById(R.id.listView1);
        /*
         * Button button = (Button) findViewById(R.id.button1);
         * button.setOnClickListener(new OnClickListener() {
         * 
         * @Override public void onClick(View arg0) { TextView textView =
         * (TextView) findViewById(R.id.textView1);
         * textView.setText("you click me"); } });
         */
//        findViewById(R.id.button2).setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View arg0) {
//                Intent intent = new Intent();
//                intent.setClass(arg0.getContext(), Display.class);
//                startActivity(intent);
//            }
//        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(com.zhaowei.HelloNN.R.menu.main, menu);
        return true;
    }

}
