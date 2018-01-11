package net.archeryc.demo;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MatrixActivity extends AppCompatActivity {

    RotationView rotationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix);
        rotationView=findViewById(R.id.imageView);
    }

    public void doAnimate(View view){
        ObjectAnimator mGoldAnimator = ObjectAnimator.ofInt(rotationView, "Degree", 0, 360)
                .setDuration(1000);
        mGoldAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mGoldAnimator.start();
    }


}
