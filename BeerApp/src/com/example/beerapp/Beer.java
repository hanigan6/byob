package com.example.beerapp;


import java.util.ArrayList;
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
import android.database.Cursor;

public class Beer extends Activity implements OnClickListener {
	private DatabaseBeer dh;
	private EditText search;
	private Spinner spinner1;
	private String selection;
	private String spinnerSelect;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i("0", "0");
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
		Log.i("01", "01");
		this.dh = new DatabaseBeer(this);
		
		
		spinner1 = (Spinner) findViewById(R.id.sort_spinner);
		//View spnSort = (Spinner) findViewById(R.id.sort_spinner);
		//spnSort.setOnClickListener(this);
		spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
		Log.i("1", "1");
		ListView list = (ListView) findViewById(R.id.beer_list);
		          
		        // defining Adapter for List content
		       ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		            android.R.layout.simple_list_item_1);
		       Log.i("2", search.getText().toString());
		       if (search.getText().toString().length() == 0) {
		    	   Log.i("3i", spinner1.getSelectedItem().toString());
		    	   List<String> names = this.dh.selectAll(String.valueOf(spinner1.getSelectedItem().toString()));
		    	   while (names.size() > 0) {
		    		   adapter.add(names.remove(0));
		    	   }
		       }
		       else {
		    	   Log.i("3e", search.getText().toString());
		    	   List<String> names = new ArrayList<String>(); 
		    	   Cursor cursor = this.dh.search(search.getText().toString());
		    	   if (cursor.moveToFirst()) {
		    	        do {
		    	        	 names.add(cursor.getString(0));
		    	         } while (cursor.moveToNext()); 
		    	      }
		    	      if (cursor != null && !cursor.isClosed()) {
		    	         cursor.close();
		    	      }
		    	   while (names.size() > 0) {
		    		   adapter.add(names.remove(0));
		    	   }
		       }
		       Log.i("3post", spinner1.getSelectedItem().toString());
		       
		       list.setAdapter(adapter);
		       
		       list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                	
                	
                	
                    TextView sel = (TextView) arg1;
                    
                    String selectedItem = sel.getText().toString();
                    selection = selectedItem;
                    showBeer();
              
                }
     
            }); 
		      
		       
	}
	
	private void showBeer () {
		 Log.i("4", "4");
		 StringBuilder retur = new StringBuilder();
	       Cursor curse = this.dh.select(selection);
	       if (curse.moveToFirst()) {
		        do {
		        	for (int x = 0; x < curse.getColumnCount(); x ++) {
		    			Log.i("cc", "retur");
		    			retur.append(curse.getString(x) + "\n");
		    		}
		        	 //retur.concat(curse.getString(0) + "\n");
		        	 
		         } while (curse.moveToNext()); 
		      }
	       if (curse != null && !curse.isClosed()) {
	    	   curse.close();
  	      }
	       Log.i("5", "5");
	       new AlertDialog.Builder(Beer.this)
         .setTitle("Selection Information")
         .setMessage( retur)
         .setNeutralButton("OK",
             new DialogInterface.OnClickListener() {
                 public void onClick(
                         DialogInterface dialog,
                         int whichButton) {

                 }
             }).show();
	       Log.i("6", "6");
	}
	
	private String cursordisplay(Cursor curse) {
		String retur= "test";
		System.out.println(curse.toString());
		List<String> list = new ArrayList<String>();

		if (curse.moveToFirst()) {
	        do {
	        	for (int x = 0; x < curse.getColumnCount(); x ++) {
	    			Log.i("cc", "retur");
	    			retur.concat(curse.getString(x) + "\n");
	    		}
	        	 //retur.concat(curse.getString(0) + "\n");
	        	 
	         } while (curse.moveToNext()); 
	      }
		if (curse != null && !curse.isClosed()) {
	         curse.close();
	      }
		retur.concat("end");
		return retur;
	}
	
	private void doMySearch(String query) {
		

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.add_beer_button:
			startActivity(new Intent(this, AddBeer.class));
			break;

		/*
		case R.id.beer_list:
			startActivity(new Intent(this, ))
			break;
			*/
		}
	}

}
