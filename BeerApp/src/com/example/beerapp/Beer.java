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
import android.widget.Spinner;

public class Beer extends Activity implements OnClickListener {
	private EditText search;
	private Spinner spinner1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_beer);
		
		Intent intent = getIntent();
	    if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
	      String query = intent.getStringExtra(SearchManager.QUERY);
	      doMySearch(query);
	    }
		
		search = (EditText) findViewById(R.id.search_text);
		View btnAddBeer = (Button) findViewById(R.id.add_beer_button);
		btnAddBeer.setOnClickListener(this);
		
		//spinner1 = (Spinner) findViewById(R.id.sort_spinner);
		//View spnSort = (Spinner) findViewById(R.id.sort_spinner);
		//spnSort.setOnClickListener(this);
		//spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
	}
	
	private void doMySearch(String query) {
		

	}

	@Override
	public void onClick(View v) {
		/*switch (v.getId()) {
		case R.id.Beer_button:
			//checkLogin();
			break;
		case R.id.Wine_button:
			//finish();
			break;
		case R.id.Liquor_button:
			//startActivity(new Intent(this, Account.class));
			break;
					
		}*/
	}

}
