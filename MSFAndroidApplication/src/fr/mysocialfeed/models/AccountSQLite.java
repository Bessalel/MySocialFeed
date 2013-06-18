package fr.mysocialfeed.models;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class AccountSQLite extends SQLiteOpenHelper {

	/* Define global variable for SQL use */
	private static final int DATABASE_VERSION = 1;
	private static final String TABLE_NAME = "accounts";
	private static final String COL_ID = "id";
	private static final String COL_NAME = "name";
	private static final String COL_LOGIN = "login";
	private static final String COL_PASSWORD = "lastname";
	private static final String COL_TYPE = "type";
	private static final String COL_USERID = "userId";		// On the server
	private static final String CREATE_DB = "CREATE TABLE "
			+ TABLE_NAME  + " ("
			+ COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ COL_NAME + " TEXT NOT NULL, "
			+ COL_LOGIN + " TEXT NOT NULL, "
			+ COL_PASSWORD + " TEXT NOT NULL, "
			+ COL_TYPE + " TEXT NOT NULL, "
			+ COL_USERID + " INTEGER NOT NULL);";
	
	public AccountSQLite( Context context, String name, CursorFactory factory, int version ) {
		super(context, name, factory, version);
	}
	
	@Override
	public void onCreate( SQLiteDatabase db ) {
		/* Creation of the database (see CREATE_DB global variable) */
		db.execSQL( CREATE_DB );
	}

	/**
	 * Update db. Drop and recreate!
	 */
	@Override
	public void onUpgrade( SQLiteDatabase db, int olderVersion, int newVersion ) {
		if( newVersion > DATABASE_VERSION ) {
			db.execSQL("DROP TABLE " + TABLE_NAME + ";");
			onCreate( db );
		}
	}
}
