package net.archeryc.demo.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import net.archeryc.demo.R;

public class NestedActivity extends AppCompatActivity {

    private MyRecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(new MyAdapter(this,getSupportFragmentManager()));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
