package techno.com.tynpu.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.siyamed.shapeimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;

import java.util.List;

import techno.com.tynpu.Activity.Food_main;
import techno.com.tynpu.Model.GetFooddetailResult;
import techno.com.tynpu.R;

import static techno.com.tynpu.constant.MySharedPref.saveData;

/**
 * Created by user1 on 11/25/2017.
 */

public class GetPostAdapter_FoodAllProduct extends RecyclerView.Adapter<GetPostAdapter_FoodAllProduct.ViewHolder> {
    private Activity activity;

    private List<GetFooddetailResult> task;
    private JSONArray arr;

    public GetPostAdapter_FoodAllProduct(Activity activity, List<GetFooddetailResult> task, JSONArray arr) {
        this.activity = activity;
        this.task = task;
        this.arr = arr;
//    public GetPostAdapter_ALLRestro(Activity activity) {
//        this.activity = activity;

        // this.task = task;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_letter_resfood, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

//
//        holder.comment.setText(task.get(position).getCaption());
//        Picasso.with(activity).load(task.get(position).getPostImage()).into(holder.img_display);
//        holder.tv_cat_name.setText(task.get(position).getCategoryName());
        holder.tv_productname.setText(task.get(position).getFoodCategoryName());
        holder.foodprice.setText(task.get(position).getFoodCategoryPrice());
        try {

            Picasso.with(activity).load(task.get(position).getFoodCategoryImage()).into(holder.img_rec_one);

        } catch (Exception e) {
        }


    }

    @Override
    public int getItemCount() {
        return task.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        RoundedImageView img_rec_one;
        TextView tv_productname;
        TextView tv_subproduct, foodprice;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            img_rec_one = (RoundedImageView) itemView.findViewById(R.id.img_foodpro);
            tv_productname = (TextView) itemView.findViewById(R.id.tv_productname);
            tv_subproduct = (TextView) itemView.findViewById(R.id.tv_subproduct);
            foodprice = (TextView) itemView.findViewById(R.id.foodprice);


            // pro_image=(ImageView)itemView.findViewById(R.id.pro_image);

//            tv_cat_name=(TextView)itemView.findViewById(R.id.tv_cat_name);
//
//            cat_img=(ImageView)itemView.findViewById(R.id.cat_image);

        }

        @Override
        public void onClick(View view) {

            // String restoID=task.get(getAdapterPosition()).getId();
            String foodcatID = task.get(getAdapterPosition()).getId();
            String strfoodcatname = task.get(getAdapterPosition()).getFoodCategoryName();
            String strFoodPrice = task.get(getAdapterPosition()).getFoodCategoryPrice();
            String strFoodImage = task.get(getAdapterPosition()).getFoodCategoryImage();

            saveData(activity, "foodcatname", strfoodcatname);
            saveData(activity, "FoodcatPrice", strFoodPrice);
            saveData(activity, "FoodcatImage", strFoodImage);


            saveData(activity, "foodcatID", foodcatID);
            Log.e("foodcatID", "==========================================" + foodcatID);

            try {
                saveData(activity,"data","" + arr.getJSONObject(getAdapterPosition()).getJSONArray("food_category_product"));
                Intent intent = new Intent(activity, Food_main.class);
               // intent.putExtra("data", "" + arr.getJSONObject(getAdapterPosition()).getJSONArray("food_category_product"));
                activity.startActivity(intent);

            } catch (Exception e) {
                e.printStackTrace();
            }

            //activity.startActivity(new Intent(activity, AllSubFoodCategory.class));

        }
    }

    public void updateList(List<GetFooddetailResult> list) {
        task = list;
        notifyDataSetChanged();
    }
}