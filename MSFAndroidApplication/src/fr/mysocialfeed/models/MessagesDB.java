package fr.mysocialfeed.models;

import java.util.HashMap;
import java.util.Map;

import android.R.array;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MessagesDB {
	private static final int DATABASE_VERSION = 1;
	private static final String DB_NAME = "messages.db";

	private static final String TABLE_NAME = "messages";
	private static final String COL_ID = "id";
	private static final String COL_DATE = "date";
	private static final String COL_ACCOUNTNAME = "accountName";
	private static final String COL_MESSAGE = "message";
	private static final String COL_SENDER = "sender";
	private static final String COL_SHARE = "share";
	private static final String COL_LIKE = "like";
	private static final String COL_ILIKE = "iLike";
	private static final String COL_TYPE = "type";
	
	private static final int NUM_COL_ID = 0;
	private static final int NUM_COL_DATE = 1;
	private static final int NUM_COL_ACCOUNTNAME = 2;
	private static final int NUM_COL_MESSAGE = 3;
	private static final int NUM_COL_SENDER = 4;
	private static final int NUM_COL_SHARE = 5;
	private static final int NUM_COL_LIKE = 6;
	private static final int NUM_COL_ILIKE = 7;
	private static final int NUM_COL_TYPE = 8;
	
	private SQLiteDatabase db;
	private MessagesSQLite messageSql;
 
	public MessagesDB( Context context ) {
		messageSql = new MessagesSQLite( context, DB_NAME, null, DATABASE_VERSION );
	}
	
	public void open() {
		db = messageSql.getWritableDatabase();
	}
	public void close() {
		db.close();
	}
	public SQLiteDatabase getBDD(){
		return db;
	}
	
	public long insertMessage( Messages msg ){
		ContentValues values = new ContentValues();
		//values.put(COL_ID, msg.getId());
		values.put(COL_DATE, msg.getDate());
		values.put(COL_ACCOUNTNAME, msg.getAccountName());
		values.put(COL_MESSAGE, msg.getMessage());
		values.put(COL_SENDER, msg.getSender());
		values.put(COL_SHARE, msg.getShare());
		values.put(COL_LIKE, msg.getLike());
		values.put(COL_ILIKE, msg.getILike());
		values.put(COL_TYPE, msg.getType());
		return db.insert( TABLE_NAME, null, values );
	}
	
	public int removeMessagesFromId( int id ){
		return db.delete( TABLE_NAME, COL_ID + " = " + id, null );
	}
	
	/**
	 * Add messages into the hashmap messages.
	 * @param arrayMap	This hashmap was created when this function is called (one hashmap for all messages from different type).
	 * @param type
	 * @return
	 */
	@SuppressLint("UseSparseArrays")	// For better performance (for arrayMap)
	public void getMessagesFromType( Map<Integer, Messages> arrayMap, String type ) {
		Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_TYPE + " = '" + type + "'", null);

		int arraySize = arrayMap.size();
		
		for( int i = 0; i < c.getCount() ; i++ )
		{
			arrayMap.put(arraySize + i, cursorToMesages( c, i));
		}
		c.close();
	}
	
	public Messages getMessagesById( int id ) {
		Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_ID + " = " + id + "", null);	
		return cursorToMesages( c );
	}
	
	/**
	 * Convert a cursor into a message object.
	 */
	private Messages cursorToMesages( Cursor c ){
		if( c.getCount() == 0 )
			return null;
		
		c.moveToFirst();
		Messages msg = new Messages();
		msg.setId( c.getInt(NUM_COL_ID) );
		msg.setDate( c.getString(NUM_COL_DATE) );
		msg.setAccountName( c.getString(NUM_COL_ACCOUNTNAME) );
		msg.setMessage( c.getString(NUM_COL_MESSAGE) );
		msg.setSender( c.getString(NUM_COL_SENDER) );
		msg.setShare( c.getString(NUM_COL_SHARE) );
		msg.setLike( c.getInt(NUM_COL_LIKE) );
		msg.setILike( c.getInt(NUM_COL_ILIKE) );
		msg.setType( c.getString(NUM_COL_TYPE) );
		c.close();
		
		return msg;
	}

	private Messages cursorToMesages( Cursor c, int position ){
		if( c.getCount() == 0 )
			return null;
		
		c.moveToPosition( position );
		Messages msg = new Messages();
		msg.setId( c.getInt(NUM_COL_ID) );
		msg.setDate( c.getString(NUM_COL_DATE) );
		msg.setAccountName( c.getString(NUM_COL_ACCOUNTNAME) );
		msg.setMessage( c.getString(NUM_COL_MESSAGE) );
		msg.setSender( c.getString(NUM_COL_SENDER) );
		msg.setShare( c.getString(NUM_COL_SHARE) );
		msg.setLike( c.getInt(NUM_COL_LIKE) );
		msg.setILike( c.getInt(NUM_COL_ILIKE) );
		msg.setType( c.getString(NUM_COL_TYPE) );
		
		return msg;
	}
}