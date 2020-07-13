package ddwucom.mobile.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CustomView extends View {

    int circleX;
    int circleY;

    public CustomView(Context context) {
        super(context);
        init();
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void init() {
        circleX = 100;
        circleY = 100;
    }

    public int getCircleX() {
        return circleX;
    }

    public int getCircleY() {
        return circleY;
    }

    public void setCircleX(int circleX) {
        this.circleX = circleX;
    }

    public void setCircleY(int circleY) {
        this.circleY = circleY;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.YELLOW);
        Paint pnt = new Paint();
        pnt.setColor(Color.BLUE);
        canvas.drawCircle(circleX, circleY, 80, pnt);
    }
}
