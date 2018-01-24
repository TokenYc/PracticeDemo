package net.archeryc.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.facebook.drawee.backends.pipeline.Fresco;

import net.archeryc.demo.download.DownloadActivity;
import net.archeryc.demo.jumpTest.WebviewActivity;
import net.archeryc.demo.recyclerview.NestedActivity;
import net.archeryc.demo.server.ServerActivity;
import net.archeryc.demo.viewpager.AutoViewPagerActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void jumpMatrix(View view) {
        startActivity(new Intent(this, MatrixActivity.class));
    }

    public void jumpViewPager(View view){
        startActivity(new Intent(this,AutoViewPagerActivity.class));
    }

    public void jumpNest(View view){
        startActivity(new Intent(this, NestedActivity.class));
    }

    public void jumpServer(View view){
        startActivity(new Intent(this, ServerActivity.class));
    }

    public void jumpDownload(View view){startActivity(new Intent(this, DownloadActivity.class));}

    public void jumpWebview(View view) {
        startActivity(new Intent(this, WebviewActivity.class));
    }
}
