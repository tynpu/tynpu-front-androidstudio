package techno.com.tynpu.Activity;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import techno.com.tynpu.Fragment.BaseFragment;
import techno.com.tynpu.Fragment.ExtraFragment;
import techno.com.tynpu.Fragment.Food_frameLayout;
import techno.com.tynpu.Fragment.Promotion_frameLayout;
import techno.com.tynpu.Fragment.SummaryFragment;
import techno.com.tynpu.R;

public class Promotion_Dialog_Veg extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private int[] tabIcons = {R.drawable.base_icon, R.drawable.extra_icon,R.drawable.summry_icon};
    FragmentManager fmgr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotion__dialog__veg);
        viewPager = (ViewPager) findViewById(R.id.viewpager1);

        setupViewPager(viewPager);


        tabLayout = (TabLayout) findViewById(R.id.tabs1);
        tabLayout.setupWithViewPager(viewPager);


         tabLayout.setTabTextColors(Color.parseColor("#ffffff"), Color.parseColor("#FF5600"));
        //  tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#10ffffff"));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                // tv_main.setText(tab.getText());
            }


            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });


        setupTabIcons();


        fmgr = getSupportFragmentManager();


    }
    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);

        View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.custom_tab_new_promotion, null);
        ((ImageView) view.findViewById(R.id.icon3)).setImageResource(R.drawable.base_selector);
        ((TextView) view.findViewById(R.id.tab3)).setText("Base");


        View view2 = LayoutInflater.from(getApplicationContext()).inflate(R.layout.custom_tab_new_promotion, null);
        ((ImageView) view2.findViewById(R.id.icon3)).setImageResource(R.drawable.extra_selector);
        ((TextView) view2.findViewById(R.id.tab3)).setText("Extra");

        View view3 = LayoutInflater.from(getApplicationContext()).inflate(R.layout.custom_tab_new_promotion, null);
        ((ImageView) view3.findViewById(R.id.icon3)).setImageResource(R.drawable.summary_selector);
        ((TextView) view3.findViewById(R.id.tab3)).setText("Summary for buy");


        tabLayout.getTabAt(0).setCustomView(view);
        tabLayout.getTabAt(1).setCustomView(view2);
        tabLayout.getTabAt(2).setCustomView(view3);

    }

    private void setupViewPager(ViewPager viewPager) {
        Promotion_Dialog_Veg.ViewPagerAdapter adapter = new Promotion_Dialog_Veg.ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new BaseFragment(), "Base");
        adapter.addFrag(new ExtraFragment(), "Extra");
        adapter.addFrag(new SummaryFragment(), "Summary for buy");


        viewPager.setAdapter(adapter);
    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
