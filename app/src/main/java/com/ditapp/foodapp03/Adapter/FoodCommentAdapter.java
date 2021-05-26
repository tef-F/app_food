package com.ditapp.foodapp03.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ditapp.foodapp03.Models.CommentModel;
import com.ditapp.foodapp03.R;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

public class FoodCommentAdapter extends RecyclerView.Adapter<FoodCommentAdapter.ViewHolder> {

private Context context;
public List<CommentModel> commentList;

public FoodCommentAdapter(Context context, List<CommentModel> commentList) {
        this.context = context;
        this.commentList = commentList;
        }
public void setCommentList(List<CommentModel> commentList) {
        this.commentList = commentList;
        notifyDataSetChanged();
        }

public class ViewHolder extends RecyclerView.ViewHolder {
    TextView tvUsername, tvComment;
    ShapeableImageView imgUser;

    public ViewHolder(View itemView) {
        super(itemView);
        tvUsername = (TextView)itemView.findViewById(R.id.tv_username);
        tvComment = (TextView)itemView.findViewById(R.id.tv_commnet);
        imgUser = itemView.findViewById(R.id.avt_user);
    }
}

    @Override
    public FoodCommentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_comment_item_layout,parent,false);
        return new FoodCommentAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(FoodCommentAdapter.ViewHolder holder, int position) {
        holder.tvUsername.setText(commentList.get(position).getName());
        holder.tvComment.setText(commentList.get(position).getContent());
        Glide.with(context).load(commentList.get(position).getAvt()).apply(RequestOptions.centerCropTransform()).into(holder.imgUser);
    }

    @Override
    public int getItemCount() {
        if (commentList != null) {
            return commentList.size();
        }
        return 0;
    }
}
