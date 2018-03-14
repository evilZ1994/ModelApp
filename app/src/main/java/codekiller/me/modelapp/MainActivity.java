package codekiller.me.modelapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

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


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        initFragments();
    }

    private void initFragments() {
        firstFragment = FirstFragment.getInstance();
        calculatorFragment = CalculatorFragment.getInstance();
        aboutFragment = AboutFragment.getInstance();
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
