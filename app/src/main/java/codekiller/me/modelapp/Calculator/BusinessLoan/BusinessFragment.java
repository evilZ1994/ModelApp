package codekiller.me.modelapp.Calculator.BusinessLoan;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import codekiller.me.modelapp.Calculator.CalculateResultDialog;
import codekiller.me.modelapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BusinessFragment extends Fragment implements BusinessContract.View{
    private RadioGroup calWayGroup;
    private LinearLayout areaPart;
    private TextInputEditText areaInput;
    private TextInputEditText unitPriceInput;
    private TextInputEditText totalPriceInput;
    private RadioGroup houseNumGroup;
    private AppCompatSpinner downPaymentSpinner;
    private TextInputEditText loanMoneyInput;
    private AppCompatSpinner loanLimitSpinner;
    private AppCompatSpinner yearRateSpinner;
    private TextInputEditText yearRateInput;
    private RadioGroup repayWay;
    private Button calculateBtn;
    private Button resetBtn;
    private CalculateResultDialog resultDialog;

    private BusinessContract.Presenter presenter;
    private SharedPreferences sharedPreferences;
    private double baseRate;
    private String[] yearRateMultiples;
    private String[] downPayValues;
    private List<Integer> monthValues;

    public BusinessFragment() {
        // Required empty public constructor
    }

    public static BusinessFragment newInstance(){
        return new BusinessFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_business, container, false);

        sharedPreferences = getContext().getSharedPreferences("rate", Context.MODE_PRIVATE);
        baseRate = Double.valueOf(sharedPreferences.getString("business_money_rate", "6.55"));
        yearRateMultiples = getContext().getResources().getStringArray(R.array.year_rate_multiple);
        downPayValues = getContext().getResources().getStringArray(R.array.down_pay_value);

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
                    //设置贷款金额可输入
                    loanMoneyInput.setFocusable(true);
                    loanMoneyInput.setFocusableInTouchMode(true);
                    loanMoneyInput.setClickable(true);
                    //设置贷款金额默认为100万
                    loanMoneyInput.setText("100");
                }else {
                    areaPart.setVisibility(View.VISIBLE);
                    //设置贷款金额不能输入
                    loanMoneyInput.setFocusable(false);
                    loanMoneyInput.setFocusableInTouchMode(false);
                    loanMoneyInput.setClickable(false);
                    //设置贷款金额默认为0
                    loanMoneyInput.setText("0");
                }
                //清空
                reset();
            }
        });

        areaInput = view.findViewById(R.id.area_input);
        unitPriceInput = view.findViewById(R.id.unit_price_input);
        totalPriceInput = view.findViewById(R.id.total_price_input);
        houseNumGroup = view.findViewById(R.id.house_num);
        downPaymentSpinner = view.findViewById(R.id.down_payment_spinner);
        loanMoneyInput = view.findViewById(R.id.loan_money_input);
        loanLimitSpinner = view.findViewById(R.id.loan_limit_spinner);
        yearRateSpinner = view.findViewById(R.id.year_rate_spinner);
        yearRateInput= view.findViewById(R.id.year_rate_input);
        repayWay = view.findViewById(R.id.repay_way);
        calculateBtn = view.findViewById(R.id.calculate_btn);
        resetBtn = view.findViewById(R.id.reset_btn);

        //监听房屋面积的输入
        areaInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //如果有内容输入，且平米单价有内容，则计算房屋总价
                if (s.toString().length()>0 && unitPriceInput.getText().toString().length()>0){
                    presenter.calHousePrice(Double.valueOf(s.toString()), Double.valueOf(unitPriceInput.getText().toString()));
                }else {
                    setHousePrice(0);
                }
            }
        });
        //监听平米单价的输入
        unitPriceInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //如果有内容输入，且房屋面积有内容，则计算房屋总价
                if (s.toString().length()>0 && areaInput.getText().toString().length()>0){
                    presenter.calHousePrice(Double.valueOf(areaInput.getText().toString()), Double.valueOf(s.toString()));
                }else {
                    setHousePrice(0);
                }
            }
        });

        //设置房屋总价默认值为0
        totalPriceInput.setText("0");
        //监听房屋总价的变化
        totalPriceInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length()>0 && Double.valueOf(s.toString())>0){
                    //计算贷款金额
                    presenter.calLoanMoney(Double.valueOf(s.toString()), Double.valueOf(downPayValues[downPaymentSpinner.getSelectedItemPosition()]));
                }
            }
        });
        houseNumGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.one_house){
                    //设置首付为3成
                    downPaymentSpinner.setSelection(1, true);
                    //修改基准利率
                    baseRate = Double.valueOf(sharedPreferences.getString("business_one_house_rate", "6.55"));
                    //设置年利率为最新基准利率
                    yearRateSpinner.setSelection(4, true);
                }else {
                    //设置首付为6成
                    downPaymentSpinner.setSelection(4, true);
                    //修改基准利率
                    baseRate = Double.valueOf(sharedPreferences.getString("business_two_house_rate", "7.205"));
                    //设置年利率为最新基准利率1.1倍
                    yearRateSpinner.setSelection(5, true);
                }
                //判断房屋总价是否为空
                String totalPrice = totalPriceInput.getText().toString();
                if (totalPrice.length()>0 && Double.valueOf(totalPrice)>0){
                    //计算贷款金额
                    presenter.calLoanMoney(Double.valueOf(totalPrice), Double.valueOf(downPayValues[downPaymentSpinner.getSelectedItemPosition()]));
                }
            }
        });

        //设置首付默认为3成
        downPaymentSpinner.setSelection(1, true);

        //设置贷款金额默认为100万
        loanMoneyInput.setText("100");

        initLoanLimitSpinner();

        //设置年利率默认为最新基准利率
        yearRateSpinner.setSelection(4, true);
        yearRateInput.setText(String.valueOf(baseRate));
        yearRateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                double yearRateMultiple = Double.valueOf(yearRateMultiples[position]);
                double rate = baseRate*yearRateMultiple;
                yearRateInput.setText(String.valueOf(rate));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //计算
                presenter.calculate(Double.valueOf(yearRateInput.getText().toString()), Double.valueOf(loanMoneyInput.getText().toString()), monthValues.get(loanLimitSpinner.getSelectedItemPosition()), repayWay.getCheckedRadioButtonId());
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
    public void setPresenter(BusinessContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showResults(List<Map<String, Double>> results) {
        resultDialog = new CalculateResultDialog(getContext(), results);
        resultDialog.show();
    }

    @Override
    public void setHousePrice(double price) {
        totalPriceInput.setText(String.valueOf(new BigDecimal(price).setScale(2, RoundingMode.HALF_UP).doubleValue()));
    }

    @Override
    public void setLoanMoney(double money) {
        loanMoneyInput.setText(String.valueOf(new BigDecimal(money).setScale(2, RoundingMode.HALF_UP).doubleValue()));
    }

    @Override
    public void reset() {
        areaInput.setText("");
        unitPriceInput.setText("");
        totalPriceInput.setText("0");
        houseNumGroup.check(R.id.one_house);
        loanMoneyInput.setText("0");
        loanLimitSpinner.setSelection(10, true);
        yearRateSpinner.setSelection(4, true);
        repayWay.check(R.id.repay_way_btn1);
        //修改基准利率
        baseRate = Double.valueOf(sharedPreferences.getString("business_one_house_rate", "6.55"));
    }
}
