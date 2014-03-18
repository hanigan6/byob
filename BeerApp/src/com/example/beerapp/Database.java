package com.example.beerapp;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class Database {
	private static final String DATABASE_NAME = "BeerApp.db";
	   private static final int DATABASE_VERSION = 1;
	   private static final String TABLE_NAME1 = "Accounts";
	   private static final String TABLE_NAME2 = "Beer";
	   private static final String TABLE_NAME3 = "Wine";
	   private static final String TABLE_NAME4 = "Liquor";
	   private Context context;
	   private SQLiteDatabase db;
	   private SQLiteStatement insertStmt;
	   private static final String INSERT = "insert into " + TABLE_NAME1 + "(name, password) values (?, ?)" ;
	   
	   public Database(Context context) {
	      this.context = context;
	      TicTacToeOpenHelper openHelper = new TicTacToeOpenHelper(this.context);
	      this.db = openHelper.getWritableDatabase();
	      this.insertStmt = this.db.compileStatement(INSERT);
	   }

	   public long insertAccount(String name, String password) {
	      this.insertStmt.bindString(1, name);
	      this.insertStmt.bindString(2, password);
	      return this.insertStmt.executeInsert();
	   }
	   public void deleteAll(String table) {

	      this.db.delete(table, null, null);
	   }
	  
	   public List<String> selectAll(String TABLE_NAME, String username, String password) {
	      List<String> list = new ArrayList<String>();
	      Cursor cursor = this.db.query(TABLE_NAME, new String[] { "name", "password" }, "name = '"+ username +"' AND password= '"+ password+"'", null, null, null, "name desc");
	      if (cursor.moveToFirst()) {
	        do {
	        	 list.add(cursor.getString(0));
	        	 list.add(cursor.getString(1));
	         } while (cursor.moveToNext()); 
	      }
	      if (cursor != null && !cursor.isClosed()) {
	         cursor.close();
	      }
	      return list;
	   }
	   
	   private static class TicTacToeOpenHelper extends SQLiteOpenHelper {
		   TicTacToeOpenHelper(Context context) {
	    	  super(context, DATABASE_NAME, null, DATABASE_VERSION);
	      }

	      @Override
	      public void onCreate(SQLiteDatabase db) {
	         db.execSQL("CREATE TABLE " + TABLE_NAME1 + "(id INTEGER PRIMARY KEY, name TEXT, password TEXT)");
	      }

	      @Override
	      public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	         Log.w("Example", "Upgrading database; this will drop and recreate the tables.");
	         db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
	         onCreate(db);
	      }
	   }

}
