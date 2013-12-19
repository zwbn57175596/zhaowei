package com.zhaowei.HelloNN;

import java.util.List;

import com.zhaowei.HelloNN.DB.DBManager;
import com.zhaowei.HelloNN.DB.pojo.Fruit;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Build;

public class FruitInfoActivity extends Activity {
    
    private DBManager mgr = null;
    
    private TextView result = null;
    
    private ImageView image = null;
    
    
    
    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        Intent i = getIntent();
        String userInput = i.getStringExtra("userInput");
        Log.d("SearchFruit", "pass params is : " + userInput);
//      String keyword = user_Input.getText().toString();
        List<Fruit> fruits = mgr.searchFruit(userInput);
        Log.d("Search after", String.valueOf(fruits.size()));
//      user_Input.clearFocus();
      
      String searchResult = null;
      if (fruits.size() <= 0) {
          searchResult = "亲，偶木有见过这个水果哦。是不是打错字了？";
      } else if (fruits.size() >= 1) {
          searchResult =  fruits.get(0).getName() + "     " + fruits.get(0).getDesc();
          image.setImageResource(getResources().getIdentifier(fruits.get(0).getPinyin(), "drawable", getPackageName()));
          this.setTitle(fruits.get(0).getName());
      } else {
          searchResult = "亲，这种水果有点多哈";
      }
//      ImageSpan is = new ImageSpan(this, R.drawable.apple);
//      SpannableString ss = new SpannableString(" ");
//      ss.setSpan(is, ss.length() - 1, ss.length(), SpannableString.SPAN_INCLUSIVE_EXCLUSIVE);
//      
//      SpannableStringBuilder ssb = new SpannableStringBuilder();
//      ssb.append(ss);
//      ssb.append("  ");
//      ssb.append(searchResult);
//      result.setText(ssb);
      result.setText(searchResult);
      for (Fruit f : mgr.query()) {
          Log.d("change", f.getId().intValue() + "-" + f.getName() + "-" + f.getDesc());
      }
//      for (Fruit f : mgr.queryNoChange()) {
//          Log.d("no_change", f.getId().intValue() + "-" + f.getName() + "-" + f.getDesc());
//      }
//      result.clearComposingText();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit_info);
        // Show the Up button in the action bar.
        setupActionBar();
        mgr = new DBManager(this);
        result = (TextView) findViewById(R.id.textview2);
        image = (ImageView) findViewById(R.id.imageView1);
    }

    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    private void setupActionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.fruit_info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            FruitInfoActivity.this.finish();
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        mgr.closeDB();
        super.onDestroy();
    }
}
