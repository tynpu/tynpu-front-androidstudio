package techno.com.tynpu.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import techno.com.tynpu.Model.GetOrder_Result;
import techno.com.tynpu.Model.Getordermodel;
import techno.com.tynpu.R;


/**
 * Created by user1 on 11/25/2017.
 */

public class GetPostAdapter_SubCart extends RecyclerView.Adapter<GetPostAdapter_SubCart.ViewHolder> {
    private Activity activity;

    private List<Getordermodel> getordermodels;

    public GetPostAdapter_SubCart(Activity activity, List<Getordermodel> getordermodels) {
        this.activity = activity;
        this.getordermodels = getordermodels;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sub_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.tv_cart_subproduct1.setText(getordermodels.get(position).getSub_item_food_name());
    }

    @Override
    public int getItemCount() {
        return getordermodels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_cart_subproduct1;
        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tv_cart_subproduct1=itemView.findViewById(R.id.tv_cart_subproduct1);
        }

        @Override
        public void onClick(View view) {
        }
    }


}