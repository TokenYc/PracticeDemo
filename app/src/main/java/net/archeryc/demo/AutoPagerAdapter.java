package net.archeryc.demo;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caik on 2016/10/11.
 */

public abstract class AutoPagerAdapter<T> extends PagerAdapter implements ViewPager.OnPageChangeListener {

    private List<T> data = new ArrayList<>();

    private Context mContext;
    private AutoViewPager mView;

    private OnAutoViewPagerItemClickListener listener;

    public AutoPagerAdapter(Context context, List<T> data) {
        this.mContext = context;
        this.data = data;
    }

    public AutoPagerAdapter(Context context, List<T> data, OnAutoViewPagerItemClickListener listener) {
        this.mContext = context;
        this.data = data;
        this.listener = listener;
    }

    public void init(AutoViewPager viewPager, AutoPagerAdapter adapter) {
        mView = viewPager;
        mView.setAdapter(this);
        mView.addOnPageChangeListener(this);

        if (data == null || data.size() == 0) {
            return;
        }
        //设置初始为中间，这样一开始就能够往左滑动了
        int position = Integer.MAX_VALUE / 2 - (Integer.MAX_VALUE / 2) % getRealCount();
        mView.setCurrentItem(position);
        if (data.size() > 1) {
            mView.start();
        }
    }

    public void setCurrentPosition(int realPosition) {
        if (realPosition < data.size()) {
            int position = Integer.MAX_VALUE / 2 - (Integer.MAX_VALUE / 2) % getRealCount();
            mView.setCurrentItem(position + realPosition);
            if (data.size() > 1) {
                mView.start();
            }
        }
    }

    public void setListener(OnAutoViewPagerItemClickListener listener) {
        this.listener = listener;
    }

    public void add(T t) {
        data.add(t);
        notifyDataSetChanged();
    }

    public int getRealCurrentItem() {
        return mView.getCurrentItem() % getRealCount();
    }

    @Override
    public int getCount() {
        return data == null ? 0 : Integer.MAX_VALUE;
    }

    public int getRealCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ImageView) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(mContext);
        simpleDraweeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(position % getRealCount(), data.get(position % getRealCount()));
                }
            }
        });

        loadImage(simpleDraweeView, position, data.get(position % getRealCount()));
        container.addView(simpleDraweeView);

        return simpleDraweeView;
    }

    public abstract void loadImage(SimpleDraweeView view, int position, T t);

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    public interface OnAutoViewPagerItemClickListener<T> {
        void onItemClick(int position, T t);
    }
}
