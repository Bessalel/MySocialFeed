package fr.mysocialfeed.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class AccountDB {
	private static final int DATABASE_VERSION = 1;
	private static final String DB_NAME = "accounts.db";
 
	private static final String TABLE_NAME = "accounts";
	private static final String COL_ID = "id";
	private static final String COL_NAME = "name";
	private static final String COL_TOKEN = "token";
	private static final String COL_TOKENSECRET = "tokenSecret";
	private static final String COL_TYPE = "type";
	private static final String COL_USERID = "userId";		// On the server
	
	private static final int NUM_COL_ID = 0;
	private static final int NUM_COL_NAME = 1;
	private static final int NUM_COL_TOKEN = 2;
	private static final int NUM_COL_TOKENSECRET = 3;
	private static final int NUM_COL_TYPE = 4;
	private static final int NUM_COL_USERID = 5;
 
	private SQLiteDatabase dbAccount;
 
	private AccountSQLite accountSql;
 
	public AccountDB( Context context ) {
		accountSql = new AccountSQLite( context, DB_NAME, null, DATABASE_VERSION );
	}
	
	public void open() {
		dbAccount = accountSql.getWritableDatabase();
	}
	public void close() {
		dbAccount.close();
	}
	public SQLiteDatabase getBDD(){
		return dbAccount;
	}
	
	public long insertAccount( Account account ){
		ContentValues values = new ContentValues();
		//values.put(COL_ID, msg.getId());
		values.put(COL_NAME, account.getName());
		values.put(COL_TOKEN, account.getToken());
		values.put(COL_TOKENSECRET, account.getTokenSecret());
		values.put(COL_USERID, account.getUserId());
		values.put(COL_TYPE, account.getType());
		
		long l;
		
		try {
			l = dbAccount.insert( TABLE_NAME, null, values );
		} catch( Exception e ) {
			l = -1;
		}
		return l;		
	}
	
	public int countByAccountType( String type ) {
		Cursor c = dbAccount.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_TYPE + " = '" + type + "'", null);
		return c.getCount();
	}
	
}
