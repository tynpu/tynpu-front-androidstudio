package techno.com.tynpu.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import techno.com.tynpu.Fragment.CommitFragment;
import techno.com.tynpu.Fragment.Food_frameLayout;
import techno.com.tynpu.Fragment.Letter;
import techno.com.tynpu.Fragment.Promotion_frameLayout;
import techno.com.tynpu.Other.AppConfig;
import techno.com.tynpu.R;
import techno.com.tynpu.Response.GetProfileResponse;
import techno.com.tynpu.Response.GetRestroDetalResponse;

import static techno.com.tynpu.constant.MySharedPref.getData;

public class FoodNextActivity extends AppCompatActivity {
    private CollapsingToolbarLayout collapsingToolbarLayout = null;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private NestedScrollView nestedviewpagger;
    private int[] tabIcons = {R.drawable.promotion_icon, R.drawable.food_icon};
    FragmentManager fmgr;
    TextView tv_subrestroname,tv_restroaddress;
    ImageView profile_id;
    String Restroid;
    ImageView back_link;
    RelativeLayout rel_cart;
    String strcartcount;
    TextView item_count;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_next);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(getResources().getString(R.string.user_name));
        tv_subrestroname=(TextView)findViewById(R.id.tv_subrestroname);
        tv_restroaddress=(TextView)findViewById(R.id.tv_restroaddress);
        profile_id=(ImageView)findViewById(R.id.profile_id);
        back_link=(ImageView)findViewById(R.id.back_link);
        rel_cart=(RelativeLayout)findViewById(R.id.rel_cart);
        item_count=findViewById(R.id.item_count);
        rel_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FoodNextActivity.this,CartActivity.class);
                startActivity(intent);

            }
        });




        Restroid=getData(FoodNextActivity.this,"resid","null");
        System.out.println("resto===ID=============FoodNExtAc"+Restroid);

        strcartcount=getData(getApplicationContext(),"cartcount",null);
        item_count.setText(strcartcount);

//        if(Restroid!=null)
//        {
//            try {
//                JSONObject jsonObject=new JSONObject(Restroid);
//
//
//                Restroid=jsonObject.getString("resid");
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
        GETallRestroDetail(Restroid);

      //  dynamicToolbarColor();

        toolbarTextAppernce();

        viewPager = (ViewPager) findViewById(R.id.viewpager3);

        setupViewPager(viewPager);

        nestedviewpagger=(NestedScrollView)findViewById(R.id.nestedviewpagger);
        nestedviewpagger.setFillViewport(true);


        tabLayout = (TabLayout) findViewById(R.id.tabs3);
        tabLayout.setupWithViewPager(viewPager);

        back_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // tabLayout.setTabTextColors(Color.parseColor("#ffffff"), Color.parseColor("#000000"));
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

    } //**********************  end of oncreearreee  **********************************



    private void toolbarTextAppernce() {
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.collapsedappbar);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.expandedappbar);
    }


//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.custom_tab_new_food, null);
        ((TextView) view.findViewById(R.id.tab3)).setText("LETTER");
        View view2 = LayoutInflater.from(getApplicationContext()).inflate(R.layout.custom_tab_new_food, null);
        ((TextView) view2.findViewById(R.id.tab3)).setText("COMMENTS");
        tabLayout.getTabAt(0).setCustomView(view);
        tabLayout.getTabAt(1).setCustomView(view2);

    }

    private void setupViewPager(ViewPager viewPager) {
        FoodNextActivity.ViewPagerAdapter adapter = new FoodNextActivity.ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new Letter(), "LETTER");
        adapter.addFrag(new CommitFragment(), "COMMENTS");
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


    //*******************  GET RESTRO DETRAI **********************************************


    private void GETallRestroDetail(final String restaurant_id) {
        // bar.setVisibility(View.VISIBLE);
        Call<ResponseBody> call = AppConfig.PostInterface().RestroDetail(restaurant_id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //  bar.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()) {
                        Gson gson = new Gson();
                        String responseData = response.body().string();
                        JSONObject object = new JSONObject(responseData);
                        String str_result = object.getString("result");
                        System.out.println("REstroDetail" + object);
                        if (object.getString("status").equals("1")) {
                            GetRestroDetalResponse getProfileResponse = gson.fromJson(responseData, GetRestroDetalResponse.class);
                            tv_subrestroname.setText(getProfileResponse.getResult().getRestaurentName());
                            tv_restroaddress.setText(getProfileResponse.getResult().getAddress());
                            Glide.with(FoodNextActivity.this).load(getProfileResponse.getResult().getRestuarentImage()).into(profile_id);
                        }

                    } else ;
                    //AppConfig.showToast("server error");
                    //  Toast.makeText(Login.this, "Server Error", Toast.LENGTH_SHORT).show();
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


}
