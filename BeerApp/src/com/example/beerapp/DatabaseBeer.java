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
		   private static final int DATABASE_VERSION = 2;
		   private static final String TABLE_NAME = "Beer";
		   private Context context;
		   private SQLiteDatabase db;
		   private SQLiteStatement insertStmt;
		   private static final String INSERT = "insert into " + TABLE_NAME + "(name, maker, maker_location, type, ABV, lat, lng, rating) values (?, ?, ?, ?, ?, ?, ?, ?)" ;
		   
		   public DatabaseBeer(Context context) {
		      this.context = context;
		      BeerDatabase openHelper = new BeerDatabase(this.context);
		      this.db = openHelper.getWritableDatabase();
		      this.insertStmt = this.db.compileStatement(INSERT);
		   }
		   
		   public Cursor search(String searchtext, String column) {
			   //SELECT count(*) FROM enrondata2 WHERE content LIKE '%linux%'
			   String search = column + " LIKE '%" + searchtext + "%'";
			   return this.db.query(TABLE_NAME, new String[] {"name"},  search,  null, null, null,  "name desc");
		   }
		   
		   public Cursor MapData() {
			   return this.db.query(TABLE_NAME, new String[] {"name", "lat", "lng"},  null,  null, null, null,  "name desc");
		   }

		   public long insert(String name, String maker, String maker_location, String type, String ABV, Double lat, Double lng, String rating) {
		      this.insertStmt.bindString(1, name);
		      this.insertStmt.bindString(2, maker);
		      this.insertStmt.bindString(3, maker_location);
		      this.insertStmt.bindString(4, type);
		      this.insertStmt.bindString(5, ABV);
		      this.insertStmt.bindDouble(6, lat);
		      this.insertStmt.bindDouble(7, lng);
		      this.insertStmt.bindString(8, rating);
		      return this.insertStmt.executeInsert();
		   }
		   
		   public void deleteAll(String table) {
			   	this.db.delete(TABLE_NAME, null, null);
		   }
		   
		   public boolean contains(String name) {
			   	String search = "name = \"" + name + "\"";
			    Cursor cursor = this.db.query(TABLE_NAME, null,  search,  null, null, null,  "name desc");
			    return (cursor.getCount() == 1);
		   }
		   
		   public Cursor select(String name) {
			   	String search = "name = \"" + name + "\"";
			    return this.db.query(TABLE_NAME, null,  search,  null, null, null,  "name desc");
		   }
		  
		   public List<String> selectAll(String sort) {
			   String sortline;
			   if (sort.equalsIgnoreCase("rating")) {
				   sortline = sort + " desc";
			   }
			   else {
				   sortline = sort + " asc";
			   }
		      List<String> list = new ArrayList<String>();
		      Log.i("d0", "d0");
		      Cursor cursor = this.db.query(TABLE_NAME, new String[] {"name"},  null,  null, null, null,  sortline, null);
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
		   
		   public void remove(String name) {
			   String whereClause = "name = \"" + name + "\"";
			   db.delete(TABLE_NAME, whereClause, null);
		   }
		   
		   public Cursor selectAll(String sort, String second) {
				  String sortline = sort + " asc";
			     
			      Log.i("d0", "d0");
			      Cursor cursor = this.db.query(TABLE_NAME, new String[] {sort, second},  null,  null, null, null,  sortline, null);
			      Log.i("d1", "d1");
			      
			   
			      return cursor;
			   }
		   
		   private static class BeerDatabase extends SQLiteOpenHelper {
			   BeerDatabase(Context context) {
		    	  super(context, DATABASE_NAME, null, DATABASE_VERSION);
		      }

		      @Override
		      public void onCreate(SQLiteDatabase db) {
		         db.execSQL("CREATE TABLE " + TABLE_NAME + "(name TEXT PRIMARY KEY, maker TEXT, maker_location TEXT, "
		         		+ "type TEXT, ABV TEXT, lat REAL, lng REAL, rating INTEGER)");
		      }
		      @Override
		      public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		         Log.w("Example", "Upgrading database; this will drop and recreate the tables.");
		         db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		         onCreate(db);
		      }
		   }
}
