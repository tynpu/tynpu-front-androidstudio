package techno.com.tynpu.Activity;

import android.app.Notification;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import techno.com.tynpu.Adapter.GetPostAdapter_Cart;
import techno.com.tynpu.Adapter.GetPostAdapter_Notification;
import techno.com.tynpu.R;

/**
 * Created by user1 on 1/12/2018.
 */

public class Notification_Activity extends AppCompatActivity {
    RecyclerView.Adapter adapter=null;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification);
        recyclerView=(RecyclerView)findViewById(R.id.rec_notification);
        RecyclerView.LayoutManager mLayoutManager =new LinearLayoutManager(Notification_Activity.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter=new GetPostAdapter_Notification(Notification_Activity.this);
        recyclerView.setAdapter(adapter);

    }
}
