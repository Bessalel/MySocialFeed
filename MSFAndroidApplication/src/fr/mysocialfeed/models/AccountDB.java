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
	private static final String COL_LOGIN = "login";
	private static final String COL_PASSWORD = "password";
	private static final String COL_TYPE = "type";
	private static final String COL_USERID = "userId";		// On the server
 
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
	
	public int countByAccountType( String type ) {
		Cursor c = dbAccount.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_TYPE + " = '" + type + "'", null);
		return c.getCount();
	}
	
}
