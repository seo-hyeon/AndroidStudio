package ddwucom.mobile.exam01;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {
    FoodDBHelper myDbHelper;
    EditText etAddFood;
    EditText etAddNation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etAddFood = findViewById(R.id.etAddFood);
        etAddNation = findViewById(R.id.etUpdateId);
        myDbHelper = new FoodDBHelper(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAddFood:
                SQLiteDatabase myDB = myDbHelper.getWritableDatabase();
                ContentValues row = new ContentValues();

                row.put(myDbHelper.COL_FOOD, etAddFood.getText().toString());
                row.put(myDbHelper.COL_NATION, etAddNation.getText().toString());

                myDB.insert(FoodDBHelper.TABLE_NAME, null, row);
                myDbHelper.close();

                break;
        }
        finish();
    }
}