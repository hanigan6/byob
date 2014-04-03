package com.example.beerapp;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
 
public class CustomOnItemSelectedListener implements OnItemSelectedListener {
 
  public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
	  //startActivity(new Intent(this, AddBeer.class));
	  //ViewGroup vg = findViewById (R.layout.activity_beer);
	  //vg.invalidate();
	  
	 
	//Toast.makeText(parent.getContext(), 
		//"OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
		//Toast.LENGTH_SHORT).show();
	  
  }
 
  @Override
  public void onNothingSelected(AdapterView<?> arg0) {
	// TODO Auto-generated method stub
  }
 
}