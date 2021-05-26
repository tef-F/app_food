package com.ditapp.foodapp03.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ditapp.foodapp03.APIClient;
import com.ditapp.foodapp03.Adapter.FoodCommentAdapter;
import com.ditapp.foodapp03.Adapter.FoodMakingAdapter;
import com.ditapp.foodapp03.Adapter.FoodResultSearchAdapter;
import com.ditapp.foodapp03.Dialog.AddCommentSheetDialog;
import com.ditapp.foodapp03.Interface.ReqAPI;
import com.ditapp.foodapp03.Models.CommentModel;
import com.ditapp.foodapp03.Models.FoodDetailModel;
import com.ditapp.foodapp03.Models.FoodFullModel;
import com.ditapp.foodapp03.Models.FoodModel;
import com.ditapp.foodapp03.Models.StepModel;
import com.ditapp.foodapp03.Models.UserModel;
import com.ditapp.foodapp03.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FoodDetailActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private CollapsingToolbarLayout toolbarLayout;
    private RecyclerView rcv_step, rc_comment;
    private FoodMakingAdapter makingAdapter;
    private FoodCommentAdapter commentAdapter;
    private CollapsingToolbarLayout collapsingLayout;
    private ShapeableImageView img_food;
    private ShapeableImageView imgUser, imgUserMe;
    private TextView tvUsername, tvAddress, tvDesc, tvIng;
    private TextView et_comment;
    private ImageView backItem;
    public int id;
    private SharedPreferences shp;
    private SharedPreferences.Editor shpEditor;


    public Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        context = FoodDetailActivity.this;

        toolbar = findViewById(R.id.detail_toolbar);
        toolbarLayout = findViewById(R.id.collapse_toolbar);
        initFood();

        Intent i = getIntent();
        id = i.getIntExtra("idFood", 1);
        setSupportActionBar(findViewById(R.id.detail_toolbar));
        foodDetailHandler(id);
    }

    public void foodDetailHandler(int id) {
        Retrofit retrofit = APIClient.getClient();
        ReqAPI callApi = retrofit.create(ReqAPI.class);
        Call<FoodDetailModel> foodDetail = callApi.getFoodDetail(id);
        foodDetail.enqueue(new Callback<FoodDetailModel>() {
            @Override
            public void onResponse(Call<FoodDetailModel> call, Response<FoodDetailModel> response) {
                assert response.body() != null;

                List<FoodFullModel> foodList = response.body().getFood();
                List<CommentModel> commentList = response.body().getComment();
                List<StepModel> stepList = response.body().getStep();

//                Log.d("TAG", "LIST FOOD: " + foodList.get(0).getAvt());
//                Log.d("TAG", "LIST COMMENT: " + commentList);
//                Log.d("TAG", "LIST STEP: " + stepList);

                getDataFood(foodList);
                getDataStep(stepList);
                getComment(commentList);
//                Timestamp timestamp = new Timestamp(response.body().getFood().get(0).getCreatedAt().getTime());
//                Date last = new Date(timestamp.getTime());
//                Log.d("TAG", "DATE TIME: " + last);
            }

            @Override
            public void onFailure(Call<FoodDetailModel> call, Throwable t) {
                Log.d("TAG", "ERROR: " + t.getMessage());
                Toast.makeText(FoodDetailActivity.this, " Lỗi ", Toast.LENGTH_LONG).show();

            }
        });
    }
    private void createTempComment(List<CommentModel> list, int id, String content, String avt, String name, int idFood, int idCmt, int role) {
        CommentModel cmtTemp = new CommentModel();
        cmtTemp.setId(id);
        cmtTemp.setRole(role);
        cmtTemp.setContent(content);
        cmtTemp.setIdCmt(idCmt);
        cmtTemp.setName(name);
        cmtTemp.setIdFood(idFood);
        cmtTemp.setAvt(avt);
        list.add(cmtTemp);
    }
    public View.OnKeyListener addComment = new View.OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (event.getAction() == KeyEvent.ACTION_DOWN)
            {
                switch (keyCode)
                {
                    case KeyEvent.KEYCODE_DPAD_CENTER:
                    case KeyEvent.KEYCODE_ENTER:
                        Log.d("EVENT", "ENTER: "+Math.random());
                        return true;
                    default:
                        break;
                }
            }
            return false;
        }
    };
    private View.OnClickListener openDiglog = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AddCommentSheetDialog dialog = new AddCommentSheetDialog().newInstance();
            dialog.show(getSupportFragmentManager(), "Bottom sheet");
        }
    };


    private void initFood() {
        backItem = findViewById(R.id.ic_back);
        backItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        collapsingLayout = findViewById(R.id.collapse_toolbar);
        img_food = findViewById(R.id.img_food_title);
        imgUser = findViewById(R.id.img_user);
        tvUsername = findViewById(R.id.tv_username);
        tvAddress = findViewById(R.id.tv_address);
        tvDesc = findViewById(R.id.tv_desc);
        tvIng = findViewById(R.id.tv_ing);
        et_comment = findViewById(R.id.et_comment);
        tvDesc.setMovementMethod(ScrollingMovementMethod.getInstance());
        tvIng.setMovementMethod(ScrollingMovementMethod.getInstance());
        imgUserMe = findViewById(R.id.img_user_cmt);

        et_comment.setOnClickListener(openDiglog);


    }



    private void getDataFood(List<FoodFullModel> foodList) {
        collapsingLayout.setTitle(foodList.get(0).getTitle());
        Glide.with(FoodDetailActivity.this)
                .setDefaultRequestOptions(RequestOptions.centerCropTransform())
                .load(foodList.get(0).getImage())
                .into(img_food);
        Glide.with(FoodDetailActivity.this)
                .setDefaultRequestOptions(RequestOptions.centerCropTransform())
                .load(foodList.get(0).getAvt())
                .into(imgUser);
        tvUsername.setText(foodList.get(0).getName());
        tvAddress.setText(foodList.get(0).getAddress());
        tvDesc.setText(foodList.get(0).getDescription());
        tvIng.setText(foodList.get(0).getIngredient());
    }
    private void  getDataStep(List<StepModel> listStep){
        rcv_step = findViewById(R.id.rc_making);
        rcv_step.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),1);
        rcv_step.setLayoutManager(gridLayoutManager);
        makingAdapter = new FoodMakingAdapter(getApplicationContext(), listStep);
        rcv_step.setAdapter(makingAdapter);
        makingAdapter.setStepList(listStep);
    }

    private void  getComment(List<CommentModel> commentList){
        rc_comment = findViewById(R.id.rc_comment);
        rc_comment.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),1);
        rc_comment.setLayoutManager(gridLayoutManager);
        commentAdapter = new FoodCommentAdapter(getApplicationContext(), commentList);
        rc_comment.setAdapter(commentAdapter);
        commentAdapter.setCommentList(commentList);
        if (shp == null) shp = FoodDetailActivity.this.getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = shp.getString("DataUser", "");
        UserModel dataObj = gson.fromJson(json, UserModel.class);
        Glide.with(FoodDetailActivity.this)
                .setDefaultRequestOptions(RequestOptions.centerCropTransform())
                .load(dataObj.getData().get(0).getAvt())
                .into(imgUserMe);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.share_item:  {
                Toast.makeText(FoodDetailActivity.this, "Share Food", Toast.LENGTH_SHORT).show();
                return true;
            }
            case R.id.bookmark_item: {
                // save profile changes
                Toast.makeText(FoodDetailActivity.this, "Bookmark true", Toast.LENGTH_SHORT).show();
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}