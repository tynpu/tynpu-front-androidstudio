package techno.com.tynpu.Activity;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import techno.com.tynpu.Fragment.NavMyDataFragment;
import techno.com.tynpu.Fragment.NavPaymentmethodFragment;
import techno.com.tynpu.Fragment.Nav_SecurityFragment;
import techno.com.tynpu.Other.AppConfig;
import techno.com.tynpu.R;
import techno.com.tynpu.Response.GetProfileResponse;

import static techno.com.tynpu.constant.MySharedPref.getData;

public class Nav_MyProfile extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PagerAdapter adapter;
    CircleImageView nav_profileimg;
    TextView username;
    String ldata,str_uid,str_image,str_name,id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav__my_profile);
        nav_profileimg=(CircleImageView)findViewById(R.id.nav_profileimg);
        username=(TextView)findViewById(R.id.username);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN); // ******for Key board hiding
        ldata = getData(getApplicationContext(), "ldata", "null");
        if (ldata != null) {
            try {
                JSONObject jsonObject = new JSONObject(ldata);
                System.out.println("Ldataint" + ldata);
                str_image = jsonObject.getString("image");
                str_name = jsonObject.getString("firstname");
                id = jsonObject.getString("id");
                // str_email=jsonObject.getString("email");
                //   id=jsonObject.getString("id");


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (str_name != null) {
            username.setText(str_name);
            // user_email.setText(str_email);


        }


        if (str_image != null) {

            Glide.with(Nav_MyProfile.this).load(str_image).into(nav_profileimg);

        }
        GetProfile(id);

        tabLayout.addTab(tabLayout.newTab().setText("My Data"));
        tabLayout.addTab(tabLayout.newTab().setText("Payment Method"));
        tabLayout.addTab(tabLayout.newTab().setText("Security"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
       // tabLayout.setTabTextColors(Color.parseColor("#aaaaaa"), Color.parseColor("#ffffff"));

        viewPager = (ViewPager) findViewById(R.id.product_pager);
        adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }//************************** end of oncreate  *************************************************

    public class PagerAdapter extends FragmentStatePagerAdapter {
        int mNumOfTabs;

        public PagerAdapter(android.support.v4.app.FragmentManager fm, int NumOfTabs) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {

            switch (position) {
                case 0:
                    NavMyDataFragment tab1 = new NavMyDataFragment();
                    return tab1;
                case 1:
                    NavPaymentmethodFragment tab2 = new NavPaymentmethodFragment();
                    return tab2;
                case 2:
                    Nav_SecurityFragment tab3 = new Nav_SecurityFragment();
                    return tab3;

                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return mNumOfTabs;
        }
    }


    //*************** Get Profile ************************************
    private void GetProfile(final String user_id) {
        // bar.setVisibility(View.VISIBLE);
        Call<ResponseBody> call = AppConfig.PostInterface().GetProfile(user_id);
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
                        System.out.println("GETPROFILEee" + object);
                        if (object.getString("status").equals("1")) {
                            GetProfileResponse getProfileResponse = gson.fromJson(responseData, GetProfileResponse.class);
                            username.setText(getProfileResponse.getResult().getName());
                             Glide.with(Nav_MyProfile.this).load(getProfileResponse.getResult().getImage()).into(nav_profileimg);

                        }

                    } else ;

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
