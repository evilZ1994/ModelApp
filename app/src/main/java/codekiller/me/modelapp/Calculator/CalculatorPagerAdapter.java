package codekiller.me.modelapp.Calculator;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import codekiller.me.modelapp.R;

/**
 * Created by Lollipop on 2018/3/15.
 */

public class CalculatorPagerAdapter extends FragmentPagerAdapter {
    private String[] titles;
    private Context context;
    private List<Fragment> fragments;

    public CalculatorPagerAdapter(FragmentManager fm, Context context, List<Fragment> fragments) {
        super(fm);
        this.context = context;
        this.fragments = fragments;
        titles = context.getResources().getStringArray(R.array.calculator_tab_title);
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
