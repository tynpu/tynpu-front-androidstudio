package techno.com.tynpu.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import techno.com.tynpu.Activity.CartActivity;
import techno.com.tynpu.Adapter.GetPostAdapter_SubSubFoodrequest;
import techno.com.tynpu.Adapter.PromotionBase_Getitemdetal;
import techno.com.tynpu.Other.AppConfig;
import techno.com.tynpu.R;
import techno.com.tynpu.Response.GetSubSubFoodProductResponse;
import techno.com.tynpu.Response.Promotion_Base_Resonse;

import static com.facebook.FacebookSdk.getApplicationContext;
import static techno.com.tynpu.constant.MySharedPref.getData;

/**
 * A simple {@link Fragment} subclass.
 */
public class BasePromotion extends Fragment {
    TextView tv_veg_subfoodname, tv_maincouseprice;
    RecyclerView recyclerView;
    String str_promotionID;
    ImageView view_base_flipper;
    String strfoodcatImage, str_foodcatname, str_foodcatprice;
    String str_uid;
    private ProgressBar bar;
    String ldata;
    RelativeLayout menu;
    RelativeLayout rel_cart;
    String proID=null;
    String str_resID=null;
    String strcartcount;
    TextView item_count;



    public BasePromotion() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_base_promotion,container, false);

        tv_veg_subfoodname = (TextView) view.findViewById(R.id.tv_veg_pro_subfoodname);
        recyclerView = (RecyclerView) view.findViewById(R.id.rec_bease_pro);
        bar = (ProgressBar) view.findViewById(R.id.progressBarbase);
        view_base_flipper = (ImageView) view.findViewById(R.id.view_base_pro_flipper);
        tv_maincouseprice = (TextView) view.findViewById(R.id.tv_pro_maincouseprice);
        menu=(RelativeLayout)view.findViewById(R.id.menu);
        rel_cart=(RelativeLayout)view.findViewById(R.id.rel_cart);
        item_count=view.findViewById(R.id.item_count);

        rel_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), CartActivity.class);
                startActivity(intent);
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        ldata = getData(getApplicationContext(), "ldata", "null");
        if (ldata != null) {
            try {
                JSONObject jsonObject = new JSONObject(ldata);
                System.out.println("Ldataint" + ldata);

                str_uid = jsonObject.getString("id");
                // str_email=jsonObject.getString("email");
                //   id=jsonObject.getString("id");


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        strcartcount=getData(getApplicationContext(),"cartcount",null);
        item_count.setText(strcartcount);


        str_promotionID = getData(getContext(), "promotionID", null);
        System.out.println("promotionID========================================" + str_promotionID);

        str_foodcatprice = getData(getContext(), "proprice", null);
        str_foodcatname = getData(getContext(), "proname", null);
        strfoodcatImage = getData(getContext(), "proImage", null);

        System.out.println("soutindian====wala===name======================" + str_foodcatname);
        System.out.println("soutindian====wala===price======================" + str_foodcatprice);
        System.out.println("soutindian====wala===images======================" + strfoodcatImage);
        System.out.println("userID==========================================="+str_uid);
        tv_veg_subfoodname.setText(str_foodcatname);
        tv_maincouseprice.setText(str_foodcatprice);

        //**********************  FOR PROMOTION SIDE  **********************************************************
        proID=getData(getApplicationContext(),"productID",null);

        str_resID=getData(getApplicationContext(),"restaID",null);


        if (strfoodcatImage != null)
        {
            Glide.with(getContext()).load(strfoodcatImage).into(view_base_flipper);
        }
        basepromotioncall(str_promotionID,proID);

        return view;
    }

    private void basepromotioncall (String promotion_id,String product_id) {
        bar.setVisibility(View.VISIBLE);
        Call<ResponseBody> call = AppConfig.PostInterface().BasePromotion(promotion_id,product_id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                bar.setVisibility(View.INVISIBLE);
                if (response.isSuccessful()) {
                    try {
                        String responedata = response.body().string();
                        JSONObject object = new JSONObject(responedata);
                        String error = object.getString("status");
                        System.out.println("itemmm" + object);
                        if (error.equals("1")) {
                            Gson gson = new Gson();
                            Promotion_Base_Resonse successResponse = gson.fromJson(responedata, Promotion_Base_Resonse.class);
                            recyclerView.setHasFixedSize(true);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            PromotionBase_Getitemdetal adapter = new PromotionBase_Getitemdetal(getActivity(), successResponse.getResult());
                            recyclerView.setAdapter(adapter);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    //Snackbar.make(parentView, R.string.string_upload_fail, Snackbar.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

}
