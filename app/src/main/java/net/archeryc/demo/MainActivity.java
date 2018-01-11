package net.archeryc.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.facebook.drawee.backends.pipeline.Fresco;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fresco.initialize(this);
    }

    public void jumpMatrix(View view) {
        startActivity(new Intent(this, MatrixActivity.class));
    }

    public void jumpViewPager(View view){
        startActivity(new Intent(this,AutoViewPagerActivity.class));
    }
}
