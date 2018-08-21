package techno.com.tynpu.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import techno.com.tynpu.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavPaymentmethodFragment extends Fragment {


    public NavPaymentmethodFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nav_paymentmethod, container, false);
    }

}
