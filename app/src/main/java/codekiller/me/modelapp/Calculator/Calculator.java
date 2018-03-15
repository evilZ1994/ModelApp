package codekiller.me.modelapp.Calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by R2D2 on 2018/3/15.
 */

public class Calculator {

    private static Calculator calculator;

    public static Calculator getInstance(){
        if (calculator == null){
            calculator = new Calculator();
        }

        return calculator;
    }

    /**
     * 等额本息计算方式
     * @param year_lilv 年利率
     * @param money 贷款金额
     * @param month 贷款期次（月）
     * @return 计算结果，列表的最后一项是最高月供、累计还款总额等
     */
    public List<Map<String, Double>> debx(double year_lilv, double money, double month) {
        money = money*10000;
        double year = month/12;
        int year_1 = (int) (year/5);
        int year_2 = (int)(year/5);
        double active = year_lilv*0.01/12;
        double t1 = Math.pow(1+active, month);
        double t2 = t1-1;
        double tmp = t1/t2;
        double monthratio = active*tmp;//月利率
        double monthback = new BigDecimal(money*monthratio).setScale(2, RoundingMode.HALF_UP).doubleValue();//每月支付本息
        year_lilv = year_lilv*0.01; //百分比
        double yue_lilv = year_lilv/12;
        List<Map<String, Double>> objArray = new ArrayList<>();//等额本息结果二维数组
        double ljch_bj = 0;//累积偿还本金
        double pre_sybj = 0;//上一个月剩余本金
        for(int i=1; i<=month; i++){ //等额本息
            Map<String, Double> map = new HashMap<>(); //二维list中的一维
            map.put("qc", (double) i); //期次
            map.put("chbx", monthback); //第i个月，偿还本息（元）  月供
            if (i == 1) { //第一个月
                pre_sybj = money;
            }else {
                pre_sybj = objArray.get(i-2).get("sybj");
            }
            map.put("chlx", new BigDecimal(pre_sybj*yue_lilv).setScale(2, RoundingMode.HALF_UP).doubleValue()); //第i个月，偿还利息（元） 每月付息额=（贷款本金-已还清贷款本金）*月利率
            double chbj = map.get("chbx") - map.get("chlx");//第i个月，偿还本金（元）
            map.put("chbj", new BigDecimal(chbj).setScale(2, RoundingMode.HALF_UP).doubleValue());
            ljch_bj += chbj;
            double sybj = money - ljch_bj; //第i个月，剩余本金（元）
            map.put("sybj", new BigDecimal(sybj).setScale(2, RoundingMode.HALF_UP).doubleValue());
            if (sybj <= 1) { //最后一个月出现小于1元的值
                map.put("sybj", 0.00); //第i个月，剩余本金（元）
            }
            objArray.add(map);
        }

        double yg = monthback;
        yg = new BigDecimal(yg).setScale(0, RoundingMode.HALF_UP).doubleValue();
        double ljhkze = monthback*month;
        ljhkze = new BigDecimal(ljhkze).setScale(0, RoundingMode.HALF_UP).doubleValue();
        double lxze = ljhkze - money;
        lxze = new BigDecimal(lxze).setScale(0, RoundingMode.HALF_UP).doubleValue();
        double yxxdy = monthback*2;
        yxxdy = new BigDecimal(yxxdy).setScale(0, RoundingMode.HALF_UP).doubleValue();

        Map<String, Double> map = new HashMap<>();
        map.put("yg", yg);
        map.put("ljhkze", ljhkze);
        map.put("lxze", lxze);
        map.put("yxxdy", yxxdy);
        objArray.add(map);

        return objArray;
    }

    /**
     * 等额本金计算方式
     * @param year_lilv 年利率
     * @param money 贷款金额
     * @param month 贷款期次（月）
     * @return 计算结果，列表的最后一项是最高月供、累计还款总额等
     */
    public List<Map<String, Double>> debj(double year_lilv, double money, double month) {
        money = money*10000;
        double year = month/12;
        int year_1 = (int) (year/5);
        int year_2 = (int)(year/5);
        double active = year_lilv*0.01/12;
        List<Map<String, Double>> objArray = new ArrayList<>();
        double interestM = 0; //月还款额
        double interestTotal = 0; //累计还款总额
        double chbj = money/month; //每月偿还本金（元）
        for(int i=1; i<=month; i++){
            double t1 = (money - money * (i-1) / month) * active; //第i月还款利息
            interestM = money / month + t1; //第i月还款额
            Map<String, Double> map = new HashMap<>();
            map.put("qc", (double)i); //期次
            map.put("chbx", new BigDecimal(interestM).setScale(2, RoundingMode.HALF_UP).doubleValue()); //第i个月，偿还本息（元） 月供
            map.put("chlx", new BigDecimal(interestM - chbj).setScale(2, RoundingMode.HALF_UP).doubleValue()); //第i个月，偿还利息（元）
            map.put("chbj", new BigDecimal(chbj).setScale(2, RoundingMode.HALF_UP).doubleValue()); //第i个月，偿还本金（元）
            map.put("sybj", new BigDecimal(money - chbj*i).setScale(2, RoundingMode.HALF_UP).doubleValue()); //第i个月，剩余本金（元）
            if (map.get("sybj") <= 1) { //最后一个月出现小于1元的值
                map.put("sybj", 0.00); //第i个月，剩余本金（元）
            }
            interestTotal += interestM;
            objArray.add(map);
        }
        interestTotal = Math.round(interestTotal*100)/100; //累计还款总额
        double yg = objArray.get(0).get("chbx"); //月供  最高月供
        yg = new BigDecimal(yg).setScale(0, RoundingMode.HALF_UP).doubleValue();
        double ljhkze = interestTotal; //累计还款总额
        double lxze = ljhkze - money; //利息总额
        lxze = new BigDecimal(lxze).setScale(0, RoundingMode.HALF_UP).doubleValue();
        double yxxdy = objArray.get(0).get("chbx") + objArray.get((int)month-1).get("chbx"); //月薪需大于
        yxxdy = new BigDecimal(yxxdy).setScale(0, RoundingMode.HALF_UP).doubleValue();
        Map<String, Double> map = new HashMap<>();
        map.put("yg", yg);
        map.put("ljhkze", ljhkze);
        map.put("lxze", lxze);
        map.put("yxxdy", yxxdy);
        objArray.add(map);

        return objArray;
    }

    /**
     * 计算房屋总价
     * @param mj 房屋面积
     * @param pmdj 平米单价
     * @return 房屋总价
     */
    public double js_fwzj(double mj, double pmdj) {
        return mj*pmdj;
    }

    /**
     * 计算贷款金额
     * @param fwzj 房屋总价
     * @param sf 首付比例
     * @return 贷款金额
     */
    public double js_dkje(double fwzj, double sf){
        return fwzj*(1-sf)/10000;
    }
}
