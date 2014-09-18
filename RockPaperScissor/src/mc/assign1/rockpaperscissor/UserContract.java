package mc.assign1.rockpaperscissor;

import android.provider.BaseColumns;

public class UserContract {

	public UserContract() {

	}

	public static abstract class UserEntry implements BaseColumns {

		public static final String TABLE_NAME = "user_info";
		public static final String COLUMN_USERNAME = "username";
		public static final String COLUMN_EMAIL = "email";
		public static final String COLUMN_WINS = "win";
		public static final String COLUMN_LOSS = "loss";
		public static final String COLUMN_DRAW = "draw";

	}

	private static final String TEXT_TYPE = " TEXT";
	private static final String COMMA_SEP = ",";
	public static final String SQL_CREATE_ENTRIES = "CREATE TABLE "
			+ UserEntry.TABLE_NAME + " (" + UserEntry._ID
			+ " INTEGER PRIMARY KEY," + UserEntry.COLUMN_USERNAME + TEXT_TYPE
			+ COMMA_SEP + UserEntry.COLUMN_EMAIL + TEXT_TYPE + COMMA_SEP
			+ UserEntry.COLUMN_WINS + TEXT_TYPE + COMMA_SEP
			+ UserEntry.COLUMN_DRAW + TEXT_TYPE + COMMA_SEP
			+ UserEntry.COLUMN_LOSS + TEXT_TYPE + " )";

	public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS "
			+ UserEntry.TABLE_NAME;

}