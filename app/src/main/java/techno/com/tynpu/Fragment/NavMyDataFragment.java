package techno.com.tynpu.Fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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
import techno.com.tynpu.Activity.Login;
import techno.com.tynpu.Other.AppConfig;
import techno.com.tynpu.R;
import techno.com.tynpu.Response.GetProfileResponse;

import static com.facebook.FacebookSdk.getApplicationContext;
import static techno.com.tynpu.R.id.user_namenav;
import static techno.com.tynpu.constant.MySharedPref.getData;
import static techno.com.tynpu.constant.MySharedPref.saveData;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavMyDataFragment extends Fragment {
    TextView tv_gendername,tv_profile_name,tv_pofileemail,tv_dateofbirth,tv_pronumber;
    String ldata,id;
    LinearLayout lay_logout;


    public NavMyDataFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_nav_my_data, container, false);
        tv_gendername=(TextView)view.findViewById(R.id.tv_gendername);
        tv_profile_name=(TextView)view.findViewById(R.id.tv_profile_name);
        tv_pofileemail=(TextView)view.findViewById(R.id.tv_pofileemail);
        tv_dateofbirth=(TextView)view.findViewById(R.id.tv_dateofbirth);
        tv_pronumber=(TextView)view.findViewById(R.id.tv_pronumber);
        lay_logout=(LinearLayout)view.findViewById(R.id.lay_logout);


        ldata = getData(getContext(), "ldata", "null");
        if (ldata != null) {
            try {
                JSONObject jsonObject = new JSONObject(ldata);
                System.out.println("Ldataint" + ldata);

                id = jsonObject.getString("id");
                // str_email=jsonObject.getString("email");
                //   id=jsonObject.getString("id");


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        lay_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData(getApplicationContext(),"ldata",null);
                Intent intent=new Intent(getContext(),Login.class);
                startActivity(intent);
                getActivity().finish();
                Toast.makeText(getContext(), "Logout Successfully ", Toast.LENGTH_SHORT).show();
            }
        });

        GetProfile(id);

        /**/

        return view;
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
                        System.out.println("Mydataprofile" + object);
                        if (object.getString("status").equals("1")) {
                            GetProfileResponse getProfileResponse = gson.fromJson(responseData, GetProfileResponse.class);

                            tv_profile_name.setText(getProfileResponse.getResult().getName());
                            tv_gendername.setText(getProfileResponse.getResult().getGender());
                            tv_dateofbirth.setText(getProfileResponse.getResult().getDateOfBirth());
                            tv_pronumber.setText(getProfileResponse.getResult().getMobile());
                            tv_pofileemail.setText(getProfileResponse.getResult().getEmail());

                           // user_namenav.setText(getProfileResponse.getResult().getName());
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


}
