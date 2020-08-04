package dduwcom.mobile.finalreport;

import android.Manifest;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.loader.content.CursorLoader;

public class AddBook extends AppCompatActivity {
    final static String TAG = "AddBook";

    private final int GET_GALLERY_IMAGE = 200;
    private ImageView imageview;
    private Uri selectedImageUri;
    String isis = "FALSE";
    String color = "empty";
    int grade = 0;
    BookHelper myDBHelper;
    EditText editTitle;
    EditText editAuthor;
    EditText editSummary;
    EditText editContent;
    EditText editExperience;
    RadioGroup radioColor;
    RadioGroup radioGrade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addbook);

        myDBHelper = new BookHelper(this);

        imageview = (ImageView) findViewById(R.id.addImage);
        //권한 요청
        int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (permission == PackageManager.PERMISSION_DENIED) {

        } else {
            imageview.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                    startActivityForResult(intent, GET_GALLERY_IMAGE);
                }
            });
        }

        editTitle = findViewById(R.id.editTitle);
        editAuthor = findViewById(R.id.editAuthor);
        editSummary = findViewById(R.id.editSummary);
        editContent = findViewById(R.id.editContent);
        editExperience = findViewById(R.id.editExperience);
        radioColor = findViewById(R.id.radioColor);
        radioGrade = findViewById(R.id.radioGrade);

        radioColor.check(R.id.empty);
        radioGrade.check(R.id.g0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            try {
                selectedImageUri = data.getData();
                imageview.setImageURI(selectedImageUri);
                isis = "TRUE";
            } catch (Exception e) {
                Toast.makeText(getBaseContext(), "사진 불러오기 실패", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }

    public void onClick(View v) {
        if (v.getId() == R.id.add) {

            switch (radioColor.getCheckedRadioButtonId()) {
                case R.id.pink:
                    color = "pink";
                    break;
                case R.id.purple:
                    color = "purple";
                    break;
                case R.id.sky:
                    color = "sky";
                    break;
                case R.id.orange:
                    color = "orange";
                    break;
                case R.id.red:
                    color = "red";
                    break;
                case R.id.green:
                    color = "green";
                    break;
                case R.id.gray:
                    color = "gray";
                    break;
                case R.id.blue:
                    color = "blue";
                    break;
                case R.id.yellow:
                    color = "yellow";
                    break;
                default:
                    color = "empty";
                    break;
            }

            switch (radioGrade.getCheckedRadioButtonId()) {
                case R.id.g0:
                    grade = 0;
                    break;
                case R.id.g1:
                    grade = 1;
                    break;
                case R.id.g2:
                    grade = 2;
                    break;
                case R.id.g3:
                    grade = 3;
                    break;
                case R.id.g4:
                    grade = 4;
                    break;
                case R.id.g5:
                    grade = 5;
                    break;
            }

            SQLiteDatabase myDB = myDBHelper.getWritableDatabase();
            ContentValues row = new ContentValues();

            row.put(BookHelper.COL_ISIS, isis);
            row.put(BookHelper.COL_IMAGE, String.valueOf(selectedImageUri));
            row.put(BookHelper.COL_TITLE, editTitle.getText().toString());
            row.put(BookHelper.COL_AUTHOR, editAuthor.getText().toString());
            row.put(BookHelper.COL_SUMMARY, editSummary.getText().toString());
            row.put(BookHelper.COL_CONTENT, editContent.getText().toString());
            row.put(BookHelper.COL_EXPERIENCE, editExperience.getText().toString());
            row.put(BookHelper.COL_COLOR, color);
            row.put(BookHelper.COL_GRADE, grade);

            myDB.insert(BookHelper.TABLE_NAME, null, row);
            myDBHelper.close();

            Log.d(TAG, "\ngrade(addBook): " + grade + "  ImageUri(addBook): " + selectedImageUri + "\n Image: " + String.valueOf(selectedImageUri));
        }
        finish();
    }

}