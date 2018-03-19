package codekiller.me.modelapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    private FirstFragment firstFragment;
    private CalculatorFragment calculatorFragment;
    private AboutFragment aboutFragment;
    private Fragment[] fragments;
    private int lastShownFragment;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if(lastShownFragment != 0){
                        switchFragment(lastShownFragment, 0);
                        lastShownFragment = 0;
                    }
                    return true;
                case R.id.calculator:
                    if(lastShownFragment != 1){
                        switchFragment(lastShownFragment, 1);
                        lastShownFragment = 1;
                    }
                    return true;
                case R.id.navigation_notifications:
                    if(lastShownFragment != 2){
                        switchFragment(lastShownFragment, 2);
                        lastShownFragment = 2;
                    }
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        initFragments();
    }

    /**
     * 初始化数据如商业贷款基准利率，公积金贷款利率
     */
    private void initData() {
        SharedPreferences sharedPreferences = getSharedPreferences("rate", MODE_PRIVATE);
        //判断是否是首次运行程序
        boolean isFirstRun = sharedPreferences.getBoolean("isFirstRun", true);
        if (isFirstRun){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("business_one_house_rate", "6.55");
            editor.putString("business_two_house_rate", "7.205");
            editor.putString("fund_rate", "4.50");
            editor.putBoolean("isFirstRun", false);
            editor.apply();
        }
    }

    private void initFragments() {
        firstFragment = FirstFragment.newInstance();
        calculatorFragment = CalculatorFragment.newInstance();
        aboutFragment = AboutFragment.newInstance();
        fragments = new Fragment[]{firstFragment, calculatorFragment, aboutFragment};
        lastShownFragment = 0;
        getSupportFragmentManager().beginTransaction()
                .add(R.id.content_frame, firstFragment)
                .show(firstFragment)
                .commit();
    }

    private void switchFragment(int lastIndex, int index){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(fragments[lastIndex]);
        if (!fragments[index].isAdded()){
            transaction.add(R.id.content_frame, fragments[index]);
        }
        transaction.show(fragments[index]).commitAllowingStateLoss();
    }

}
