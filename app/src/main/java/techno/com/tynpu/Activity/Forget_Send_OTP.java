package techno.com.tynpu.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import techno.com.tynpu.Other.AppConfig;
import techno.com.tynpu.R;

import static techno.com.tynpu.constant.MySharedPref.saveData;


/**
 * Created by user1 on 11/29/2017.
 */

public class Forget_Send_OTP extends AppCompatActivity {

    RelativeLayout rel_back;
    EditText emailedt;
    Button btn_reset;
    String user_id = null;
    private ProgressBar bar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_password);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN); // ******for Key board hiding

        rel_back = (RelativeLayout) findViewById(R.id.rel_back);
        emailedt = (EditText) findViewById(R.id.emailedt);
        btn_reset = (Button) findViewById(R.id.btn_reset);
        bar = (ProgressBar) findViewById(R.id.progressBarsendotp);

        rel_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               ForgetCall(emailedt.getText().toString());
            }
        });


    }
    private void ForgetCall(String email) {
        bar.setVisibility(View.VISIBLE);
        Call<ResponseBody> call = AppConfig.PostInterface().SendOTPForget(email);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                bar.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()) {
                        String responseData = response.body().string();
                        JSONObject object = new JSONObject(responseData);
                        String str_result = object.getString("result");
                        System.out.println("sendotp" + object);
                        if (object.getString("status").equals("1")) {
                            user_id = object.getString("user_id");
                            saveData(getApplicationContext(), "user_id", user_id);
                            System.out.println("forgetwaliid" + user_id);
                            Intent i = new Intent(Forget_Send_OTP.this, ChangePassword.class);
                            startActivity(i);

                            Toast.makeText(Forget_Send_OTP.this, " OTP Send Please Check Email ", Toast.LENGTH_LONG).show();

                        } else if (object.getString("status").equals("0")) {
                            Toast.makeText(Forget_Send_OTP.this, "Wrong Email...please Enter Valid Email", Toast.LENGTH_SHORT).show();
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
