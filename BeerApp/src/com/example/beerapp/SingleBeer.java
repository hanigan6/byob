package com.example.beerapp;


import java.util.List;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class SingleBeer extends Activity implements OnClickListener {
	private DatabaseBeer dh;
	private String BeerName;
	
	public SingleBeer(String name) {
		BeerName = name;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i("0", "0");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_beer);
		this.dh = new DatabaseBeer(this);
		
		ListView list = (ListView) findViewById(R.id.beer_stats);
		
		View btnEditBeer = (Button) findViewById(R.id.edit_beer_button);
		btnEditBeer.setOnClickListener(this);
		View btnCmpBeer = (Button) findViewById(R.id.compare_beer_button);
		btnCmpBeer.setOnClickListener(this);
		View btnDelBeer = (Button) findViewById(R.id.delete_beer_button);
		btnDelBeer.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.add_beer_button:
			startActivity(new Intent(this, AddBeer.class));
			break;
		
					
		}
	}
}