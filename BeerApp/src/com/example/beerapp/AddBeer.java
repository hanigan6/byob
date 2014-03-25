package com.example.beerapp;




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
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;

public class AddBeer extends Activity implements OnClickListener {
	private DatabaseBeer dh;
	private EditText name;
	private EditText maker;
	private EditText maker_location;
	private EditText ABV;
	private EditText type;
	private Spinner spinner1;
	  private RatingBar ratingBar;
	 
	  
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_beverage_add);
		
		
		
		name = (EditText) findViewById(R.id.name_text);
		maker = (EditText) findViewById(R.id.maker_text);
		maker_location = (EditText) findViewById(R.id.maker_location_text);
		ABV = (EditText) findViewById(R.id.ABV_text);
		type = (EditText) findViewById(R.id.type_text);
		
		//View ratingBar = (RatingBar) findViewById(R.id.ratingBar);
		//ratingBar.setOnClickListener(this);
		ratingBar = (RatingBar) findViewById(R.id.ratingBar);
		
		View btnAddBeer = (Button) findViewById(R.id.add_beverage_button);	
		btnAddBeer.setOnClickListener(this);
		
		
	}
	
	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.add_beverage_button:
			String nametext = this.name.getText().toString();
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
			/*else if (this.dh.contains(nametext)) {
				new AlertDialog.Builder(AddBeer.this)
	            .setTitle("Beer already exists")
	            .setNeutralButton("OK",
	                new DialogInterface.OnClickListener() {
	                    public void onClick(
	                            DialogInterface dialog,
	                            int whichButton) {
	
	                    }
	                }).show();
			}*/
			else {
				String makertext = this.maker.getText().toString();
				String makerloctext = this.maker_location.getText().toString();
				String ABVtext = this.ABV.getText().toString();
				String typetext = this.type.getText().toString();
				String ratingtext = String.valueOf(ratingBar.getRating());
				this.dh = new DatabaseBeer(this);
				this.dh.insert(nametext, makertext, makerloctext, typetext, ABVtext, ratingtext);
				startActivity(new Intent("com.example.beerapp.Beer"));
			}
			break;
			

		}
		
		
	}
	
	 
}
