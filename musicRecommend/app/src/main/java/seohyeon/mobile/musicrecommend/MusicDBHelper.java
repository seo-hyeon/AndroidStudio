package seohyeon.mobile.musicrecommend;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MusicDBHelper extends SQLiteOpenHelper {
    private final static String DB_NAME = "music_db";
    public final static String TABLE_NAME = "music_table";
    public final static String COL_ID = "_id";
    public final static String COL_TITLE = "title";
    public final static String COL_SINGER = "singer";
    public final static String COL_LENGTH = "length";
    public final static String COL_REVIEW = "review";

    public MusicDBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " ( " + COL_ID + " integer primary key autoincrement,"
                + COL_TITLE + " TEXT, " + COL_SINGER + " TEXT, " + COL_LENGTH + " TEXT, "+ COL_REVIEW + " TEXT );");

//		샘플 데이터
        db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES (null, '옥탑방', 'N.flying', '03:19', '노래가 좋다.');");
        db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES (null, '살짝 설렜어 (Nonstop)', '오마이걸', '03:23', '노래가 좋다.');");
        db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES (null, '덤디덤디', '(여자)아이들', '03:31', '노래가 좋다');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table " + TABLE_NAME);
        onCreate(db);
    }
}
