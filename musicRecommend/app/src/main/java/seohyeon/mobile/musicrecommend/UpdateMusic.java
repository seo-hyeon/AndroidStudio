package seohyeon.mobile.musicrecommend;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateMusic extends AppCompatActivity {
    final static String TAG = "UpdateBook";

    MusicDBHelper myDBHelper;
    EditText editTitle;
    EditText editSinger;
    EditText editLength;
    EditText editReview;

    Button buttonAdd;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addmusic);

        myDBHelper = new MusicDBHelper(this);

        intent = getIntent();

        SQLiteDatabase db = myDBHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + MusicDBHelper.TABLE_NAME + " where "
                + MusicDBHelper.COL_ID + " = " + intent.getIntExtra("id", 0), null);

        cursor.moveToNext();

        editTitle = findViewById(R.id.editTitle);
        editTitle.setText(cursor.getString(cursor.getColumnIndex(MusicDBHelper.COL_TITLE)));
        editSinger = findViewById(R.id.editSinger);
        editSinger.setText(cursor.getString(cursor.getColumnIndex(MusicDBHelper.COL_SINGER)));
        editLength = findViewById(R.id.editLength);
        editLength.setText(cursor.getString(cursor.getColumnIndex(MusicDBHelper.COL_LENGTH)));
        editReview = findViewById(R.id.editReview);
        editReview.setText(cursor.getString(cursor.getColumnIndex(MusicDBHelper.COL_REVIEW)));

        buttonAdd = findViewById(R.id.add);
        buttonAdd.setText("수정");

        cursor.close();
        myDBHelper.close();
    }

    public void onClick(View v) {
        if (v.getId() == R.id.add) {

            SQLiteDatabase myDB = myDBHelper.getWritableDatabase();
            ContentValues row = new ContentValues();

            row.put(MusicDBHelper.COL_TITLE, editTitle.getText().toString());
            row.put(MusicDBHelper.COL_SINGER, editSinger.getText().toString());
            row.put(MusicDBHelper.COL_LENGTH, editLength.getText().toString());
            row.put(MusicDBHelper.COL_REVIEW, editReview.getText().toString());

            String whereClause = "_id=" + intent.getIntExtra("id", 0);
            myDB.update(MusicDBHelper.TABLE_NAME, row, whereClause, null);
            myDBHelper.close();
        }
        finish();
    }
}
