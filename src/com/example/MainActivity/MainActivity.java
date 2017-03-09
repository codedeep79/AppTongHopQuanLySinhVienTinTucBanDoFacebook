package com.example.MainActivity;

import com.example.entity.news;
import com.example.map.Map_activity;
import com.example.quanlynhansu.News;
import com.example.quanlynhansu.R;
import com.example.social.Facebook_Social;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import async.AsyncTask_News;

public class MainActivity extends Activity implements OnClickListener {
	ImageButton btnQuanLyNhanVien, btnMap, btnNews, btnSocial;
	ListView listNews;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		initControlListener();
	}
	
	public void initControlListener(){
		btnQuanLyNhanVien.setOnClickListener(this);
		btnMap.setOnClickListener(this);
		btnNews.setOnClickListener(this);
		btnSocial.setOnClickListener(this);
	}
	public void init(){
		btnQuanLyNhanVien = (ImageButton) findViewById(R.id.btnQuanLyNhanVien);
		btnMap 			  = (ImageButton) findViewById(R.id.btnMap);
		btnNews 		  = (ImageButton) findViewById(R.id.btnNews);
		btnSocial 		  = (ImageButton) findViewById(R.id.btnSocial);
		listNews          = (ListView) findViewById(R.id.listNews);
	}

	@Override
	public void onClick(View v) {
		Intent intent;
		switch(v.getId())
		{
			case R.id.btnQuanLyNhanVien:
				intent = new Intent(MainActivity.this, QuanLyNhanVienActivity.class);
				startActivity(intent);
				break;
			case R.id.btnMap:
				intent = new Intent(MainActivity.this, Map_activity.class);
				startActivity(intent);
				break;
			case R.id.btnNews:
				intent = new Intent(MainActivity.this, News.class);
				startActivity(intent);
				break;
			case R.id.btnSocial:
				intent = new Intent(MainActivity.this, Facebook_Social.class);
				startActivity(intent);
//				Toast.makeText(getApplicationContext(), "Đang cập nhật", Toast.LENGTH_LONG).show();
				break;
		}
		
		overridePendingTransition(R.anim.transition1, R.anim.transition2);
	}
	
	@Override
	public void finish() {
		super.finish();
		overridePendingTransition(R.anim.scale2, R.anim.scale1);
	}
	
}
