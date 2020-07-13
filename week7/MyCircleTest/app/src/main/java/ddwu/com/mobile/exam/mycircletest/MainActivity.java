package ddwu.com.mobile.exam.mycircletest;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private MyCircle myCircle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Custom View 객체*/
        myCircle = findViewById(R.id.myCircle);
        registerForContextMenu(myCircle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.manu1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.bigger:
                myCircle.bigR();
                break;
            case R.id.smaller:
                myCircle.smallR();
                break;
        }

        myCircle.invalidate();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu,
                                    View v,
                                    ContextMenu.ContextMenuInfo menuInfo)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.manu2, menu);
    }

    public boolean onContextItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.red:
                myCircle.setMyPaint(Color.RED);
                myCircle.invalidate();
                return true;
            case R.id.blue:
                myCircle.setMyPaint(Color.BLUE);
                myCircle.invalidate();
                return true;
            case R.id.green:
                myCircle.setMyPaint(Color.GREEN);
                myCircle.invalidate();
                return true;
        }

        return super.onContextItemSelected(item);
    }
}
