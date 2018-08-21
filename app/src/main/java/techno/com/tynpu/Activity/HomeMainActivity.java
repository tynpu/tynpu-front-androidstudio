package techno.com.tynpu.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import techno.com.tynpu.Fragment.Food_frameLayout;
import techno.com.tynpu.Fragment.PromotionFragment;
import techno.com.tynpu.Fragment.Promotion_frameLayout;
import techno.com.tynpu.Other.AppConfig;
import techno.com.tynpu.R;
import techno.com.tynpu.Response.GetProfileResponse;

import static techno.com.tynpu.constant.MySharedPref.getData;
import static techno.com.tynpu.constant.MySharedPref.saveData;

public class HomeMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    RelativeLayout rel_cart, rel_notification, menu;
    TextView user_namenav;
    ImageView profile_image, nav_backdraw;
    String ldata, str_name, str_image, id;
    TextView item_count;

    private int[] tabIcons = {R.drawable.promotion_icon, R.drawable.food_icon};
    FragmentManager fmgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_food);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN); // ******for Key board hiding
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        menu = (RelativeLayout) findViewById(R.id.menu);
        item_count=findViewById(R.id.item_count);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });


        rel_cart = (RelativeLayout) findViewById(R.id.rel_cart);
        rel_notification = (RelativeLayout) findViewById(R.id.rel_notification);


        rel_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeMainActivity.this, Notification_Activity.class);
                startActivity(intent);
                finish();
            }
        });

        rel_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeMainActivity.this, CartActivity.class);
                startActivity(intent);


            }
        });
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        setupViewPager(viewPager);


        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

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
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = View.inflate(getApplicationContext(), R.layout.nav_header_main_food, null);
        navigationView.getMenu().getItem(0).setActionView(R.layout.menu_image);
        navigationView.getMenu().getItem(1).setActionView(R.layout.menu_image);
        navigationView.getMenu().getItem(2).setActionView(R.layout.menu_image);
        navigationView.getMenu().getItem(3).setActionView(R.layout.menu_image);
        navigationView.getMenu().getItem(4).setActionView(R.layout.menu_image);
        navigationView.getMenu().getItem(5).setActionView(R.layout.menu_image);
        navigationView.getMenu().getItem(6).setActionView(R.layout.menu_image);

        final RelativeLayout nav_profile = (RelativeLayout) header.findViewById(R.id.nav_profile);
        user_namenav = (TextView) header.findViewById(R.id.user_namenav);
        profile_image = (CircleImageView) header.findViewById(R.id.profile_image);
        nav_backdraw = (ImageView) header.findViewById(R.id.nav_backdraw);
        nav_backdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.closeDrawer(GravityCompat.START);

            }
        });

//        nav_profile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(HomeMainActivity.this, EditProfile.class);
//                startActivity(intent);
//            }
//        });

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
            user_namenav.setText(str_name);
            // user_email.setText(str_email);


        }
        try {
            if (str_image != null) {

                Glide.with(HomeMainActivity.this).load(str_image).error(R.drawable.icons).into(profile_image);

            }
        } catch (Exception e) {
            Glide.with(HomeMainActivity.this).load(R.drawable.profile_imgbg).into(profile_image);
        }


        GetProfile(id);
        Cartcount(id);

        navigationView.addHeaderView(header);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {

                    //   updatetext(item);
                    drawer.openDrawer(GravityCompat.START);
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        final Dialog dialog = new Dialog(this);
        // Include dialog.xml file
        dialog.setContentView(R.layout.exitdialog);
        // Set dialog title

        // set values for custom dialog components - text, image and button
        ImageView image = (ImageView) dialog.findViewById(R.id.imageDialog);
        image.setImageResource(R.drawable.logo);
        dialog.show();
        TextView txt_msg = (TextView) dialog.findViewById(R.id.text_msgs);
        Button complet_btn = (Button) dialog.findViewById(R.id.btn_doneexit);
        Button declineButton = (Button) dialog.findViewById(R.id.btn_exitcancel);
        txt_msg.setText("Are you sure do you want to Exit");
        // if decline button is clicked, close the custom dialog
        declineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Writing data to SharedPreferences
                dialog.dismiss();


            }
        });
        complet_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.exit(0);

            }
        });
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_myprofile) {
            startActivity(new Intent(HomeMainActivity.this, Nav_MyProfile.class));
            // Handle the camera action
        } else if (id == R.id.nav_food) {
//            Intent intent = new Intent(HomeMainActivity.this, PromotionFragment.class);
//            startActivity(intent);
        } else if (id == R.id.nav_order) {

        } else if (id == R.id.nav_locateme) {
            startActivity(new Intent(HomeMainActivity.this, Location.class));
            finish();
        } else if (id == R.id.nav_msg) {

        } else if (id == R.id.nav_contactus) {

        } else if (id == R.id.nav_fav) {
            startActivity(new Intent(HomeMainActivity.this, Nav_favourite.class));
            finish();

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);

        View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.custom_tab_new, null);
        ((ImageView) view.findViewById(R.id.icon2)).setImageResource(R.drawable.promotion_selector);
        ((TextView) view.findViewById(R.id.tab)).setText("Promotion");

        View view2 = LayoutInflater.from(getApplicationContext()).inflate(R.layout.custom_tab_new, null);
        ((ImageView) view2.findViewById(R.id.icon2)).setImageResource(R.drawable.food_selector);
        ((TextView) view2.findViewById(R.id.tab)).setText("Food");


        tabLayout.getTabAt(0).setCustomView(view);
        tabLayout.getTabAt(1).setCustomView(view2);

    }

    private void setupViewPager(ViewPager viewPager) {
        HomeMainActivity.ViewPagerAdapter adapter = new HomeMainActivity.ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new Promotion_frameLayout(), "Promotion");
        adapter.addFrag(new Food_frameLayout(), "Food");
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
                        System.out.println("GETPROFILE" + object);
                        if (object.getString("status").equals("1")) {
                            GetProfileResponse getProfileResponse = gson.fromJson(responseData, GetProfileResponse.class);
                            user_namenav.setText(getProfileResponse.getResult().getName());
                            // Glide.with(HomeMainActivity.this).load(getProfileResponse.getResult().getImage()).into(profile_image);
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

    private void Cartcount(String user_id) {
        // bar.setVisibility(View.VISIBLE);
        Call<ResponseBody> call = AppConfig.PostInterface().CartCount(user_id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                //bar.setVisibility(View.INVISIBLE);
                if (response.isSuccessful()) {
                    try {
                        String responedata = response.body().string();
                        JSONObject object = new JSONObject(responedata);
                        String error = object.getString("status");
                        System.out.println("CartCount" + object);
                        if (error.equals("1")) {
                            String cartcount=object.getString("cart_count");
                            item_count.setText(cartcount);
                            saveData(getApplicationContext(),"cartcount",cartcount);

                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();

            }
        });

    }

}
