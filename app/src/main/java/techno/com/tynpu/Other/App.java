package techno.com.tynpu.Other;

import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.multidex.MultiDexApplication;
import android.util.Base64;
import android.util.Log;


import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by user1 on 11/22/2017.
 */

public class App extends MultiDexApplication {
    private static App instance = null;
    public static SharedPreferences pref;
    public static SharedPreferences.Editor editor ;
    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        setInstance(this);
        printHashKey();

        pref = this.getSharedPreferences("TYNPU",this.MODE_PRIVATE);
        editor = pref.edit();
        //....
    }

    public static App getInstance() {
        return instance;
    }

    public void setInstance(App instance) {
        App.instance = instance;
    }
    public void printHashKey(){
        // Add code to print out the key hash
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "techno.com.tynpu",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
