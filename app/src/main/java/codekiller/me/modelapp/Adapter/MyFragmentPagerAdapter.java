package codekiller.me.modelapp.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.List;

/**
 * Created by Lollipop on 2018/3/15.
 */

public class MyFragmentPagerAdapter extends android.support.v4.app.FragmentStatePagerAdapter {
    private String[] titles;
    private List<Fragment> fragments;

    public MyFragmentPagerAdapter(FragmentManager fm, String[] titles, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
