package ddwucom.mobile.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private CustomView myView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myView = findViewById(R.id.myView);
    }

    public void onClick(View v)
    {
        switch (v.getId()) {
            case R.id.button:
                Random random = new Random();

                int x = random.nextInt(myView.getWidth());
                int y = random.nextInt(myView.getHeight());

                myView.setCircleX(x);
                myView.setCircleY(y);
                myView.invalidate();
                break;
        }
    }
}
