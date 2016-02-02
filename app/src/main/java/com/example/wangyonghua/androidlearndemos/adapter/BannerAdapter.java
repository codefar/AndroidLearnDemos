package com.example.wangyonghua.androidlearndemos.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.wangyonghua.androidlearndemos.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BannerAdapter extends AbsBannerAdapter<String> {

    LayoutInflater mInflater;
    private ImageLoader mImageLoader;
    DisplayImageOptions mDisplayImageOptions;
    private BlockingQueue<View> mViewCache;

    public BannerAdapter(Context context, List<String> datas) {
        super(context, datas);
        mDisplayImageOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)//设置下载的图片是否缓存在SD卡中
                .considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
                .imageScaleType(ImageScaleType.NONE)//设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型
                .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
                .displayer(new SimpleBitmapDisplayer())//是否图片加载好后渐入的动画时间
                .build();//构建完成
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewCache = new ArrayBlockingQueue<>(2);
    }

    @Override
    protected View bindView(ViewGroup container, int position) {
        View view = null;
        if (!mViewCache.isEmpty()) {
            view = mViewCache.poll();
        }
        if (view == null) {
            Log.i("DD", "cache not hint postrion="+position + " " + mViewCache.size() );
            view = mInflater.inflate(R.layout.viewpager_item, container, false);
        } else {
            Log.i("DD", "cache hint postrion="+position + " " + mViewCache.size() );
            mViewCache.remove(view);
        }
        ImageView imageView = (ImageView) view.findViewById(R.id.image);
        ImageLoader.getInstance().displayImage(mDatas.get(position) ,imageView, mDisplayImageOptions);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
        Log.i("DD", "destroyItem postrion="+position + " " + mViewCache.size() );
        mViewCache.offer((View) object);
    }

}