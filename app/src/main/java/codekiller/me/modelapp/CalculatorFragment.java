package codekiller.me.modelapp;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import codekiller.me.modelapp.Calculator.BusinessFragment;
import codekiller.me.modelapp.Calculator.CalculatorPagerAdapter;
import codekiller.me.modelapp.Calculator.CombineFragment;
import codekiller.me.modelapp.Calculator.FundFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class CalculatorFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private CalculatorPagerAdapter pagerAdapter;
    private List<Fragment> fragments;

    public CalculatorFragment() {
        // Required empty public constructor
    }

    public static CalculatorFragment getInstance(){
        return new CalculatorFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculator, container, false);

        initFragments();

        initViews(view);

        return view;
    }

    private void initFragments() {
        fragments = new ArrayList<>();
        fragments.add(BusinessFragment.getInstance());
        fragments.add(FundFragment.getInstance());
        fragments.add(CombineFragment.getInstance());
    }

    private void initViews(View view) {
        tabLayout = view.findViewById(R.id.tab);
        viewPager = view.findViewById(R.id.view_pager);
        pagerAdapter = new CalculatorPagerAdapter(getActivity().getSupportFragmentManager(), getContext(), fragments);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

}
