package techno.com.tynpu.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import techno.com.tynpu.R;

public class OrderPayment extends AppCompatActivity {
    RelativeLayout rel_ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_payment);
        rel_ok=(RelativeLayout)findViewById(R.id.rel_ok);
        rel_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(OrderPayment.this,OrederPayment.class);
                startActivity(intent);
            }
        });
    }
}
