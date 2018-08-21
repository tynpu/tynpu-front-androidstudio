package techno.com.tynpu.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Stack;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import techno.com.tynpu.Activity.CartActivity;
import techno.com.tynpu.Adapter.GetPostAdapter_GetCartFoodrequest;
import techno.com.tynpu.Adapter.GetPostAdapter_SubSubFoodrequest;
import techno.com.tynpu.Other.AppConfig;
import techno.com.tynpu.R;

import techno.com.tynpu.Response.GetSubSubFoodProductResponse;

import static com.facebook.FacebookSdk.getApplicationContext;
import static techno.com.tynpu.constant.MySharedPref.getData;


/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment {

    TextView tv_veg_subfoodname, tv_maincouseprice;
    RecyclerView recyclerView;
    String str_foodcatSubID;
    ImageView view_base_flipper;
    String strfoodcatImage, str_foodcatname, str_foodcatprice;
    String str_uid;
    private ProgressBar bar;
    String ldata;
    RelativeLayout menu;
    RelativeLayout rel_cart;
    String proID = null;
    String str_resID = null;
    String strcartcount;
    TextView item_count;

    String subListowner;
    private JSONArray arr;
    GetPostAdapter_SubSubFoodrequest adapter;

    public BaseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_base, container, false);
        tv_veg_subfoodname = (TextView) view.findViewById(R.id.tv_veg_subfoodname);
        recyclerView = (RecyclerView) view.findViewById(R.id.rec_bease);
        bar = (ProgressBar) view.findViewById(R.id.progressBarbase);
        view_base_flipper = (ImageView) view.findViewById(R.id.view_base_flipper);
        tv_maincouseprice = (TextView) view.findViewById(R.id.tv_maincouseprice);
        menu = (RelativeLayout) view.findViewById(R.id.menu);
        rel_cart = (RelativeLayout) view.findViewById(R.id.rel_cart);
        item_count=view.findViewById(R.id.item_count);

        rel_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CartActivity.class);
                startActivity(intent);
            }
        });

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


      //  subListowner = getActivity().getIntent().getExtras().getString("data");
        subListowner=getData(getActivity(),"data",null);
        try {
            arr=new JSONArray(subListowner);
            System.out.println("arr____________________"+arr);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println("DATA______________________________"+subListowner);
//        str_foodcatSubID = getData(getContext(), "foodcatproID", "null");
//        System.out.println("foodcatSUBID========================================" + str_foodcatSubID);
//
        str_foodcatprice = getData(getContext(), "FoodcatPrice", null);
        str_foodcatname = getData(getContext(), "foodcatname", null);
        strfoodcatImage = getData(getContext(), "FoodcatImage", null);
//
//        System.out.println("soutindian====wala===name======================" + str_foodcatname);
//        System.out.println("soutindian====wala===price======================" + str_foodcatprice);
//        System.out.println("soutindian====wala===images======================" + strfoodcatImage);
        System.out.println("userID===========================================" + str_uid);



        strcartcount=getData(getApplicationContext(),"cartcount",null);
        item_count.setText(strcartcount);

        tv_veg_subfoodname.setText(str_foodcatname);
        tv_maincouseprice.setText(str_foodcatprice);

        //**********************  FOR PROMOTION SIDE  **********************************************************
        proID = getData(getApplicationContext(), "productID", null);
        str_resID = getData(getApplicationContext(), "restaID", null);

        if (strfoodcatImage != null) {

            Glide.with(getContext()).load(strfoodcatImage).into(view_base_flipper);

        }

        //SubFOODProduct(str_foodcatSubID);
        RecyclerView.LayoutManager mLayoutManager =new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter=new GetPostAdapter_SubSubFoodrequest(getActivity(),arr);
        recyclerView.setAdapter(adapter);

        return view;
    }

//    private void SubFOODProduct(String food_category_product_id) {
//        bar.setVisibility(View.VISIBLE);
//        Call<ResponseBody> call = AppConfig.PostInterface().SubSubFoodProduct(food_category_product_id);
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
//                        System.out.println("SubSubfood" + object);
//                        if (error.equals("1")) {
//                            Gson gson = new Gson();
//                            GetSubSubFoodProductResponse successResponse = gson.fromJson(responedata, GetSubSubFoodProductResponse.class);
//                            recyclerView.setHasFixedSize(true);
//                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
//                            recyclerView.setLayoutManager(layoutManager);
//                            recyclerView.setItemAnimator(new DefaultItemAnimator());
//                            adapter = new GetPostAdapter_SubSubFoodrequest(getActivity(), successResponse.getResult());
//                            recyclerView.setAdapter(adapter);
//                        }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//
//                } else {
//                    //Snackbar.make(parentView, R.string.string_upload_fail, Snackbar.LENGTH_LONG).show();
//                }
//            }
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                t.printStackTrace();
//                Toast.makeText(getActivity(), "Please Check Connection", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

}
