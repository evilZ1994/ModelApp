package codekiller.me.modelapp.Calculator.BusinessLoan;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;

import codekiller.me.modelapp.Calculator.Calculator;

/**
 * Created by R2D2 on 2018/3/15.
 */

public class BusinessPresenter implements BusinessContract.Presenter {
    private BusinessContract.View view;
    private Calculator calculator;

    public BusinessPresenter(BusinessContract.View view){
        this.view = view;
        view.setPresenter(this);
        calculator = Calculator.getInstance();
    }

    @Override
    public void calculate(double rate, double money, double month, int repay_way) {
        List<Map<String, Double>> list;
        if (repay_way == 0){
            list = calculator.debx(rate, money, month);
        }else {
            list = calculator.debj(rate, money, month);
        }
        view.showResults(list);
    }

    @Override
    public void calHousePrice(double area, double unitPrice) {
        double price = calculator.js_fwzj(area, unitPrice);
        view.setHousePrice(new BigDecimal(price).setScale(2, RoundingMode.HALF_UP).doubleValue());
    }

    @Override
    public void calLoanMoney(double housePrice, double downPay) {
        double money = calculator.js_dkje(housePrice, downPay);
        view.setLoanMoney(new BigDecimal(money).setScale(2, RoundingMode.HALF_UP).doubleValue());
    }
}
