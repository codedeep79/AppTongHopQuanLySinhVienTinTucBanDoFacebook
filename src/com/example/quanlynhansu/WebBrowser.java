package com.example.quanlynhansu;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Toast;

public class WebBrowser extends Activity {
	private WebView webView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web_browser);
		String url = getIntent().getExtras().getString("link");
		webView = (WebView)  findViewById(R.id.webView);
		webView.loadUrl(url);
	}
	
	@Override
	public void finish() {
		super.finish();
		overridePendingTransition(R.anim.scale2, R.anim.scale1);
	}
}

