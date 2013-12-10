package com.example.adDemo;

import android.webkit.WebView;

public class InterfaceThread implements Runnable {
	
	private WebView view;
	
	private String callbackMethod;
	
	@SuppressWarnings("unused")
	private InterfaceThread () { super();}
	
	public InterfaceThread (WebView view, String callbackMethod) {
		this.view = view;
		this.callbackMethod = callbackMethod;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(100);
			view.loadUrl("javascript:" + callbackMethod + "({\"name\" : \"zhaowei\", \"age\" : 18});");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
