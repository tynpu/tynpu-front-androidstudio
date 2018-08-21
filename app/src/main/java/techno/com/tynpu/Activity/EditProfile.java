package techno.com.tynpu.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.enums.EPickType;
import com.vansuita.pickimage.listeners.IPickResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import techno.com.tynpu.Other.AppConfig;
import techno.com.tynpu.R;

import static techno.com.tynpu.constant.MySharedPref.getData;
import static techno.com.tynpu.constant.MySharedPref.saveData;

/**
 * Created by user1 on 1/19/2018.
 */

public class EditProfile extends AppCompatActivity implements IPickResult {
    CircleImageView edit_image;
    EditText edit_name, edit_phone, et_emailupdate;
    String str_imagepath;
    RelativeLayout rel_profile;
    String str_uid;
    private ProgressBar regisprogressBar;
    String ldata;
    Button btn_updated;
    ImageView calc_img;
    TextView tv_dob;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    String str_radiobtn;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_profile);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN); // ******for Key board hiding
        edit_image = (CircleImageView) findViewById(R.id.edit_image);
        edit_name = (EditText) findViewById(R.id.edit_name);
        edit_phone = (EditText) findViewById(R.id.edit_phone);
        rel_profile = (RelativeLayout) findViewById(R.id.rel_profile);
        regisprogressBar = (ProgressBar) findViewById(R.id.regisprogressBar1);
        btn_updated = (Button) findViewById(R.id.btn_updated);
        calc_img = (ImageView) findViewById(R.id.calc_img);
        tv_dob = (TextView) findViewById(R.id.tv_dob);
        et_emailupdate = (EditText) findViewById(R.id.et_emailupdate);
        radioGroup = (RadioGroup) findViewById(R.id.radio);


        str_uid = getData(getApplicationContext(), "id", null);
        System.out.println("uusssssseeeeerrrriiiiidddddd" + str_uid);


        ldata = getData(getApplicationContext(), "ldata", "null");
        if (ldata != null) {
            try {
                JSONObject jsonObject = new JSONObject(ldata);
                str_uid = jsonObject.getString("id");

                System.out.println("idddddddddddxzcdsfsdddddddddddddddddddddddddddddddddddddddddddddddddddddddddd" + str_uid);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        rel_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PickImageDialog.build(new PickSetup()).show(EditProfile.this);
            }
        });

        calc_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ab = new AlertDialog.Builder(EditProfile.this);
                final DatePicker dp = new DatePicker(EditProfile.this);
                ab.setView(dp);
                ab.setPositiveButton("Done", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int dom = dp.getDayOfMonth();
                        int mm = dp.getMonth();
                        int yy = dp.getYear();

                        String date = dom + "/" + (mm + 1) + "/" + yy;
                        tv_dob.setText(date);
                    }
                });

                ab.show();

            }
        });


        btn_updated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file;
                file = new File(str_imagepath);
                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestFile);

                int selectedId = radioGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioButton = (RadioButton) findViewById(selectedId);

                Toast.makeText(EditProfile.this, radioButton.getText(), Toast.LENGTH_SHORT).show();


                EditCall(str_uid, edit_name.getText().toString(), edit_phone.getText().toString(), et_emailupdate.getText().toString(), tv_dob.getText().toString(), String.valueOf(radioButton.getText()), body);
            }
        });



        /* AlertDialog.Builder ab = new AlertDialog.Builder(PromoactOne.this);
                final DatePicker dp = new DatePicker(PromoactOne.this);
                ab.setView(dp);
                ab.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int dom = dp.getDayOfMonth();
                        int mm = dp.getMonth();
                        int yy = dp.getYear();

                        String date = dom + "/" + (mm + 1) + "/" + yy;
                        tv_cal.setText(date);
                    }
                });
                ab.show();*/

    }

//
//
//    public void addListenerOnButton() {
//
//
//        btnDisplay = (Button) findViewById(R.id.btnDisplay);
//
//        btnDisplay.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                // get selected radio button from radioGroup
//                int selectedId = radioGroup.getCheckedRadioButtonId();
//
//                // find the radiobutton by returned id
//                radioButton = (RadioButton) findViewById(selectedId);
//
//                Toast.makeText(MyAndroidAppActivity.this,
//                        radioButton.getText(), Toast.LENGTH_SHORT).show();
//
//            }
//
//        });
//
//    }


    private void EditCall(final String user_id, String name, String mobile, String email, String date_of_birth, String gender, MultipartBody.Part body) {
        regisprogressBar.setVisibility(View.VISIBLE);
        Call<ResponseBody> call = AppConfig.PostInterface().EditProfile(user_id, name, mobile, email, date_of_birth, gender, body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                regisprogressBar.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()) {
                        String responseData = response.body().string();
                        System.out.println("data" + responseData);
                        JSONObject object = new JSONObject(responseData);
                        System.out.println("Editprofile" + object);
                        if (object.getString("status").equals("1")) {
                            Intent intent = new Intent(EditProfile.this, HomeMainActivity.class);
                            startActivity(intent);
                            Toast.makeText(EditProfile.this, "Profile Update Successfully", Toast.LENGTH_SHORT).show();

                        } else if (object.getString("status").equals("0")) {
                            Toast.makeText(EditProfile.this, "Profile Update Unsucessfull", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onPickResult(PickResult r) {
        if (r.getError() == null) {
            //If you want the Uri.
            //Mandatory to refresh image from Uri.
            //getImageView().setImageURI(null);
            //Setting the real returned image.
            //getImageView().setImageURI(r.getUri());
            //If you want the Bitmap.
            edit_image.setImageBitmap(r.getBitmap());
            str_imagepath = r.getPath();
            //r.getPath();
        } else {
            //Handle possible errors
            //TODO: do what you have to do with r.getError();
            Toast.makeText(this, r.getError().getMessage(), Toast.LENGTH_LONG).show();
        }

    }


}
