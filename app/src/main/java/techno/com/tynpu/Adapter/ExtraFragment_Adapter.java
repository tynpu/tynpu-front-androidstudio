package techno.com.tynpu.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.siyamed.shapeimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import techno.com.tynpu.Model.ExtraFood_Result;
import techno.com.tynpu.Model.GetCart_Result;
import techno.com.tynpu.Other.AppConfig;
import techno.com.tynpu.R;

/**
 * Created by user1 on 11/25/2017.
 */

public class ExtraFragment_Adapter extends RecyclerView.Adapter<ExtraFragment_Adapter.ViewHolder> {
    private Activity activity;
    String str_quantity;
    String userID, ItemID;
    private List<GetCart_Result> task;

   String last="abc";

    public ExtraFragment_Adapter(Activity activity, List<GetCart_Result> task) {
        this.activity = activity;
        this.task = task;
//    public GetPostAdapter_ALLRestro(Activity activity) {
//        this.activity = activity;

        // this.task = task;
    }

    int count = 1;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_extrapromotion, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        try {

            Picasso.with(activity).load(task.get(position).getFoodCategory().getFoodCategoryImage()).into(holder.foodcatimg);

        } catch (Exception e) {
        }

        holder.sub_pro_name.setText(task.get(position).getFoodCategory().getFoodCategoryName());
        holder.pricefood.setText(task.get(position).getFoodCategory().getFoodCategoryPrice());

      //  holder.combo_quantitiy.setText(task.get(position).getComboCount()+"");
//        holder.combo_quantitiy.setText(task.get(position).getComboCount());
       // QuantityAdded(task.get(position).getUserId(), task.get(position).getPromotionProductId(), "" + count);
//        userID = task.get(position).getUserId();
//        ItemID = task.get(position).getFoodCategoryProductId();

        holder.increaseInteger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (last.equals(task.get(position).getItemId())){

                }else {
                    count=1;
                }
                count++;
                holder.quantity.setText(""+count);

                QuantityAdded(task.get(position).getUserId(), task.get(position).getItemId(), "","" + count);
           last=task.get(position).getItemId();
                Log.e("---id---","-----------------"+task.get(position).getItemId());
            }
        });

        holder.remove_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (last.equals(task.get(position).getItemId())){

                }else {
                    count=1;
                }
                if (count>1){


                count--;
                holder.quantity.setText(""+count);
                Log.e("---id---","-----------------"+task.get(position).getItemId());
                    QuantityAdded(task.get(position).getUserId(), task.get(position).getItemId(), "","" + count);
             //   QuantityAdded(task.get(position).getUserId(), task.get(position).getPromotionProductId(), "" + count);
                last=task.get(position).getItemId();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return task.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        RoundedImageView foodcatimg;
        TextView sub_pro_name;
        int minteger = 1;
        TextView quantity;
        TextView increaseInteger, remove_item, combo_quantitiy, pricefood;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            foodcatimg = (RoundedImageView) itemView.findViewById(R.id.foodcatimg);
            quantity = (TextView) itemView.findViewById(R.id.iteam_amount);
            increaseInteger = (TextView) itemView.findViewById(R.id.add_item);
            remove_item = (TextView) itemView.findViewById(R.id.remove_item);
            sub_pro_name = (TextView) itemView.findViewById(R.id.sub_pro_namepromotion);
            combo_quantitiy = (TextView) itemView.findViewById(R.id.combo_quantitiypro);
            pricefood = (TextView) itemView.findViewById(R.id.pricefood);



        }

        @Override
        public void onClick(View view) {

            // String restoID=task.get(getAdapterPosition()).getId();

//          saveData(activity,"foodcatID",foodcatID);

//            activity.startActivity(new Intent(activity, AllSubFoodCategory.class));


        }
    }


//    private void display(int number, TextView quantity) {
//
//        quantity.setText("" + number);
//
//      //  Log.e("Itemid", "====Display====wali==================" + ItemID);
//        str_quantity = String.valueOf(number);
//        System.out.println("number" + number);
//
//        //  Toast.makeText(All_Product_Detail.this, "You Selected "+number, Toast.LENGTH_SHORT).show();
//
//    }


    private void QuantityAdded(final String user_id, String item_id,String sub_item_id, String quantity) {
        // bar.setVisibility(View.VISIBLE);
        Call<ResponseBody> call = AppConfig.PostInterface().Sendquantity(user_id, item_id,sub_item_id, quantity,"Food");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //   bar.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()) {
                        String responseData = response.body().string();
                        JSONObject object = new JSONObject(responseData);
                        String str_result = object.getString("result");
                        System.out.println("Quantityaddedtopromotion" + object);
                        if (object.getString("status").equals("1"))
                        {
                            Toast.makeText(activity, "Added Quantity", Toast.LENGTH_SHORT).show();

                        } else if (object.getString("status").equals("0")) {
                            Toast.makeText(activity, "Somthing Went Wrong", Toast.LENGTH_SHORT).show();
                        }

                    } else ;

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(activity, "Please Check Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void updateList(List<GetCart_Result> list) {
        task = list;
        notifyDataSetChanged();
    }
}