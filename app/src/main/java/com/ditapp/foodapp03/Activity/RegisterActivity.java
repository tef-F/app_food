package com.ditapp.foodapp03.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ditapp.foodapp03.APIClient;
import com.ditapp.foodapp03.Interface.ReqAPI;
import com.ditapp.foodapp03.Models.RegisterData;
import com.ditapp.foodapp03.Models.UserModel;
import com.ditapp.foodapp03.R;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegisterActivity extends AppCompatActivity {

      private TextInputEditText txtUsername;
      private EditText txtPassword, txtRePassword;
      private Button btnRegister;
      private ImageView back;
      Retrofit retrofit;
      ReqAPI callApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getElement();
        retrofit = APIClient.getClient();
        callApi = retrofit.create(ReqAPI.class);
    }

    private void getElement() {
        txtUsername = findViewById(R.id.et_username);
        txtPassword = findViewById(R.id.et_password);
        txtRePassword = findViewById(R.id.et_confirm_password);
        btnRegister = findViewById(R.id.button_signin);
        back = findViewById(R.id.backItem);
        back.setOnClickListener(buttonClickListener);
        btnRegister.setOnClickListener(buttonClickListener);
    }
    public void registerHandler(String u, String p, String re_p) {
        if(p.equals(re_p) == true) {
            Call<RegisterData> signupCall  = callApi.registerCall(u, p, re_p);
            signupCall.enqueue(new Callback<RegisterData>() {
                @Override
                public void onResponse(Call<RegisterData> call, Response<RegisterData> response) {
                    Log.d("TAG", "LIST: " + response.body().toString());
                    Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivities(new Intent[]{intent});
                    finish();
                }
                @Override
                public void onFailure(Call<RegisterData> call, Throwable t) {
                    Log.d("TAG", "ERROR: " + t.getMessage());
                    Toast.makeText(RegisterActivity.this, "Lỗi: " +t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(RegisterActivity.this, "Mật khẩu đã nhập không khớp", Toast.LENGTH_SHORT).show();
        }
    }
//
    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v){
            switch (v.getId()) {
                case R.id.backItem:
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivities(new Intent[]{intent});
                    finish();
                    break;
                case R.id.button_signin:
                    registerHandler(txtUsername.getText().toString(), txtPassword.getText().toString(), txtRePassword.getText().toString());
                    break;
                case View.NO_ID:
                default:
                    // TODO Auto-generated method stub
                    break;
            }
        }
    };

}
