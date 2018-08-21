package techno.com.tynpu.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.scottyab.showhidepasswordedittext.ShowHidePasswordEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import techno.com.tynpu.Activity.Location;
import techno.com.tynpu.Activity.Login;
import techno.com.tynpu.Other.AppConfig;
import techno.com.tynpu.R;

import static techno.com.tynpu.constant.MySharedPref.getData;
import static techno.com.tynpu.constant.MySharedPref.saveData;

/**
 * A simple {@link Fragment} subclass.
 */
public class Nav_SecurityFragment extends Fragment {
    Button btn_changepassword;
    ShowHidePasswordEditText edit_currentpaswd,edit_newpaswd,edit_confirmpaswd;
    ProgressBar bar;
    String ldata,id;



    public Nav_SecurityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_nav__security, container, false);
        btn_changepassword=(Button)view.findViewById(R.id.btn_changepassword);
        edit_currentpaswd=(ShowHidePasswordEditText)view.findViewById(R.id.edit_currentpaswd);
        edit_newpaswd=(ShowHidePasswordEditText)view.findViewById(R.id.edit_newpaswd);
        edit_confirmpaswd=(ShowHidePasswordEditText)view.findViewById(R.id.edit_confirmpaswd);
        bar =(ProgressBar)view.findViewById(R.id.progressBar_changepassword);

        ldata = getData(getContext(), "ldata", "null");
        if (ldata != null) {
            try {
                JSONObject jsonObject = new JSONObject(ldata);
                System.out.println("Ldataint" + ldata);
                id = jsonObject.getString("id");

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        btn_changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateNewPassword(id,edit_currentpaswd.getText().toString(),edit_newpaswd.getText().toString());
            }
        });




        return view;
    }

    private void CreateNewPassword(final String user_id, String old_password, String new_password) {
        bar.setVisibility(View.VISIBLE);
        Call<ResponseBody> call = AppConfig.PostInterface().CreateNewPassword(user_id, old_password, new_password);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                bar.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()) {
                        String responseData = response.body().string();
                        JSONObject object = new JSONObject(responseData);
                        String str_result = object.getString("result");

                        System.out.println("Login" + object);
                        if (object.getString("status").equals("1")) {

                            Toast.makeText(getContext(), "Password Change Successfully", Toast.LENGTH_SHORT).show();

                        } else if (object.getString("status").equals("0")) {

                            Toast.makeText(getContext(), "Somthing Went Wrong", Toast.LENGTH_SHORT).show();
                        }

                    } else ;

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getContext(), "Please Check Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
