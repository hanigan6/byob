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
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i("0", "0");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_beer);
		this.dh = new DatabaseBeer(this);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.add_beer_button:
			startActivity(new Intent(this, AddBeverage.class));
			break;
		
					
		}
	}
}