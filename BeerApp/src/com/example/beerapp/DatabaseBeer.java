package com.example.beerapp;

import java.util.ArrayList;
import java.util.List;



import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

public class DatabaseBeer {
		private static final String DATABASE_NAME = "BeerAppBeer.db";
		   private static final int DATABASE_VERSION = 1;
		   private static final String TABLE_NAME = "Beer";
		   private Context context;
		   private SQLiteDatabase db;
		   private SQLiteStatement insertStmt;
		   private static final String INSERT = "insert into " + TABLE_NAME + "(name, maker, maker_location, type, ABV, rating) values (?, ?, ?, ?, ?, ?)" ;
		   
		   public DatabaseBeer(Context context) {
		      this.context = context;
		      BeerDatabase openHelper = new BeerDatabase(this.context);
		      this.db = openHelper.getWritableDatabase();
		      this.insertStmt = this.db.compileStatement(INSERT);
		   }

		   public long insert(String name, String maker, String maker_location, String type, String ABV, String rating) {
		      this.insertStmt.bindString(1, name);
		      this.insertStmt.bindString(2, maker);
		      this.insertStmt.bindString(3, maker_location);
		      this.insertStmt.bindString(4, type);
		      this.insertStmt.bindString(5, ABV);
		      this.insertStmt.bindString(6, rating);
		      return this.insertStmt.executeInsert();
		   }
		   
		   public void deleteAll(String table) {
		      this.db.delete(TABLE_NAME, null, null);
		   }
		  
		   public List<String> selectAll(String sort) {
			  String sortline = sort + " desc";
		      List<String> list = new ArrayList<String>();
		      Log.i("d0", "d0");
		      Cursor cursor = this.db.query(TABLE_NAME, new String[] {"name"},  null,  null, null, null,  "name desc", null);
		      Log.i("d1", "d1");
		      if (cursor.moveToFirst()) {
		        do {
		        	 list.add(cursor.getString(0));
		        	 //list.add(cursor.getString(1));
		         } while (cursor.moveToNext()); 
		      }
		      Log.i("d2", "d2");
		      if (cursor != null && !cursor.isClosed()) {
		         cursor.close();
		      }
		      return list;
		   }
		   
		   private static class BeerDatabase extends SQLiteOpenHelper {
			   BeerDatabase(Context context) {
		    	  super(context, DATABASE_NAME, null, DATABASE_VERSION);
		      }

		      @Override
		      public void onCreate(SQLiteDatabase db) {
		         db.execSQL("CREATE TABLE " + TABLE_NAME + "(name TEXT PRIMARY KEY, maker TEXT, maker_location TEXT,  type TEXT,  ABV TEXT, rating INTEGER)");
		      }
		      @Override
		      public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		         Log.w("Example", "Upgrading database; this will drop and recreate the tables.");
		         db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		         onCreate(db);
		      }
		   }
}
