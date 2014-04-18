package com.example.beerapp;


import android.location.Criteria;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.RatingBar;
import android.location.Location;
import android.location.LocationListener;

public class AddBeer extends Activity implements OnClickListener, LocationListener {
	private DatabaseBeer dh;
	private EditText name;
	private EditText maker;
	private EditText maker_location;
	private EditText ABV;
	private EditText type;
	private RatingBar ratingBar;
	private LocationManager locationManager;
	private String provider;
	private Location location;
	private boolean locationInitialized = true;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_beverage_add);
		

		// Get the location manager
	    locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	    // Define the criteria how to select the locatioin provider -> use
	    // default
	    Criteria criteria = new Criteria();
	    provider = locationManager.getBestProvider(criteria, false);
	    location = locationManager.getLastKnownLocation(provider);
	    
		name = (EditText) findViewById(R.id.name_text);
		maker = (EditText) findViewById(R.id.maker_text);
		maker_location = (EditText) findViewById(R.id.maker_location_text);
		type = (EditText) findViewById(R.id.type_text);
		ABV = (EditText) findViewById(R.id.ABV_text);

		ratingBar = (RatingBar) findViewById(R.id.ratingBar);
		
		View btnAddBeer = (Button) findViewById(R.id.add_beverage_button);	
		btnAddBeer.setOnClickListener(this);
		
		// Initialize the location fields
	    if (location != null) {
	      System.out.println("Provider " + provider + " has been selected.");
	      onLocationChanged(location);
	    } 
		
	}

	/* Request updates at startup */
	  @Override
	  protected void onResume() {
	    super.onResume();
	    locationManager.requestLocationUpdates(provider, 400, 1, this);
	  }

	  /* Remove the locationlistener updates when Activity is paused */
	  @Override
	  protected void onPause() {
	    super.onPause();
	    locationManager.removeUpdates(this);
	  }

	  @Override
	  public void onLocationChanged(Location location) {
		  locationManager.requestLocationUpdates(provider, 400, 1, this);
	  }

	  @Override
	  public void onStatusChanged(String provider, int status, Bundle extras) {
	    // TODO Auto-generated method stub

	  }

	  @Override
	  public void onProviderEnabled(String provider) {
		  //if gps enabled
		  Toast.makeText(this, "Enabled new provider " + provider,
	        Toast.LENGTH_SHORT).show();
	    	locationInitialized = true;
	  }

	  @Override
	  public void onProviderDisabled(String provider) {
		//if gps disabled
	    Toast.makeText(this, "Disabled provider " + provider,
	        Toast.LENGTH_SHORT).show();
	    	locationInitialized = false;
	  }
	
	@Override
	public void onClick(View v) {
		this.dh = new DatabaseBeer(this);
		this.dh.contains(this.name.getText().toString());
		switch (v.getId()) {
		//when add beverage pushed add beverage to database
		case R.id.add_beverage_button:
			String nametext = this.name.getText().toString();
			//error message if no name
			if (nametext.equals("")) {
				new AlertDialog.Builder(AddBeer.this)
	            .setTitle("Name cannot be empty")
	            .setNeutralButton("OK",
	                new DialogInterface.OnClickListener() {
	                    public void onClick(
	                            DialogInterface dialog,
	                            int whichButton) {
	
	                    }
	                }).show();
			}
			//error message if beverage already exists
			else if (this.dh.contains(nametext)) {
				new AlertDialog.Builder(AddBeer.this)
	            .setTitle("Beer already exists")
	            .setNeutralButton("OK",
	                new DialogInterface.OnClickListener() {
	                    public void onClick(
	                            DialogInterface dialog,
	                            int whichButton) {
	
	                    }
	                }).show();
			}
			//no problems
			else {
				String makertext = this.maker.getText().toString();
				String makerloctext = this.maker_location.getText().toString();
				String typetext = this.type.getText().toString();
				String ABVtext = this.ABV.getText().toString();
				String ratingtext = String.valueOf(ratingBar.getRating());
				if (! locationInitialized) {
					//if no location enter 0, 0
					Log.i(typetext, ABVtext);
					this.dh.insert(nametext, makertext, makerloctext, typetext, ABVtext, (double)0, (double)0, ratingtext);
				}
				else {
					//if location avaiable add it			
					Log.i(typetext, ABVtext);
					this.dh.insert(nametext, makertext, makerloctext, typetext, ABVtext, location.getLatitude(), location.getLongitude(), ratingtext);
				}
				startActivity(new Intent("com.example.beerapp.Beer"));
			}
			break;
			

		}
		
		
	}

	
	 
}
