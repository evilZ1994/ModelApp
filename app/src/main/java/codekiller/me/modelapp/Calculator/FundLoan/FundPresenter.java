package codekiller.me.modelapp.Calculator.FundLoan;

import codekiller.me.modelapp.Calculator.Calculator;
import codekiller.me.modelapp.R;

/**
 * Created by Lollipop on 2018/3/18.
 */

public class FundPresenter implements FundContract.Presenter {
    private FundContract.View view;
    private Calculator calculator;

    public FundPresenter(FundContract.View view){
        this.view = view;
        view.setPresenter(this);
        this.calculator = Calculator.getInstance();
    }

    @Override
    public void calculate(double money, double month, double rate, int repayWay) {
        if (repayWay == R.id.repay_way_btn1){
            view.showResults(calculator.debx(money, month, rate));
        }else {
            view.showResults(calculator.debj(money, month, rate));
        }
    }
}
