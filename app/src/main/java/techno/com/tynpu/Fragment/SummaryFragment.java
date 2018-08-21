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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import techno.com.tynpu.Activity.CartActivity;
import techno.com.tynpu.Activity.FoodNextActivity;
import techno.com.tynpu.Adapter.GetPostAdapter_Cart;
import techno.com.tynpu.Adapter.GetPostAdapter_ExtraFragment;
import techno.com.tynpu.Adapter.GetPostAdapter_SummaryFoodFragment;
import techno.com.tynpu.Adapter.GetPostAdapter_SummaryFragment;
import techno.com.tynpu.Model.DataHolder;
import techno.com.tynpu.Model.DataHolderFood;
import techno.com.tynpu.Other.AppConfig;
import techno.com.tynpu.R;
import techno.com.tynpu.Response.ExtraFood_Response;
import techno.com.tynpu.Response.GetCartResponse;
import techno.com.tynpu.Response.GetExtraFragResponse;
import techno.com.tynpu.Response.GetSummaryFood_Response;
import techno.com.tynpu.Response.GetSummaryResponse;

import static com.facebook.FacebookSdk.getApplicationContext;
import static techno.com.tynpu.constant.MySharedPref.getData;

/**
 * A simple {@link Fragment} subclass.
 */
public class SummaryFragment extends Fragment {
    LinearLayout addtovcart;
    RelativeLayout menu, rel_cart;
    private ProgressBar bar;
    RecyclerView recyclerView;
    String ldata, str_uid;
    ImageView view_base_flipper;
    TextView tv_veg_subfoodname;

    String strfoodcatImage, str_foodcatname, str_foodcatSubID;
    String Restroid, strfoodcatID;
    Button btn_place_order_food;
    RadioButton radioawaypromotion, radiohomepromotion;
    String nameDays = "";
    String strcartcount;
    TextView item_count;


    public SummaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_summary, container, false);
        bar = (ProgressBar) view.findViewById(R.id.progressBarsummary);
        recyclerView = (RecyclerView) view.findViewById(R.id.rec_summary);

        addtovcart = (LinearLayout) view.findViewById(R.id.addtovcart);
        rel_cart = (RelativeLayout) view.findViewById(R.id.rel_cart);
        menu = (RelativeLayout) view.findViewById(R.id.menu);
        btn_place_order_food = (Button) view.findViewById(R.id.btn_place_order_food);
        radioawaypromotion = (RadioButton) view.findViewById(R.id.radioawaypromotion);
        radiohomepromotion = (RadioButton) view.findViewById(R.id.radiohomepromotion);
        item_count=view.findViewById(R.id.item_count);


        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        rel_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CartActivity.class);
                startActivity(intent);
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

        addtovcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FoodNextActivity.class);
                startActivity(intent);
            }
        });

        SummaryCall(str_uid);

//        menu=(RelativeLayout)view.findViewById(R.id.menu);
//        menu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getActivity().finish();
//            }
//        });


        btn_place_order_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DataHolderFood.getGetSummaryFood_results() != null) {

                    for (int i = 0; i < DataHolderFood.getGetSummaryFood_results().size(); i++) {
                        if (nameDays.equals(""))
                            nameDays = DataHolderFood.getGetSummaryFood_results().get(i).getCartId();
                        else
                            nameDays = nameDays + "," + DataHolderFood.getGetSummaryFood_results().get(i).getCartId();
                    }

                    System.out.println("cart===ki=====ID" + nameDays);


                    if (radioawaypromotion.isChecked()) {

                        PlaceOrder(str_uid, nameDays, "Take Away");
                        // Toast.makeText(getActivity(), "yhi searve krne wala", Toast.LENGTH_SHORT).show();
                    } else if (radiohomepromotion.isChecked()) {
                        PlaceOrder(str_uid, nameDays, "Take Home");
                        //Toast.makeText(getActivity(), "ghr le jane wala", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "Please Select Order", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return view;
    }

    private void SummaryCall(String user_id) {
        bar.setVisibility(View.VISIBLE);
        Call<ResponseBody> call = AppConfig.PostInterface().Getcart(user_id, "Food");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                bar.setVisibility(View.INVISIBLE);
                if (response.isSuccessful()) {
                    try {
                        String responedata = response.body().string();
                        JSONObject object = new JSONObject(responedata);
                        String error = object.getString("status");
                        System.out.println("GetcartSummary" + object);
                        if (error.equals("1")) {
                            Gson gson = new Gson();
                            GetCartResponse successResponse = gson.fromJson(responedata, GetCartResponse.class);
                            recyclerView.setHasFixedSize(true);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            DataHolderFood.setGetSummaryFood_results(successResponse.getResult());
                            GetPostAdapter_SummaryFoodFragment adapter = new GetPostAdapter_SummaryFoodFragment(getActivity(), successResponse.getResult());
                            recyclerView.setAdapter(adapter);
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
    //***************************  PLACE ORDER  ********************************************************

    private void PlaceOrder(String user_id, String cart_id, String delivery_type) {
        Call<ResponseBody> call = AppConfig.PostInterface().PlaceOrder(user_id, cart_id, delivery_type, "Food");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {

                    if (response.isSuccessful()) {
                        String responseData = response.body().string();
                        JSONObject object = new JSONObject(responseData);
                        String status = object.getString("status");
                        System.out.println("Placeorder" + object);

                        if (status.equals("1")) {
                            Intent intent = new Intent(getContext(), CartActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getActivity(), "Please Check Connection", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
