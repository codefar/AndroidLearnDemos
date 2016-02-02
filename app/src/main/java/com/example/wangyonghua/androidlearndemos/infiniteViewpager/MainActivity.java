package com.example.wangyonghua.androidlearndemos.infiniteViewpager;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;

import com.example.wangyonghua.androidlearndemos.BaseBackActivity;
import com.example.wangyonghua.androidlearndemos.R;
import com.example.wangyonghua.androidlearndemos.adapter.BannerAdapter;

import java.util.Arrays;


public class MainActivity extends BaseBackActivity {

    private static final String TAG = "MainActivity";

    private ViewPager mBanner;
    private BannerAdapter mBannerAdapter;
    private boolean mIsUserTouched = false;

    private int[] mImagesSrc = {
            R.mipmap.jy1,
            R.mipmap.jy2,
            R.mipmap.jy3,
            R.mipmap.jy4,
            R.mipmap.jy5
    };

    private String[] mImages = {
            "http://www.baidu.com/img/baidu_jgylogo3.gif",
            "http://img2.3lian.com/2014/f5/158/d/86.jpg",
            "http://images.99pet.com/InfoImages/wm600_450/1d770941f8d44c6e85ba4c0eb736ef69.jpg",
            "http://images.99pet.com/InfoImages/wm600_450/ef48d0d8e8f64172a28b9451fc5a941d.jpg"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        initView();
    }



    private void initView() {
        mBanner = (ViewPager) findViewById(R.id.banner);
        mBanner.setPageMargin(40);
        mBannerAdapter = new BannerAdapter(this, Arrays.asList(mImages));
        mBanner.setAdapter(mBannerAdapter);
        mBannerAdapter.setViewPager(mBanner);
        mBanner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                if (action == MotionEvent.ACTION_DOWN
                        || action == MotionEvent.ACTION_MOVE) {
                    mIsUserTouched = true;
                } else if (action == MotionEvent.ACTION_UP) {
                    mIsUserTouched = false;
                }
                return false;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}