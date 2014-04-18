package com.example.beerapp;


import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class Location extends Activity implements OnClickListener {
	private GoogleMap googleMap;
	private DatabaseBeer dh;

	
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
        	
            googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                    R.id.map)).getMap();
            //button to zoom in on user
            googleMap.getUiSettings().setMyLocationButtonEnabled(true);
            //shows user location
            googleMap.setMyLocationEnabled(true);
            
            this.dh = new DatabaseBeer(this);
            //markers to each location attached to all beverages
            Cursor cursor = this.dh.MapData();
            Marker[] markers = new Marker[cursor.getCount()];
            int count = 0;
            Log.i("map", "im2");
            if (cursor.moveToFirst()) {
    	        do {
    	        	LatLng coord = new LatLng(cursor.getDouble(1), cursor.getDouble(2));
    	        	//if no location lat and long will both equal 0
    	        	if (coord.latitude != (double)0 & coord.longitude != (double)0) {
    	        		markers[count] = googleMap.addMarker(new MarkerOptions().position(coord).title(cursor.getString(0)) );
    	        	}
    	
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
        
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}