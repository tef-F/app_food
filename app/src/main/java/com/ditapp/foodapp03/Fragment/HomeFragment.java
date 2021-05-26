package com.ditapp.foodapp03.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ditapp.foodapp03.APIClient;
import com.ditapp.foodapp03.Adapter.FoodHomeAdapter;
import com.ditapp.foodapp03.Interface.ReqAPI;
import com.ditapp.foodapp03.Models.FoodModel;
import com.ditapp.foodapp03.R;
import com.ditapp.foodapp03.util.ItemClickSupport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class HomeFragment extends Fragment {
    // TextView txt;
    public List<FoodModel> foodList;
    private RecyclerView rcvFood;
    FoodHomeAdapter foodHomeAdapter;
    View inflaterLay;


    public HomeFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        inflaterLay = inflater.inflate(R.layout.fragment_home,container,false);
        //txt = view.findViewById(R.id.txt);

        foodList = new ArrayList<>();


        Retrofit retrofit = APIClient.getClient();
        ReqAPI callApi = retrofit.create(ReqAPI.class);
        foodHandler(callApi);

        // Inflate the layout for this fragment
        return inflaterLay;
    }
    private void foodHandler(ReqAPI callApi) {
        Call<List<FoodModel>> listFood = callApi.getlistFood();

        listFood.enqueue(new Callback<List<FoodModel>>() {
            @Override
            public void onResponse(Call<List<FoodModel>> call, Response<List<FoodModel>> response) {
                assert response.body() != null;
//                Log.d("TAG", "LIST: " + response.body().toString());
                foodList = response.body();
                getFoodData(foodList);
            }
            @Override
            public void onFailure(Call<List<FoodModel>> call, Throwable t) {
                Log.d("TAG", "ERROR: " + t.getMessage());
                Toast.makeText(getContext(), " Lỗi ", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void  getFoodData(List<FoodModel> foodList){
        rcvFood = inflaterLay.findViewById(R.id.recyclerviewFood);
        rcvFood.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext().getApplicationContext(),2);
        rcvFood.setLayoutManager(gridLayoutManager);
        foodHomeAdapter = new FoodHomeAdapter(getContext().getApplicationContext(), foodList);
        rcvFood.setAdapter(foodHomeAdapter);
        foodHomeAdapter.setFoodList(foodList);

    }


}