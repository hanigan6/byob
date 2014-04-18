package com.example.beerapp;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.location.Address;

import android.location.Geocoder;
import android.widget.ArrayAdapter;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.TextView.OnEditorActionListener;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;

public class Beer extends Activity implements OnClickListener, OnItemSelectedListener, OnEditorActionListener {
	private DatabaseBeer dh;
	private EditText search;
	private Spinner spinner1;
	private String selection;
	private ListView list;
	private ArrayAdapter<String> adapter;
	private boolean inEdit = false;
	protected int sleepTime = 100;
	private EditText editname;
	private EditText editmaker;
	private EditText editmaker_location;
	private EditText editABV;
	private EditText edittype;
	private RatingBar editratingBar;
	double lat = 0, lng = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i("0", "0");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_beer);
		
		search = (EditText) findViewById(R.id.search_text);
		search.setOnEditorActionListener(this);
		View btnAddBeer = (Button) findViewById(R.id.add_beer_button);
		btnAddBeer.setOnClickListener(this);
		spinner1 = (Spinner) findViewById(R.id.sort_spinner);
		spinner1.setOnItemSelectedListener(this);
		
		
		Log.i("01", "01");
		this.dh = new DatabaseBeer(this);
		
		spinner1 = (Spinner) findViewById(R.id.sort_spinner);
		spinner1.setOnItemSelectedListener(this);
		
		Log.i("1", "1");
		
		populate();
		       
	}
	
	//displays beers from database
	private void populate () {
		Cursor cursor;
		list = (ListView) findViewById(R.id.beer_list);
		List<String> names = new ArrayList<String>();
        // defining Adapter for List content
       adapter = new ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_1);
       Log.i("2", search.getText().toString());
       //if no search entered
       if (search.getText().toString().length() == 0) {
    	   Log.i("3i", spinner1.getSelectedItem().toString());
    	   //display ordered based on spinner
    	   if (spinner1.getSelectedItem().toString().equalsIgnoreCase("name")) {
    		   cursor = this.dh.selectAll(spinner1.getSelectedItem().toString(), "rating");
    	   }
    	   else {
    		   cursor = this.dh.selectAll(spinner1.getSelectedItem().toString(), "name");
    	   }
    	   if (cursor.moveToFirst()) {
		        do {
		        	
		        	if (spinner1.getSelectedItem().toString().equalsIgnoreCase("name")) {
		        		names.add(cursor.getString(0));
		        	}
		        	else {
		        		names.add(cursor.getString(0) + "   --   "  + cursor.getString(1));
		        	}
		        	 
		         } while (cursor.moveToNext()); 
		      }
	       if (cursor != null && !cursor.isClosed()) {
	    	   cursor.close();
 	      }
    	   while (names.size() > 0) {
    		   adapter.add(names.remove(0));
    	   }
       }
       else {
    	   //if search field not empty
    	   doMySearch();
       }       
       
       //add listener to beers
       list.setAdapter(adapter);
       list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            TextView sel = (TextView) arg1;
            StringBuilder selectedItem = new StringBuilder(sel.getText().toString());
            int loc = selectedItem.indexOf("   --   ", 0);
            if (spinner1.getSelectedItem().toString().equalsIgnoreCase("name")) {
            	selection = selectedItem.toString();
            }
            else {
            	selection = selectedItem.substring(0, loc);
            }
            try {
				showBeer();
			} catch (IOException e) {
				e.printStackTrace();
			}
      
        }

    }); 
	}

	//checks if phone has a signal
	//needed to put lat and long to address
	private boolean isOnline() {
	    ConnectivityManager cm =
	        (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
	    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
	        return true;
	    }
	    return false;
	}
	
	//shows individual beer when selected
	private void showBeer () throws IOException {
		if (isOnline()) {
		 StringBuilder retur = new StringBuilder();
		 Geocoder geocoder;
		 List<Address> addresses;
		 geocoder = new Geocoder(this, Locale.getDefault());
		 
		 Cursor curse = this.dh.select(selection);
	       if (curse.moveToFirst()) {
		        do {
		        
		        
		        	addresses = geocoder.getFromLocation(curse.getDouble(5), curse.getDouble(6), 1);
		    			retur.append("name:            " + curse.getString(0) + "\n");
		    			retur.append("maker:           " + curse.getString(1) + "\n");
		    			retur.append("maker location:  " + curse.getString(2) + "\n");
		    			retur.append("type:             " + curse.getString(3) + "\n");
		    			retur.append("ABV:            " + curse.getString(4) + "\n");
		    			if (addresses.size() > 0) {
		    				retur.append("location bought: " + addresses.get(0).getAddressLine(0) + ", " + addresses.get(0).getAddressLine(1) + "\n");
		    			}

		    			
		    			retur.append("rating:          " + curse.getString(7) + "\n");
		    	
		        	 
		         } while (curse.moveToNext()); 
		      }
	       if (curse != null && !curse.isClosed()) {
	    	   curse.close();
  	      }
	       
	       //dialog box for individual beer: edit ok remove
	       new AlertDialog.Builder(Beer.this)
         .setTitle("Selection Information")
         .setMessage( retur)
         .setNeutralButton("OK",
             new DialogInterface.OnClickListener() {
                 public void onClick(
                         DialogInterface dialog,
                         int whichButton) {

                 }
             })
             .setNegativeButton("Remove", new DialogInterface.OnClickListener() {
                 public void onClick(
                         DialogInterface dialog,
                         int whichButton) {
                	 //if remove pressed dialog box to confirm delete
                	 new AlertDialog.Builder(Beer.this)
                     .setTitle("Confirm Delete " + selection)
                     .setPositiveButton("NO",
                         new DialogInterface.OnClickListener() {
                             public void onClick(
                                     DialogInterface dialog,
                                     int whichButton) {

                             }
                         })
                         .setNegativeButton("YES",
                         new DialogInterface.OnClickListener() {
                             public void onClick(
                                     DialogInterface dialog,
                                     int whichButton) {
                            	 dh.remove(selection);
                            	 populate();
                             }
                         })
                         .show();
                 }
             })
             .setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                 public void onClick(
                         DialogInterface dialog,
                         int whichButton) {
                	 	editBeer();
                 }
             })
             .show();
		}
		else  {
			StringBuilder retur = new StringBuilder();
			 
			 
			 Cursor curse = this.dh.select(selection);
		       if (curse.moveToFirst()) {
			        do {
			        	
			    			retur.append("name:            " + curse.getString(0) + "\n");
			    			retur.append("maker:           " + curse.getString(1) + "\n");
			    			retur.append("maker location:  " + curse.getString(2) + "\n");
			    			retur.append("type:             " + curse.getString(3) + "\n");
			    			retur.append("ABV:            " + curse.getString(4) + "\n");
			    			retur.append("location bought: unavailable \n");
			    			retur.append("rating:          " + curse.getString(7) + "\n");
			    	
			        	 
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
	             })
	             .setNegativeButton("Remove", new DialogInterface.OnClickListener() {
	                 public void onClick(
	                         DialogInterface dialog,
	                         int whichButton) {
	                	 new AlertDialog.Builder(Beer.this)
	                     .setTitle("Confirm Delete " + selection)
	                     .setPositiveButton("NO",
	                         new DialogInterface.OnClickListener() {
	                             public void onClick(
	                                     DialogInterface dialog,
	                                     int whichButton) {

	                             }
	                         })
	                         .setNegativeButton("YES",
	                         new DialogInterface.OnClickListener() {
	                             public void onClick(
	                                     DialogInterface dialog,
	                                     int whichButton) {
	                            	 dh.remove(selection);
	                            	 populate();
	                             }
	                         })
	                         .show();
	                 }
	             })
	             .setPositiveButton("Edit", new DialogInterface.OnClickListener() {
	                 public void onClick(
	                         DialogInterface dialog,
	                         int whichButton) {
	                	 	editBeer();
	                 }
	             })
	             .show();
		}
	}

	//when edit beer selected uses add beverage layout with text fields populated with selected beers information
	private void editBeer() {
		setContentView(R.layout.activity_beverage_add);
		inEdit = true;
		View btnAddBeer = (Button) findViewById(R.id.add_beverage_button);	
		btnAddBeer.setOnClickListener(this);
		
		Log.i("editBeer", selection);
		Cursor curse = this.dh.select(selection);
		Log.i("eb", curse.getColumnCount() + " " + curse.getCount());
		
		if (curse.moveToFirst()) {		
			editname = (EditText) findViewById(R.id.name_text);
			editname.setText(curse.getString(0), TextView.BufferType.EDITABLE);
			
			editmaker = (EditText) findViewById(R.id.maker_text);
			editmaker.setText(curse.getString(1), TextView.BufferType.EDITABLE);
			
			editmaker_location = (EditText) findViewById(R.id.maker_location_text);
			editmaker_location.setText(curse.getString(2), TextView.BufferType.EDITABLE);

			edittype = (EditText) findViewById(R.id.type_text);
			edittype.setText(curse.getString(3), TextView.BufferType.EDITABLE);
			
			editABV = (EditText) findViewById(R.id.ABV_text);
			editABV.setText(curse.getString(4), TextView.BufferType.EDITABLE);
			
			lat = curse.getDouble(5);
			lng = curse.getDouble(6);
			
			editratingBar = (RatingBar) findViewById(R.id.ratingBar);
			editratingBar.setRating(curse.getInt(7));
		}
		if (curse != null && !curse.isClosed()) {
	    	   curse.close();
		}
			
		
	}
	
	//pulls all beverages matching search criteria from database
	private void doMySearch() {
		Log.i("dMS", "0");
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
 	  Log.i("dMS", "1");

	}  

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.add_beer_button:
			//when add beer clicked goes to add beverage page
			startActivity(new Intent(this, AddBeer.class));
			break;
		case R.id.add_beverage_button:
			//when in edit mode adds beer to database
			this.dh.remove(selection);
			String nametext = this.editname.getText().toString();
			if (nametext.equals("")) {
				new AlertDialog.Builder(Beer.this)
	            .setTitle("Name cannot be empty")
	            .setNeutralButton("OK",
	                new DialogInterface.OnClickListener() {
	                    public void onClick(
	                            DialogInterface dialog,
	                            int whichButton) {

	                    }
	                }).show();
			}
			else if (this.dh.contains(nametext)) {
				new AlertDialog.Builder(Beer.this)
	            .setTitle("Beer already exists")
	            .setNeutralButton("OK",
	                new DialogInterface.OnClickListener() {
	                    public void onClick(
	                            DialogInterface dialog,
	                            int whichButton) {

	                    }
	                }).show();
			}
			else {
				String makertext = this.editmaker.getText().toString();
				String makerloctext = this.editmaker_location.getText().toString();
				String typetext = this.edittype.getText().toString();
				String ABVtext = this.editABV.getText().toString();
				String ratingtext = String.valueOf(editratingBar.getRating());
				
				this.dh.insert(nametext, makertext, makerloctext, typetext, ABVtext, lat, lng, ratingtext);
			}
			startActivity(new Intent(this, Beer.class));
			break;
		}
		
			
	}
	

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		//refreshes beer list
		populate();
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		//does nothing
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		//if in edit mode goes to beer page
        if (keyCode == KeyEvent.KEYCODE_BACK) {
        	if (!inEdit ){
        	Log.i("oKD", "1");
        	startActivity(new Intent(this, MainActivity.class));
           return true;
        	}
        	else {
        		//else goes to main page
        		startActivity(new Intent(this, Beer.class));
        	}
        }
        return false;
    }

	@Override
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		//refreshes beer list when search entered
		populate();

		return false;
	}

}
