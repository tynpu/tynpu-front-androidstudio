package techno.com.tynpu.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import techno.com.tynpu.Activity.Food_main;
import techno.com.tynpu.Model.GetPromotionAvailbleResult;
import techno.com.tynpu.R;

/**
 * Created by user1 on 11/25/2017.
 */

public class GetPostAdapter_food_orderfrag extends RecyclerView.Adapter<GetPostAdapter_food_orderfrag.ViewHolder> {
    private Activity activity;

     private List<GetPromotionAvailbleResult>task;
    public GetPostAdapter_food_orderfrag(Activity activity, List<GetPromotionAvailbleResult> task) {
        this.activity = activity;

         this.task = task;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_food_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

//
//        holder.comment.setText(task.get(position).getCaption());
//        Picasso.with(activity).load(task.get(position).getPostImage()).into(holder.img_display);
//        holder.tv_cat_name.setText(task.get(position).getCategoryName());
//
//        try {
//            //Picasso.with(activity).load(task.get(position).getCategoryImage()).into(holder.cat_img);
//
//        }catch (Exception e){}

        holder.tv_available_foodname.setText(task.get(position).getProductName());
        holder.available_res_name.setText(task.get(position).getRestaurant().getRestaurentName());

        try {
            Picasso.with(activity).load(task.get(position).getProductImage()).into(holder.availebleproimg);

        }catch (Exception e){}

        try{
            if (task.get(position).getRestaurant().getOcstatus().equals("Open")){
                Picasso.with(activity).load(task.get(position).getRestaurant().getOcstatus()).into(holder.img_availblerestro_close);
            }else {
                Picasso.with(activity).load(task.get(position).getRestaurant().getOcstatus()).into(holder.img_availablerestro_open);

            }}catch (Exception e){}


    }

    @Override
    public int getItemCount() {
        return task.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {



        ImageView availebleproimg;
        TextView available_res_name,tv_available_foodname;
        ImageView img_availablerestro_open,img_availblerestro_close;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            available_res_name=(TextView)itemView.findViewById(R.id.available_res_name);
            tv_available_foodname=(TextView)itemView.findViewById(R.id.tv_available_foodname);
            availebleproimg=(ImageView)itemView.findViewById(R.id.availebleproimg);
            img_availblerestro_close=(ImageView)itemView.findViewById(R.id.img_availblerestro_close);
            img_availablerestro_open=(ImageView)itemView.findViewById(R.id.img_availablerestro_open);









        }

        @Override
        public void onClick(View view) {
            // String catId = task.get(getAdapterPosition()).getId();


            //  sp=new MySharedPref();
            // sp.saveData(activity,"catid",catId);

            activity.startActivity(new Intent(activity, Food_main.class));



        }
    }

    public void updateList(List<GetPromotionAvailbleResult> list){
        task = list;
        notifyDataSetChanged();
    }
}