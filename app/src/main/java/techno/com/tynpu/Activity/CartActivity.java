package techno.com.tynpu.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import techno.com.tynpu.Adapter.GetPostAdapter_Cart;

import techno.com.tynpu.Other.AppConfig;
import techno.com.tynpu.R;

import techno.com.tynpu.Response.GetOrder_Response;

import static com.facebook.FacebookSdk.getApplicationContext;
import static techno.com.tynpu.constant.MySharedPref.getData;

/**
 * Created by user1 on 1/12/2018.
 */

public class CartActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RelativeLayout confirmorder;
    String ldata;
    String uid;
    ImageView img_getcartcross;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cartactivcity);
        recyclerView = (RecyclerView) findViewById(R.id.rec_cart);
        confirmorder = (RelativeLayout) findViewById(R.id.confirmorder);
        img_getcartcross = (ImageView) findViewById(R.id.img_getcartcross);

        img_getcartcross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, HomeMainActivity.class);
                startActivity(intent);
            }
        });

        confirmorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, OrderPayment.class);
                startActivity(intent);
            }
        });


        ldata = getData(getApplicationContext(), "ldata", "null");
        if (ldata != null) {
            try {
                JSONObject jsonObject = new JSONObject(ldata);
                System.out.println("Ldataint" + ldata);

                uid = jsonObject.getString("id");
                System.out.println("get order wali id===========================" + uid);
                // str_email=jsonObject.getString("email");
                //   id=jsonObject.getString("id");


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        GetOrdercall(uid);


    }

    private void GetOrdercall(String user_id) {
        //    bar.setVisibility(View.VISIBLE);
        Call<ResponseBody> call = AppConfig.PostInterface().GetOrder(user_id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                //bar.setVisibility(View.INVISIBLE);
                if (response.isSuccessful()) {
                    try {
                        String responedata = response.body().string();
                        JSONObject object = new JSONObject(responedata);
                        String error = object.getString("status");
                        System.out.println("GetOrder"+ object);
                        if (error.equals("1")){
                            Gson gson = new Gson();
                            GetOrder_Response successResponse = gson.fromJson(responedata, GetOrder_Response.class);
                            recyclerView.setHasFixedSize(true);
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(CartActivity.this);
                            recyclerView.setLayoutManager(mLayoutManager);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            GetPostAdapter_Cart adapter = new GetPostAdapter_Cart(CartActivity.this, successResponse.getResult());
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

}
