package ddwucom.mobile.a20180968;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void one(View v)
    {
        EditText result = (EditText)findViewById(R.id.result);
        String text = result.getText().toString();
        text = text + "1";

        result.setText(text);
    }

    public void two(View v)
    {
        EditText result = (EditText)findViewById(R.id.result);
        String text = result.getText().toString();
        text = text + "2";

        result.setText(text);
    }

    public void three(View v)
    {
        EditText result = (EditText)findViewById(R.id.result);
        String text = result.getText().toString();
        text = text + "3";

        result.setText(text);
    }

    public void clear(View v)
    {
        EditText result = (EditText)findViewById(R.id.result);
        result.setText("");
    }
}
