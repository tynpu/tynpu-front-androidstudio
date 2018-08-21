package techno.com.tynpu.Activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import techno.com.tynpu.Fragment.BasePromotion;
import techno.com.tynpu.Fragment.ExtraPRomotion;
import techno.com.tynpu.Fragment.SummaryPromotionFragment;
import techno.com.tynpu.R;

public class Promotion_main_act extends AppCompatActivity {

    private TextView mTextMessage;
    private static FragmentManager fragmentManager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigationpro_base:
                    fragmentManager = getSupportFragmentManager();
                    Fragment fragment = new BasePromotion();
                    FragmentTransaction  transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.contentpro, fragment);
                    transaction.commit();

                    return true;
                case R.id.navigationpro_extra:
                    fragmentManager = getSupportFragmentManager();
                    Fragment fragmentextra = new ExtraPRomotion();
                    FragmentTransaction transactionextra = getSupportFragmentManager().beginTransaction();
                    transactionextra.replace(R.id.contentpro, fragmentextra);
                    transactionextra.commit();
                    //mTextMessage.setText(R.string.extra);
                    return true;
                case R.id.navigationpro_summary:
                    fragmentManager = getSupportFragmentManager();
                    Fragment fragmentsummary = new SummaryPromotionFragment();
                    FragmentTransaction transactionsummary= getSupportFragmentManager().beginTransaction();
                    transactionsummary.replace(R.id.contentpro, fragmentsummary);
                    transactionsummary.commit();
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotion_main_act);
        fragmentManager = getSupportFragmentManager();
        Fragment fragment = new BasePromotion();
        FragmentTransaction  transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contentpro, fragment);
        transaction.commit();

       // mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigationpro);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
