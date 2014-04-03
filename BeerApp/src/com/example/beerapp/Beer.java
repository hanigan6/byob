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
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemSelectedListener;
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

public class Beer extends Activity implements OnClickListener, OnItemSelectedListener {
	private DatabaseBeer dh;
	private EditText search;
	private Spinner spinner1;
	private String selection;
	private static String spinnerSelect;
	private ListView list;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i("0", "0");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_beer);
		
		
		//ViewGroup vg = (ViewGroup) findViewById (R.layout.activity_beer);
		//vg.invalidate();
		
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
		spinner1.setOnItemSelectedListener(this);
		spinnerSelect = spinner1.getSelectedItem().toString();
		
		Log.i("1", "1");
		
		populate();
		       
	}
	
	private void populate () {
		list = (ListView) findViewById(R.id.beer_list);
        
        // defining Adapter for List content
       ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_1);
       Log.i("2", search.getText().toString());
       if (search.getText().toString().length() == 0) {
    	   Log.i("3i", spinner1.getSelectedItem().toString());
    	   List<String> names = this.dh.selectAll(spinner1.getSelectedItem().toString());
    	   while (names.size() > 0) {
    		   adapter.add(names.remove(0));
    	   }
       }
       else {
    	   Log.i("3e", search.getText().toString());
    	   List<String> names = new ArrayList<String>(); 
    	   Cursor cursor = this.dh.search(search.getText().toString(), spinner1.getSelectedItem().toString());
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
	
	protected void onResume() {
		Log.i("oR", "0");
		super.onResume();
		//setContentView(R.layout.activity_beer);
	}
	
	private void showBeer () {
		Log.i("sB", "0");
		 StringBuilder retur = new StringBuilder();
	       Cursor curse = this.dh.select(selection);
	       if (curse.moveToFirst()) {
		        do {
		        	for (int x = 0; x < curse.getColumnCount(); x ++) {
		    			retur.append(curse.getString(x) + "\n");
		    		}
		        	 //retur.concat(curse.getString(0) + "\n");
		        	 
		         } while (curse.moveToNext()); 
		      }
	       if (curse != null && !curse.isClosed()) {
	    	   curse.close();
  	      }
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
		Log.i("dMS", "0");

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.add_beer_button:
			startActivity(new Intent(this, AddBeer.class));
			break;
		//TODO done button pressed
		

		/*
		case R.id.beer_list:
			startActivity(new Intent(this, ))
			break;
			*/
		}
	}
	

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		//onResume();
		//list.deferNotifyDataSetChanged();
		Log.i("oIS", "0");
		populate();
		Log.i("oIS", "1");
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		Log.i("oNS", "0");
	}

}
