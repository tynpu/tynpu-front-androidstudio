package techno.com.tynpu.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import techno.com.tynpu.Other.AppConfig;
import techno.com.tynpu.R;
import techno.com.tynpu.Response.GetProfileResponse;

import static techno.com.tynpu.constant.MySharedPref.getData;

public class SendComment extends AppCompatActivity {
    TextView tv_email,tv_mobile;
    String ldata,str_name,id,stremail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_comment);
        tv_email=findViewById(R.id.tv_email);
        tv_mobile=findViewById(R.id.tv_mobile);

        ldata = getData(getApplicationContext(), "ldata", "null");
        if (ldata != null) {
            try {
                JSONObject jsonObject = new JSONObject(ldata);
                System.out.println("Ldataint" + ldata);

                str_name = jsonObject.getString("firstname");
                id = jsonObject.getString("id");
                stremail=jsonObject.getString("email");
                // str_email=jsonObject.getString("email");
                //   id=jsonObject.getString("id");


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        GetProfile(id);


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
                        System.out.println("GETPROFILE" + object);
                        if (object.getString("status").equals("1")) {
                            GetProfileResponse getProfileResponse = gson.fromJson(responseData, GetProfileResponse.class);
                            tv_email.setText(getProfileResponse.getResult().getEmail());
                            tv_mobile.setText(getProfileResponse.getResult().getMobile());
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
