package techno.com.tynpu.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import techno.com.tynpu.Activity.FoodNextActivity;
import techno.com.tynpu.Model.GetAllRestroResult;
import techno.com.tynpu.R;

import static techno.com.tynpu.constant.MySharedPref.saveData;

/**
 * Created by user1 on 11/25/2017.
 */

public class GetPostAdapter_ALLRestro extends RecyclerView.Adapter<GetPostAdapter_ALLRestro.ViewHolder> {
    private Activity activity;

     private List<GetAllRestroResult> task;
    public GetPostAdapter_ALLRestro(Activity activity, List<GetAllRestroResult> task) {
        this.activity = activity;
        this.task = task;
//    public GetPostAdapter_ALLRestro(Activity activity) {
//        this.activity = activity;

        // this.task = task;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_food_frag, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

//
//        holder.comment.setText(task.get(position).getCaption());
//        Picasso.with(activity).load(task.get(position).getPostImage()).into(holder.img_display);
//        holder.tv_cat_name.setText(task.get(position).getCategoryName());
//

        holder.tv_Restroname.setText(task.get(position).getRestaurentName());
        holder.tv_subtitle.setText(task.get(position).getSubTitle());

        try {
            Picasso.with(activity).load(task.get(position).getRestuarentImage()).into(holder.imageAllRestro);

        }catch (Exception e){}

        try{
        if (task.get(position).getOcstatus().equals("Open")){
            Picasso.with(activity).load(task.get(position).getOcstatus()).into(holder.img_restro_close);
        }else {
            Picasso.with(activity).load(task.get(position).getOcstatus()).into(holder.img_restro_open);

        }}catch (Exception e){}

    }

    @Override
    public int getItemCount() {
        return task.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            ImageView imageAllRestro,img_restro_open,img_restro_close;
        TextView tv_Restroname,tv_subtitle;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            imageAllRestro=(ImageView)itemView.findViewById(R.id.imageAllRestro);
            tv_Restroname=(TextView)itemView.findViewById(R.id.tv_Restroname);
            tv_subtitle=(TextView)itemView.findViewById(R.id.tv_subtitle);
            img_restro_open=(ImageView)itemView.findViewById(R.id.img_restro_open);
            img_restro_close=(ImageView)itemView.findViewById(R.id.img_restro_close);




           // pro_image=(ImageView)itemView.findViewById(R.id.pro_image);

//            tv_cat_name=(TextView)itemView.findViewById(R.id.tv_cat_name);
//
//            cat_img=(ImageView)itemView.findViewById(R.id.cat_image);

        }

        @Override
        public void onClick(View view) {

            String restoID=task.get(getAdapterPosition()).getId();


          saveData(activity,"resid",restoID);
            Log.e("resid","----------------------------------------------------------------"+restoID);

            activity.startActivity(new Intent(activity, FoodNextActivity.class));

        }
    }

    public void updateList(List<GetAllRestroResult> list){
        task = list;
        notifyDataSetChanged();
    }
}