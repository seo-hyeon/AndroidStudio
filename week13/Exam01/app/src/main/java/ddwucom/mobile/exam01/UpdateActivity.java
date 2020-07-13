package ddwucom.mobile.exam01;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {

    FoodDBHelper myDbHelper;
    EditText etUpdateId;
    EditText etUpdateFood;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        etUpdateId = findViewById(R.id.etUpdateId);
        etUpdateFood = findViewById(R.id.etUpdateFood);
        myDbHelper = new FoodDBHelper(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAddFood:
                SQLiteDatabase myDB = myDbHelper.getWritableDatabase();
                ContentValues row = new ContentValues();

                row.put(myDbHelper.COL_FOOD, etUpdateFood.getText().toString());
                String whereClause = "id=" + etUpdateId.getText().toString();

                myDB.update(myDbHelper.TABLE_NAME, row, whereClause, null);
                myDbHelper.close();

                break;
        }
        finish();
    }
}

