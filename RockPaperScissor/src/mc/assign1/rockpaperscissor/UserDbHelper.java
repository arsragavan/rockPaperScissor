package mc.assign1.rockpaperscissor;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDbHelper extends SQLiteOpenHelper {

	private static UserDbHelper userDbHelper;
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "User.db";

	private UserDbHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	public static UserDbHelper getInstance(Context context) {
		if (userDbHelper == null) {
			userDbHelper = new UserDbHelper(context, DATABASE_NAME, null,
					DATABASE_VERSION);
		}
		return userDbHelper;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(UserContract.SQL_CREATE_ENTRIES);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(UserContract.SQL_DELETE_ENTRIES);
		onCreate(db);
	}
}
