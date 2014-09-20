package mc.assign1.rockpaperscissor;

import mc.assign1.rockpaperscissor.UserContract.UserEntry;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;

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
		System.out.println("Srini inserting" + username + " " + email);
		db = userDbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(UserContract.UserEntry.COLUMN_USERNAME, username);
		values.put(UserContract.UserEntry.COLUMN_EMAIL, email);
		values.put(UserContract.UserEntry.COLUMN_WINS, 0);
		values.put(UserContract.UserEntry.COLUMN_DRAW, 0);
		values.put(UserContract.UserEntry.COLUMN_LOSS, 0);

		return db.insert(UserEntry.TABLE_NAME, null, values);

	}

	public void getStats(String userName, TextView win, TextView loss,
			TextView draw) {
		db = userDbHelper.getReadableDatabase();

		String[] projection = {UserContract.UserEntry.COLUMN_WINS,
				UserContract.UserEntry.COLUMN_LOSS,
				UserContract.UserEntry.COLUMN_DRAW};
		String selection = UserContract.UserEntry.COLUMN_USERNAME + " LIKE ?";
		String[] selectionArgs = {userName};
		Cursor cursor = db.query(UserContract.UserEntry.TABLE_NAME, projection,
				selection, selectionArgs, null, null, null);
		win.setText(String.valueOf(cursor.getInt(0)));
		loss.setText(String.valueOf(cursor.getInt(1)));
		draw.setText(String.valueOf(cursor.getInt(2)));

	}

	public void updateDB(String userName, String eMail, String result) {

		System.out.println("Srini updateDB" + userName + " " + result);
		String[] projection = {UserContract.UserEntry.COLUMN_WINS,
				UserContract.UserEntry.COLUMN_LOSS,
				UserContract.UserEntry.COLUMN_DRAW};
		String selection = UserContract.UserEntry.COLUMN_USERNAME + " LIKE ?";
		String[] selectionArgs = {userName};

		db = userDbHelper.getReadableDatabase();
		Cursor cursor = db.query(UserContract.UserEntry.TABLE_NAME, projection,
				selection, selectionArgs, null, null, null);
		System.out.println("Srini count=" + cursor.getCount());
		String colNames[] = cursor.getColumnNames();
		int i = 0;
		while (i < colNames.length)
			System.out.println("Srini colname = " + colNames[i]);
		ContentValues values = new ContentValues();

		if (cursor.getCount() == 1) {

			if (result.equals("win"))
				values.put(
						UserContract.UserEntry.COLUMN_WINS,
						cursor.getInt(cursor
								.getColumnIndex(UserContract.UserEntry.COLUMN_WINS)) + 1);
			else if (result.equals("loss"))
				values.put(
						UserContract.UserEntry.COLUMN_LOSS,
						cursor.getInt(cursor
								.getColumnIndex(UserContract.UserEntry.COLUMN_LOSS)) + 1);
			else if (result.equals("draw"))
				values.put(
						UserContract.UserEntry.COLUMN_DRAW,
						cursor.getInt(cursor
								.getColumnIndex(UserContract.UserEntry.COLUMN_DRAW)) + 1);

			db.update(UserContract.UserEntry.TABLE_NAME, values, selection,
					selectionArgs);
		}

	}
}
