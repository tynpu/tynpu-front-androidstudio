package techno.com.tynpu.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;

import android.content.Intent;
import android.content.pm.PackageManager;

import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.TextView;



import techno.com.tynpu.Other.GPSTracker;
import techno.com.tynpu.R;


import static techno.com.tynpu.constant.MySharedPref.getData;
import static techno.com.tynpu.constant.MySharedPref.saveData;

public class Splash extends AppCompatActivity {


    Activity activity;
    Context context;
    private static final int PERMISSION_REQUEST_CODE = 1;
    String ldata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        activity = this;
           context = getApplicationContext();
        ldata=getData(getApplicationContext(),"ldata",null);
        System.out.println("***ldata"+ldata);
//        if (checkPermission()) {
//            proceed();
//
//        } else {
//            requestPermission();
//        }

        Thread t = new Thread() {
            public void run() {
                try {
                    sleep(2000);
                    if (ldata!=null){
                        Intent myintent = new Intent(Splash.this, Location .class);
                        startActivity(myintent);
                        finish();
                    }else {
                        Intent myintent = new Intent(Splash.this, Login.class);
                        startActivity(myintent);
                        finish();
                    }

                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();


    }


//    private boolean checkPermission() {
//        int result = ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION);
//        return result == PackageManager.PERMISSION_GRANTED;
//    }
//
//    private void requestPermission() {
//        ActivityCompat.requestPermissions(activity, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_CODE);
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
//        switch (requestCode) {
//            case PERMISSION_REQUEST_CODE:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    proceed();
//                } else {
//                    requestPermission();
//                }
//                break;
//        }
//    }
//
//    private void proceed() {
//        //**************************** Location ( Latitude & longitude ) *********************************
//        if (ActivityCompat.checkSelfPermission(getApplicationContext(),
//                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
//                ActivityCompat.checkSelfPermission(getApplicationContext(),
//                        android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//
//            ActivityCompat.requestPermissions(activity, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION},
//                    PERMISSION_REQUEST_CODE);
//        } else {
//            checkGPS();
//        }
//
//    }
//
//    private void checkGPS() {
//        GPSTracker gpsTracker = new GPSTracker(Splash.this);
//        if (gpsTracker.canGetLocation()) {
//            gpsTracker.getLatitude();
//            gpsTracker.getLongitude();
//            gpsTracker.getLocation();
//            saveData(getApplicationContext(), "LAT", String.valueOf(gpsTracker.getLatitude()));
//            saveData(getApplicationContext(), "LON", String.valueOf(gpsTracker.getLongitude()));
//
//            Thread t = new Thread() {
//                public void run() {
//                    try {
//                        sleep(2000);
//                        if (ldata!=null){
//                            Intent myintent = new Intent(Splash.this, Location .class);
//                            startActivity(myintent);
//                            finish();
//                        }else {
//                            Intent myintent = new Intent(Splash.this, Login.class);
//                            startActivity(myintent);
//                            finish();
//                        }
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
//
//        } else
//            showSettingsAlert();
//    }
//
//        private void showSettingsAlert() {
//        // Create custom dialog object
//        final Dialog dialog = new Dialog(this);
//        // Include dialog.xml file
//        dialog.setContentView(R.layout.dialog_location);
//       TextView tv_configration=(TextView)dialog.findViewById(R.id.tv_configration);
//       TextView tv_ignore=(TextView)dialog.findViewById(R.id.tv_ignore);
//        tv_ignore.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//            }
//        });
//
//        tv_configration.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                startActivityForResult(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS), 1);
//                dialog.dismiss();
//            }
//        });
//
//        dialog.show();
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == 1) {
//            checkGPS();
//        } else checkGPS();
//    }
}
