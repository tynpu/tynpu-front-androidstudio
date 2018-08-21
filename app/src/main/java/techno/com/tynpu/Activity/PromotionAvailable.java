package techno.com.tynpu.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import techno.com.tynpu.Adapter.GetPostAdapter_available_food;
import techno.com.tynpu.Other.AppConfig;
import techno.com.tynpu.R;
import techno.com.tynpu.Response.GetPromotionAvailbleResopnse;

import static techno.com.tynpu.constant.MySharedPref.getData;

/**
 * Created by user1 on 1/17/2018.
 */

public class PromotionAvailable extends AppCompatActivity {
    RelativeLayout menu;
    private ProgressBar bar;
    RecyclerView recyclerView;
    String Str_res_ID;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_promotionsclick);
        menu = (RelativeLayout) findViewById(R.id.menu);
        recyclerView=(RecyclerView)findViewById(R.id.rec_order);

        Str_res_ID=getData(PromotionAvailable.this,"promotion_ID",null);
        bar=(ProgressBar)findViewById(R.id.progressBaravailavlre);


        System.out.println("promotionID=========="+Str_res_ID);


        AllAvailablePromotion(Str_res_ID);
    }

    private void AllAvailablePromotion(final String promotion_id)
    {
        bar.setVisibility(View.VISIBLE);
        Call<ResponseBody> call = AppConfig.PostInterface().GetAvailablePromotion(promotion_id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                bar.setVisibility(View.INVISIBLE);
                if (response.isSuccessful()) {
                    try {
                        String responedata=response.body().string();
                        JSONObject object=new JSONObject(responedata);
                        String error = object.getString("status");
                        System.out.println("AvailablePromotion"+object);
                        if (error.equals("1")) {
                            Gson gson = new Gson();
                            GetPromotionAvailbleResopnse successResponse = gson.fromJson(responedata,GetPromotionAvailbleResopnse.class);
                            recyclerView.setHasFixedSize(true);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(PromotionAvailable.this);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            GetPostAdapter_available_food adapter = new GetPostAdapter_available_food(PromotionAvailable.this,successResponse.getResult());
                            recyclerView.setAdapter(adapter);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                else
                {
                    //Snackbar.make(parentView, R.string.string_upload_fail, Snackbar.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(PromotionAvailable.this, "Please Check Connection", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
