package com.example.adDemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created with IntelliJ IDEA.
 * User: telen
 * Date: 13-10-14
 * Time: 下午6:31
 * To change this template use File | Settings | File Templates.
 */
public class WebViewActivity extends Activity {
  

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WebView webView = new WebView(this);
        WebSettings settings = webView.getSettings();
        webView.setClickable(true);
       
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setDatabasePath(getString(R.string._data_data_) + this.getClass().getPackage().getName() + "/app_database/");
        System.out.println(settings.getDatabasePath());

        webView.addJavascriptInterface(new WebAppInterface(this, webView), "Android");
        webView.loadUrl("file:///android_asset/myhtml.html");
        //webView.loadUrl("http://www.baidu.com/");

        setContentView(webView);
    }
}