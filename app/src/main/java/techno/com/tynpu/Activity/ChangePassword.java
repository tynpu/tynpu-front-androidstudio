package techno.com.tynpu.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
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

import static techno.com.tynpu.constant.MySharedPref.getData;
import static techno.com.tynpu.constant.MySharedPref.saveData;


/**
 * Created by user1 on 1/3/2018.
 */

public class ChangePassword extends AppCompatActivity {
    RelativeLayout rel_menu,rel_user_back;
    EditText et_otp,createpassword,et_conformpassword;
    Button btn_user_submit;
    String str_uer_otp,str_user_password;
    private ProgressBar bar;

    String str_uid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_password);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN); // ******for Key board hiding
        rel_menu=(RelativeLayout)findViewById(R.id.rel_menu);
        rel_user_back=(RelativeLayout)findViewById(R.id.rel_user_back);
        et_otp=(EditText)findViewById(R.id.et_otp);
        createpassword=(EditText)findViewById(R.id.createpassword);
        et_conformpassword=(EditText)findViewById(R.id.et_conformpassword);
        bar = (ProgressBar) findViewById(R.id.progressBarchangepassword);

        btn_user_submit=(Button)findViewById(R.id.btn_user_submit);



       str_uid=getData(getApplicationContext(),"user_id",null);
        System.out.println("uusssssseeeeerrrriiiiidddddd"+str_uid);


        et_conformpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String passwrd = createpassword.getText().toString();
               String str_driv_cpass=et_conformpassword.getText().toString();
                if (editable.length() > 0 && passwrd.length() > 0) {
                    if(!str_driv_cpass .equals(passwrd )){
                        // give an error that password and confirm password not match
                        et_conformpassword.setError("Password Not match");

                    }

                }
            }
        });




        btn_user_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str_uer_otp = et_otp.getText().toString();
                str_user_password = createpassword.getText().toString();
                if (str_uer_otp.equalsIgnoreCase("") || str_uer_otp == null) {
                    et_otp.setError("Please Enter Valid OTP");
                } else if (str_user_password.equalsIgnoreCase("") || str_user_password == null) {
                    createpassword.setError("Please Enter New Password");


                } else {

                    ChangePasswordCall(str_uid,str_uer_otp,str_user_password);

                }

            }
        });



        rel_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        rel_user_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ChangePassword.this,Login.class);
                startActivity(intent);
              //  finish();
            }
        });






    }



    //----------------------------------- retrofit-------------------------------

    private void ChangePasswordCall(String user_id, String otp_code, String new_password) {
          bar.setVisibility(View.VISIBLE);
        Call<ResponseBody> call = AppConfig.PostInterface().ChangePassword(user_id, otp_code, new_password);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                     bar.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()) {
                        String responseData = response.body().string();
                        JSONObject object = new JSONObject(responseData);
                        String str_result = object.getString("result");
                        System.out.println("changepassword" + object);
                        if (object.getString("status").equals("1")) {
                            Intent i = new Intent(ChangePassword.this, Login.class);
                            startActivity(i);
                            Toast.makeText(ChangePassword.this, "Password Changed successfully", Toast.LENGTH_LONG).show();
                        } else if (object.getString("status").equals("0")) {
                            Toast.makeText(ChangePassword.this, "OTP does not match please check otp", Toast.LENGTH_SHORT).show();
                        }
                    } else ;

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
