package techno.com.tynpu.Adapter;

import android.app.Activity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import techno.com.tynpu.Model.GetOrder_Result;
import techno.com.tynpu.Model.Getordermodel;
import techno.com.tynpu.R;


/**
 * Created by user1 on 11/25/2017.
 */

public class GetPostAdapter_Cart extends RecyclerView.Adapter<GetPostAdapter_Cart.ViewHolder> {
    private Activity activity;

    private List<GetOrder_Result> task;
    RecyclerView rec_cart_item;
    private JSONArray arr;
    List<Getordermodel> getordermodels;

    public GetPostAdapter_Cart(Activity activity, List<GetOrder_Result> task) {
        this.activity = activity;

        this.task = task;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cartlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.tv_productname.setText(task.get(position).getItems().get(position).getFoodCategoryProductName());



      //  holder.tv_cart_subproduct1.setText(task.get(position).getItems().get(position).getSubItem().get(0).getFoodCategorySubProductName());


//        holder.tv_cart_subproduct2.setText(task.get(position).getItems().get(position).getSubItem().get(1).getFoodCategorySubProductName());
//
//
//        holder.tv_cart_subproduct3.setText(task.get(position).getItems().get(position).getSubItem().get(2).getFoodCategorySubProductName());
//
//        holder.tv_cart_subproduct4.setText(task.get(position).getItems().get(position).getSubItem().get(3).getFoodCategorySubProductName());


        holder.item_price.setText("$/"+task.get(position).getTotalPrice());

        getordermodels=new ArrayList<>();
        for (int i=0; i<task.get(position).getItems().get(0).getSubItem().size(); i++) {
            Getordermodel getordermodel=new Getordermodel();
            String  item_name=task.get(position).getItems().get(0).getSubItem().get(i).getFoodCategorySubProductName();
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^"+item_name);
            getordermodel.setSub_item_food_name(item_name);
            getordermodels.add(getordermodel);
        }

        rec_cart_item.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(activity);
        rec_cart_item.setLayoutManager(mLayoutManager);
        rec_cart_item.setItemAnimator(new DefaultItemAnimator());
        GetPostAdapter_SubCart adapter = new GetPostAdapter_SubCart(activity, getordermodels);
        rec_cart_item.setAdapter(adapter);
//
//        holder.comment.setText(task.get(position).getCaption());
//        Picasso.with(activity).load(task.get(position).getPostImage()).into(holder.img_display);
//        holder.tv_cat_name.setText(task.get(position).getCategoryName());
//
//        try {
//            //Picasso.with(activity).load(task.get(position).getCategoryImage()).into(holder.cat_img);
//
//        }catch (Exception e){}

        // holder.tv_productname.setText(task.get(position).getItemData().get(0).getItemName());
//        holder.tv_productname.setText(task.get(position).getItemData().get(1).getItemName());
//        holder.tv_cart_subproduct1.setText(task.get(position).getItemData().get(0).getSubitemData().get(0).getSubProductName());


        //holder.tv_cart_subproduct2.setText(task.get(position).getItemData().get(0).getSubitemData().get(0).getSubProductName());
//        holder.tv_cart_subproduct3.setText(task.get(position).getItemData().get(position).getSubitemData().get(2).getSubProductName());
//        holder.tv_cart_subproduct4.setText(task.get(position).getItemData().get(position).getSubitemData().get(3).getSubProductName());


    }

    @Override
    public int getItemCount() {
        return task.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        TextView tv_productname, tv_cart_subproduct1, tv_cart_subproduct2, tv_cart_subproduct3, tv_cart_subproduct4,item_price;



        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tv_productname = (TextView) itemView.findViewById(R.id.tv_cartproductname);
            rec_cart_item=itemView.findViewById(R.id.rec_cart_item);

//            tv_cart_subproduct1 = (TextView) itemView.findViewById(R.id.tv_cart_subproduct1);
//            tv_cart_subproduct2 = (TextView) itemView.findViewById(R.id.tv_cart_subproduct2);
//            tv_cart_subproduct3 = (TextView) itemView.findViewById(R.id.tv_cart_subproduct3);
//            tv_cart_subproduct4 = (TextView) itemView.findViewById(R.id.tv_cart_subproduct4);
            item_price = (TextView) itemView.findViewById(R.id.item_price);


        }

        @Override
        public void onClick(View view) {

            // String catId = task.get(getAdapterPosition()).getId();


            //  sp=new MySharedPref();
            // sp.saveData(activity,"catid",catId);

            // activity.startActivity(new Intent(activity, Sub_Categories.class));

        }
    }

    public void updateList(List<GetOrder_Result> list) {
        task = list;
        notifyDataSetChanged();
    }
}