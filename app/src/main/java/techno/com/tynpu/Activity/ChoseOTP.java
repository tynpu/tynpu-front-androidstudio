package techno.com.tynpu.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import techno.com.tynpu.R;


/**
 * Created by user1 on 1/3/2018.
 */

public class ChoseOTP extends AppCompatActivity {
    Button btn_generateotp,alreadyotp;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chose_otp);

        btn_generateotp=(Button)findViewById(R.id.btn_generateotp);
        alreadyotp=(Button)findViewById(R.id.alreadyotp);


        btn_generateotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ChoseOTP.this,Forget_Send_OTP.class);
                startActivity(intent);
            }
        });

        alreadyotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ChoseOTP.this,ChangePassword.class);
                startActivity(intent);
            }
        });





    }
}
