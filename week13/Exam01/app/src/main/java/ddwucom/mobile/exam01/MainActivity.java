package ddwucom.mobile.exam01;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView tvDisplay;
    FoodDBHelper myDbHelper;
    final static int ADD = 100;
    final static int REMOVE = 101;
    final static int UPDATE = 102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDisplay = findViewById(R.id.tvDisplay);
        myDbHelper = new FoodDBHelper(this);
    }



    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnSelect:
                SQLiteDatabase myDB = myDbHelper.getReadableDatabase();
                Cursor cursor = myDB.rawQuery("SELECT * FROM " + FoodDBHelper.TABLE_NAME +";", null);

                String result = "";
                while (cursor.moveToNext()) {
                    int id = cursor.getInt(cursor.getColumnIndex(FoodDBHelper.COL_ID));
                    String food = cursor.getString(cursor.getColumnIndex(FoodDBHelper.COL_FOOD));
                    String nation = cursor.getString(cursor.getColumnIndex(FoodDBHelper.COL_NATION));
                    result += id + ":" + food + "-" + nation + "\n";
                }
                cursor.close();
                tvDisplay.setText(result);
                break;
            case R.id.btnAdd:
                Intent intentAdd = new Intent(this, AddActivity.class);
                startActivity(intentAdd);
                break;
            case R.id.btnUpdate:
                Intent intentUpate = new Intent(this, UpdateActivity.class);
                startActivity(intentUpate);
                break;
            case R.id.btnRemove:
                Intent intentRemove = new Intent(this, RemoveActivity.class);
                startActivity(intentRemove);
                break;
        }

        myDbHelper.close();
    }

}
