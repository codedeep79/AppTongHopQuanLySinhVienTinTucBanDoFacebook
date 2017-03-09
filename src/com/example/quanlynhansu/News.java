package com.example.quanlynhansu;

import com.example.entity.news;
import com.example.map.Map_activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import async.AsyncTask_News;

public class News extends Activity {
	ListView listNews;
	TextView txtLoading;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news);
		listNews = (ListView) findViewById(R.id.listNews);
		txtLoading = (TextView) findViewById(R.id.txtLoading);
		
		if (checkConnect())
		{
			new AsyncTask_News(News.this).execute("http://vnexpress.net/rss/phap-luat.rss");
			listNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					news banTin = (news) parent.getAdapter().getItem(position);
					Intent intent = new Intent(News.this, WebBrowser.class);
					Bundle b = new Bundle();
					b.putString("link", banTin.getLink());
					intent.putExtras(b);
					startActivity(intent);
					overridePendingTransition(R.anim.transition1, R.anim.transition2);
				}
			});
		}
		else if (!checkConnect())
			Toast.makeText(News.this, "Turn on Wifi to use this app", Toast.LENGTH_LONG).show();
	}
	
	private boolean checkConnect(){
		ConnectivityManager connectivity = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
      if (connectivity != null) {
          NetworkInfo info1 = connectivity.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
          NetworkInfo info2 = connectivity.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
          if (info1 != null || info2 != null) {
              if (info1.isConnected() || info2.isConnected()) {
                  return true;
              }
          }
      }
      return false;
  }
}
