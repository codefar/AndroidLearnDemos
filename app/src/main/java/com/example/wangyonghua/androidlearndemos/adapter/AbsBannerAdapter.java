package com.example.wangyonghua.androidlearndemos.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public abstract class AbsBannerAdapter<T> extends PagerAdapter {

    protected ViewPager mViewPager;
    protected List<T> mDatas;

    public AbsBannerAdapter(Context context, List<T> datas) {
        mDatas = datas;
    }

    @Override
    final public int getCount() {
        return mDatas.size() > 1 ? mDatas.size() + 2 : mDatas.size();
    }

    @Override
    final public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }

    @Override
    final public Object instantiateItem(ViewGroup container, int position) {
        if (position == 0)
            position = mDatas.size() - 1;
        else if (position == getCount() - 1)
            position = 0;
        else
            position = position - 1;
        View view = bindView(container, position);
        container.addView(view);
        return view;
    }

    protected abstract View bindView(ViewGroup container, int position);

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    final public void finishUpdate(ViewGroup container) {
        if (getCount() > 1) {
            int cur = mViewPager.getCurrentItem();
            if (cur == getCount() - 1) {
                mViewPager.setCurrentItem(1, false);
            }
            if (cur == 0) {
                mViewPager.setCurrentItem(getCount() - 2, false);
            }
        }
    }

    public void setViewPager(ViewPager mViewPager) {
        this.mViewPager = mViewPager;
        mViewPager.setCurrentItem(1);
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}