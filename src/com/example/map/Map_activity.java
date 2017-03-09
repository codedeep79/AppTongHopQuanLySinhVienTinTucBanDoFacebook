package com.example.map;

import java.io.IOException;
import java.util.ArrayList;

import com.example.quanlynhansu.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Map_activity extends Activity implements OnMapReadyCallback,OnClickListener{
	private GoogleMap googleMap;
	private GoogleMap googleMap1;
	Button btnCs1, btnCs2;
	double vido 	= 10.790837;
	double kinhdo 	= 106.682046;
	String title = "";
	LatLng cs1 = new LatLng(10.790837, 106.682046);
	LatLng cs2 = new LatLng(10.811740, 106.680199);
	EditText edtSearch;
	Button btnSearch;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map_activity);
		edtSearch = (EditText) findViewById(R.id.edtSearch);
		btnCs1 = (Button) findViewById(R.id.cs1);
		btnCs2 = (Button) findViewById(R.id.cs2);
		btnSearch = (Button) findViewById(R.id.btnSearch);
		btnSearch.setOnClickListener(this);
		btnCs1.setOnClickListener(this);
		btnCs2.setOnClickListener(this);
		googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
	}
	@Override
	public void onMapReady(GoogleMap arg0) {
//		googleMap = arg0;
//		LatLng cs1 = new LatLng(10.790837, 106.682046);
//		title = "Trường cao đẳng thực hành FPT Polutechnic(Cơ sở 1)";
//		googleMap.addMarker(new MarkerOptions().position(cs1).title(title));
//		CameraUpdate cam = CameraUpdateFactory.newLatLngZoom(cs1, 16);
//		googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cs1, 15));
//		googleMap.animateCamera(CameraUpdateFactory.zoomTo(20), 2000, null);
	}
	@Override
	public void onClick(View v) {
		switch(v.getId())
		{
		case R.id.cs1:
			title = "Trường cao đẳng thực hành FPT Polutechnic(Cơ sở 1)";
			googleMap.addMarker(new MarkerOptions().position(cs1).title(title));
			CameraUpdate cam = CameraUpdateFactory.newLatLngZoom(cs1, 16);
			googleMap.animateCamera(cam);
			break;
		case R.id.cs2:
			title = "Trường cao đẳng thực hành FPT Polutechnic(Cơ sở 2)";
			googleMap.addMarker(new MarkerOptions().position(cs2).title(title));
			googleMap.addPolyline(new PolylineOptions().add(
								 new LatLng(10.813453, 106.678686)
								, new LatLng(10.812062, 106.678677)
								, new LatLng(10.812026, 106.679805)
								, new LatLng(10.811684, 106.679780)
								, cs2).width(5).color(Color.BLUE));
			CameraUpdate cam1 = CameraUpdateFactory.newLatLngZoom(cs2, 16);
			googleMap.animateCamera(cam1);
			break;
		case R.id.btnSearch:
			Geocoder geocoder = new Geocoder(Map_activity.this);
			ArrayList<Address> address = null;
			String location = edtSearch.getText().toString();
			try {
				address = (ArrayList<Address>) geocoder.getFromLocationName(location, 1);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (!address.isEmpty())
			{
				Address ad = address.get(0);
				LatLng coor = new LatLng(ad.getLatitude(), ad.getLongitude());
				googleMap.addMarker(new MarkerOptions().position(coor).title(location));
				googleMap.animateCamera(CameraUpdateFactory.newLatLng(coor));
			}
			else
				Toast.makeText(Map_activity.this, "Không có dữ liệu", Toast.LENGTH_LONG).show();
			break;
		}
	}
	
	@Override
	public void finish() {
		super.finish();
		overridePendingTransition(R.anim.scale2, R.anim.scale1);
	}
}
