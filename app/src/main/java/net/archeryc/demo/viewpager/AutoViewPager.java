package net.archeryc.demo.viewpager;

import android.content.Context;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by yc on 2018/1/11.
 */

public class AutoViewPager extends ViewPager {
    private static final String TAG = "AutoViewPager";

    private int currentItem;

    private AutoPagerAdapter mAdapter;
    private Timer mTimer;
    private AutoTask mTask;

    public AutoViewPager(Context context) {
        super(context);
        initListener();
    }

    public AutoViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        initListener();
    }

    public void init(AutoViewPager viewPager, AutoPagerAdapter adapter) {
        adapter.init(viewPager, adapter);
    }

    private void initListener(){
        addOnAttachStateChangeListener(new OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {
                start();
            }

            @Override
            public void onViewDetachedFromWindow(View v) {
                onStop();
            }
        });
    }

    @Override
    public void setAdapter(PagerAdapter adapter) {
        super.setAdapter(adapter);
        if (adapter instanceof AutoPagerAdapter){
            this.mAdapter= (AutoPagerAdapter) adapter;
        }
    }

    public void start() {
        //先停止
        onStop();
        if (mAdapter!=null){
            if (mAdapter.getRealCount()<=1){
                return;
            }
        }
        if (mTimer == null) {
            mTimer = new Timer();
        }
        mTimer.schedule(new AutoTask(), 3000, 3000);

    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            currentItem = getCurrentItem();
            if (currentItem == getAdapter().getCount() - 1) {
                currentItem = 0;
            } else {
                currentItem++;
            }
            setCurrentItem(currentItem);
        }
    };

    private AutoHandler mHandler = new AutoHandler();


    private class AutoTask extends TimerTask {

        @Override
        public void run() {
            mHandler.post(runnable);
        }
    }

    private final static class AutoHandler extends android.os.Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    }




    public AutoPagerAdapter getAutoAdapter(){
        return mAdapter;
    }

    public void onStop() {
        //先取消定时器
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }

    public void onDestroy() {
        onStop();
    }

    public void onResume() {
        start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                onStop();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                onResume();
                break;
            default:
                break;
        }
        return super.onTouchEvent(ev);
    }

}
