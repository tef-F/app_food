package com.ditapp.foodapp03.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ditapp.foodapp03.Activity.FoodDetailActivity;
import com.ditapp.foodapp03.Fragment.MeFragment;
import com.ditapp.foodapp03.Models.UserModel;
import com.ditapp.foodapp03.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.gson.Gson;

public class AddCommentSheetDialog extends BottomSheetDialogFragment {

    private SharedPreferences shp;
    private SharedPreferences.Editor shpEditor;
    private ShapeableImageView iv_imgUser;
    private TextView tv_userName;

    public static AddCommentSheetDialog newInstance() {
        AddCommentSheetDialog fragment = new AddCommentSheetDialog();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void setupDialog(Dialog dialog, int style) {
        setStyle(AddCommentSheetDialog.STYLE_NORMAL, R.style.DialogStyle);
        View contentView = View.inflate(getContext(), R.layout.fragment_addcmt_bottomsheet, null);
        dialog.setContentView(contentView);
        ((View) contentView.getParent()).setBackgroundColor(getResources().getColor(android.R.color.transparent));
        iv_imgUser = contentView.findViewById(R.id.img_user_cmt);
        tv_userName = contentView.findViewById(R.id.tv_username);

        if (shp == null) shp = this.getActivity().getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = shp.getString("DataUser", "");
        UserModel dataObj = gson.fromJson(json, UserModel.class);

        tv_userName.setText(dataObj.getData().get(0).getName());
        RequestOptions requestOptions=new RequestOptions();
        requestOptions.error(R.color.md_red_200);
        Glide.with(AddCommentSheetDialog.this)
                .load(dataObj.getData().get(0).getAvt())
                .apply(requestOptions)
                .into(iv_imgUser);
    }
}
