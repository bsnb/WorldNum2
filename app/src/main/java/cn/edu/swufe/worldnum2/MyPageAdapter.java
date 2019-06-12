package cn.edu.swufe.worldnum2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

public class MyPageAdapter extends FragmentStatePagerAdapter {
    /**
     *  FragmentPagerAdapter:FragmentPagerAdapter适合使用在固定的数量较少的场景,最多可以保留3个
     *  FragmentStatePagerAdapter:
     * @param fm
     */
    private List<Fragment> fragments;

    public MyPageAdapter(FragmentManager fm) {
        super(fm);
    }
    public MyPageAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.fragments = list;
    }


    @Override
    public Fragment getItem(int position) {
        return this.fragments.get(position);
    }

    @Override
    public int getCount() {
        return this.fragments.size();
    }

}