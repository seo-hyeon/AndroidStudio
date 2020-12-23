package ddwu.mobile.finalproject.ma01_20180968;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class BookMark extends AppCompatActivity {
    final static String TAG = "Main";
    private ArrayList<YoutubeData> myDataList;
    private VideoAdapter myAdapter;
    private ListView listView;
    VideoDBHelper dbHelper;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookmark);

        myDataList = new ArrayList<YoutubeData>();

        dbHelper = new VideoDBHelper(this);

        myAdapter = new VideoAdapter(this, R.layout.video, myDataList);
        listView = (ListView) findViewById(R.id.customView);
        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                Intent intent = new Intent(context, YoutubeActivity.class);
                intent.putExtra("id", myDataList.get(pos).getId());
                startActivity(intent);
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
                        db.execSQL("DELETE FROM " + VideoDBHelper.TABLE_NAME + " WHERE _id = " + _id);
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
        readAllVideos();
        myAdapter.notifyDataSetChanged();
    }

    private void readAllVideos() {
        myDataList.clear();

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + VideoDBHelper.TABLE_NAME, null);

        while(cursor.moveToNext()) {
            YoutubeData data = new YoutubeData();
            data.set_id(cursor.getInt(cursor.getColumnIndex(VideoDBHelper.COL_ID)));
            data.setId(cursor.getString(cursor.getColumnIndex(VideoDBHelper.COL_VIDEOID)));
            data.setTitle(cursor.getString(cursor.getColumnIndex(VideoDBHelper.COL_TITLE)));
            data.setDescription(cursor.getString(cursor.getColumnIndex(VideoDBHelper.COL_DESC)));
            data.setThubnailsURL(cursor.getString(cursor.getColumnIndex(VideoDBHelper.COL_THUBURL)));
            data.setThub(getBitmap(cursor.getBlob(cursor.getColumnIndex(VideoDBHelper.COL_THUB))));

            //Log.d(TAG, "\n grade(main): " + grade);
            myDataList.add(data);
        }

        cursor.close();
        dbHelper.close();
    }

    public Bitmap getBitmap(byte[] b) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
        return bitmap;
    }
}
