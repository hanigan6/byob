package com.example.beerapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Settings extends Activity implements OnClickListener{
	private DatabaseBeer dbb;
	private DatabaseWine dbw;
	private DatabaseLiquor dbl;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		
		View btnLogin = (Button) findViewById(R.id.edit_account_button);
		btnLogin.setOnClickListener(this);
		View btnCancel = (Button) findViewById(R.id.logout_button);
		btnCancel.setOnClickListener(this);
		View btnDropTables = (Button) findViewById(R.id.drop_tables_button);
		btnDropTables.setOnClickListener(this);

	}
	
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.edit_account_button:
			startActivity(new Intent(this, MainActivity.class));
			break;
		case R.id.logout_button:
			finish();
			break;
		case R.id.drop_tables_button:
			this.dbb = new DatabaseBeer(this);
			this.dbw = new DatabaseWine(this);
			this.dbl = new DatabaseLiquor(this);
			
			this.dbb.deleteAll("Beer");
			this.dbw.deleteAll("Wine");
			this.dbl.deleteAll("Liquor");
			
			break;

		}
	}
}
