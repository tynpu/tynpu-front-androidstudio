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

import de.hdodenhof.circleimageview.CircleImageView;
import techno.com.tynpu.Activity.FoodNextActivity;
import techno.com.tynpu.Model.GetComment_Result;
import techno.com.tynpu.R;

/**
 * Created by user1 on 11/25/2017.
 */

public class GetPostAdapter_Comment extends RecyclerView.Adapter<GetPostAdapter_Comment.ViewHolder> {
    private Activity activity;

     private List<GetComment_Result> task;
    public GetPostAdapter_Comment(Activity activity,List<GetComment_Result> task) {
        this.activity = activity;
         this.task = task;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_comment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

//
        holder.tv_username.setText(task.get(position).getUserDetails().get(position).getUserName());
//        Picasso.with(activity).load(task.get(position).getPostImage()).into(holder.img_display);
        holder.tv_addcommnet.setText(task.get(position).getComment());
//
        try {
            Picasso.with(activity).load(task.get(position).getUserDetails().get(position).getImage()).error(R.drawable.icons).into(holder.profile_image_user);

        }catch (Exception e){}



    }

    @Override
    public int getItemCount() {
        return task.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


    CircleImageView profile_image_user;
    TextView tv_username,tv_addcommnet;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            profile_image_user=itemView.findViewById(R.id.profile_image_user);
            tv_username=itemView.findViewById(R.id.tv_username);
            tv_addcommnet=itemView.findViewById(R.id.tv_addcommnet);

        }

        @Override
        public void onClick(View view) {


          //  activity.startActivity(new Intent(activity, FoodNextActivity.class));

        }
    }

    public void updateList(List<GetComment_Result> list){
        task = list;
        notifyDataSetChanged();
    }
}