package techno.com.tynpu.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import techno.com.tynpu.R;


/**
 * Created by user1 on 12/15/2017.
 */

public class Promotion_frameLayout extends Fragment {
   private static FragmentManager fragmentManager;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.promostion_framlayout,container,false);
        fragmentManager = getChildFragmentManager();
        Fragment fragment = new PromotionFragment();
        FragmentTransaction  transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.promotioncontaner, fragment);
        transaction.commit();


        return view;
    }

    public static void changeFragment(String type) {
        Fragment fragment;
        switch (type){
            case "foodaction":
                 fragment = new FoodFragment();
                break;


            default:
                fragment=new PromotionFragment();

        }
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.promotioncontaner, fragment);
        transaction.commit();
    }
}
