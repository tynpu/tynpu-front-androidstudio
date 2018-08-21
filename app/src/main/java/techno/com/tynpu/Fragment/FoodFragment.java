package techno.com.tynpu.Fragment;


import android.app.ProgressDialog;
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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import techno.com.tynpu.Activity.FilterActivity;
import techno.com.tynpu.Adapter.GetPostAdapter_ALLRestro;
import techno.com.tynpu.Model.GetAllRestroResult;
import techno.com.tynpu.Other.AppConfig;
import techno.com.tynpu.Other.GPSTracker;
import techno.com.tynpu.R;
import techno.com.tynpu.Response.GetAllRestroResponse;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoodFragment extends Fragment {
    RecyclerView.Adapter adapter=null;
    RecyclerView recyclerView;
    Button btn_filter;
    private ProgressBar bar;
    GPSTracker tracker;
    double latitude; // latitude
    double longitude; // longitude
    String str_lat, str_lon;


    public FoodFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_food, container, false);
      recyclerView =(RecyclerView)view.findViewById(R.id.rec_food);
        bar = (ProgressBar)view.findViewById(R.id.progressBarfood);


        btn_filter=(Button)view.findViewById(R.id.btn_filter);

        btn_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), FilterActivity.class);
                startActivity(intent);
            }
        });

        tracker = new GPSTracker(getContext());
        if (tracker.canGetLocation()) {

            latitude = tracker.getLatitude();
            longitude = tracker.getLongitude();
//            DataHolder.setLat(latitude);
//            DataHolder.setLng(longitude);
            System.out.println("Latitude" + latitude);
            System.out.println("Longitude" + longitude);
        }
        str_lat = String.valueOf(latitude);
        str_lon = String.valueOf(longitude);
        System.out.println("login=================lat" + str_lat);
        System.out.println("login=================lon" + str_lon);

        AllRestrocall(str_lat,str_lon);

        return view;
    }

    private void AllRestrocall(String lat,String lon)
    {
       bar.setVisibility(View.VISIBLE);
        Call<ResponseBody> call = AppConfig.PostInterface().ALLRestro(lat,lon);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

              bar.setVisibility(View.INVISIBLE);
                if (response.isSuccessful()) {
                    try {
                        String responedata=response.body().string();
                        JSONObject object=new JSONObject(responedata);
                        String error = object.getString("status");
                        System.out.println("NearRestaurent"+object);
                        if (error.equals("1")) {
                            Gson gson = new Gson();
                            GetAllRestroResponse successResponse = gson.fromJson(responedata,GetAllRestroResponse.class);
                            recyclerView.setHasFixedSize(true);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            GetPostAdapter_ALLRestro adapter = new GetPostAdapter_ALLRestro(getActivity(),successResponse.getResult());
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

                bar.setVisibility(View.INVISIBLE);
                Toast.makeText(getContext(), "Please check connection", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });

    }


}
