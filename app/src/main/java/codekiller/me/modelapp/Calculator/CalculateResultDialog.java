package codekiller.me.modelapp.Calculator;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import codekiller.me.modelapp.R;

/**
 * Created by Lollipop on 2018/3/17.
 */

public class CalculateResultDialog extends Dialog {
    private TextView totalInterest;
    private TextView totalRepay;
    private TextView monthlyPay;
    private TextView leastMonthlyMoney;
    private TextView monthTitle;
    private TextView principalInterestTitle;
    private TextView interestTitle;
    private TextView principalTitle;
    private TextView lastPrincipalTitle;
    private RecyclerView recyclerView;

    private CalculateResultAdapter adapter;
    private List<Map<String, Double>> results;

    public CalculateResultDialog(@NonNull Context context, List<Map<String, Double>> results) {
        super(context);
        this.results = results;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_calculate_result);

        totalInterest = findViewById(R.id.total_interest);
        totalRepay = findViewById(R.id.total_repay_money);
        monthlyPay = findViewById(R.id.monthly_pay);
        leastMonthlyMoney = findViewById(R.id.least_monthly_money);
        monthTitle = findViewById(R.id.month);
        principalInterestTitle = findViewById(R.id.principal_interest);
        interestTitle = findViewById(R.id.interest);
        principalTitle = findViewById(R.id.principal);
        lastPrincipalTitle = findViewById(R.id.last_principal);
        recyclerView = findViewById(R.id.recycler);

        Map<String, Double> map = results.remove(results.size()-1);
        totalInterest.setText(String.valueOf(map.get("lxze")));
        totalRepay.setText(String.valueOf(map.get("ljhkze")));
        monthlyPay.setText(String.valueOf(map.get("yg")));
        leastMonthlyMoney.setText(String.valueOf(map.get("yxxdy")));

        monthTitle.setText("期次");
        monthTitle.getPaint().setFakeBoldText(true);
        principalInterestTitle.setText("偿还本息（元）");
        principalInterestTitle.getPaint().setFakeBoldText(true);
        interestTitle.setText("偿还利息（元）");
        interestTitle.getPaint().setFakeBoldText(true);
        principalTitle.setText("偿还本金（元）");
        principalTitle.getPaint().setFakeBoldText(true);
        lastPrincipalTitle.setText("剩余本金（元）");
        lastPrincipalTitle.getPaint().setFakeBoldText(true);

        adapter = new CalculateResultAdapter(results, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }
}
