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

public class Liquor extends Activity implements OnClickListener {
	private DatabaseLiquor dh;
	private EditText search;
	private Spinner spinner1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i("0", "0");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_liquor);
		
		Intent intent = getIntent();
	    if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
	      String query = intent.getStringExtra(SearchManager.QUERY);
	      doMySearch(query);
	    }
		
		search = (EditText) findViewById(R.id.search_text);
		View btnAddliquor = (Button) findViewById(R.id.add_liquor_button);
		btnAddliquor.setOnClickListener(this);
		this.dh = new DatabaseLiquor(this);
		
		
		//spinner1 = (Spinner) findViewById(R.id.sort_spinner);
		//View spnSort = (Spinner) findViewById(R.id.sort_spinner);
		//spnSort.setOnClickListener(this);
		//spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
		Log.i("1", "1");
		ListView list = (ListView) findViewById(R.id.liquor_list);
		          
		        // defining Adapter for List content
		       ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		            android.R.layout.simple_list_item_1);
		       Log.i("2", "2");
		       List<String> names = this.dh.selectAll("name");
		       Log.i("3", "3");
		       while (names.size() > 0) {
		    	   adapter.add(names.remove(0));
		       }
		       Log.i("4", "4");
		       //adapter.add("One");
		       //adapter.add("Two");
		       
		       list.setAdapter(adapter);
		       
		       list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                	
                	
                    TextView sel = (TextView) arg1;
                    String selectedItem = sel.getText().toString();
                   // startActivity(new Intent(this, SingleBeer.class));
                    new AlertDialog.Builder(Liquor.this)
                            .setTitle("Selection Information")
                            .setMessage("You have selected " 
                                    + selectedItem)
                            .setNeutralButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(
                                            DialogInterface dialog,
                                            int whichButton) {
     
                                    }
                                }).show();
                }
     
            }); 
	}
	
	private void doMySearch(String query) {
		

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.add_liquor_button:
			startActivity(new Intent(this, AddLiquor.class));
			break;
		
					
		}
	}

}
