package net.archeryc.demo.recyclerview;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.archeryc.demo.R;

/**
 * Created by yc on 2018/1/17.
 */

public class MyAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private FragmentManager fragmentManager;


    public MyAdapter(Context mContext,FragmentManager fragmentManager) {
        this.mContext = mContext;
        this.fragmentManager=fragmentManager;
    }

    @Override
    public int getItemViewType(int position) {

        if (position < 14) {
            return 1;
        } else {
            return 2;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 2) {
            return new ViewPagerHolder(LayoutInflater.from(mContext).inflate(R.layout.item_viewpager, parent, false));
        } else {
            return new TxtViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_txt, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TxtViewHolder){
            TxtViewHolder txtViewHolder= (TxtViewHolder) holder;
            txtViewHolder.textView.setText("position---->"+position);
        }
    }

    @Override
    public int getItemCount() {
        return 15;
    }

    public class TxtViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public TxtViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }

    public class ViewPagerHolder extends RecyclerView.ViewHolder {
        ViewPager viewPager;

        public ViewPagerHolder(View itemView) {
            super(itemView);
            viewPager = itemView.findViewById(R.id.viewpager);
            viewPager.setAdapter(new MyPagerAdapter(fragmentManager,mContext));
        }
    }
}
