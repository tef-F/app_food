package com.ditapp.foodapp03.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ditapp.foodapp03.Models.CategoryModel;
import com.ditapp.foodapp03.R;

import java.util.List;

public class FoodCategoryAdapter extends RecyclerView.Adapter<FoodCategoryAdapter.ViewHolder> {

    private Context context;
    public List<CategoryModel> foodCategoryList;

    public FoodCategoryAdapter(Context context, List<CategoryModel> foodCategoryList) {
        this.context = context;
        this.foodCategoryList = foodCategoryList;
    }
    public void setFoodCategoryList(List<CategoryModel> foodCategoryList) {
        this.foodCategoryList = foodCategoryList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCate;
        ImageFilterView imgCate;

        public ViewHolder(View itemView) {
            super(itemView);
            tvCate = (TextView)itemView.findViewById(R.id.titleCate);
            imgCate = itemView.findViewById(R.id.imgCate);
        }
    }

    @Override
    public FoodCategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_category_item_layout,parent,false);
        return new FoodCategoryAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(FoodCategoryAdapter.ViewHolder holder, int position) {
        holder.tvCate.setText(foodCategoryList.get(position).getName());
        Glide.with(context).load(foodCategoryList.get(position).getImageCate()).apply(RequestOptions.centerCropTransform()).into(holder.imgCate);
    }

    @Override
    public int getItemCount() {
        if (foodCategoryList != null) {
            return foodCategoryList.size();
        }
        return 0;
    }

}
