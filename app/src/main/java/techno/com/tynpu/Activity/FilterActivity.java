package techno.com.tynpu.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.crystal.crystalrangeseekbar.interfaces.OnSeekbarChangeListener;

import com.crystal.crystalrangeseekbar.widgets.CrystalSeekbar;

import techno.com.tynpu.R;


public class FilterActivity extends AppCompatActivity {
    RelativeLayout rv_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        rv_1=(RelativeLayout)findViewById(R.id.rv_1);
        rv_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        final CrystalSeekbar rangeSeekbar = (CrystalSeekbar)findViewById(R.id.rangeSeekbar7);

        final TextView seekBarValue = (TextView)findViewById(R.id.textMin3);
        final TextView txt_end=(TextView)findViewById(R.id.textMax3);
        rangeSeekbar.setOnSeekbarChangeListener(new OnSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue) {
                txt_end.setText(String.valueOf(minValue)+"  km");

            }
        });

        final CrystalSeekbar rangeSeekbar1 = (CrystalSeekbar)findViewById(R.id.rangeSeekbar8);

        final TextView seekBarValue1 = (TextView)findViewById(R.id.textMin4);
        final TextView txt_end1=(TextView)findViewById(R.id.textMax2);
        rangeSeekbar1.setOnSeekbarChangeListener(new OnSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValuee) {
                txt_end1.setText("$  "+String.valueOf(minValuee));

            }
        });
    }
}
