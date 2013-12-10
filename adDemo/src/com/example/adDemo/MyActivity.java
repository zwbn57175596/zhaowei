package com.example.adDemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;

@SuppressLint("SetJavaScriptEnabled") public class MyActivity extends Activity {
    public static final String EXTRA_MESSAGE = "com.example.adDemo.Message";


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        WebView webView = (WebView) findViewById(R.id.webview_pan);
        webView.setClickable(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.loadUrl("http://10.2.134.57:8080/weather/");

    }

    /**
     * Called when the user click Send button
     * @param view
     */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    /**
     * Open a WebView
     * @param view
     */
    public void openWebView(View view) {
        final Context context = this;
        Intent intent = new Intent(context, WebViewActivity.class);
        startActivity(intent);

//        Button button = (Button) findViewById(R.id.webview_button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, WebViewActivity.class);
//                startActivity(intent);
//            }
//        });

    }
}
