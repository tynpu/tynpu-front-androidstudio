package techno.com.tynpu.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
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
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import techno.com.tynpu.Connection.PostInterface;
import techno.com.tynpu.Other.AppConfig;
import techno.com.tynpu.Other.GPSTracker;
import techno.com.tynpu.R;

public class Registration extends AppCompatActivity {
    TextView tv_lohinhere;
    GPSTracker tracker;
    double latitude; // latitude
    double longitude; // longitude
    String str_lat, str_lon;
    EditText et_email, et_password, et_name, et_username, et_number;
    Button btn_register;
    private ProgressBar regisprogressBar;
    CheckBox checkboxregi;
    String check_true = "No";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN); // ******for Key board hiding
        findID();
        tv_lohinhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ui = new Intent(Registration.this, Login.class);
                startActivity(ui);
            }
        });
        tracker = new GPSTracker(this);
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
        System.out.println("latttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt" + str_lat);
        System.out.println("lonnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn" + str_lon);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (check_true.equals("Yes")) {
                    String strfirst = et_name.getText().toString();
                    String strsecond = et_username.getText().toString();
                    String stremail = et_email.getText().toString();
                    String strmobile = et_number.getText().toString();
                    String strpassword = et_password.getText().toString();

                    if (strfirst.equalsIgnoreCase("") || strfirst == null) {
                      //  Toast.makeText(Registration.this, "Please enter firstname", Toast.LENGTH_SHORT).show();
                        et_name.setError("Please enter firstname");
                    } else if (strsecond.equalsIgnoreCase("") || strsecond == null) {
                      //  Toast.makeText(Registration.this, "Please enter lastname", Toast.LENGTH_SHORT).show();
                        et_username.setError("Please enter lastname");
                    } else if (stremail.equalsIgnoreCase("") || stremail == null) {
                      //  Toast.makeText(Registration.this, "Please enter email", Toast.LENGTH_SHORT).show();
                        et_email.setError("Please enter email");
                    } else if (strmobile.equalsIgnoreCase("") || strmobile == null) {
                        //Toast.makeText(Registration.this, "Please enter mobile", Toast.LENGTH_SHORT).show();
                        et_number.setError("Please enter mobile number");
                    } else if (strpassword.equalsIgnoreCase("") || strpassword == null) {
                       // Toast.makeText(Registration.this, "Please enter password", Toast.LENGTH_SHORT).show();
                        et_password.setError("Please enter password");
                    } else {
                        SignUpCall(strfirst, strsecond, stremail,strmobile, strpassword, str_lat, str_lon);
                    }

                } else {

                    Toast.makeText(Registration.this, "Please Accept term's and condition's", Toast.LENGTH_SHORT).show();
                }
            }
        });


        checkboxregi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (checkboxregi.isChecked()) {
                    check_true = "Yes";
                } else {
                    check_true = "No";
                }
            }
        });


    }//9**************************** end of oncreate ********************

    public void findID() {
        tv_lohinhere = (TextView) findViewById(R.id.tv_lohinhere);
        et_email = (EditText) findViewById(R.id.et_email);
        et_password = (EditText) findViewById(R.id.et_password);
        et_name = (EditText) findViewById(R.id.et_name);
        et_username = (EditText) findViewById(R.id.et_username);
        et_number = (EditText) findViewById(R.id.et_number);
        btn_register = (Button) findViewById(R.id.btn_register);
        regisprogressBar = (ProgressBar) findViewById(R.id.regisprogressBar);
        checkboxregi = (CheckBox) findViewById(R.id.checkboxregi);


    }


    private void SignUpCall(String firstname, String lastname, String email, String mobile, String password, String lat, String lon) {
        regisprogressBar.setVisibility(View.VISIBLE);
        Call<ResponseBody> call = AppConfig.PostInterface().signup(firstname, lastname, email, mobile, password, lat, lon);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                regisprogressBar.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()) {
                        String responseData = response.body().string();
                        JSONObject object = new JSONObject(responseData);
                        System.out.println("signup" + object);
                        if (object.getString("status").equals("1")) {

                            Intent intent = new Intent(Registration.this, Login.class);
                            startActivity(intent);
                            finish();
                            Toast.makeText(Registration.this, "Registration Successfully", Toast.LENGTH_SHORT).show();

                        } else if (object.getString("status").equals("0")) {
                            Toast.makeText(Registration.this, "Please Filled Required Fields", Toast.LENGTH_SHORT).show();
                        }
                    } else ;
                    // AppConfig.showToast("server error");
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
