package com.example.wangyonghua.androidlearndemos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.eftimoff.androipathview.PathView;

public class SvgActivity extends BaseBackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svg);
        final ViewPager viewpager = (ViewPager) findViewById(R.id.viewpager);
        final ScreenSlidePagerAdapter screenSlidePagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        viewpager.setAdapter(screenSlidePagerAdapter);
        viewpager.setPageTransformer(true, new CustomTransformer());
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new IssuesFragment();
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

    private class CustomTransformer implements ViewPager.PageTransformer {
        @Override
        public void transformPage(View page, float position) {
            if (position < -1 || position > 1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                page.setAlpha(0);
                return;
            }
            final float abs = 1 - Math.abs(position);
            final PathView pathView = (PathView) page.findViewById(R.id.pathView);
            pathView.setPercentage(abs);
        }
    }

    public static class IssuesFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View inflate = inflater.inflate(R.layout.fragment_issues, container, false);
            final PathView pathView = (PathView) inflate.findViewById(R.id.pathView);
            pathView.getPathAnimator().
                    delay(100).
                    duration(1500).
                    interpolator(new AccelerateDecelerateInterpolator()).
                    start();
            pathView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pathView.getPathAnimator().
                            delay(100).
                            duration(1500).
                            interpolator(new AccelerateDecelerateInterpolator()).
                            start();
                }
            });
            return inflate;
        }
    }
}
