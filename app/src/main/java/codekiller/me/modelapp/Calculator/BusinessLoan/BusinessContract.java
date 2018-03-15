package codekiller.me.modelapp.Calculator.BusinessLoan;

import java.util.List;
import java.util.Map;

import codekiller.me.modelapp.Base.BaseView;

/**
 * Created by R2D2 on 2018/3/15.
 */

public interface BusinessContract {
    interface View extends BaseView<Presenter>{
        void showResults(List<Map<String, Double>> results);

        void setHousePrice(double price);

        void setLoanMoney(double money);

        void reset();
    }

    interface Presenter{
        void calculate(double money, double month, double rate, int repay_way);

        void calHousePrice(double area, double unitPrice);

        void calLoanMoney(double housePrice, double downPay);
    }
}
