package techno.com.tynpu.Adapter;

import android.app.Activity;
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

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import techno.com.tynpu.Model.DataHolderActiveFood;
import techno.com.tynpu.R;

/**
 * Created by user1 on 11/25/2017.
 */

public class GetPostAdapter_SubSubFoodrequest extends RecyclerView.Adapter<GetPostAdapter_SubSubFoodrequest.ViewHolder> {
    private Activity activity;
    String Restroid, strfoodcatID, str_foodcatSubID, ldata, str_uid;
    DataHolderActiveFood hold;

    private JSONArray arr;

   // private List<GetSubSubFoodResult> task;
    public static List<DataHolderActiveFood> seleceditem;

    public GetPostAdapter_SubSubFoodrequest(Activity activity,JSONArray arr) {
        this.activity = activity;
     //   this.task = task;
        this.arr = arr;

        seleceditem = new ArrayList<>();
        for (int i = 0; i < arr.length(); i++) {

            try {
                hold = new DataHolderActiveFood();
                hold.setID( arr.getJSONObject(i).getString("id"));
                hold.setStatus("true");
                seleceditem.add(hold);
                System.out.println("activeed=====hold"+hold);
                System.out.println("activeed=====selectitem"+seleceditem);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_subsubcatproduct, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
      //  holder.tv_subsubname.setText(task.get(position).getFoodCategorySubProductName());
        try {
            holder.tv_subsubname.setText(arr.getJSONObject(position).getString("food_category_product_name"));
            holder.tv_sub_sub_price.setText(arr.getJSONObject(position).getString("food_category_product_price"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
       // holder.tv_sub_sub_price.setText(task.get(position).getFoodCategorySubProductPrice());


//        try {
//            Picasso.with(activity).load(task.get(position).getFoodCategorySubProductImage()).into(holder.img_subsub_one);
//        } catch (Exception r) {
//        }
        try {
            Picasso.with(activity).load(arr.getJSONObject(position).getString("food_category_product_image")).into(holder.img_subsub_one);
        } catch (Exception r) {
        }
       // UpdateCart(Restroid, strfoodcatID, str_foodcatSubID, task.get(position).getId(), str_uid, "Active");
        holder.toggleadd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {


                    try {
                        hold = new DataHolderActiveFood();
                        hold.setID(arr.getJSONObject(position).getString("id"));
                        hold.setStatus("true");
                        seleceditem.set(position,hold);
                        System.out.println("active=====hold"+hold);
                        System.out.println("active=====selectitem"+seleceditem);
                        // UpdateCart(Restroid, strfoodcatID, str_foodcatSubID, task.get(position).getId(), str_uid, "Active");
                        Toast.makeText(activity, "Active " + position, Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    // The toggle is enabled
                } else {


                    try {
                        hold = new DataHolderActiveFood();
                        hold.setID(arr.getJSONObject(position).getString("id"));
                        hold.setStatus("false");
                        seleceditem.set(position,hold);
                        System.out.println("deactive=====hold"+hold);
                        System.out.println("deactive=====selectitem"+seleceditem);
                        // UpdateCart(Restroid, strfoodcatID, str_foodcatSubID, task.get(position).getId(), str_uid, "Deactive");
                        Toast.makeText(activity, "Deactive " + position, Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    // The toggle is disabled

                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return arr.length();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        RoundedImageView img_subsub_one;
        TextView tv_subsubname;
        TextView tv_subsubcombo, tv_sub_sub_price;
        ToggleButton toggleadd;


        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            img_subsub_one = (RoundedImageView) itemView.findViewById(R.id.img_subsub_one);
            tv_subsubname = (TextView) itemView.findViewById(R.id.tv_subsubname);
            tv_subsubcombo = (TextView) itemView.findViewById(R.id.tv_subsubcombo);
            tv_sub_sub_price = (TextView) itemView.findViewById(R.id.tv_sub_sub_price);
            toggleadd = (ToggleButton) itemView.findViewById(R.id.toggle_sub_active);




        }

        @Override
        public void onClick(View view) {

//            String FoodcatsubProID = task.get(getAdapterPosition()).getId();
//
//
//            saveData(activity, "FoodcatsubProID", FoodcatsubProID);


        }
    }



//    public void updateList(List<GetSubSubFoodResult> list) {
//        task = list;
//        notifyDataSetChanged();
//    }
}