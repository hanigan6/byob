package com.example.beerapp;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.maps.MapActivity;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;

public class Location extends Activity implements OnClickListener {
	private GoogleMap googleMap;
	private DatabaseBeer dh;
	private android.location.Location mCurrentLocation;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        
        try {
            // Loading map
            initilizeMap();
 
        } catch (Exception e) {
            e.printStackTrace();
        }
 
    }
 
    /**
     * function to load map. If map is not created it will create it for you
     * */
    private void initilizeMap() {
    	
        if (googleMap == null) {
        	Log.i("map", "im0");
            googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                    R.id.map)).getMap();
            googleMap.getUiSettings().setMyLocationButtonEnabled(true);
            googleMap.setMyLocationEnabled(true);
            
            //Marker hamburg = map.addMarker(new MarkerOptions().position(HAMBURG).title("Hamburg"));
            Log.i("map", "im1");
            this.dh = new DatabaseBeer(this);
            Cursor cursor = this.dh.MapData();
            Marker[] markers = new Marker[cursor.getCount()];
            int count = 0;
            Log.i("map", "im2");
            if (cursor.moveToFirst()) {
    	        do {
    	        	 //names.add(cursor.getString(0));
    	        	//LocationClient mLocationClient = new LocationClient();
    	    	     // = mLocationClient.getLastLocation();
    	        	Log.i("map", "im3");
    	        	LatLng coord = new LatLng(cursor.getDouble(1), cursor.getDouble(2));
    	        	if (coord.latitude != (double)0 & coord.longitude != (double)0) {
    	        		markers[count] = googleMap.addMarker(new MarkerOptions().position(coord).title(cursor.getString(0)) );
    	        	}
    	        	Log.i("map", "im4");
    	         } while (cursor.moveToNext()); 
    	      }
    	      if (cursor != null && !cursor.isClosed()) {
    	         cursor.close();
    	      }
    	
           
            // check if map is created successfully or not
            if (googleMap == null) {
                Toast.makeText(getApplicationContext(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
            }
        }
        Log.i("map", "im5");
    }
 
    @Override
    protected void onResume() {
        super.onResume();
        initilizeMap();
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}