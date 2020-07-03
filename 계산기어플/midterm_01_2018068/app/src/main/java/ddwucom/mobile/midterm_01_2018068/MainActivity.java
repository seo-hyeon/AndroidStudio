/* 과제명: 중간고사 과제
*  분반: 01
*  학번: 20180968
*  제출일: 2020-05-29
*/
package ddwucom.mobile.midterm_01_2018068;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int num = 0;
    double num2 = 0;
    int op = 0;
    int error = 0;

    private Button plus;
    private Button sub;
    private Button mul;
    private Button div;
    private Button clear;
    private Button result;
    private Button power;
    private Button s;
    private Button sin;
    private EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        plus = (Button)findViewById(R.id.b_plus);
        sub = (Button)findViewById(R.id.b_sub);
        mul = (Button)findViewById(R.id.b_mul);
        div = (Button)findViewById(R.id.b_div);
        clear = (Button)findViewById(R.id.b_del);
        result = (Button)findViewById(R.id.b_result);
        power = (Button)findViewById(R.id.b_power);
        s = (Button)findViewById(R.id.b_s);
        sin = (Button)findViewById(R.id.b_sin);
        text = findViewById(R.id.t);

        plus.setOnClickListener(new Button.OnClickListener(){
                @Override
                public void onClick(View v) {
                    text.setText("");
                    text.setHint("+");

                    if (error == 1) {error(1);}
                    else {
                        if (num2 != 0) { c();}
                        else { num2 = num;}
                        op = 1;
                        num = 0;
                        error = 1;
                    }
                }
        });

        sub.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                text.setText("");
                text.setHint("-");

                if (error == 1) {error(1);}
                else {
                    if (num2 != 0) { c(); }
                    else { num2 = num; }
                    op = 2;
                    num = 0;
                    error = 1;
                }
            }
        });

        mul.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                text.setText("");
                text.setHint("*");

                if (error == 1) {error(1);}
                else {
                    if (num2 != 0) { c(); }
                    else { num2 = num; }
                    op = 3;
                    num = 0;
                    error = 1;
                }
            }
        });

        div.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                text.setText("");
                text.setHint("/");

                if (error == 1) {error(1);}
                else {
                    if (num2 != 0) { c(); }
                    else { num2 = num; }
                    op = 4;
                    num = 0;
                    error = 1;
                }
            }
        });

        power.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                text.setText("");
                text.setHint("power");

                if (error == 1) {error(1);}
                else {
                    if (num2 != 0) { c(); }
                    else { num2 = num; }
                    op = 5;
                    num = 0;
                    error = 1;
                }
            }
        });

        s.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                text.setText("");
                text.setHint("ROOT");

                if (error == 1) {error(1);}
                else {
                    if (num2 != 0) { c(); }
                    else { num2 = num; }
                    op = 6;
                    num = 0;
                    error = 4;
                }
            }
        });

        sin.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                text.setText("");
                text.setHint("SIN");

                if (error == 1) {error(1);}
                else {
                    if (num2 != 0) { c(); }
                    else { num2 = num; }
                    op = 7;
                    num = 0;
                    error = 4;
                }
            }
        });

        clear.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                text.setText("");
                text.setHint("");
                num = 0;
                num2 = 0;
                op = 0;
                error = 1;
            }
        });

        result.setOnClickListener(resultListener);
    }

    View.OnClickListener resultListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            text.setHint("=");
            if (error == 1 || error == 0) {error(1);}
            else {
                c();

                text.setText(String.valueOf(num2));

                num = 0;
                num2 = 0;
                op = 0;
                error = 1;
            }
        }
    };

    public void c ()
    {
        if (op == 1)
            num2 = num2 + num;
        else if (op == 2)
            num2 = num2 - num;
        else if (op == 3)
            num2 = num2 * num;
        else if (op == 4)
            num2 = num2 / num;
        else if (op == 5)
            num2 = Math.pow(num2, num);
        else if (op == 6)
            num2 = Math.sqrt(num2);
        else if (op == 7)
            num2 = Math.sin(Math.toRadians(num2));
        if(num2 == Double.POSITIVE_INFINITY) {error(2);}
    }

    public void calNum(View view) {
        Button v = (Button) view;

        if (error == 4) {error(1);}
        else if (num != num * 10 / 10) {error(3);}
        else {
            num = num * 10 + Integer.parseInt(v.getText().toString());

            text.setText(String.valueOf(num));

            if (error == 1)
                error = 3;
        }
    }

    public void error(int i) {
        if (i == 1)
        {
            Toast myToast = Toast.makeText(this.getApplicationContext(),"연산 순서 오류", Toast.LENGTH_SHORT);
            myToast.show();
        }
        else if (i == 2)
        {
            Toast myToast = Toast.makeText(this.getApplicationContext(),"연산 오류", Toast.LENGTH_SHORT);
            myToast.show();
        }
        else
        {
            Toast myToast = Toast.makeText(this.getApplicationContext(),"오버플로우", Toast.LENGTH_SHORT);
            myToast.show();
        }

        text.setText("");
        text.setHint("");
        num = 0;
        num2 = 0;
        op = 0;
        error = 0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        LinearLayout layout = findViewById(R.id.line_eng);
        item.setChecked(true);

        switch(item.getItemId())
        {
            case R.id.basic:
                layout.setVisibility(View.INVISIBLE);
                break;
            case R.id.engineer:
                layout.setVisibility(View.VISIBLE);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
