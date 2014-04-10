package com.example.beerapp;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		View btnBeer = (Button) findViewById(R.id.Beer_button);
		btnBeer.setOnClickListener(this);
		View btnWine = (Button) findViewById(R.id.Wine_button);
		btnWine.setOnClickListener(this);
		View btnLiquor = (Button) findViewById(R.id.Liquor_button);
		btnLiquor.setOnClickListener(this);
		View btnMap = (Button) findViewById(R.id.Map_button);
		btnMap.setOnClickListener(this);
		View btnSettings = (Button) findViewById(R.id.Settings_button);
		btnSettings.setOnClickListener(this);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
	}

	
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.Beer_button:
			startActivity(new Intent(
					"com.example.beerapp.Beer"));
			break;
		case R.id.Wine_button:
			startActivity(new Intent(
					"com.example.beerapp.Wine"));
			break;
		case R.id.Liquor_button:
			startActivity(new Intent(
					"com.example.beerapp.Liquor"));
			break;		
		case R.id.Map_button:
			Log.i("map", "0");
			startActivity(new Intent(
					"com.example.beerapp.Location"));
			break;	
		case R.id.Settings_button:
			startActivity(new Intent(
					"com.example.beerapp.Settings"));
			break;	
		}
	}

}
