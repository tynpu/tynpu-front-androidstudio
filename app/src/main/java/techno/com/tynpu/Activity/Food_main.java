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

import org.json.JSONArray;

import techno.com.tynpu.Adapter.GetPostAdapter_SubSubFoodrequest;
import techno.com.tynpu.Fragment.BaseFragment;
import techno.com.tynpu.Fragment.ExtraFragment;
import techno.com.tynpu.Fragment.FoodFragment;
import techno.com.tynpu.Fragment.SummaryFragment;
import techno.com.tynpu.R;

public class Food_main extends AppCompatActivity {

    private static FragmentManager fragmentManager;

    private TextView mTextMessage;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_base:
                    fragmentManager = getSupportFragmentManager();
                    Fragment fragment = new BaseFragment();
                    FragmentTransaction  transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.content, fragment);
                    transaction.commit();

                    return true;
                case R.id.navigation_extra:
                    fragmentManager = getSupportFragmentManager();
                    Fragment fragmentextra = new ExtraFragment();
                    FragmentTransaction transactionextra = getSupportFragmentManager().beginTransaction();
                    transactionextra.replace(R.id.content, fragmentextra);
                    transactionextra.commit();
                    //mTextMessage.setText(R.string.extra);
                    return true;
                case R.id.navigation_summary:
                    fragmentManager = getSupportFragmentManager();
                    Fragment fragmentsummary = new SummaryFragment();
                    FragmentTransaction transactionsummary= getSupportFragmentManager().beginTransaction();
                    transactionsummary.replace(R.id.content, fragmentsummary);
                    transactionsummary.commit();

                    //  mTextMessage.setText(R.string.summarybuy);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotion_main);

        fragmentManager = getSupportFragmentManager();
        Fragment fragment = new BaseFragment();
        FragmentTransaction  transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content, fragment);
        transaction.commit();

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
