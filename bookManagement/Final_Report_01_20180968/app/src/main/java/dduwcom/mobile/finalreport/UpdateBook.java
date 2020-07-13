package dduwcom.mobile.finalreport;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateBook extends AppCompatActivity {
    final static String TAG = "UpdateBook";

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
    Button buttonAdd;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addbook);

        myDBHelper = new BookHelper(this);

        intent = getIntent();

        editTitle = findViewById(R.id.editTitle);
        editTitle.setText(intent.getStringExtra("title"));
        editAuthor = findViewById(R.id.editAuthor);
        editAuthor.setText(intent.getStringExtra("author"));
        editSummary = findViewById(R.id.editSummary);
        editSummary.setText(intent.getStringExtra("summary"));
        editContent = findViewById(R.id.editContent);
        editContent.setText(intent.getStringExtra("content"));
        editExperience = findViewById(R.id.editExperience);
        editExperience.setText(intent.getStringExtra("experience"));
        radioColor = findViewById(R.id.radioColor);

        radioGrade = findViewById(R.id.radioGrade);
        switch(intent.getStringExtra("color")) {
            case "pink":
                radioColor.check(R.id.pink);
                color = "pink";
                break;
            case "purple":
                radioColor.check(R.id.purple);
                color = "purple";
                break;
            case "sky":
                radioColor.check(R.id.sky);
                color = "sky";
                break;
            case "orange":
                radioColor.check(R.id.orange);
                color = "orange";
                break;
            case "red":
                radioColor.check(R.id.red);
                color = "red";
                break;
            case "green":
                radioColor.check(R.id.green);
                color = "green";
                break;
            case "gray":
                radioColor.check(R.id.gray);
                color = "gray";
                break;
            case "blue":
                radioColor.check(R.id.blue);
                color = "blue";
                break;
            case "yellow":
                radioColor.check(R.id.yellow);
                color = "yellow";
                break;
            case "empty":
                radioColor.check(R.id.empty);
                color = "empty";
                break;
        }

        buttonAdd = findViewById(R.id.add);
        buttonAdd.setText("수정");

        switch (intent.getIntExtra("grade", 0)){
            case 0:
                radioGrade.check(R.id.g0);
                grade = 0;
                break;
            case 1:
                radioGrade.check(R.id.g1);
                grade = 1;
                break;
            case 2:
                radioGrade.check(R.id.g2);
                grade = 2;
                break;
            case 3:
                radioGrade.check(R.id.g3);
                grade = 3;
                break;
            case 4:
                radioGrade.check(R.id.g4);
                grade = 4;
                break;
            case 5:
                radioGrade.check(R.id.g5);
                grade = 5;
                break;
            default:
                radioGrade.check(R.id.g0);
                break;
        }

        imageview = findViewById(R.id.addImage);
        isis = intent.getStringExtra("isIm");
        if (isis.equals("TRUE")) {
            selectedImageUri = Uri.parse(intent.getStringExtra("image"));
            imageview.setImageURI(selectedImageUri);
        }
        imageview.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, GET_GALLERY_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            selectedImageUri = data.getData();
            imageview.setImageURI(selectedImageUri);
            isis = "TRUE";

        }
    }

    public void onClick(View v) {
        if (v.getId() == R.id.add) {

            switch(radioColor.getCheckedRadioButtonId()) {
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

            switch(radioGrade.getCheckedRadioButtonId()) {
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

            String whereClause = "_id=" + intent.getLongExtra("_id", 0);
            myDB.update(BookHelper.TABLE_NAME, row, whereClause, null);
            myDBHelper.close();
        }
        finish();
    }
}
