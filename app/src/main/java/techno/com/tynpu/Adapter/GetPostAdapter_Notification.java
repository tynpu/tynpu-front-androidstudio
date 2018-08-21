package techno.com.tynpu.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import techno.com.tynpu.R;


/**
 * Created by user1 on 11/25/2017.
 */

public class GetPostAdapter_Notification extends RecyclerView.Adapter<GetPostAdapter_Notification.ViewHolder> {
    private Activity activity;

    // private List<CategoriesListResult> task;
    public GetPostAdapter_Notification(Activity activity) {
        this.activity = activity;

        // this.task = task;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_notification, parent, false);
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



    }

    @Override
    public int getItemCount() {
        return 12;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        //        TextView tv_cat_name;
//        CircleImageView profile_pic;
//        ImageView cat_img;
//        CheckBox status;
//        MySharedPref sp;
//        String ldata;
        ImageView pro_image;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            //pro_image=(ImageView)itemView.findViewById(R.id.pro_image);

//            tv_cat_name=(TextView)itemView.findViewById(R.id.tv_cat_name);
//
//            cat_img=(ImageView)itemView.findViewById(R.id.cat_image);

        }

        @Override
        public void onClick(View view) {
            // String catId = task.get(getAdapterPosition()).getId();


            //  sp=new MySharedPref();
            // sp.saveData(activity,"catid",catId);

            // activity.startActivity(new Intent(activity, Sub_Categories.class));

        }
    }

//    public void updateList(List<CategoriesListResult> list){
//        task = list;
//        notifyDataSetChanged();
//    }
}