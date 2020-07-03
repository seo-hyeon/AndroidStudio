package dduwcom.mobile.finalreport;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SearchBook extends AppCompatActivity {
    private ArrayList<BooKData> myDataList;
    private BookAdapter myAdapter;
    private ListView listView;
    EditText search;
    BookHelper dbHelper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchbook);

        myDataList = new ArrayList<BooKData>();
        dbHelper = new BookHelper(this);
        myAdapter = new BookAdapter(this, R.layout.book, myDataList);
        listView = findViewById(R.id.searchView);
        listView.setAdapter(myAdapter);
        search = findViewById(R.id.editSearch);

    }

    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.searchTitle:
                myDataList.clear();

                SQLiteDatabase db = dbHelper.getReadableDatabase();
                Cursor cursor = db.rawQuery("SELECT * FROM " + BookHelper.TABLE_NAME
                        + " WHERE " + BookHelper.COL_TITLE + " LIKE '%" + search.getText().toString() + "%';", null);

                while (cursor.moveToNext()) {
                    long id = cursor.getInt(cursor.getColumnIndex(BookHelper.COL_ID));
                    String isImage = cursor.getString(cursor.getColumnIndex(BookHelper.COL_ISIS));
                    Uri imageUri;

                    if (isImage.equals("TRUE")) {
                        imageUri = Uri.parse(cursor.getString(cursor.getColumnIndex(BookHelper.COL_IMAGE)));
                    } else
                        imageUri = null;

                    String title = cursor.getString(cursor.getColumnIndex(BookHelper.COL_TITLE));
                    String author = cursor.getString(cursor.getColumnIndex(BookHelper.COL_AUTHOR));
                    String summary = cursor.getString(cursor.getColumnIndex(BookHelper.COL_SUMMARY));
                    String content = cursor.getString(cursor.getColumnIndex(BookHelper.COL_CONTENT));
                    String experience = cursor.getString(cursor.getColumnIndex(BookHelper.COL_EXPERIENCE));
                    String color = cursor.getString(cursor.getColumnIndex(BookHelper.COL_COLOR));
                    int grade = cursor.getInt(cursor.getColumnIndex(BookHelper.COL_GRADE));

                    //Log.d(TAG, "\n grade(main): " + grade);
                    myDataList.add(new BooKData(id, isImage, imageUri, title, author, summary, content, experience, color, grade));
                }

                cursor.close();
                dbHelper.close();

                myAdapter.notifyDataSetChanged();
                break;
            case R.id.exit:
                finish();
                break;
        }
    }
}
