package net.archeryc.demo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by yc on 2017/12/28.
 */

public class RotationView extends View{
    private Bitmap bitmap;
    private Paint paint;
    private Camera camera;

    private int degree=0;

    public RotationView(Context context) {
        super(context);
        init();
    }

    public RotationView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RotationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        bitmap= BitmapFactory.decodeResource(getResources(),R.mipmap.gold);
        paint=new Paint();
        camera=new Camera();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.save();

        camera.save();
        camera.rotateY(degree);
        canvas.translate(bitmap.getWidth()/2, bitmap.getHeight()/2);
        camera.applyToCanvas(canvas);
        canvas.translate(-bitmap.getWidth()/2, -bitmap.getHeight()/2);
        camera.restore();

        canvas.drawBitmap(bitmap,0,0,paint);
        canvas.restore();

        super.onDraw(canvas);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(bitmap.getWidth(),bitmap.getHeight());
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
        invalidate();
    }
}
