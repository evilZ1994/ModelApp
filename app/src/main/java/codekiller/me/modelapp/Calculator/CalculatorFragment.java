package codekiller.me.modelapp.Calculator;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import codekiller.me.modelapp.Calculator.BusinessLoan.BusinessContract;
import codekiller.me.modelapp.Calculator.BusinessLoan.BusinessFragment;
import codekiller.me.modelapp.Calculator.BusinessLoan.BusinessPresenter;
import codekiller.me.modelapp.Adapter.MyFragmentPagerAdapter;
import codekiller.me.modelapp.Calculator.CombineLoan.CombineContract;
import codekiller.me.modelapp.Calculator.CombineLoan.CombineFragment;
import codekiller.me.modelapp.Calculator.CombineLoan.CombinePresenter;
import codekiller.me.modelapp.Calculator.FundLoan.FundContract;
import codekiller.me.modelapp.Calculator.FundLoan.FundFragment;
import codekiller.me.modelapp.Calculator.FundLoan.FundPresenter;
import codekiller.me.modelapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class CalculatorFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private MyFragmentPagerAdapter pagerAdapter;
    private List<Fragment> fragments;

    public CalculatorFragment() {
        // Required empty public constructor
    }

    public static CalculatorFragment newInstance(){
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
        BusinessFragment businessFragment = BusinessFragment.newInstance();
        BusinessContract.Presenter businessPresenter = new BusinessPresenter(businessFragment);
        fragments.add(businessFragment);

        FundFragment fundFragment = FundFragment.newInstance();
        FundContract.Presenter fundPresenter = new FundPresenter(fundFragment);
        fragments.add(fundFragment);

        CombineFragment combineFragment = CombineFragment.newInstance();
        CombineContract.Presenter combinePresenter = new CombinePresenter(combineFragment);
        fragments.add(combineFragment);
    }

    private void initViews(View view) {
        tabLayout = view.findViewById(R.id.tab);
        viewPager = view.findViewById(R.id.view_pager);
        pagerAdapter = new MyFragmentPagerAdapter(getActivity().getSupportFragmentManager(), getContext().getResources().getStringArray(R.array.calculator_tab_title), fragments);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

}
