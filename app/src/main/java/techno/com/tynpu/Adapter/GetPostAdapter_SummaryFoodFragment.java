package techno.com.tynpu.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.siyamed.shapeimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

import techno.com.tynpu.Model.ExtraFood_Result;
import techno.com.tynpu.Model.GetCart_Result;
import techno.com.tynpu.Model.GetSummaryFood_Result;
import techno.com.tynpu.Model.GetSummaryResult;
import techno.com.tynpu.R;

/**
 * Created by user1 on 11/25/2017.
 */

public class GetPostAdapter_SummaryFoodFragment extends RecyclerView.Adapter<GetPostAdapter_SummaryFoodFragment.ViewHolder> {
    private Activity activity;

    private List<GetCart_Result> task;


    public GetPostAdapter_SummaryFoodFragment(Activity activity, List<GetCart_Result> task) {
        this.activity = activity;
        this.task = task;
//    public GetPostAdapter_ALLRestro(Activity activity) {
//        this.activity = activity;

        // this.task = task;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_summary, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        try {

            Picasso.with(activity).load(task.get(position).getFoodCategory().getFoodCategoryImage()).into(holder.foodcatimgsum);

        } catch (Exception e) {
        }

        holder.tv_sum_item_name.setText(task.get(position).getFoodCategory().getFoodCategoryName());
        holder.sum_combo_count.setText(task.get(position).getQuantity());
        holder.tv_sumprice.setText(task.get(position).getFoodCategory().getFoodCategoryPrice());


    }

    @Override
    public int getItemCount() {
        return task.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        RoundedImageView foodcatimgsum;
        TextView tv_sum_item_name, tv_summaryveg;
        TextView sum_combo_count, tv_sumprice;


        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            foodcatimgsum = (RoundedImageView) itemView.findViewById(R.id.foodcatimgsum);
            tv_sum_item_name = (TextView) itemView.findViewById(R.id.tv_sum_item_name);
            tv_summaryveg = (TextView) itemView.findViewById(R.id.tv_summaryveg);
            sum_combo_count = (TextView) itemView.findViewById(R.id.sum_combo_count);
            tv_sumprice = (TextView) itemView.findViewById(R.id.tv_sumprice);

        }

        @Override
        public void onClick(View view) {

            // String restoID=task.get(getAdapterPosition()).getId();

            //saveData(activity,"foodcatID",foodcatID);

//            activity.startActivity(new Intent(activity, AllSubFoodCategory.class));


        }
    }


    public void updateList(List<GetCart_Result> list) {
        task = list;
        notifyDataSetChanged();
    }
}