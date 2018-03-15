package codekiller.me.modelapp.Calculator;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import codekiller.me.modelapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BusinessFragment extends Fragment {


    public BusinessFragment() {
        // Required empty public constructor
    }

    public static BusinessFragment getInstance(){
        return new BusinessFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_business, container, false);
    }

}
