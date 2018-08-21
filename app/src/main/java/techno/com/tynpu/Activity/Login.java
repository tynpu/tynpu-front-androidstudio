package techno.com.tynpu.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

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
import techno.com.tynpu.Other.GPSTracker;
import techno.com.tynpu.R;

import static techno.com.tynpu.constant.MySharedPref.saveData;

public class Login extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {
    TextView tv_signup;

    GPSTracker tracker;
    double latitude; // latitude
    double longitude; // longitude
    String str_lat, str_lon;
    EditText et_login_username, et_loginpassword;
    private ProgressBar bar;
    String str_driver_user, str_driver_password;
    private static final int PREFERENCE_PRIVATE_MODE = 0;
    String u_id;
    RelativeLayout rel_driver,rel_password;

    Button btn_fb, btn_login, btn_signup, btn_fbb;
    SharedPreferences sh_preff;
    SharedPreferences.Editor toEdit, toEdittt;
    private static final String TAG = Login.class.getSimpleName();
    private static final int RC_SIGN_IN = 007;
    private CallbackManager callbackManager;
    private GoogleApiClient mGoogleApiClient;
    private ProgressDialog mProgressDialog;
    private LoginButton loginButton;
    TextView tv_forgetpassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        facebookSDKInitialize();
        setContentView(R.layout.activity_login);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN); // ******for Key board hiding
        findID();
        super.onStart();

        //********************************** SharedPreferences (Profile set on Drawer from fb login )**********************************
        sh_preff = getSharedPreferences("profile", PREFERENCE_PRIVATE_MODE);
        toEdittt = sh_preff.edit();


        tv_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent o = new Intent(Login.this, Registration.class);
                startActivity(o);

            }
        });

        tv_forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,ChoseOTP.class);
                startActivity(intent);
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
        System.out.println("lohig_=================lat" + str_lat);
        System.out.println("login=================lon" + str_lon);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str_driver_user = et_login_username.getText().toString();
                str_driver_password = et_loginpassword.getText().toString();

                if (str_driver_user.equalsIgnoreCase("") || str_driver_user == null) {
                    et_login_username.setError("Please Enter Valid Email");
                } else if (str_driver_password.equalsIgnoreCase("") || str_driver_password == null) {
                    et_loginpassword.setError("Please Enter Valid Password");


                } else {

                    LoginCall(et_login_username.getText().toString(), et_loginpassword.getText().toString(), str_lat, str_lon);
                    //DriverLoginCall(str_driver_user,str_driver_password,str_lat,str_lon,reg_ID);
                }

            }
        });

        callbackManager = CallbackManager.Factory.create();
        //new HideKeyboard(findViewById(R.id.container),this);

        findViewById(R.id.btn_fbb).setOnClickListener(this);
        //findViewById(R.id.employee_login_link).setOnClickListener(this);
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                getUserDetails(loginResult);
            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException exception) {
            }
        });
        findViewById(R.id.google_button).setOnClickListener(this);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                //  .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

    }//******************************   end of Oncreate *************************

    public void findID() {
        tv_signup = (TextView) findViewById(R.id.tv_signup);
        btn_login = (Button) findViewById(R.id.btn_login);
        et_login_username = (EditText) findViewById(R.id.et_login_username);
        et_loginpassword = (EditText) findViewById(R.id.et_loginpassword);
        bar = (ProgressBar) findViewById(R.id.progressBar);
        tv_forgetpassword=(TextView)findViewById(R.id.tv_forgetpassword);
        rel_password=(RelativeLayout)findViewById(R.id.rel_password);
        rel_driver=(RelativeLayout)findViewById(R.id.rel_password);

    }


    private void LoginCall(final String email, String password, String lat, String lon) {
        bar.setVisibility(View.VISIBLE);
        Call<ResponseBody> call = AppConfig.PostInterface().Login(email, password, lat, lon);
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
                            saveData(getApplicationContext(), "ldata", str_result + "");
                            System.out.println("ldata" + str_result);
                            Intent intent = new Intent(Login.this, Location.class);
                            startActivity(intent);
                            finish();
                            Toast.makeText(Login.this, "Login Successfully", Toast.LENGTH_SHORT).show();

                        } else if (object.getString("status").equals("0")) {


                            YoYo.with(Techniques.Tada)
                                    .duration(700)
                                    .playOn(et_login_username);
                            YoYo.with(Techniques.Tada)
                                    .duration(700)
                                    .playOn(et_loginpassword);
                            Toast.makeText(Login.this, "Wrong Email & Password", Toast.LENGTH_SHORT).show();
                        }

                    } else ;

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                t.printStackTrace();
                bar.setVisibility(View.GONE);
                Toast.makeText(Login.this, "Please Check Internet Connection", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
           /* case R.id.forgot_password_link:
                startActivity(new Intent(LoginActivity.this,ForgotPasswordActivity.class));
                break;*/
//            case R.id.tv_signup:
//                Intent intent = new Intent(LoginActivity.this, Registeration.class);
//                startActivity(intent);
//                finish();
//                break;
//            case R.id.btn_signin:
//                //CheckValidation();
//                final EditText name = (EditText)findViewById(R.id.et_email) ;
//                final EditText password = (EditText)findViewById(R.id.et_password) ;
//                if (Validation.checkNull(name.getText().toString())){
//                    if (Validation.checkPassword(name.getText().toString())){
//                        loginUser(name.getText().toString(),password.getText().toString());
//                    }else
//                        password.setError("too small.");
//                }else
//                    name.setError("required field");
//                break;
            case R.id.google_button:
                googleSignIn();
                break;
           /* case R.id.linked_in_image_button:
                //LinkedLogin();
                break;*/
            case R.id.btn_fbb:
                loginButton.performClick();
                break;
//            case R.id.twitter_in_image_button:
//                twitterLoginButton.performClick();
//                break;
//            case R.id.employee_login_link:
//                startActivity(new Intent(LoginActivity.this, EmployeeLoginActivity.class));
//                finish();
//                break;
        }
    }

    //**************************GOOGLE Login *********************************************
    private void googleSignIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    //******************Google Account Info fetch*****************************************
    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {

            GoogleSignInAccount acct = result.getSignInAccount();

            Log.e(TAG, "display name: " + acct.getDisplayName());
            String personName = acct.getDisplayName();
            String personPhotoUrl;
            try {
                personPhotoUrl = acct.getPhotoUrl().toString();
            } catch (Exception e) {
                e.printStackTrace();
                personPhotoUrl = "";
            }
            String email = acct.getEmail();
            String socialId = acct.getId();

            SocialLogin(socialId, email, personName, "", personPhotoUrl, str_lat, str_lon);
//            txtName.setText(personName);
//            txtEmail.setText(email);
//            Glide.with(getApplicationContext()).load(personPhotoUrl)
//                    .thumbnail(0.5f)
//                    .crossFade()
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                    .into(imgProfilePic);

            //updateUI(true);
        } else {
            // Signed out, show unauthenticated UI.
            //updateUI(false);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }/*else if(requestCode == 3672){
            LISessionManager.getInstance(getApplicationContext())
                    .onActivityResult(this, requestCode, resultCode, data);
        }*/ else if (requestCode == 64206) {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
//        else if (requestCode == 140){
//            twitterLoginButton.onActivityResult(requestCode,resultCode,data);
//        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }

    //************************Show Progress Dialog****************************************
    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(true);
        }
        mProgressDialog.show();
    }

    //************************Hide Progress Dialog****************************************
    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing())
            mProgressDialog.hide();

    }

    //**********************Facebook Integration******************************************
    protected void facebookSDKInitialize() {
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
    }

    protected void getUserDetails(LoginResult loginResult) {
        GraphRequest data_request = GraphRequest.newMeRequest(
                loginResult.getAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject response,
                            GraphResponse response1) {
                        try {
                            final String socialId = (response.getString("id"));
                            String email = null;
                            final String username = (response.get("name").toString());
                            JSONObject profile_pic_data = new JSONObject(response.get("picture").toString());
                            JSONObject profile_pic_url = new JSONObject(profile_pic_data.getString("data"));
                            final String imageUrl = profile_pic_url.getString("url");
                            SocialLogin(socialId, "", username, "", imageUrl, str_lat, str_lon);
                            try {
                                email = (response.get("email").toString());
                                SocialLogin(socialId, email, username, "", imageUrl, str_lat, str_lon);
                            } catch (JSONException e) {
                                e.printStackTrace();
//                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
//
//                                LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
//                               final View alertView = inflater.inflate(R.layout.dialog_email, null);
//                                builder.setView(alertView);
//
//                                builder.setTitle("Log in");
//
//                                builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
//
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        EditText t_user = (EditText)alertView.findViewById(R.id.email_dialog);
//
//                                        String email = t_user.getText().toString();
//                                        if (email.length()>0&& Validation.checkEmail(email))
//                                            SocialLogin(socialId, email,username,"",imageUrl);
//                                        else
//                                            t_user.setError("invalid email");
//
//                                        Toast toast = Toast.makeText(getApplicationContext(), "invalid email", Toast.LENGTH_SHORT);
//                                        toast.show();
//                                    }
//                                });
//
//                                builder.setCancelable(false);
//                                AlertDialog myDialog = builder.create();
//                                myDialog.show();

                            }


//
//                            txtEmail.setText(email);
//                            txtName.setText(username);
//                            Glide.with(getApplicationContext()).load(profile_pic_url.getString("url"))
//                                    .thumbnail(0.5f).crossFade()
//                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                                    .into(imgProfilePic);
                            //updateUI(true);
                        } catch (Exception e) {

                            e.printStackTrace();

                        }
                    }

                });
        Bundle permission_param = new Bundle();
        permission_param.putString("fields", "id,name,email, picture.width(120).height(120)");
        data_request.setParameters(permission_param);
        data_request.executeAsync();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }

    private void SocialLogin(String socialIdString, String emailString, String usernameString, String mobileString, String imageUrlString, String lat, String lon) {
        bar.setVisibility(View.VISIBLE);

        Call<ResponseBody> resultCall = AppConfig.PostInterface().SocialLogin(socialIdString, emailString, usernameString, mobileString, imageUrlString, lat, lon);
        resultCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                bar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    try {
                        String responedata = response.body().string();
                        JSONObject object = new JSONObject(responedata);
                        System.out.println("------------------------- " + object);
                        String error = object.getString("status");
                        if (error.equals("1"))
                        {
                            String str_result = object.getString("result");
                            System.out.println("Social" + str_result);
                            Toast.makeText(Login.this, "Social Login Successfully", Toast.LENGTH_SHORT).show();
                            Intent intenta = new Intent(Login.this, Location.class);
                            startActivity(intenta);
                            saveData(getApplicationContext(), "ldata", str_result + "");
                        } else {

                        }


                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
//                        if (response.body().getStatus().equals("1"))
//                            Snackbar.make(parentView, R.string.string_upload_success, Snackbar.LENGTH_LONG).show();
//                        else
//                            Snackbar.make(parentView, R.string.string_upload_fail, Snackbar.LENGTH_LONG).show();
                    //AppConfig.showToast("gfjldskgsdfdjhgjfj");
                } else {

                    //Snackbar.make(parentView, R.string.string_upload_fail, Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                bar.setVisibility(View.GONE);
                Toast.makeText(Login.this, "Please Check Connection ", Toast.LENGTH_SHORT).show();


                // showErrorDialog();

            }
        });

    }

}
