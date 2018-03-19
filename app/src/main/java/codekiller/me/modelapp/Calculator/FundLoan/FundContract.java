package codekiller.me.modelapp.Calculator.FundLoan;

import java.util.List;
import java.util.Map;

import codekiller.me.modelapp.Base.BaseView;

/**
 * Created by Lollipop on 2018/3/18.
 */

public interface FundContract {
    interface View extends BaseView<Presenter>{
        void showResults(List<Map<String, Double>> results);

        void reset();
    }

    interface Presenter {
        void calculate(double money, double month, double rate, int repay_way);
    }
}
