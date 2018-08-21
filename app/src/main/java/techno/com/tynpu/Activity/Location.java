package techno.com.tynpu.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import techno.com.tynpu.Other.GPSTracker;
import techno.com.tynpu.R;
import techno.com.tynpu.autocomplete.GeoAutoCompleteAdapter;

import static techno.com.tynpu.constant.MySharedPref.saveData;

public class Location extends AppCompatActivity {
    View view;
    Activity activity;
    Context context;
    GPSTracker tracker;
    double latitude; // latitude
    double longitude; // longitude
    //MySharedPref sp;
    String addressdata = null;
    static String Addressvalue = "";
    AutoCompleteTextView et_location;
    int countDrop1 = 0;
    RelativeLayout rel_continue;
    private static final int PERMISSION_REQUEST_CODE = 1;
    Geocoder geocoder;
    List<Address> addresses;
    String address22;
    Button btn_continue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN); // ******for Key board hiding
        activity = this;
        context = getApplicationContext();
        tracker = new GPSTracker(this);

        if (checkPermission()) {
            proceed();

        } else {
            requestPermission();
        }


        rel_continue = (RelativeLayout) findViewById(R.id.rel_continue);
        et_location = (AutoCompleteTextView) findViewById(R.id.et_location);
        btn_continue=findViewById(R.id.btn_continue);

        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tracker = new GPSTracker(Location.this);
                if (tracker.canGetLocation()) {

                    latitude = tracker.getLatitude();
                    longitude = tracker.getLongitude();
                    try {
                        addresses = geocoder.getFromLocation(latitude, longitude, 1);
                        // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                        System.out.println("Address" + addresses);

                        String address22 = null;
                        try {
                            address22 = addresses.get(0).getAddressLine(0);
                        } catch (Exception e) {
                            Toast.makeText(Location.this, "Please Turn On Your GPS ", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                        Addressvalue = address22;


                        System.out.println("");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    Toast.makeText(Location.this, "Please Turn On Your GPS ", Toast.LENGTH_SHORT).show();
                }


                String str_ccloc = et_location.getText().toString();
                if (str_ccloc.length() < 8) {
                    et_location.setText("");
                    et_location.setText(Addressvalue);
                }
            }
        });



        rel_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Location.this, HomeMainActivity.class);
                startActivity(intent);
                finish();
            }
        });

//        tracker = new GPSTracker(this);
//        if (tracker.canGetLocation()) {
//
//            latitude = tracker.getLatitude();
//            longitude = tracker.getLongitude();
//            System.out.println("Latitude" + latitude);
//            System.out.println("Longitude" + longitude);
//
//
//        }
//
//       // Geocoder geocoder;
//        //List<Address> addresses;
//        geocoder = new Geocoder(Location.this, Locale.getDefault());
//
//        try {
//            addresses = geocoder.getFromLocation(latitude, longitude, 1);
//            // Here 1 represent max location result to returned, by documents it recommended 1 to 5
//            System.out.println("Address" + addresses);
//            //   String address22 = addresses.get(0).getAddressLine(1);
//            //  String address1=addresses.get(0).getLocality();
//            String address22 = addresses.get(0).getAddressLine(0);
//            Addressvalue = address22;
////            sp = new MySharedPref();
////            sp.saveData(getApplicationContext(), "addressdata", Addressvalue + "");
//            System.out.println("");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        if (Addressvalue != null) {
//            et_location.setText(Addressvalue);
//        }
//
//        System.out.println("**************************************add**********" + Addressvalue);
////        if (addressdata != null) {
////            et_location.setText(addressdata);
////        }
//
//        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%% Location %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%" + addressdata);


        //***************************** first poinytttt  *************************************

        et_location.setThreshold(1);
        et_location.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    loadDataDrop1(et_location.getText().toString());
                 //   getLatLongFromAddress(et_location.getText().toString(), "starting point");
                  //  str_first_add = et_location.getText().toString();

                }
            }
        });


    } //***************************************************  end of on create  *******************************************

    private void loadDataDrop1(String s) {

        try {


            if (countDrop1 == 0) {
                List<String> l1 = new ArrayList<>();
                if (s == null) {

                } else {
                    l1.add(s);

                    GeoAutoCompleteAdapter ga = new GeoAutoCompleteAdapter(Location.this, l1, "" + latitude, "" + longitude);
                    et_location.setAdapter(ga);

                }

            }
            countDrop1++;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(activity, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    proceed();
                } else {
                    requestPermission();
                }
                break;
        }
    }

    private void proceed() {
        //**************************** Location ( Latitude & longitude ) *********************************
        if (ActivityCompat.checkSelfPermission(getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getApplicationContext(),
                        android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(activity, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSION_REQUEST_CODE);
        } else {
            checkGPS();
        }

    }

    private void checkGPS() {
        GPSTracker gpsTracker = new GPSTracker(Location.this);
        if (gpsTracker.canGetLocation()) {
            gpsTracker.getLatitude();
            gpsTracker.getLongitude();
            gpsTracker.getLocation();
            saveData(getApplicationContext(), "LAT", String.valueOf(gpsTracker.getLatitude()));
            saveData(getApplicationContext(), "LON", String.valueOf(gpsTracker.getLongitude()));

            Thread t = new Thread() {
                public void run() {
                    try {
                        if (tracker.canGetLocation()) {

                            latitude = tracker.getLatitude();
                            longitude = tracker.getLongitude();
                            System.out.println("Latitude" + latitude);
                            System.out.println("Longitude" + longitude);
                        }

                        geocoder = new Geocoder(Location.this, Locale.getDefault());

                        try {
                            addresses = geocoder.getFromLocation(latitude, longitude, 1);
                            // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                            System.out.println("Address" + addresses);
                            //   String address22 = addresses.get(0).getAddressLine(1);
                            //  String address1=addresses.get(0).getLocality();
                            try {
                                address22 = addresses.get(0).getAddressLine(0);
                            } catch (Exception e) {
                                Toast.makeText(Location.this, "Please Turn On Your GPS", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }
                            Addressvalue = address22;

                            System.out.println("");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (Addressvalue != null) {
                            et_location.setText(Addressvalue);
                        }

                        System.out.println("**************************************add**********" + Addressvalue);
//        if (addressdata != null) {
//            et_location.setText(addressdata);
//        }

                        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%% Location %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%" + addressdata);
                    }catch (Exception e){

                    }




                }
            };
            t.start();

//            Thread t = new Thread() {
//                public void run() {
//                    try {
////                        sleep(2000);
////                        if (ldata!=null){
////                            Intent myintent = new Intent(Splash.this, Location .class);
////                            startActivity(myintent);
////                            finish();
////                        }else {
////                            Intent myintent = new Intent(Splash.this, Login.class);
////                            startActivity(myintent);
////                            finish();
////                        }
//
//
////                                Intent myintent = new Intent(Splash.this, Login.class);
////                                startActivity(myintent);
////                                finish();
//
//
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            };
//            t.start();

        } else
            showSettingsAlert();
    }

    private void showSettingsAlert() {
        // Create custom dialog object
        final Dialog dialog = new Dialog(this);
        // Include dialog.xml file
        dialog.setContentView(R.layout.dialog_location);
        TextView tv_configration=(TextView)dialog.findViewById(R.id.tv_configration);
        TextView tv_ignore=(TextView)dialog.findViewById(R.id.tv_ignore);
        tv_ignore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        tv_configration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivityForResult(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS), 1);
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1) {
            checkGPS();
        } else checkGPS();
    }


}