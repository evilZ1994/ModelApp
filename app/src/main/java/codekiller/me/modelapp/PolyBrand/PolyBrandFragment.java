package codekiller.me.modelapp.PolyBrand;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import codekiller.me.modelapp.Adapter.MyFragmentPagerAdapter;
import codekiller.me.modelapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class PolyBrandFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private MyFragmentPagerAdapter adapter;
    private List<Fragment> fragments;

    public PolyBrandFragment() {
        // Required empty public constructor
    }

    public static PolyBrandFragment newInstance(){
        return new PolyBrandFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_poly_brand, container, false);

        initFragments();

        initViews(view);

        return view;
    }

    private void initFragments() {
        fragments = new ArrayList<>();

        fragments.add(ChinaPolyFragment.newInstance());
        fragments.add(PolyEstateFragment.newInstance());
        fragments.add(PolyCDFragment.newInstance());
    }

    private void initViews(View view) {
        tabLayout = view.findViewById(R.id.brand_tab_layout);
        viewPager = view.findViewById(R.id.brand_pager);
        adapter = new MyFragmentPagerAdapter(getActivity().getSupportFragmentManager(), getContext().getResources().getStringArray(R.array.brand_tab_title), fragments);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
