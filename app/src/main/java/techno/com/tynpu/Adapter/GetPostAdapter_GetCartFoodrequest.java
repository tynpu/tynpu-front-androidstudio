package techno.com.tynpu.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.github.siyamed.shapeimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import techno.com.tynpu.Model.GetCartResult;
import techno.com.tynpu.Model.GetSubSubFoodResult;
import techno.com.tynpu.Other.AppConfig;
import techno.com.tynpu.R;

/**
 * Created by user1 on 11/25/2017.
 */

public class GetPostAdapter_GetCartFoodrequest extends RecyclerView.Adapter<GetPostAdapter_GetCartFoodrequest.ViewHolder> {
    private Activity activity;

     private List<GetCartResult> task;
    public GetPostAdapter_GetCartFoodrequest(Activity activity, List<GetCartResult> task) {
        this.activity = activity;
        this.task = task;
//    public GetPostAdapter_ALLRestro(Activity activity) {
//        this.activity = activity;

        // this.task = task;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_subsubcatproduct, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

//
//        holder.comment.setText(task.get(position).getCaption());
//        Picasso.with(activity).load(task.get(position).getPostImage()).into(holder.img_display);
//        holder.subfoodprice.setText(task.get(position).getFoodCategoryProductPrice());
//        holder.tv_subprodname.setText(task.get(position).getFoodCategoryProductName());
//      holder.foodprice.setText(task.get(position).getFoodCategoryPrice());
//        try {
//
//            Picasso.with(activity).load(task.get(position).getFoodCategoryProductImage()).into(holder.img_fooddes);
//
//        }catch (Exception e){}

        holder.tv_subsubname.setText(task.get(position).getItemName());
        holder.tv_sub_sub_price.setText(task.get(position).getPrice());

        Picasso.with(activity).load(task.get(position).getImage()).into(holder.img_subsub_one);
        holder.toggleadd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    String cartID=task.get(position).getId();
                    //UpdateCart(cartID);
                    Toast.makeText(activity, "Active "+position, Toast.LENGTH_SHORT).show();
                    // The toggle is enabled
                } else {
                    String cartID=task.get(position).getId();
                 //   UpdateCart(cartID);
                    Toast.makeText(activity, "Deactive "+position, Toast.LENGTH_SHORT).show();
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
        TextView tv_subsubcombo,tv_sub_sub_price;
        ToggleButton toggleadd;


        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            img_subsub_one= (RoundedImageView) itemView.findViewById(R.id.img_subsub_one);
            tv_subsubname=(TextView)itemView.findViewById(R.id.tv_subsubname);
            tv_subsubcombo=(TextView)itemView.findViewById(R.id.tv_subsubcombo);
            tv_sub_sub_price=(TextView)itemView.findViewById(R.id.tv_sub_sub_price);
            toggleadd=(ToggleButton)itemView.findViewById(R.id.toggle_sub_active);








           // pro_image=(ImageView)itemView.findViewById(R.id.pro_image);

//            tv_cat_name=(TextView)itemView.findViewById(R.id.tv_cat_name);
//
//            cat_img=(ImageView)itemView.findViewById(R.id.cat_image);

        }

        @Override
        public void onClick(View view) {
            String cartID=task.get(getAdapterPosition()).getId();
            System.out.println("cartID============================="+cartID);




        }
    }

//
//    private void UpdateCart(String cart_id) {
//        Call<ResponseBody> call = AppConfig.PostInterface().UpdateCart(cart_id);
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                try {
//
//                    if (response.isSuccessful()) {
//                        String responseData = response.body().string();
//                        JSONObject object = new JSONObject(responseData);
//                        String status=object.getString("status");
//                        String result=object.getString("result");
//                        System.out.println("updatecart"+object);
//
//                        if (status.equals("1")){
//                            if (result.equals("Item Updated successfull")){
//                                Toast.makeText(activity, "Success", Toast.LENGTH_SHORT).show();
//                            }
//
//
//
//                        }else {
//                            Toast.makeText(activity, "Something went wrong", Toast.LENGTH_SHORT).show();
//
//                        }
//
//
//
//
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//
//            }
//        });
//    }




    public void updateList(List<GetCartResult> list){
        task = list;
        notifyDataSetChanged();
    }



}