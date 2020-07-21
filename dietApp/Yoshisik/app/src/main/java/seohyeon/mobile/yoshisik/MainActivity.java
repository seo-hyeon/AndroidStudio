package seohyeon.mobile.yoshisik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<DietData> myDataList;
    private DietAdapter myAdapter;
    private ListView listView;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        myDataList = new ArrayList<DietData>();
        myDataList.add(new DietData("고구마 1개, 삶은 달걀 1개, 귤 2개",
                "돈코츠 라멘, 담터 꿀 유자차", "풀무원 소고기 버섯비빔밥, 파콩나물국, 닭볶음탕",
                242.3, 59.8, 39.1, 2396, 33.3, 1515));
        myDataList.add(new DietData("삼림효모통밀식빵, 생강차, 셀러드",
                "엽기 떡볶이, 순대", "쌀밥, 오징어채 볶음, 동원홈푸드닭볶음탕, 콩나물무침, 귤 2개",
                250.62, 62.55, 34.07, 2248, 40, 1539));
        myDataList.add(new DietData("오트밀 그레놀라, 서울저지망우유, 꿀차, 고구마 1개",
                "롯데리아 클래식 치즈버거", "쌀밥, 콩나물 무침, 도라지 나물, 두부, 생강차",
                200.95, 57.77, 37.35, 1511, 52.26, 1751));
        myDataList.add(new DietData("고구마 1개, 삶은 달걀 1개, 귤 2개, 풀무원다논 greek요거트, 샐러드",
                "돈코츠 라멘, 이디야 유자차", "지지고 나이스 라이스, 오징어채 볶음, 파콩나물국, 동원홈푸드닭볶음탕",
                288.42, 77.25, 25.4, 2675, 67.2, 1535));
        myDataList.add(new DietData("삼림효모통밀식빵, 계란후라이, 토마토 캐찹, 서울 저지방우유, 샐러드, 키위2개",
                "엽기 떡붂이, 순대", "쌀밥, 오징어채 볶음, 닭볶음탕, 콩나물 무침, 귤 2개",
                270.22, 69.81, 42.3, 2442, 48, 1523));
        myDataList.add(new DietData("오트밀 그레놀라, 서울 저지방 우유, 꿀차",
                "뽀모로도 토마토 파스타, 토마토 2개, 풀무원 올바른 브리또", "쌀밥, 붕어빵 3개, 도라지 나물, 두부국",
                265.05, 60.76, 46.01, 2143, 78.45, 1695));

        myAdapter = new DietAdapter(this, R.layout.diet, myDataList);
        listView = (ListView) findViewById(R.id.dietView);
        listView.setAdapter(myAdapter);
    }
}
