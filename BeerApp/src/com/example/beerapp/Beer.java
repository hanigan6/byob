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
import android.database.Cursor;

public class Beer extends Activity implements OnClickListener {
	private DatabaseBeer dh;
	private EditText search;
	private Spinner spinner1;
	private String selection;
	
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
		this.dh = new DatabaseBeer(this);
		
		
		//spinner1 = (Spinner) findViewById(R.id.sort_spinner);
		//View spnSort = (Spinner) findViewById(R.id.sort_spinner);
		//spnSort.setOnClickListener(this);
		//spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
		Log.i("1", "1");
		ListView list = (ListView) findViewById(R.id.beer_list);
		          
		        // defining Adapter for List content
		       ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		            android.R.layout.simple_list_item_1);
		       Log.i("2", "2");
		       List<String> names = this.dh.selectAll("name");
		       Log.i("3", "3");
		       while (names.size() > 0) {
		    	   adapter.add(names.remove(0));
		       }
		      
		       
		       list.setAdapter(adapter);
		       
		       list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                	
                	
                	
                    TextView sel = (TextView) arg1;
                    
                    String selectedItem = sel.getText().toString();
                    selection = selectedItem;
                    showBeer();
                   /*
                    new AlertDialog.Builder(Beer.this)
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
                                */
                }
     
            }); 
		      
		       
	}
	
	private void showBeer () {
		 Log.i("4", "4");
	       Cursor cursor = this.dh.select(selection);
	       Log.i("5", "5");
	       new AlertDialog.Builder(Beer.this)
         .setTitle("Selection Information")
         .setMessage( cursordisplay(cursor))//cursor.getString(0) + cursor.getString(1) + cursor.getString(2) + cursor.getString(3) + cursor.getString(4)
        		// + cursor.getString(5) + cursor.getString(6))
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
		String retur= new String();
		if (curse.moveToFirst()) {
	        do {
	        	Log.i(curse.getString(0), "qwe");
	        	 retur.concat(curse.getString(0) + "\n");
	        	 
	         } while (curse.moveToNext()); 
	      }

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
