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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import techno.com.tynpu.Adapter.GetPostAdapter_ExtraPromotionFragment;
import techno.com.tynpu.Model.DataHolderActive;
import techno.com.tynpu.Other.AppConfig;
import techno.com.tynpu.R;
import techno.com.tynpu.Response.ExtraFood_Response;
import techno.com.tynpu.Response.GetCartResponse;
import techno.com.tynpu.Response.GetSummaryResponse;

import static com.facebook.FacebookSdk.getApplicationContext;
import static techno.com.tynpu.Adapter.PromotionBase_Getitemdetal.seleceditem;
import static techno.com.tynpu.constant.MySharedPref.getData;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExtraPRomotion extends Fragment {
    private ProgressBar bar;
    RecyclerView recyclerView;
    String ldata, str_uid;
    ImageView view_base_flipper;
    TextView tv_veg_subfoodname;
    RelativeLayout menu, rel_cart;
    String strfoodcatImage, str_foodcatname, proID;
    String str_promotionID, str_foodcatprice, Restroid, Str_promotion_proID;
    String strItemID,strcartcount;
    TextView item_count;


    public ExtraPRomotion() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_extra_promotion, container, false);
        bar = (ProgressBar) view.findViewById(R.id.progressBarextra);
        recyclerView = (RecyclerView) view.findViewById(R.id.rec_extraa);
        view_base_flipper = (ImageView) view.findViewById(R.id.view_base_flipperpro);
        tv_veg_subfoodname = (TextView) view.findViewById(R.id.tv_fnamepro);
        //tv_maincouseprice = (TextView) view.findViewById(R.id.tv_maincouseprice);
        menu = (RelativeLayout) view.findViewById(R.id.menu);
        rel_cart = (RelativeLayout) view.findViewById(R.id.rel_cart);
        item_count=view.findViewById(R.id.item_count);

        strItemID=getData(getContext(),"productID",null);
        System.out.println("product==========ID=========="+strItemID);

        if (seleceditem!=null) {
            String s = null;
            for (int i = 0; i < seleceditem.size(); i++) {
                DataHolderActive hold = seleceditem.get(i);
                if (hold.getStatus().equals("true")) {
                    if (s == null) {
                        s = hold.getID();
                    } else {
                        s = s + "," + hold.getID();
                    }
                }
            }
            System.out.println("----------id------------- " + s);

            menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().finish();
                }
            });

            ldata = getData(getApplicationContext(), "ldata", null);
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


            QuantityAdded(str_uid, strItemID, s, "1");
        }

        rel_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CartActivity.class);
                startActivity(intent);
            }
        });
        strcartcount=getData(getApplicationContext(),"cartcount",null);
        item_count.setText(strcartcount);

        str_promotionID = getData(getContext(), "promotionID", null);
        System.out.println("promotionID========================================" + str_promotionID);

        str_foodcatprice = getData(getContext(), "proprice", null);
        str_foodcatname = getData(getContext(), "proname", null);
        strfoodcatImage = getData(getContext(), "proImage", null);
        Str_promotion_proID = getData(getContext(), "promosubID", null);

        System.out.println("soutindian====wala===name======================" + str_foodcatname);
        System.out.println("soutindian====wala===price======================" + str_foodcatprice);
        System.out.println("soutindian====wala===images======================" + strfoodcatImage);
        System.out.println("userID===========================================" + str_uid);
        tv_veg_subfoodname.setText(str_foodcatname);
        // tv_maincouseprice.setText(str_foodcatprice);
        Restroid = getData(getApplicationContext(), "restaID", null);

        if (strfoodcatImage != null) {

            Glide.with(getContext()).load(strfoodcatImage).into(view_base_flipper);

        }



        //ExtraCAll(str_uid, Restroid, str_promotionID, Str_promotion_proID);


        return view;
    }


//***********************************************************  ADD TO CARTR   ****************************

    private void QuantityAdded(final String user_id, String item_id,String sub_item_id, String quantity) {
        // bar.setVisibility(View.VISIBLE);
        Call<ResponseBody> call = AppConfig.PostInterface().Sendquantity(user_id, item_id,sub_item_id, quantity,"Promotion");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //   bar.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()) {
                        String responseData = response.body().string();
                        JSONObject object = new JSONObject(responseData);
                        System.out.println("Addtocart" + object);
                        if (object.getString("status").equals("1")) {
                            GetCart(str_uid);
                            Toast.makeText(getActivity(), "Added Quantity", Toast.LENGTH_SHORT).show();

                        } else if (object.getString("status").equals("0")) {
                            Toast.makeText(getActivity(), "Somthing Went Wrong", Toast.LENGTH_SHORT).show();
                        }

                    } else ;

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });
    }


//*******************************  GET CARt  ***********************************************************
private void GetCart(String user_id) {
    bar.setVisibility(View.VISIBLE);
    Call<ResponseBody> call = AppConfig.PostInterface().Getcart(user_id,"Promotion");
    call.enqueue(new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            bar.setVisibility(View.INVISIBLE);
            if (response.isSuccessful()) {
                try {
                    String responedata = response.body().string();
                    JSONObject object = new JSONObject(responedata);
                    String error = object.getString("status");
                    System.out.println("GetCart_ExtraPromoion" + object);
                    if (error.equals("1")) {
                        Gson gson = new Gson();
                        GetCartResponse successResponse = gson.fromJson(responedata, GetCartResponse.class);
                        recyclerView.setHasFixedSize(true);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());

                        GetPostAdapter_ExtraPromotionFragment adapter = new GetPostAdapter_ExtraPromotionFragment(getActivity(),successResponse.getResult());
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













//
//    //     *******************************  Extra   ********************************************************
//
//    private void ExtraCAll(String user_id, String restaurant_id, String promotion_id, String promotion_product_id) {
//        bar.setVisibility(View.VISIBLE);
//        Call<ResponseBody> call = AppConfig.PostInterface().GetExtraPromotion(user_id, restaurant_id, promotion_id, promotion_product_id);
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//
//                bar.setVisibility(View.INVISIBLE);
//                if (response.isSuccessful()) {
//                    try {
//                        String responedata = response.body().string();
//                        JSONObject object = new JSONObject(responedata);
//                        String error = object.getString("status");
//                        System.out.println("GetExtraPromotion" + object);
//                        if (error.equals("1")) {
//                            Gson gson = new Gson();
//                            Extra_Promotion_Response successResponse = gson.fromJson(responedata, Extra_Promotion_Response.class);
//                            recyclerView.setHasFixedSize(true);
//                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
//                            recyclerView.setLayoutManager(layoutManager);
//                            recyclerView.setItemAnimator(new DefaultItemAnimator());
//                            GetPostAdapter_ExtraPromotionFragment adapter = new GetPostAdapter_ExtraPromotionFragment(getActivity(), successResponse.getResult());
//                            recyclerView.setAdapter(adapter);
//                        } else if (error.equals("0")) {
//                            Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
//
//                        }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                bar.setVisibility(View.INVISIBLE);
//                Toast.makeText(getActivity(), "Please Check Connection", Toast.LENGTH_SHORT).show();
//                t.printStackTrace();
//            }
//        });
//
//    }

}
