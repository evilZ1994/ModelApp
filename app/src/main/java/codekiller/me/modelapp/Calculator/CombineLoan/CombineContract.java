package codekiller.me.modelapp.Calculator.CombineLoan;

import java.util.List;
import java.util.Map;

import codekiller.me.modelapp.Base.BaseView;

/**
 * Created by Lollipop on 2018/3/18.
 */

public interface CombineContract {
    interface View extends BaseView<Presenter>{
        void showResults(List<Map<String, Double>> results);

        void reset();
    }

    interface Presenter{
        void calculate(double businessMoney, double fundMoney, double businessRate, double fundRate, double month, int repayWay);
    }
}
