package codekiller.me.modelapp.Calculator.CombineLoan;

import codekiller.me.modelapp.Calculator.Calculator;

/**
 * Created by Lollipop on 2018/3/18.
 */

public class CombinePresenter implements CombineContract.Presenter {
    private CombineContract.View view;
    private Calculator calculator;

    public CombinePresenter(CombineContract.View view){
        this.view = view;
        view.setPresenter(this);
        calculator = Calculator.getInstance();
    }

    @Override
    public void calculate(double businessMoney, double fundMoney, double businessRate, double fundRate, double month, int repayWay) {
        view.showResults(calculator.combineLoan(businessMoney, fundMoney, businessRate, fundRate, month, repayWay));
    }
}
