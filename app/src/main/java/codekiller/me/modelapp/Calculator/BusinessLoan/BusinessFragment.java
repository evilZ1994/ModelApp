package codekiller.me.modelapp.Calculator.BusinessLoan;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import java.util.List;
import java.util.Map;

import codekiller.me.modelapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BusinessFragment extends Fragment implements BusinessContract.View{
    private RadioGroup calWayGroup;
    private LinearLayout areaPart;

    private BusinessContract.Presenter presenter;

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
        View view = inflater.inflate(R.layout.fragment_business, container, false);

        initViews(view);

        return view;
    }

    private void initViews(View view) {
        areaPart = view.findViewById(R.id.area_part);
        calWayGroup = view.findViewById(R.id.cal_way_group);
        calWayGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.cal_way_btn1){
                    areaPart.setVisibility(View.GONE);
                }else {
                    areaPart.setVisibility(View.VISIBLE);
                }
                //清空
                reset();
            }
        });

    }

    @Override
    public void setPresenter(BusinessContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showResults(List<Map<String, Double>> results) {

    }

    @Override
    public void setHousePrice(double price) {

    }

    @Override
    public void setLoanMoney(double money) {

    }

    @Override
    public void reset() {

    }
}
