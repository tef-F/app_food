package com.ditapp.foodapp03.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ditapp.foodapp03.Activity.FoodDetailActivity;
import com.ditapp.foodapp03.Models.FoodModel;
import com.ditapp.foodapp03.R;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

public class FoodHomeAdapter extends RecyclerView.Adapter<FoodHomeAdapter.ViewHolder> {

    private Context context;
    public List<FoodModel> foodList;

    public FoodHomeAdapter(Context context, List<FoodModel> foodList) {
        this.context = context;
        this.foodList = foodList;
    }
    public void setFoodList(List<FoodModel> foodList) {
        this.foodList = foodList;
        notifyDataSetChanged();
    }




    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvUserName, tvTitle;
        private AppCompatImageView imgFood;
        private ShapeableImageView userAvt;
        private ImageView icLike, icSave;
        public Context context;
        public MaterialCardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            tvUserName = (TextView)itemView.findViewById(R.id.tv_username);
            tvTitle = itemView.findViewById(R.id.title);
            userAvt = (ShapeableImageView) itemView.findViewById(R.id.avtUser);
            imgFood = itemView.findViewById(R.id.imgFood);
            icLike = itemView.findViewById(R.id.ic_like);
            icSave = itemView.findViewById(R.id.ic_save);
            cardView = itemView.findViewById(R.id.card_view);
            context = itemView.getContext();

        }



    }



    @Override
    public FoodHomeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_home_item_layout,parent,false);
        return new FoodHomeAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(FoodHomeAdapter.ViewHolder holder, int position) {
        holder.tvTitle.setText(foodList.get(position).getTitle());
        holder.tvUserName.setText(foodList.get(position).getName());
        Glide.with(context).load(foodList.get(position).getImage()).apply(RequestOptions.centerCropTransform()).into(holder.imgFood);
        Glide.with(context).load(foodList.get(position).getAvt()).apply(RequestOptions.centerCropTransform()).into(holder.userAvt);
        setClickCardView(holder, position, foodList, context);

    }
    public void setClickCardView(FoodHomeAdapter.ViewHolder holder, int position, List<FoodModel> foodList, Context context) {
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

