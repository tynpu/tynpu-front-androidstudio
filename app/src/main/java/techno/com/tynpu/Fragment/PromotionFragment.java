package techno.com.tynpu.Fragment;


import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import techno.com.tynpu.Activity.FilterActivity;
import techno.com.tynpu.Adapter.GetPostAdapter_ALLRestro;
import techno.com.tynpu.Adapter.GetPostAdapter_promotion_Frag;
import techno.com.tynpu.Other.AppConfig;
import techno.com.tynpu.R;
import techno.com.tynpu.Response.GetAllRestroResponse;
import techno.com.tynpu.Response.GetPromotionResponse;

/**
 * A simple {@link Fragment} subclass.
 */
public class PromotionFragment extends Fragment {
    Button btn_profilter;
    RecyclerView recyclerView;
    private  ProgressBar bar;

    public PromotionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_promotion, container, false);
        btn_profilter=(Button)view.findViewById(R.id.btn_profilter);

        btn_profilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), FilterActivity.class);
                startActivity(intent);
            }
        });

        recyclerView = (RecyclerView)view.findViewById(R.id.task_promotion_rec);
        bar = (ProgressBar)view.findViewById(R.id.progressBarpromotion);
//        recyclerView.setHasFixedSize(true);

        // adapter = new MuralRVAdapter(getActivity());
//        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
//        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(5), true));
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        GetPostAdapter_promotion_Frag adapter = new GetPostAdapter_promotion_Frag(getActivity());
//        recyclerView.setAdapter(adapter);

        AllPromotion();

        return view;
    }

    private void AllPromotion()
    {
        bar.setVisibility(View.VISIBLE);
        Call<ResponseBody> call = AppConfig.PostInterface().Promotion();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                bar.setVisibility(View.INVISIBLE);
                if (response.isSuccessful()) {
                    try {
                        String responedata=response.body().string();
                        JSONObject object=new JSONObject(responedata);
                        String error = object.getString("status");
                        System.out.println("AllPromotion"+object);
                        if (error.equals("1"))
                        {
                            Gson gson = new Gson();
                            GetPromotionResponse successResponse = gson.fromJson(responedata,GetPromotionResponse.class);
                            recyclerView.setHasFixedSize(true);
                            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
                            recyclerView.setLayoutManager(mLayoutManager);
                            recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(3), true));
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            GetPostAdapter_promotion_Frag adapter = new GetPostAdapter_promotion_Frag(getActivity(),successResponse.getResult());
                            recyclerView.setAdapter(adapter);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    //Snackbar.make(parentView, R.string.string_upload_fail, Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                bar.setVisibility(View.INVISIBLE);
                Toast.makeText(getContext(), "Please check connection", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });

    }


    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge)
            {
                outRect.left = spacing - column; //spacing / spanCount; // spacing - column  ((1f / spanCount) * spacing)
                outRect.right = (column + 1);   //spacing / spanCount; // (column + 1)  ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column;   //spacing / spanCount; // column  ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1);  //spacing / spanCount; // spacing - (column + 1)  ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

}
