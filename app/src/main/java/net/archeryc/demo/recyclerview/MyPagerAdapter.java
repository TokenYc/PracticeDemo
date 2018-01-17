package net.archeryc.demo.recyclerview;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by yc on 2018/1/17.
 */

public class MyPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private MyFragment firstFragment;
    private MyFragment secondFragment;

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
        firstFragment = new MyFragment();
        secondFragment = new MyFragment();
    }

    public MyPagerAdapter(FragmentManager fm, Context mContext) {
        this(fm);
        this.mContext = mContext;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return firstFragment;
        } else {
            return secondFragment;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
