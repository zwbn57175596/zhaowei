package com.example.adDemo;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

/**
 * Created with IntelliJ IDEA.
 * User: telen
 * Date: 13-10-15
 * Time: 下午2:58
 * To change this template use File | Settings | File Templates.
 */
public class WebAppInterface {
    Context mContext;
    WebView view;

    public WebAppInterface(Context mContext, WebView view) {
        this.mContext = mContext;
        this.view = view;
    }
    
    @JavascriptInterface
    public void reqHttpGet(String url, String callbackMethod) {
    	Thread t = new Thread(new InterfaceThread(this.view, callbackMethod));
    	t.start();
    }
    
    @JavascriptInterface
    public void openUrl(String url) {
    	view.loadUrl(url);
    }
    
    @JavascriptInterface
    public void goback() {
    	if (view.copyBackForwardList().getSize() <= 0)
    		return;
    	view.goBackOrForward(-1);
    }

    /**
     * js method for webview page
     * @param toast
     */
    @JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();

    }
}
