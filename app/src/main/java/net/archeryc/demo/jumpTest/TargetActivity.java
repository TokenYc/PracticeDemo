package net.archeryc.demo.jumpTest;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import net.archeryc.demo.R;

public class TargetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);
        Intent intent=getIntent();
        Uri uri=intent.getData();
        String uid=uri.getQueryParameter("uid");
        Toast.makeText(this, uid, Toast.LENGTH_SHORT).show();
    }
}
