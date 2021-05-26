package com.ditapp.foodapp03.Adapter;

import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ditapp.foodapp03.Models.StepModel;
import com.ditapp.foodapp03.R;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

public class FoodMakingAdapter extends RecyclerView.Adapter<FoodMakingAdapter.ViewHolder> {

    private Context context;
    public List<StepModel> stepList;

    public FoodMakingAdapter(Context context, List<StepModel> stepList) {
        this.context = context;
        this.stepList = stepList;
    }
    public void setStepList(List<StepModel> stepList) {
        this.stepList = stepList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvStep, tvContent;
        ShapeableImageView sivStep;

        public ViewHolder(View itemView) {
            super(itemView);
            tvStep = (TextView)itemView.findViewById(R.id.tv_stepp);
            tvContent = itemView.findViewById(R.id.tv_content);
            sivStep = itemView.findViewById(R.id.img_food_step);
        }
    }

    @Override
    public FoodMakingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_step_item_layout,parent,false);
        return new FoodMakingAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(FoodMakingAdapter.ViewHolder holder, int position) {
        holder.tvStep.setText(Integer.toString(stepList.get(position).getStep()));
//        holder.tvStep.setMovementMethod(ScrollingMovementMethod.getInstance());
        holder.tvContent.setText(stepList.get(position).getDescription());
        holder.tvContent.setMovementMethod(ScrollingMovementMethod.getInstance());
        Glide.with(context).load(stepList.get(position).getImage()).apply(RequestOptions.centerCropTransform()).into(holder.sivStep);

    }

    @Override
    public int getItemCount() {
        if (stepList != null) {
            return stepList.size();
        }
        return 0;
    }
}
