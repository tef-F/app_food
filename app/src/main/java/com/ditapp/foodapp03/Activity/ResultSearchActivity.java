package com.ditapp.foodapp03.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ditapp.foodapp03.APIClient;
import com.ditapp.foodapp03.Adapter.FoodCategoryAdapter;
import com.ditapp.foodapp03.Adapter.FoodResultSearchAdapter;
import com.ditapp.foodapp03.Fragment.SearchFragment;
import com.ditapp.foodapp03.Interface.ReqAPI;
import com.ditapp.foodapp03.Models.CategoryModel;
import com.ditapp.foodapp03.Models.FoodModel;
import com.ditapp.foodapp03.Models.SearchResultModel;
import com.ditapp.foodapp03.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ResultSearchActivity extends AppCompatActivity {

    private Retrofit retrofit;
    public ReqAPI callApi;
    private RecyclerView rcv_result_food;
    private FoodResultSearchAdapter foodSearchAdapter;
    private TextView txtKey, txtResult;
    private ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_search2);
        txtKey = findViewById(R.id.tv_key);
        txtResult = findViewById(R.id.result_search);
        imgBack = findViewById(R.id.backItem);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        retrofit = APIClient.getClient();
        callApi = retrofit.create(ReqAPI.class);
        Intent i = getIntent();
        String keyword = i.getStringExtra("keyword");
        txtKey.setText(keyword);
        SearchHandler(callApi, keyword);

    }

    private void SearchHandler(ReqAPI callApi, String q) {
        Call<SearchResultModel> searchResult = callApi.getFoodResult(q);
        searchResult.enqueue(new Callback<SearchResultModel>() {
            @Override
            public void onResponse(Call<SearchResultModel> call, Response<SearchResultModel> response) {
                List<FoodModel> listFood =  response.body().getFoodList();
                String mess = response.body().getMessage();
                Log.d("TAG", "SEARCH RESULT: " + response.body().toString());
//                Log.d("TAG", "SEARCH RESULT INTENT : " + listFood);
//                Log.d("TAG", "SEARCH MESS : " + mess);
                txtResult.setText(mess);
                getCategoryData(listFood);

            }

            @Override
            public void onFailure(Call<SearchResultModel> call, Throwable t) {
                Log.d("TAG", "ERROR SEARCH: " + t.getMessage());
                Toast.makeText(ResultSearchActivity.this, " Lỗi ", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void  getCategoryData(List<FoodModel> listFood){
        rcv_result_food = findViewById(R.id.rc_result);
        rcv_result_food.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),1);
        rcv_result_food.setLayoutManager(gridLayoutManager);
        foodSearchAdapter = new FoodResultSearchAdapter(getApplicationContext(), listFood);
        rcv_result_food.setAdapter(foodSearchAdapter);
        foodSearchAdapter.setFoodCategoryList(listFood);
    }
}