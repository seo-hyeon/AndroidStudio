package ddwucom.mobile.exam01;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class RemoveActivity extends AppCompatActivity {
    FoodDBHelper myDbHelper;
    EditText etRemoveFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove);

        etRemoveFood = findViewById(R.id.etRemoveFood);
        myDbHelper = new FoodDBHelper(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRemoveFood:
                SQLiteDatabase myDB = myDbHelper.getWritableDatabase();
                myDB.delete(myDbHelper.TABLE_NAME, "food=" + etRemoveFood.getText().toString(), null);
                myDbHelper.close();

                break;
        }
        finish();
    }
}


