
package com.example.suwonsmartapp.androidexam.viewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.suwonsmartapp.androidexam.R;
import com.example.suwonsmartapp.androidexam.fragment.ColorFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by junsuk on 15. 9. 16..
 */
public class ScreenSlideActivity extends AppCompatActivity {
    private ViewPager mViewPager;

    private List<Fragment> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_screen_slide);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        // 짝퉁 데이터
        mList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mList.add(new ColorFragment());
        }

        ScreenSlidePagerAdapter adapter = new ScreenSlidePagerAdapter(getSupportFragmentManager(),
                mList);

        mViewPager.setAdapter(adapter);
    }

    // FragmentStatePagerAdapter : 메모리 관리가 필요할 때 사용
    private class ScreenSlidePagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> mmList;

        public ScreenSlidePagerAdapter(FragmentManager fm, List<Fragment> list) {
            super(fm);

            mmList = list;
        }

        @Override
        public Fragment getItem(int position) {
            return mmList.get(position);
        }

        @Override
        public int getCount() {
            return mmList.size();
        }
    }
}
