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
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import techno.com.tynpu.Activity.CartActivity;
import techno.com.tynpu.Adapter.ExtraFragment_Adapter;
import techno.com.tynpu.Adapter.GetPostAdapter_ExtraFragment;
import techno.com.tynpu.Adapter.GetPostAdapter_ExtraPromotionFragment;
import techno.com.tynpu.Model.DataHolderActiveFood;
import techno.com.tynpu.Model.GetExtraFragResult;
import techno.com.tynpu.Other.AppConfig;
import techno.com.tynpu.R;
import techno.com.tynpu.Response.ExtraFood_Response;
import techno.com.tynpu.Response.GetCartResponse;
import techno.com.tynpu.Response.GetExtraFragResponse;
import techno.com.tynpu.Response.GetSummaryResponse;

import static com.facebook.FacebookSdk.getApplicationContext;
import static techno.com.tynpu.Adapter.GetPostAdapter_SubSubFoodrequest.seleceditem;
import static techno.com.tynpu.constant.MySharedPref.getData;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExtraFragment extends Fragment {

//    int minteger=1;
//    TextView quantity;
//    String str_quantity;
//    TextView increaseInteger,remove_item;
private ProgressBar bar;
    RecyclerView recyclerView;
    String ldata,str_uid;
    ImageView view_base_flipper;
    TextView tv_veg_subfoodname,item_count;
    RelativeLayout menu,rel_cart;
    String strfoodcatImage,str_foodcatname,str_foodcatSubID;
    String Restroid,strfoodcatID,strcartcount;



    public ExtraFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_extra, container, false);
        bar = (ProgressBar) view.findViewById(R.id.progressBarextra);
        recyclerView = (RecyclerView) view.findViewById(R.id.rec_extra);
        view_base_flipper = (ImageView) view.findViewById(R.id.view_base_flipper1);
        tv_veg_subfoodname = (TextView) view.findViewById(R.id.tv_fname);
        //tv_maincouseprice = (TextView) view.findViewById(R.id.tv_maincouseprice);
        menu=(RelativeLayout)view.findViewById(R.id.menu);
        rel_cart=(RelativeLayout)view.findViewById(R.id.rel_cart);
        item_count=view.findViewById(R.id.item_count);

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

        if(seleceditem!=null) {
            String s = null;

            for (int i = 0; i < seleceditem.size(); i++) {
                DataHolderActiveFood hold = seleceditem.get(i);
                if (hold.getStatus().equals("true")) {
                    if (s == null) {
                        s = hold.getID();
                    } else {
                        s = s + "," + hold.getID();
                    }
                }
            }
            System.out.println("----------id------------- " + s);


            rel_cart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), CartActivity.class);
                    startActivity(intent);
                }
            });



            Restroid = getData(getContext(), "resid", null);

            strfoodcatID = getData(getContext(), "foodcatID", null);
            str_foodcatname = getData(getContext(), "foodcatname", null);
            strfoodcatImage = getData(getContext(), "FoodcatImage", null);

            System.out.println("Extra_Food_Name___________________"+str_foodcatname);
            System.out.println("Extra_Food_Image___________________"+strfoodcatImage);
            System.out.println("Extra_Food_id___________________"+strfoodcatID);



            tv_veg_subfoodname.setText(str_foodcatname);


            if (strfoodcatImage != null) {
                Glide.with(getContext()).load(strfoodcatImage).into(view_base_flipper);
            }

            QuantityAdded(str_uid, strfoodcatID, s, "1");
        }
       //ExtraCAll(str_uid,Restroid,strfoodcatID,str_foodcatSubID);

        /*   Restroid = getData(activity, "resid", "null");
            strfoodcatID = getData(activity, "foodcatID", "null");
            str_foodcatSubID = getData(activity, "foodcatproID", "null");


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


            System.out.println("Base===wali====res===ID=======" + Restroid);
            System.out.println("Base===wali====food=====cat===ID=======" + strfoodcatID);
            System.out.println("Base===wali====food=====cat==Sub=====ID=======" + str_foodcatSubID);
            System.out.println("Base===wali====food=====User===ID=======" + str_uid);
*/

       // ExtraCAll(str_uid);

//        quantity= (TextView)view.findViewById(R.id.iteam_amount);
//        increaseInteger=(TextView)view.findViewById(R.id.add_item);
//        remove_item=(TextView)view.findViewById(R.id.remove_item);
//
//        increaseInteger.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                minteger = minteger + 1;
//                display(minteger);
//            }
//        });
//
//        remove_item.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (minteger<=1)
//                {
//
//
//                }
//                else {
//
//                    minteger = minteger - 1;
//                }
//
//                display(minteger);
//            }
//        });

        return view;
    }
//    private void ExtraCAll(String user_id,String restaurant_id,String food_category_id,String food_category_product_id) {
//        bar.setVisibility(View.VISIBLE);
//        Call<ResponseBody> call = AppConfig.PostInterface().ExtraFrag(user_id,restaurant_id,food_category_id,food_category_product_id);
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
//                        System.out.println("GEtExtraCart" + object);
//                        if (error.equals("1")) {
//                            Gson gson = new Gson();
//                            GetExtraFragResponse successResponse = gson.fromJson(responedata, GetExtraFragResponse.class);
//                            recyclerView.setHasFixedSize(true);
//                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
//                            recyclerView.setLayoutManager(layoutManager);
//                            recyclerView.setItemAnimator(new DefaultItemAnimator());
//                            GetPostAdapter_ExtraFragment adapter = new GetPostAdapter_ExtraFragment(getActivity(),successResponse.getResult());
//                            recyclerView.setAdapter(adapter);
//                        }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                } else {
//                    //Snackbar.make(parentView, R.string.string_upload_fail, Snackbar.LENGTH_LONG).show();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                t.printStackTrace();
//            }
//        });
//
//    }
////    private void display(int number) {
////
////        quantity.setText("" + number);
////
////        str_quantity= String.valueOf(number);
////        System.out.println("number"+number);
////
////        //  Toast.makeText(All_Product_Detail.this, "You Selected "+number, Toast.LENGTH_SHORT).show();
////
////    }


//***********************************************************  ADD TO CARTR   ****************************

    private void QuantityAdded(final String user_id, String item_id,String sub_item_id, String quantity) {
        // bar.setVisibility(View.VISIBLE);
        Call<ResponseBody> call = AppConfig.PostInterface().Sendquantity(user_id, item_id,sub_item_id, quantity,"Food");
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
        Call<ResponseBody> call = AppConfig.PostInterface().Getcart(user_id,"Food");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                bar.setVisibility(View.INVISIBLE);
                if (response.isSuccessful()) {
                    try {
                        String responedata = response.body().string();
                        JSONObject object = new JSONObject(responedata);
                        String error = object.getString("status");
                        System.out.println("GetCart_Food" + object);
                        if (error.equals("1")) {
                            Gson gson = new Gson();
                            GetCartResponse successResponse = gson.fromJson(responedata, GetCartResponse.class);
                            recyclerView.setHasFixedSize(true);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());

                            //GetPostAdapter_ExtraPromotionFragment adapter = new GetPostAdapter_ExtraPromotionFragment(getActivity(),successResponse.getResult());
                            ExtraFragment_Adapter adapter = new ExtraFragment_Adapter(getActivity(),successResponse.getResult());
                            //GetExtraFragResponse
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
