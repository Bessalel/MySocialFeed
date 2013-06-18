package fr.mysocialfeed.models;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class MessagesSQLite extends SQLiteOpenHelper {

	/* Define global variable for SQL use */
	private static final int DATABASE_VERSION = 1;
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
	private static final String CREATE_DB = "CREATE TABLE "
			+ TABLE_NAME  + " ("
			+ COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ COL_DATE + " INTEGER NOT NULL, "
			+ COL_ACCOUNTNAME + " TEXT NOT NULL, "
			+ COL_MESSAGE + " BLOD NOT NULL, "
			+ COL_SENDER + " TEXT NOT NULL, "
			+ COL_SHARE + " INTEGER, "
			+ COL_LIKE + " INTEGER, "
			+ COL_ILIKE + " INTEGER, "
			+ COL_TYPE + " TEXT NOT NULL);";
	
	public MessagesSQLite( Context context, String name, CursorFactory factory, int version ) {
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
