package com.example.beerapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Settings extends Activity implements OnClickListener{
	private DatabaseBeer dbb;
	private DatabaseWine dbw;
	private DatabaseLiquor dbl;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		
		View btnbeer = (Button) findViewById(R.id.reset_beer_button);
		btnbeer.setOnClickListener(this);
		View btnwine = (Button) findViewById(R.id.reset_wine_button);
		btnwine.setOnClickListener(this);
		View btnliquor = (Button) findViewById(R.id.reset_liquor_button);
		btnliquor.setOnClickListener(this);


		View btnDropTables = (Button) findViewById(R.id.drop_tables_button);
		btnDropTables.setOnClickListener(this);

	}
	
	private void resetALL() {
		this.dbb = new DatabaseBeer(this);
		this.dbw = new DatabaseWine(this);
		this.dbl = new DatabaseLiquor(this);
		
		this.dbb.deleteAll("Beer");
		this.dbw.deleteAll("Wine");
		this.dbl.deleteAll("Liquor");
	}
	
	private void resetBEER() {
		this.dbb = new DatabaseBeer(this);
		this.dbb.deleteAll("Beer");
	}
	
	private void resetWINE() {
		this.dbw = new DatabaseWine(this);
		this.dbw.deleteAll("Wine");
	}

	private void resetLIQUOR() {
		this.dbl = new DatabaseLiquor(this);
		this.dbl.deleteAll("Liquor");
	}
	
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.reset_beer_button:
			new AlertDialog.Builder(Settings.this)
            .setTitle("Confirm Delete BEER")
            .setNegativeButton("NO",
                new DialogInterface.OnClickListener() {
                    public void onClick(
                            DialogInterface dialog,
                            int whichButton) {

                    }
                })
                .setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(
                            DialogInterface dialog,
                            int whichButton) {
                    	resetBEER();
                    }
                })
                .show();
			break;
		case R.id.reset_wine_button:
			new AlertDialog.Builder(Settings.this)
            .setTitle("Confirm Delete WINE")
            .setNegativeButton("NO",
                new DialogInterface.OnClickListener() {
                    public void onClick(
                            DialogInterface dialog,
                            int whichButton) {

                    }
                })
                .setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(
                            DialogInterface dialog,
                            int whichButton) {
                    	resetWINE();
                    }
                })
                .show();
			break;
		case R.id.reset_liquor_button:
			new AlertDialog.Builder(Settings.this)
            .setTitle("Confirm Delete LIQUOR")
            .setNegativeButton("NO",
                new DialogInterface.OnClickListener() {
                    public void onClick(
                            DialogInterface dialog,
                            int whichButton) {

                    }
                })
                .setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(
                            DialogInterface dialog,
                            int whichButton) {
                    	resetLIQUOR();
                    }
                })
                .show();
			break;
		case R.id.drop_tables_button:
			new AlertDialog.Builder(Settings.this)
            .setTitle("Confirm Delete ALL")
            .setNegativeButton("NO",
                new DialogInterface.OnClickListener() {
                    public void onClick(
                            DialogInterface dialog,
                            int whichButton) {

                    }
                })
                .setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(
                            DialogInterface dialog,
                            int whichButton) {
                    	resetALL();
                    }
                })
                .show();
			break;

		}
	}

}
