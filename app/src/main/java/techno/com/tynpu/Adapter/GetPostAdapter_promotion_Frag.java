package techno.com.tynpu.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import techno.com.tynpu.Activity.PromotionAvailable;
import techno.com.tynpu.Model.GetPromotionResult;
import techno.com.tynpu.R;

import static techno.com.tynpu.constant.MySharedPref.saveData;

/**
 * Created by user1 on 11/25/2017.
 */

public class GetPostAdapter_promotion_Frag extends RecyclerView.Adapter<GetPostAdapter_promotion_Frag.ViewHolder> {
    private Activity activity;

     private List<GetPromotionResult> task;
    public GetPostAdapter_promotion_Frag(Activity activity ,List<GetPromotionResult> task) {
        this.activity = activity;
         this.task = task;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_promotion_fragment, parent, false);
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

        try {
            Picasso.with(activity).load(task.get(position).getPromotionImage()).into(holder.promotion_images);

        }catch (Exception e){

        }





    }

    @Override
    public int getItemCount() {
        return task.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        //        TextView tv_cat_name;
//        CircleImageView profile_pic;
//        ImageView cat_img;
//        CheckBox status;
//        MySharedPref sp;
//        String ldata;
        ImageView promotion_images;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            promotion_images=(ImageView)itemView.findViewById(R.id.promotion_images);

//            tv_cat_name=(TextView)itemView.findViewById(R.id.tv_cat_name);
//
//            cat_img=(ImageView)itemView.findViewById(R.id.cat_image);



        }

        @Override
        public void onClick(View view) {
            // String catId = task.get(getAdapterPosition()).getId();

        String str_res_ID=task.get(getAdapterPosition()).getId();
            saveData(activity,"promotion_ID",str_res_ID);

            //  sp=new MySharedPref();
            // sp.saveData(activity,"catid",catId);

            activity.startActivity(new Intent(activity, PromotionAvailable.class));

        }
    }

    public void updateList(List<GetPromotionResult> list){
        task = list;
        notifyDataSetChanged();
    }
}