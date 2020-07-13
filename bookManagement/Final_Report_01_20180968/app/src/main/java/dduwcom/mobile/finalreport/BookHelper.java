package dduwcom.mobile.finalreport;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BookHelper extends SQLiteOpenHelper {
    final static String TAG = "BookDBHelper";
    final static String DB_NAME = "foods.db";

    public final static String TABLE_NAME = "book_table";
    public final static String COL_ID = "_id";
    public final static String COL_ISIS = "isIm";
    public final static String COL_IMAGE = "imageUri";
    public final static String COL_TITLE = "title";
    public final static String COL_AUTHOR = "author";
    public final static String COL_SUMMARY = "summary";
    public final static String COL_CONTENT = "content";
    public final static String COL_EXPERIENCE = "experience";
    public final static String COL_COLOR = "color";
    public final static String COL_GRADE = "grade";

    public BookHelper(Context context) { super(context, DB_NAME, null, 1);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + " ( " + COL_ID + " integer primary key autoincrement, "
                + COL_ISIS + " TEXT, " + COL_IMAGE + " TEXT, " + COL_TITLE + " TEXT, " + COL_AUTHOR + " TEXT, "
                + COL_SUMMARY + " TEXT, " + COL_CONTENT + " TEXT, " + COL_EXPERIENCE + " TEXT, "
                + COL_COLOR + " TEXT, " + COL_GRADE + " integer)";
        Log.d(TAG, sql);
        db.execSQL(sql);

        db.execSQL("insert into " + TABLE_NAME +" values (null, 'FALSE', null, '제목1', '작가', '요약', '내용', '느낀점', 'pink',5);");
        db.execSQL("insert into " + TABLE_NAME +" values (null, 'FALSE', null, '제목2', '작가', '요약', '내용', '느낀점', 'blue',5);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
