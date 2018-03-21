package codekiller.me.modelapp.PolyValley;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import codekiller.me.modelapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HouseTypeFragment extends Fragment {


    public HouseTypeFragment() {
        // Required empty public constructor
    }

    public static HouseTypeFragment newInstance(){
        return new HouseTypeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_house_type, container, false);

        return view;
    }

}
