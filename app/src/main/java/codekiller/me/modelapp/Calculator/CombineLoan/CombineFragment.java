package codekiller.me.modelapp.Calculator.CombineLoan;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import codekiller.me.modelapp.Calculator.CalculateResultDialog;
import codekiller.me.modelapp.R;

public class CombineFragment extends Fragment implements CombineContract.View{
    private TextInputEditText businessMoneyInput;
    private TextInputEditText fundMoneyInput;
    private AppCompatSpinner loanLimitSpinner;
    private AppCompatSpinner businessRateSpinner;
    private TextInputEditText businessRateInput;
    private TextInputEditText fundRateInput;
    private RadioGroup repayWayGroup;
    private Button calculateBtn;
    private Button resetBtn;

    private List<Integer> monthValues;
    private String[] yearRateMultiples;
    private Double businessRate;
    private Double fundRate;
    private CombineContract.Presenter presenter;

    public CombineFragment() {
        // Required empty public constructor
    }

    public static CombineFragment newInstance(){
        return new CombineFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_combine, container, false);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("rate", Context.MODE_PRIVATE);
        businessRate = Double.valueOf(sharedPreferences.getString("business_one_house_rate", "6.55"));
        fundRate = Double.valueOf(sharedPreferences.getString("fund_rate", "4.50"));
        yearRateMultiples = getContext().getResources().getStringArray(R.array.year_rate_multiple);

        initViews(view);

        return view;
    }

    private void initViews(View view) {
        businessMoneyInput = view.findViewById(R.id.business_money_input);
        fundMoneyInput = view.findViewById(R.id.fund_money_input);
        loanLimitSpinner = view.findViewById(R.id.loan_limit_spinner);
        businessRateSpinner = view.findViewById(R.id.business_rate_spinner);
        businessRateInput = view.findViewById(R.id.business_rate_input);
        fundRateInput = view.findViewById(R.id.fund_rate_input);
        repayWayGroup = view.findViewById(R.id.repay_way);
        calculateBtn = view.findViewById(R.id.calculate_btn);
        resetBtn = view.findViewById(R.id.reset_btn);

        //设置商业贷款金额默认100万
        businessMoneyInput.setText("100");
        //设置公积金贷款金额默认40万
        fundMoneyInput.setText("40");

        initLoanLimitSpinner();

        businessRateSpinner.setSelection(4, true);
        businessRateInput.setText(String.valueOf(businessRate));
        businessRateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                double yearRateMultiple = Double.valueOf(yearRateMultiples[position]);
                double rate = businessRate*yearRateMultiple;
                businessRateInput.setText(String.valueOf(rate));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        fundRateInput.setText(String.valueOf(fundRate));

        calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double businessMoney = Double.valueOf(businessMoneyInput.getText().toString());
                double fundMoney = Double.valueOf(fundMoneyInput.getText().toString());
                double businessRate = Double.valueOf(businessRateInput.getText().toString());
                double fundRate = Double.valueOf(fundRateInput.getText().toString());
                double month = monthValues.get(loanLimitSpinner.getSelectedItemPosition());
                int repayWay = repayWayGroup.getCheckedRadioButtonId();
                presenter.calculate(businessMoney, fundMoney, businessRate, fundRate, month, repayWay);
            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });
    }

    private void initLoanLimitSpinner() {
        List<String> entries = new ArrayList<>();
        monthValues = new ArrayList<>();
        for (int i=30; i>0; i--){
            monthValues.add(i*12);
            String entryStr = i+"年"+"("+i*12+"期)";
            entries.add(entryStr);
        }
        ArrayAdapter<String> entriesAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, entries);
        entriesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        loanLimitSpinner.setAdapter(entriesAdapter);
        //设置贷款期限默认为20年
        loanLimitSpinner.setSelection(10, true);
    }

    @Override
    public void setPresenter(CombineContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showResults(List<Map<String, Double>> results) {
        new CalculateResultDialog(getContext(), results).show();
    }

    @Override
    public void reset() {
        businessMoneyInput.setText("100");
        fundMoneyInput.setText("40");
        loanLimitSpinner.setSelection(10, true);
        businessRateSpinner.setSelection(4, true);
        fundRateInput.setText(String.valueOf(fundRate));
        repayWayGroup.check(R.id.repay_way_btn1);
    }
}
