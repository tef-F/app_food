package com.ditapp.foodapp03.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ditapp.foodapp03.APIClient;
import com.ditapp.foodapp03.Interface.ReqAPI;
import com.ditapp.foodapp03.Models.UserModel;
import com.ditapp.foodapp03.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {
    private TextInputEditText editTxtUserName;
    private EditText editTextPassword;
    Retrofit retrofit;
    ReqAPI callApi;
    SharedPreferences shp;
    SharedPreferences.Editor shpEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acctivity_login);
        CheckLogin();
        getElement();
        retrofit = APIClient.getClient();
        callApi = retrofit.create(ReqAPI.class);
    }

    private void getElement() {
        editTxtUserName = findViewById(R.id.et_username);
        editTextPassword = findViewById(R.id.et_password);
        Button btnLogin = findViewById(R.id.button_signin);
        Button btnRegister = findViewById(R.id.button_signup);
        btnLogin.setOnClickListener(buttonClickListener);
        btnRegister.setOnClickListener(buttonClickListener);
    }

    public void loginHandler(String u, String p) {
        Call <UserModel> loginCall  = callApi.loginCall(u, p);
                loginCall.enqueue(new Callback<UserModel>() {
                    @Override
                    public void onResponse(@NotNull Call<UserModel> call, @NotNull Response<UserModel> response) {
                        assert response.body() != null;
                        Log.d("TAG", "LIST: " + response.body().toString());
                        Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        if (shp == null) shp = getSharedPreferences("myPreferences", MODE_PRIVATE);
                        shpEditor = shp.edit();
                        Gson gson = new Gson();

                        String json = gson.toJson(response.body());
                        shpEditor.putString("DataUser", json);
                        shpEditor.putString("token", response.body().getToken());
                        shpEditor.apply();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivities(new Intent[]{intent});
                        finish();
                    }

                    @Override
                    public void onFailure(@NotNull Call<UserModel> call, @NotNull Throwable t) {
                        Log.d("TAG", "ERROR: " + t.getMessage());
                        Toast.makeText(LoginActivity.this, "Lỗi Server !!", Toast.LENGTH_SHORT).show();

                    }
                });
    }

    public void registerHandler() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivities(new Intent[]{intent});
        finish();
    }

    public void CheckLogin() {
        if (shp == null) shp = getSharedPreferences("myPreferences", MODE_PRIVATE);
        new Gson();
        shp.getString("DataUser", "");
        String userName = shp.getString("token", "");

        if (userName != null && !userName.equals("")) {
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivities(new Intent[]{i});
            finish();
        }
    }

    private final View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View v){
            switch (v.getId()) {
                case R.id.button_signin:
                    loginHandler(Objects.requireNonNull(editTxtUserName.getText()).toString(), editTextPassword.getText().toString());
                    break;
                case R.id.button_signup:
                    registerHandler();
                    break;
                case View.NO_ID:
                default:
                    // TODO Auto-generated method stub
                    break;
            }
        }
    };


}
