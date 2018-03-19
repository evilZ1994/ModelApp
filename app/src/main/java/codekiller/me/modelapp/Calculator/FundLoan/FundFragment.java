package codekiller.me.modelapp.Calculator.FundLoan;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import codekiller.me.modelapp.Calculator.CalculateResultDialog;
import codekiller.me.modelapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FundFragment extends Fragment implements FundContract.View{
    private TextInputEditText loanMoneyInput;
    private AppCompatSpinner loanLimitSpinner;
    private TextInputEditText yearRateInput;
    private RadioGroup repayWayGroup;
    private Button calculateBtn;
    private Button resetBtn;

    private FundContract.Presenter presenter;
    private List<Integer> monthValues;
    private String fundRate;

    public FundFragment() {
        // Required empty public constructor
    }

    public static FundFragment newInstance(){
        return new FundFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fund, container, false);

        initViews(view);

        return view;
    }

    private void initViews(View view) {
        loanMoneyInput = view.findViewById(R.id.loan_money_input);
        loanLimitSpinner = view.findViewById(R.id.loan_limit_spinner);
        yearRateInput = view.findViewById(R.id.year_rate_input);
        repayWayGroup = view.findViewById(R.id.repay_way);
        calculateBtn = view.findViewById(R.id.calculate_btn);
        resetBtn = view.findViewById(R.id.reset_btn);

        //设置贷款金额默认50万
        loanMoneyInput.setText("50");

        //设置贷款期限下拉菜单
        initLoanLimitSpinner();

        //设置年利率为公积金年利率
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("rate", Context.MODE_PRIVATE);
        fundRate = sharedPreferences.getString("fund_rate", "4.50");
        yearRateInput.setText(fundRate);

        calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double loanMoney = Double.valueOf(loanMoneyInput.getText().toString());
                double loanLimit = monthValues.get(loanLimitSpinner.getSelectedItemPosition());
                double yearRate = Double.valueOf(yearRateInput.getText().toString());
                int repayWay = repayWayGroup.getCheckedRadioButtonId();
                presenter.calculate(loanMoney, loanLimit, yearRate, repayWay);
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
    public void setPresenter(FundContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showResults(List<Map<String, Double>> results) {
        CalculateResultDialog dialog = new CalculateResultDialog(getContext(), results);
        dialog.show();
    }

    @Override
    public void reset() {
        loanMoneyInput.setText("0");
        loanLimitSpinner.setSelection(10, true);
        yearRateInput.setText(fundRate);
        repayWayGroup.check(R.id.repay_way_btn1);
    }
}
