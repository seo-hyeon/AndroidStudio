package ddwucom.mobile.test11.exam01;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter adapter;
    ArrayList<Food> foodList;
    FoodManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = new FoodManager();
        listView = findViewById(R.id.listView);

        // DataManager 적용해 볼 것
        foodList = manager.getFoodList();

        // Food 객체의 toString() 메소드가 호출되어 하나의 문자열로 처리됨
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, foodList);

        listView.setAdapter(adapter);

        // listView 롱클릭 이벤트 추가
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int index = position;
                String food = foodList.get(position).getFood();

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("음식 삭제");
                builder.setMessage(food + "을(를) 삭제하시겠습니까?");
                builder.setIcon(R.drawable.ic_launcher_foreground);
                builder.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //원본 데이터 삭제
                        manager.deleteFood(index);
                        //화면에 반영
                        adapter.notifyDataSetChanged();;
                    }
                });
                builder.setNegativeButton("취소", null);
                builder.setCancelable(false);
                builder.show();

                return true;
            }
        });
    }

    public void onClick(View v) {
        final ConstraintLayout addfood = (ConstraintLayout) View.inflate(this, R.layout.addfood, null);


        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("음식 추가");
        builder.setView(addfood);

        builder.setPositiveButton("추가", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //원본 데이터 추가
                EditText foodName = addfood.findViewById(R.id.foodName);
                EditText nationName = addfood.findViewById(R.id.nationName);

                String food = foodName.getText() + "";
                String nation = nationName.getText() + "";

                manager.addFood(food, nation);

                //화면에 반영
                adapter.notifyDataSetChanged();;
            }
        });
        builder.setNegativeButton("취소", null);
        //builder.setCancelable(false);
        builder.show();
    }

}
