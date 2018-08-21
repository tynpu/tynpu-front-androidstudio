package techno.com.tynpu.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import techno.com.tynpu.R;


/**
 * Created by chinu on 16-10-2017.
 */

public class OrederPayment extends AppCompatActivity
{
    RelativeLayout back_screen;

    Button btn_continue;
    RadioButton Paypal, COD;
    String PayType="";





    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_payment);


        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN); // ******for Key board hiding

        back_screen=(RelativeLayout)findViewById(R.id.back_screen);

        back_screen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_continue = (Button) findViewById(R.id.continuepay);
      //  Creditcard = (RadioButton) findViewById(R.id.credit_card);
        Paypal = (RadioButton) findViewById(R.id.paypal);
        COD = (RadioButton) findViewById(R.id.cod);



//        Creditcard.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                PayType = "CreditCard";
//                Creditcard.setChecked(true);
//                Paypal.setChecked(false);
//                COD.setChecked(false);
//                // Toast.makeText(Payment.this,PayType, Toast.LENGTH_SHORT).show();
//
//            }
//        });

        Paypal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                PayType = "PayPal";
               // Creditcard.setChecked(false);
                Paypal.setChecked(true);
                COD.setChecked(false);
                // Toast.makeText(Payment.this,PayType, Toast.LENGTH_SHORT).show();
            }
        });

        COD.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                PayType = "COD";
               // Creditcard.setChecked(false);
                Paypal.setChecked(false);
                COD.setChecked(true);
                // Toast.makeText(Payment.this,PayType, Toast.LENGTH_SHORT).show();
            }
        });
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (PayType.equals("")) {
                    Toast.makeText(OrederPayment.this, "select payment type", Toast.LENGTH_SHORT).show();
                } else {

           OpenDialog();
                }
            }
        });

    }

    private void OpenDialog() {
        final Dialog dialog = new Dialog(OrederPayment.this);
        dialog.setContentView(R.layout.dialog_item);
        //dialog.setTitle("Custom Dialog");
        dialog.show();

        Button pay = (Button) dialog.findViewById(R.id.payment);
        LinearLayout cancel = (LinearLayout) dialog.findViewById(R.id.laycancel);
//        pay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (PayType.equals("COD")){
//
//                    Intent i=new Intent(OrederPayment.this,Checkout.class);
//                    startActivity(i);
//                    dialog.dismiss();
//
//
//                }else{
//                    // Close dialog
//                    Intent i = new Intent(getApplicationContext(), Gateway.class);
//                    startActivity(i);
//                    dialog.dismiss();
//                }
//
//
//               // Toast.makeText(Payment.this, "Success..", Toast.LENGTH_SHORT).show();
//
//
//            }
//        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}
