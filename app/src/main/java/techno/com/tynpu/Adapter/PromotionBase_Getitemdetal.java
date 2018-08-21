package techno.com.tynpu.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import techno.com.tynpu.Model.DataHolderActive;
import techno.com.tynpu.Model.GetSubSubFoodResult;
import techno.com.tynpu.Model.Promotion_Base_Result;
import techno.com.tynpu.Other.AppConfig;
import techno.com.tynpu.R;

import static com.facebook.FacebookSdk.getApplicationContext;
import static techno.com.tynpu.constant.MySharedPref.getData;
import static techno.com.tynpu.constant.MySharedPref.saveData;

/**
 * Created by user1 on 11/25/2017.
 */

public class PromotionBase_Getitemdetal extends RecyclerView.Adapter<PromotionBase_Getitemdetal.ViewHolder> {
    private Activity activity;
    String Restroid, str_promotionID, proID, ldata, str_uid;
    String nameDays = "";
    DataHolderActive hold;
    private List<Promotion_Base_Result> task;
    public static List<DataHolderActive> seleceditem;

    public PromotionBase_Getitemdetal(Activity activity, List<Promotion_Base_Result> task) {
        this.activity = activity;
        this.task = task;
        seleceditem = new ArrayList<>();
        for (int i = 0; i < task.size(); i++) {
            hold = new DataHolderActive();
            hold.setID( task.get(i).getId());
            hold.setStatus("true");
            seleceditem.add(hold);
            System.out.println("activeed=====hold"+hold);
            System.out.println("activeed=====selectitem"+seleceditem);
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_promotoincatitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tv_subsubname.setText(task.get(position).getSubProductName());
        holder.tv_sub_sub_price.setText(task.get(position).getSubProductPrice());
        try {
            Picasso.with(activity).load(task.get(position).getSubProductImage()).into(holder.img_subsub_one);
        } catch (Exception r) {
        }


        //UpdateCart(Restroid, str_promotionID, proID, task.get(position).getId(), str_uid, "Active");
        holder.toggleadd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    hold = new DataHolderActive();
                    hold.setID(task.get(position).getId());
                    hold.setStatus("true");
                    seleceditem.set(position,hold);
                    System.out.println("active=====hold"+hold);
                    System.out.println("active=====selectitem"+seleceditem);

                    // UpdateCart(Restroid, str_promotionID, proID, task.get(position).getId(), str_uid, "Active");
                    Toast.makeText(activity, "Active ", Toast.LENGTH_SHORT).show();
                    // The toggle is enabled
                } else {
                    hold = new DataHolderActive();
                    hold.setID(task.get(position).getId());
                    hold.setStatus("false");
                    seleceditem.set(position,hold);
                    System.out.println("deactive=====hold"+hold);
                    System.out.println("deactive=====selectitem"+seleceditem);
                    // UpdateCart(Restroid, str_promotionID, proID,task.get(position).getId(), str_uid,"Deactive");
                    Toast.makeText(activity, "Deactive", Toast.LENGTH_SHORT).show();
                    // The toggle is disabled
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return task.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        RoundedImageView img_subsub_one;
        TextView tv_subsubname;
        TextView tv_subsubcombo, tv_sub_sub_price;
        ToggleButton toggleadd;


        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            img_subsub_one = (RoundedImageView) itemView.findViewById(R.id.img_pro_one);
            tv_subsubname = (TextView) itemView.findViewById(R.id.tv_proname);
            tv_subsubcombo = (TextView) itemView.findViewById(R.id.tv_subsubcombo);
            tv_sub_sub_price = (TextView) itemView.findViewById(R.id.tv_pro_price);
            toggleadd = (ToggleButton) itemView.findViewById(R.id.toggle_sub_active);


            Restroid = getData(activity, "restaID", null);
            str_promotionID = getData(activity, "promotionID", null);
            proID = getData(activity, "productID", null);
//            strfoodcatID=getData(activity,"foodcatID", null);
//            str_foodcatSubID = getData(activity,"foodcatproID", null);


            ldata = getData(getApplicationContext(), "ldata", "null");
            if (ldata != null) {
                try {
                    JSONObject jsonObject = new JSONObject(ldata);
                    System.out.println("Ldataint" + ldata);

                    str_uid = jsonObject.getString("id");
                    // str_email=jsonObject.getString("email");
                    //   id=jsonObject.getString("id");


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


//            System.out.println("Base===wali====res===ID=======" + Restroid);
//            System.out.println("Base===wali====food=====cat===ID=======" + strfoodcatID);
//            System.out.println("Base===wali====food=====cat==Sub=====ID=======" + str_foodcatSubID);
//            System.out.println("Base===wali====food=====User===ID=======" + str_uid);


            // pro_image=(ImageView)itemView.findViewById(R.id.pro_image);

//            tv_cat_name=(TextView)itemView.findViewById(R.id.tv_cat_name);
//
//            cat_img=(ImageView)itemView.findViewById(R.id.cat_image);

        }

        @Override
        public void onClick(View view) {
            String PromotionSubcatID = task.get(getAdapterPosition()).getId();
            String itemID=task.get(getAdapterPosition()).getProductId();

            saveData(activity,"itemID",itemID);


            saveData(activity, "promosubID", PromotionSubcatID);


            // String restoID=task.get(getAdapterPosition()).getId();
//            String foodcatID=task.get(getAdapterPosition()).getId();
//
//
//          saveData(activity,"foodcatID",foodcatID);
//
//            activity.startActivity(new Intent(activity, AllSubFoodCategory.class));

        }
    }


    private void UpdateCart(String restaurant_id, String promotion_id, String promotion_product_id, String promotion_sub_product_id, String user_id, String book_status) {
        Call<ResponseBody> call = AppConfig.PostInterface().PromotionBase(restaurant_id, promotion_id, promotion_product_id, promotion_sub_product_id, user_id, book_status);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {

                    if (response.isSuccessful()) {
                        String responseData = response.body().string();
                        JSONObject object = new JSONObject(responseData);
                        String status = object.getString("status");
                        String result = object.getString("result");
                        System.out.println("cartupdate" + object);

                        if (status.equals("1")) {
                            if (result.equals("Item Updated successfull")) {
                                // Toast.makeText(activity, "Success", Toast.LENGTH_SHORT).show();
                                Log.d("--data---", "" + object);
                            }

                        } else {
                            Toast.makeText(activity, "Something went wrong", Toast.LENGTH_SHORT).show();

                        }


                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {


            }
        });
    }


    public void updateList(List<Promotion_Base_Result> list) {
        task = list;
        notifyDataSetChanged();
    }
}