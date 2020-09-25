package seohyeon.mobile.musicrecommend;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class InsertMusic  extends AppCompatActivity {
    final static String TAG = "AddBook";

    MusicDBHelper myDBHelper;
    EditText editTitle;
    EditText editSinger;
    EditText editLength;
    EditText editReview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addmusic);

        myDBHelper = new MusicDBHelper(this);

        editTitle = findViewById(R.id.editTitle);
        editSinger = findViewById(R.id.editSinger);
        editLength = findViewById(R.id.editLength);
        editReview = findViewById(R.id.editReview);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.add) {

            SQLiteDatabase myDB = myDBHelper.getWritableDatabase();
            ContentValues row = new ContentValues();

            row.put(MusicDBHelper.COL_TITLE, editTitle.getText().toString());
            row.put(MusicDBHelper.COL_SINGER, editSinger.getText().toString());
            row.put(MusicDBHelper.COL_LENGTH, editLength.getText().toString());
            row.put(MusicDBHelper.COL_REVIEW, editReview.getText().toString());

            myDB.insert(MusicDBHelper.TABLE_NAME, null, row);
            myDBHelper.close();

            }
        finish();
    }
}
