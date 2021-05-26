package com.ditapp.foodapp03.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ditapp.foodapp03.Activity.FoodDetailActivity;
import com.ditapp.foodapp03.Models.CategoryModel;
import com.ditapp.foodapp03.Models.FoodModel;
import com.ditapp.foodapp03.R;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

public class FoodResultSearchAdapter extends RecyclerView.Adapter<FoodResultSearchAdapter.ViewHolder> {

    private Context context;
    public List<FoodModel> foodList;

    public FoodResultSearchAdapter(Context context, List<FoodModel> foodList) {
        this.context = context;
        this.foodList = foodList;
    }
    public void setFoodCategoryList(List<FoodModel> foodList) {
        this.foodList = foodList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle, tvDesc, tvUserName;
        private ShapeableImageView imgUser;
        private AppCompatImageView imgFood;
        private MaterialCardView cardView;
        private Context context;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView)itemView.findViewById(R.id.tv_title);
            tvDesc = itemView.findViewById(R.id.tv_desc);
            tvUserName = itemView.findViewById(R.id.tv_username);
            imgFood = itemView.findViewById(R.id.img_Food);
            imgUser = itemView.findViewById(R.id.avtUser);
            cardView = itemView.findViewById(R.id.card_view_rs);
            context = itemView.getContext();
        }
    }

    @Override
    public FoodResultSearchAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_result_item_layout,parent,false);
        return new FoodResultSearchAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FoodResultSearchAdapter.ViewHolder holder, int position) {
        holder.tvTitle.setText(foodList.get(position).getTitle());
        holder.tvDesc.setText(foodList.get(position).getDescription());
        holder.tvUserName.setText(foodList.get(position).getName());
        Glide.with(context).load(foodList.get(position).getImage()).apply(RequestOptions.centerCropTransform()).into(holder.imgFood);
        Glide.with(context).load(foodList.get(position).getAvt()).apply(RequestOptions.centerCropTransform()).into(holder.imgUser);
        setClickCardView(holder, position, foodList, context);
    }

    public void setClickCardView(FoodResultSearchAdapter.ViewHolder holder, int position, List<FoodModel> foodList, Context context) {
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, " "+foodList.get(position), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(context, FoodDetailActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("idFood", foodList.get(position).getIdFood());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (foodList != null) {
            return foodList.size();
        }
        return 0;
    }
}
