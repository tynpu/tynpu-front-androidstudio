package techno.com.tynpu.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import techno.com.tynpu.Activity.FoodNextActivity;
import techno.com.tynpu.Adapter.GetPostAdapter_ALLRestro;
import techno.com.tynpu.Adapter.GetPostAdapter_FoodAllProduct;
import techno.com.tynpu.Other.AppConfig;
import techno.com.tynpu.R;
import techno.com.tynpu.Response.GetAllRestroResponse;
import techno.com.tynpu.Response.GetFoodProductResponse;

import static techno.com.tynpu.constant.MySharedPref.getData;

/**
 * A simple {@link Fragment} subclass.
 */
public class Letter extends Fragment {
    RecyclerView recyclerView;

    private ProgressBar bar;
    String Restroid;

    public Letter() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_letter, container, false);
        bar=(ProgressBar)view.findViewById(R.id.progressBarletter);
        recyclerView =(RecyclerView)view.findViewById(R.id.rec_letter);


        Restroid=getData(getActivity(),"resid","null");


        System.out.println("resID=============================================letterwali"+Restroid);



        //AllFoodcall(Restroid);
        AllFoodcall("21");
        return view;
    }

    private void AllFoodcall(final String restaurant_id)
    {
        bar.setVisibility(View.VISIBLE);
        Call<ResponseBody> call = AppConfig.PostInterface().AllFoodDetail(restaurant_id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                bar.setVisibility(View.INVISIBLE);
                if (response.isSuccessful()) {
                    try {
                        String responedata=response.body().string();
                        JSONObject object=new JSONObject(responedata);
                        String error = object.getString("status");
                        System.out.println("AllAvailablefood"+object);
                        if (error.equals("1")) {
                            Gson gson = new Gson();
                            GetFoodProductResponse successResponse = gson.fromJson(responedata,GetFoodProductResponse.class);
                            JSONArray arr = object.getJSONArray("result");
                            recyclerView.setHasFixedSize(true);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            GetPostAdapter_FoodAllProduct adapter = new GetPostAdapter_FoodAllProduct(getActivity(),successResponse.getResult(),arr);
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
            }
        });

    }

}
