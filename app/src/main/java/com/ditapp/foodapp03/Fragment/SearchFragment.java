package com.ditapp.foodapp03.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ditapp.foodapp03.APIClient;
import com.ditapp.foodapp03.Activity.LoginActivity;
import com.ditapp.foodapp03.Activity.MainActivity;
import com.ditapp.foodapp03.Activity.ResultSearchActivity;
import com.ditapp.foodapp03.Adapter.FoodCategoryAdapter;
import com.ditapp.foodapp03.Adapter.FoodHomeAdapter;
import com.ditapp.foodapp03.Interface.ReqAPI;
import com.ditapp.foodapp03.Models.CategoryModel;
import com.ditapp.foodapp03.Models.FoodModel;
import com.ditapp.foodapp03.Models.SearchResultModel;
import com.ditapp.foodapp03.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class SearchFragment extends Fragment {

    public List<CategoryModel> foodCategoryList;
    private RecyclerView rcvCate;
    private FoodCategoryAdapter foodCategoryAdapter;
    private View inflaterLay;
    private SearchView searchView;
    private Retrofit retrofit;
    public ReqAPI callApi;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflaterLay =  inflater.inflate(R.layout.fragment_search, container, false);

        searchView = inflaterLay.findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(searchEvent);

        foodCategoryList = new ArrayList<>();
        retrofit = APIClient.getClient();
        callApi = retrofit.create(ReqAPI.class);
        CategoryHandler(callApi);
        return inflaterLay;
    }



    public final  SearchView.OnQueryTextListener searchEvent = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            if(query!=null) {
//                Toast.makeText(getContext(), "Result:" +query, Toast.LENGTH_SHORT).show();
//                SearchHandler(callApi, query);
                Intent i = new Intent(getActivity(), ResultSearchActivity.class);
                i.putExtra("keyword", query);
                startActivity(i);
                

            }
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            Log.d("TAG", "TEXTCHANGE: " + newText);
            return false;
        }
    };


    private void CategoryHandler(ReqAPI callApi) {
        Call<List<CategoryModel>> listCate = callApi.getListCategory();
        listCate.enqueue(new Callback<List<CategoryModel>>() {
            @Override
            public void onResponse(Call<List<CategoryModel>> call, Response<List<CategoryModel>> response) {
                assert response.body() != null;
//                Log.d("TAG", "LIST: " + response.body().toString());
                foodCategoryList = response.body();
                getCategoryData(foodCategoryList);
            }

            @Override
            public void onFailure(Call<List<CategoryModel>> call, Throwable t) {
                Log.d("TAG", "ERROR: " + t.getMessage());
                Toast.makeText(getContext(), " Lỗi ", Toast.LENGTH_LONG).show();

            }
        });

    }

    private void  getCategoryData(List<CategoryModel> foodCategoryList){
        rcvCate = inflaterLay.findViewById(R.id.rc_category);
        rcvCate.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext().getApplicationContext(),2);
        rcvCate.setLayoutManager(gridLayoutManager);
        FoodCategoryAdapter foodCategoryAdapter = new FoodCategoryAdapter(getContext().getApplicationContext(), foodCategoryList);
        rcvCate.setAdapter(foodCategoryAdapter);
        foodCategoryAdapter.setFoodCategoryList(foodCategoryList);
    }
}