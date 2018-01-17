package net.archeryc.demo.viewpager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.drawee.view.SimpleDraweeView;

import net.archeryc.demo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yc
 */
public class AutoViewPagerActivity extends AppCompatActivity {

    List<String> imgs=new ArrayList<>();

    AutoViewPager viewPager;

    AutoCircleIndicator circleIndicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_view_pager);
        viewPager=findViewById(R.id.viewpager);
        circleIndicator = findViewById(R.id.circleIndicator);
        imgs.add("http://qianfan-qianfanyun.qiniudn.com/_20160927100258_57e9d35256777.png");
        imgs.add("http://qianfan-qianfanyun.qiniudn.com/_20160922142057_57e3784946999.jpg");
        imgs.add("http://qianfan-qianfanyun.qiniudn.com/_20161027155423_5811b2af7346e.jpg");
        imgs.add("http://qianfan-qianfanyun.qiniudn.com/_20171018140129_59e6ee39b13f0.png");
        AutoPagerAdapter<String> adapter=new AutoPagerAdapter<String>(this,imgs) {
            @Override
            public void loadImage(SimpleDraweeView view, int position, String s) {
                view.setImageURI(s);
            }
        };
        adapter.init(viewPager,adapter);
        adapter.setCurrentPosition(2);
        circleIndicator.setViewPager(viewPager);
    }
}
