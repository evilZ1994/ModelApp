package codekiller.me.modelapp.PolyValley;


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
public class ValleyFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private MyFragmentPagerAdapter adapter;
    private List<Fragment> fragments;

    public ValleyFragment() {
        // Required empty public constructor
    }

    public static ValleyFragment newInstance(){
        return new ValleyFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_valley, container, false);

        initFragments();

        initViews(view);

        return view;
    }

    private void initFragments() {
        fragments = new ArrayList<>();

        fragments.add(ProjectFragment.newInstance());
        fragments.add(HouseTypeFragment.newInstance());
        fragments.add(ContactUsFragment.newInstance());
    }

    private void initViews(View view) {
        tabLayout = view.findViewById(R.id.tab_layout);
        viewPager = view.findViewById(R.id.pager);
        adapter = new MyFragmentPagerAdapter(getActivity().getSupportFragmentManager(), getContext().getResources().getStringArray(R.array.valley_tab_title), fragments);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

}
