package ddwu.mobile.finalproject.ma01_20180968;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.util.Log;

public class VideoDBHelper extends SQLiteOpenHelper {
    final static String TAG = "VideoDBHelper";
    final static String DB_NAME = "videos.db";

    public final static String TABLE_NAME = "video_table";
    public final static String COL_ID = "_id";
    public final static String COL_VIDEOID = "videoid";
    public final static String COL_TITLE = "title";
    public final static String COL_DESC = "description";
    public final static String COL_THUBURL = "thub_url";
    public final static String COL_THUB = "thub";

    public VideoDBHelper(Context context) { super(context, DB_NAME, null, 1);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + " ( " + COL_ID + " integer primary key autoincrement, "
                + COL_VIDEOID + " TEXT, " + COL_TITLE + " TEXT, " + COL_DESC + " TEXT, "
                + COL_THUBURL + " TEXT, " + COL_THUB + " BLOB) ";
        Log.d(TAG, sql);
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
