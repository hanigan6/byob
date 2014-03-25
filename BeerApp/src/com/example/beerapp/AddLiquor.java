package com.example.beerapp;




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

public class AddLiquor extends Activity implements OnClickListener {
	private DatabaseLiquor dh;
	private EditText name;
	private EditText maker;
	private EditText maker_location;
	private EditText ABV;
	private EditText type;
	private Spinner spinner1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_beverage_add);
		
		
		name = (EditText) findViewById(R.id.name_text);
		maker = (EditText) findViewById(R.id.maker_text);
		maker_location = (EditText) findViewById(R.id.maker_location_text);
		ABV = (EditText) findViewById(R.id.ABV_text);
		type = (EditText) findViewById(R.id.type_text);
		
		View btnAddLiquor = (Button) findViewById(R.id.add_beverage_button);
		btnAddLiquor.setOnClickListener(this);
		
		
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.add_beverage_button:
			String nametext = this.name.getText().toString();
			String makertext = this.maker.getText().toString();
			String makerloctext = this.maker_location.getText().toString();
			String ABVtext = this.ABV.getText().toString();
			String typetext = this.type.getText().toString();
			Log.i("5", "5");
			this.dh = new DatabaseLiquor(this);
			this.dh.insert(nametext, makertext, makerloctext, typetext, ABVtext, "5");
			startActivity(new Intent("com.example.beerapp.Liquor"));
			
			break;
					
		}
	}

}
