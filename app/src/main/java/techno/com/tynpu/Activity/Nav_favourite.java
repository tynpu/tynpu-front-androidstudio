package techno.com.tynpu.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
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
import techno.com.tynpu.Other.AppConfig;
import techno.com.tynpu.R;

import static com.facebook.FacebookSdk.getApplicationContext;
import static techno.com.tynpu.constant.MySharedPref.getData;

public class Nav_favourite extends AppCompatActivity {
    RelativeLayout rel_back;
    String ldata, str_email, str_mob, id;
    TextView tv_useremail, tv_usernum;
    EditText et_comment;
    Button btn_addcomment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_favourite);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN); // ******for Key board hiding
        rel_back = (RelativeLayout) findViewById(R.id.rel_back);
        tv_useremail = findViewById(R.id.tv_useremail);
        tv_usernum = findViewById(R.id.tv_usernum);
        et_comment = findViewById(R.id.et_comment);
        btn_addcomment = findViewById(R.id.btn_addcomment);


        ldata = getData(getApplicationContext(), "ldata", "null");
        if (ldata != null) {
            try {
                JSONObject jsonObject = new JSONObject(ldata);
                System.out.println("Ldataint" + ldata);

                id = jsonObject.getString("id");
                str_email = jsonObject.getString("email");
                str_mob = jsonObject.getString("mobile");
                //   id=jsonObject.getString("id");
                System.out.println("uuuuuuussssser====Email======================" + str_email);


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        try {
            tv_useremail.setText(str_email);
            tv_usernum.setText(str_mob);
        } catch (Exception e) {
            e.printStackTrace();
        }


        rel_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_addcomment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strcomm = et_comment.getText().toString();
                if (strcomm.equalsIgnoreCase("") || strcomm == null) {
                    Toast.makeText(Nav_favourite.this, "Please Enter Comment", Toast.LENGTH_SHORT).show();
                } else {
                    AddComment(id, strcomm);
                }
            }
        });


    }

    private void AddComment(final String user_id, String comment) {
        // bar.setVisibility(View.VISIBLE);
        Call<ResponseBody> call = AppConfig.PostInterface().Addcomment(user_id, comment);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //  bar.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()) {

                        String responseData = response.body().string();
                        JSONObject object = new JSONObject(responseData);

                        System.out.println("Addcomment" + object);
                        if (object.getString("status").equals("1")) {

                            Intent intent=new Intent(Nav_favourite.this,HomeMainActivity.class);
                            startActivity(intent);
                            finish();
                            Toast.makeText(Nav_favourite.this, "Done", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Nav_favourite.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(Nav_favourite.this, "Please Check Connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
