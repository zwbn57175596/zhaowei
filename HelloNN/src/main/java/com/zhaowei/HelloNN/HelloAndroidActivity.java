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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

public class HelloAndroidActivity extends Activity {

    private DBManager mgr = null;
    
    private EditText user_Input = null;
    @SuppressWarnings("unused")
    private Button search = null;
    private GridView gridview;
    private List<Fruit> list;
    private GridAdapter adapter;
    private Intent i;

    public void searchFruit(View view) {
        Log.d("SearchFruit", "enter search");

        String userInput = user_Input.getText().toString();
        Log.d("SearchFruit  user input is ", userInput);
        i.putExtra("userInput", userInput);
        startActivity(i);
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
        
        this.i = new Intent();
        this.i.setClass(this, FruitInfoActivity.class);

        user_Input = (EditText) findViewById(R.id.editText1);
        search = (Button) findViewById(R.id.button1);

        gridview = (GridView) findViewById(R.id.gridview);
        list = mgr.query();
        
//        list.add(new Fruit("name1", R.drawable.cherry));
//        list.add(new Fruit("name2", R.drawable.cherry));
//        list.add(new Fruit("name3", R.drawable.kiwi));
//        list.add(new Fruit("name4", R.drawable.peach));
//        list.add(new Fruit("name5", R.drawable.lemon));
//        list.add(new Fruit("name6", R.drawable.peach));
//        list.add(new Fruit("name6", R.drawable.qiyiguo));
//        list.add(new Fruit("name7", R.drawable.kiwi));
        // list.add(new GridInfo("name8", R.drawable.cherry));
        // list.add(new GridInfo("name9", R.drawable.cherry));
        // list.add(new GridInfo("name10", R.drawable.lemon));
        adapter = new GridAdapter(this);
        adapter.setList(list);
        gridview.setAdapter(adapter);
        gridview.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String userInput = list.get(position).getName();
                Log.d("SearchFruit  user input is ", userInput);
                Intent i = new Intent();
                i.putExtra("userInput", userInput);
                i.setClass(parent.getContext(), FruitInfoActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(com.zhaowei.HelloNN.R.menu.main, menu);
        return true;
    }
    
    @Override
    protected void onDestroy() {
        mgr.closeDB();
        super.onDestroy();
    }

}
