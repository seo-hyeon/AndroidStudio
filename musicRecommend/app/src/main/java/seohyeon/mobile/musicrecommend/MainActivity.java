//20180968 박서현
package seohyeon.mobile.musicrecommend;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {

    ListView lvContacts = null;
    MusicDBHelper helper;
    Cursor cursor;
    MyCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvContacts = (ListView)findViewById(R.id.cursorView);
        helper = new MusicDBHelper(this);
        adapter = new MyCursorAdapter(this, R.layout.musicview, null);

        lvContacts.setAdapter(adapter);

        lvContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intentUp = new Intent(MainActivity.this, UpdateMusic.class);

                Cursor cursor = (Cursor) adapter.getItem(i);
                String index = cursor.getString(cursor.getColumnIndex(MusicDBHelper.COL_ID));
                int id = Integer.parseInt(index);

                intentUp.putExtra("id", id);
                startActivity(intentUp);

                onResume();
            }
        });

        lvContacts.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final long _id = l;

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("데이터를 삭제하시겠습니까?");
                builder.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SQLiteDatabase db = helper.getReadableDatabase();
                        db.execSQL("DELETE FROM " + MusicDBHelper.TABLE_NAME + " WHERE _id = " + _id);
                        helper.close();
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
//        DB에서 데이터를 읽어와 Adapter에 설정
        SQLiteDatabase db = helper.getReadableDatabase();
        cursor = db.rawQuery("select * from " + MusicDBHelper.TABLE_NAME, null);

        adapter.changeCursor(cursor);
        helper.close();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        cursor 사용 종료
        if (cursor != null) cursor.close();
    }

    public void onClick(View v){
        Intent intentAdd = new Intent(this, InsertMusic.class);
        startActivity(intentAdd);
    }
}
