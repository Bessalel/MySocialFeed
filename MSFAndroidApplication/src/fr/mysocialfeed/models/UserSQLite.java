package fr.mysocialfeed.models;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class UserSQLite extends SQLiteOpenHelper {

	/* Define global variable for SQL use */
	private static final int DATABASE_VERSION = 1;
	private static final String TABLE_NAME = "users";
	private static final String COL_ID = "id";
	private static final String COL_USERNAME = "username";
	private static final String COL_NAME = "name";
	private static final String COL_LASTNAME = "lastname";
	private static final String COL_EMAIL = "email";
	private static final String COL_FACEBOOK = "facebook";
	private static final String COL_TWITTER = "twitter";
	private static final String COL_GOOGLE = "google";
	private static final String CREATE_BDD = "CREATE IF NOT EXIST TABLE "
			+ TABLE_NAME  + " ("
			+ COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ COL_USERNAME + " TEXT NOT NULL, "
			+ COL_NAME + " TEXT NOT NULL, "
			+ COL_LASTNAME + " TEXT NOT NULL, "
			+ COL_EMAIL + " TEXT NOT NULL, "
			+ COL_FACEBOOK + " INTEGER, "
			+ COL_TWITTER + " INTEGER, "
			+ COL_GOOGLE + " INTEGER);";
	
	public UserSQLite(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		/* Creation of the database (see CREATE_BDD global variable) */
		db.execSQL(CREATE_BDD);
	}

	/**
	 * Update db. Drop and recreate!
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int olderVersion, int newVersion) {
		if (newVersion > DATABASE_VERSION) {
			db.execSQL("DROP TABLE " + TABLE_NAME + ";");
			onCreate(db);
		}
	}
}
