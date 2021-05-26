package com.ditapp.foodapp03.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.ditapp.foodapp03.APIClient;
import com.ditapp.foodapp03.Fragment.AddFragment;
import com.ditapp.foodapp03.Fragment.HomeFragment;
import com.ditapp.foodapp03.Fragment.MeFragment;
import com.ditapp.foodapp03.Fragment.NotificationFragment;
import com.ditapp.foodapp03.Fragment.SearchFragment;
import com.ditapp.foodapp03.Interface.ReqAPI;
import com.ditapp.foodapp03.Models.FoodModel;
import com.ditapp.foodapp03.Models.UserModel;
import com.ditapp.foodapp03.R;
import com.ditapp.foodapp03.databinding.ActivityMainBinding;
import com.google.gson.Gson;



import org.jetbrains.annotations.NotNull;

import java.util.List;

import me.ibrahimsn.lib.OnItemSelectedListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private SharedPreferences shp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FragmentTransaction homeTrans = getSupportFragmentManager().beginTransaction();
        homeTrans.replace(R.id.content, new HomeFragment());
        homeTrans.commit();

        binding.bottomNav.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public boolean onItemSelect(int i) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                switch (i) {
                    case 0:
                        transaction.replace(R.id.content, new HomeFragment());
                        break;
                    case 1:
                        transaction.replace(R.id.content, new SearchFragment());
                        break;
                    case 2:
                        transaction.replace(R.id.content, new AddFragment());
                        break;
                    case 3:
                        transaction.replace(R.id.content, new NotificationFragment());
                        break;
                    case 4:
                        transaction.replace(R.id.content, new MeFragment());
                        break;
                }
                transaction.commit();
                return false;
            }
        });
        shp = getSharedPreferences("myPreferences", MODE_PRIVATE);
        CheckLogin();
    }

    @SuppressLint("SetTextI18n")
    public void CheckLogin() {
        if (shp == null) shp = getSharedPreferences("myPreferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = shp.getString("DataUser", "");
        UserModel dataObj = gson.fromJson(json, UserModel.class);
        String userName = shp.getString("token", "");

        if (userName != null && !userName.equals("")) {
//            txtUsername.setText("Welcome " + dataObj.getData().get(0).getUserName());
        } else
        {
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
        }
    }


}