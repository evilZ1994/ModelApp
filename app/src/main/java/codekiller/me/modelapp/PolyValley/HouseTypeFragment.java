package codekiller.me.modelapp.PolyValley;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import codekiller.me.modelapp.Adapter.HouseTypeRecyclerAdapter;
import codekiller.me.modelapp.Bean.HouseType;
import codekiller.me.modelapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HouseTypeFragment extends Fragment {
    private RecyclerView recyclerView;
    private HouseTypeRecyclerAdapter adapter;
    private List<HouseType> houseTypes;

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

        recyclerView = view.findViewById(R.id.house_type_recycler);

        initHouseTypes();

        adapter = new HouseTypeRecyclerAdapter(houseTypes, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void initHouseTypes() {
        houseTypes = new ArrayList<>();
        houseTypes.add(new HouseType("8#建面82平A户型", Uri.parse("asset:///images/pic1.jpg")));
        houseTypes.add(new HouseType("8#建面92平D户型", Uri.parse("asset:///images/pic2.jpg")));
        houseTypes.add(new HouseType("8#建面66平E户型", Uri.parse("asset:///images/pic3.jpg")));
    }

}
