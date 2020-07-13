package ddwucom.mobile.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) {
        TextView etDisplay = findViewById(R.id.etDisplay);
        String text = etDisplay.getText().toString();

        switch (v.getId()) {
            case R.id.btn_1:
                text = text + "1";

                etDisplay.setText(text);
                break;
            case R.id.btn_2:
                text = text + "2";

                etDisplay.setText(text);
                break;
            case R.id.btn_plus:
                num = Integer.parseInt(text);
                text = "";

                etDisplay.setText(text);
                break;
            case R.id.btn_equal:
                int result = num + Integer.parseInt(text);
                etDisplay.setText(String.valueOf(result));
                break;
        }

    }
}
