//과제명: 도서관리 앱
//분반: 01분반
//학번: 20180968 성명: 박서현
//제출일: 2020년 7월 3일
package dduwcom.mobile.finalreport;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.style.UpdateAppearance;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    final static String TAG = "Main";
    private ArrayList<BooKData> myDataList;
    private BookAdapter myAdapter;
    private ListView listView;
    BookHelper dbHelper;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDataList = new ArrayList<BooKData>();

        dbHelper = new BookHelper(this);

        myAdapter = new BookAdapter(this, R.layout.book, myDataList);
        listView = (ListView) findViewById(R.id.customView);
        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                Intent intentUp = new Intent(context, UpdateBook.class);
                intentUp.putExtra("_id", myDataList.get(pos).get_id());
                intentUp.putExtra("isIm", myDataList.get(pos).getIsIm());
                intentUp.putExtra("image", String.valueOf(myDataList.get(pos).getImageUri()));
                intentUp.putExtra("title", myDataList.get(pos).getTitle());
                intentUp.putExtra("author", myDataList.get(pos).getAuthor());
                intentUp.putExtra("summary", myDataList.get(pos).getSummary());
                intentUp.putExtra("content", myDataList.get(pos).getContent());
                intentUp.putExtra("experience", myDataList.get(pos).getExperience());
                intentUp.putExtra("color", myDataList.get(pos).getColor());
                intentUp.putExtra("grade", myDataList.get(pos).getGrade());
                Log.d(TAG, "image(main): " + myDataList.get(pos).getImageUri());
                startActivity(intentUp);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int pos, long id) {
                final long _id = id;

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("데이터를 삭제하시겠습니까?");
                builder.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SQLiteDatabase db = dbHelper.getReadableDatabase();
                        db.execSQL("DELETE FROM " + BookHelper.TABLE_NAME + " WHERE _id = " + _id);
                        dbHelper.close();
                        onResume();
                    }
                });
                builder.setNegativeButton("취소", null);

                builder.show();
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        readAllBooks();
        myAdapter.notifyDataSetChanged();
    }

    private void readAllBooks() {
        myDataList.clear();

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + BookHelper.TABLE_NAME, null);

        while(cursor.moveToNext()) {
            long id = cursor.getInt(cursor.getColumnIndex(BookHelper.COL_ID));
            String isImage = cursor.getString(cursor.getColumnIndex(BookHelper.COL_ISIS));
            Uri imageUri;

            if (isImage.equals("TRUE")) {
                imageUri = Uri.parse(cursor.getString(cursor.getColumnIndex(BookHelper.COL_IMAGE)));}
            else
                imageUri = null;

            String title = cursor.getString(cursor.getColumnIndex(BookHelper.COL_TITLE));
            String author = cursor.getString(cursor.getColumnIndex(BookHelper.COL_AUTHOR));
            String summary = cursor.getString(cursor.getColumnIndex(BookHelper.COL_SUMMARY));
            String content = cursor.getString(cursor.getColumnIndex(BookHelper.COL_CONTENT));
            String experience = cursor.getString(cursor.getColumnIndex(BookHelper.COL_EXPERIENCE));
            String color = cursor.getString(cursor.getColumnIndex(BookHelper.COL_COLOR));
            int grade = cursor.getInt(cursor.getColumnIndex(BookHelper.COL_GRADE));

            //Log.d(TAG, "\n grade(main): " + grade);
            myDataList.add( new BooKData(id, isImage, imageUri, title, author, summary, content, experience, color, grade));
        }

        cursor.close();
        dbHelper.close();
    }

    public void onClick(View v){
        Intent intentAdd = new Intent(this, AddBook.class);
        startActivity(intentAdd);
    }
    public void onClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addBook:
                Intent intentAdd = new Intent(this, AddBook.class);
                startActivity(intentAdd);
                break;
            case R.id.developer:
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("개발자 소개");
                builder.setMessage("01분반 20180968 박서현☆\n 뾰로롱~♡");
                builder.setIcon(R.mipmap.me);
                builder.setPositiveButton("닫기", null);
                builder.show();
                break;

            case R.id.fin:
                AlertDialog.Builder builder2 = new AlertDialog.Builder(context);
                builder2.setMessage("앱을 종료하시겠습니까?");
                builder2.setPositiveButton("종료", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                builder2.setNegativeButton("취소", null);
                builder2.show();
                break;
            case R.id.sea:
                Intent intentSea = new Intent(this, SearchBook.class);
                startActivity(intentSea);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
