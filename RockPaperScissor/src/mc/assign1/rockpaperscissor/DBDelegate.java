package mc.assign1.rockpaperscissor;

import mc.assign1.rockpaperscissor.UserContract.UserEntry;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBDelegate {

	private UserDbHelper userDbHelper;
	private SQLiteDatabase db;
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "User.db";

	public DBDelegate(Context context) {
		userDbHelper = new UserDbHelper(context, DATABASE_NAME, null,
				DATABASE_VERSION);
	}

	public long insert(String username, String email) {
		db = userDbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(UserContract.UserEntry.COLUMN_USERNAME, username);
		values.put(UserContract.UserEntry.COLUMN_EMAIL, email);
		values.put(UserContract.UserEntry.COLUMN_WINS, 0);
		values.put(UserContract.UserEntry.COLUMN_DRAW, 0);
		values.put(UserContract.UserEntry.COLUMN_LOSS, 0);

		return db.insert(UserEntry.TABLE_NAME, null, values);

	}

	public int getCount() {
		db = userDbHelper.getReadableDatabase();

		String[] projection = {UserContract.UserEntry.COLUMN_USERNAME};
		Cursor cursor = db.query(UserContract.UserEntry.TABLE_NAME, projection,
				null, null, null, null, null);
		return cursor.getCount();

	}

}
