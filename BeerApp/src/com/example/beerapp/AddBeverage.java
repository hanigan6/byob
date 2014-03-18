package com.example.beerapp;


import android.os.Bundle;


import android.widget.ArrayAdapter;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
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

public class AddBeverage extends Activity implements OnClickListener {
	private EditText name;
	private Spinner spinner1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_beverage_add);
		
		
		name = (EditText) findViewById(R.id.name_text);
		name = (EditText) findViewById(R.id.maker_text);
		name = (EditText) findViewById(R.id.maker_location_text);
		name = (EditText) findViewById(R.id.ABV_text);
		name = (EditText) findViewById(R.id.type_text);
		
		View btnAddBeer = (Button) findViewById(R.id.add_beer_button);
		btnAddBeer.setOnClickListener(this);
		
		
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.add_beer_button:
			//checkLogin();
			break;
					
		}
	}

}
