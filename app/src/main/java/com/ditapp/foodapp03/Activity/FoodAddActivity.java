package com.ditapp.foodapp03.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ditapp.foodapp03.APIClient;
import com.ditapp.foodapp03.Interface.ReqAPI;
import com.ditapp.foodapp03.Models.AddFoodModel;
import com.ditapp.foodapp03.Models.RequestAddModel;
import com.ditapp.foodapp03.Models.UserModel;
import com.ditapp.foodapp03.R;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FoodAddActivity extends AppCompatActivity {
    private LinearLayout stepLayout;
    private Button addFood, saveFoodBtn;
    private View inflater;
    private Context context;
    private TextView tvId;
    private ImageView imgDel, backItem, addImgStep;
    private EditText ed_step;
    private int i = 0;
    private List<String> listImg;
    private List<AddFoodModel> listLink;
    private Button addImgFoodBtn;
    private ShapeableImageView imgFood;
    private String namefile;
    private TextInputEditText ed_title, ed_desc, ed_ing;
    private AppCompatEditText ed_time;
    private SharedPreferences shp;
    private SharedPreferences.Editor shpEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_add);
        listImg =  new ArrayList<String>();
        listLink = new ArrayList<>();
        shp = getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
        init();

    }
    private void init() {

        context = getApplicationContext();
        stepLayout =(LinearLayout)findViewById(R.id.card_add_step);
        addFood = findViewById(R.id.btn_add_food);
        saveFoodBtn = findViewById(R.id.save_food_btn);
        setSupportActionBar(findViewById(R.id.toolbar));
        addImgFoodBtn = findViewById(R.id.add_img_btn);
        backItem = findViewById(R.id.backItem);
        imgFood = findViewById(R.id.add_img);
        ed_title = findViewById(R.id.ed_title);
        ed_desc = findViewById(R.id.ed_desc);
        ed_time = findViewById(R.id.ed_time);
        ed_ing = findViewById(R.id.ed_ing);
        //Add onClick
        saveFoodBtn.setOnClickListener(buttonClickListener);
        backItem.setOnClickListener(buttonClickListener);
        addFood.setOnClickListener(buttonClickListener);
        addImgFoodBtn.setOnClickListener(buttonClickListener);
    }


    //------------------ Xu li cac su kien nhan nut ---------------------//

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case 1:
//                if(resultCode == RESULT_OK){
                    Uri selectedImageUri = data.getData();
                    //Log.d("Test", "Path"+ selectedImageUri);
                    addImgStep.setImageURI(selectedImageUri);
                    String path = getPathFromURI(selectedImageUri);
                    listImg.add(path);
                    //Log.d("Tag", "LIST PATH: "+ listImg);
//                    utilUpload(listImg);
                    uploadStep(path);
//                }
                break;
            case 2:
//                if(requestCode == RESULT_OK) {
                    Uri selectedImageUri2 = data.getData();
                    //Log.d("Test", "Path IMG: "+ selectedImageUri2);
                    imgFood.setImageURI(selectedImageUri2);
                    uploadImg(getPathFromURI(selectedImageUri2));
//                }
        }
    }

    private final View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View v){
            switch (v.getId()) {
                case R.id.btn_add_food:
                    i++;
                    inflater = LayoutInflater.from(context).inflate(R.layout.item_row_step, null);
                    tvId = inflater.findViewById(R.id.id_item_food);
                    stepLayout.addView(inflater, stepLayout.getChildCount());
                    tvId.setText("Bước "+stepLayout.getChildCount());
                    handlerImgStep();
                    delItem(inflater);
                    break;
                case R.id.backItem:
                    onBackPressed();
                    break;
                case R.id.save_food_btn:
                    postData();
                    break;
                case R.id.add_img_btn:
                    takePictureFromGallery(2);
                    break;
                case View.NO_ID:
                default:
                    // TODO Auto-generated method stub
                    break;
            }
        }
    };
    public void delItem(View inflater) {
        imgDel = inflater.findViewById(R.id.btn_del);
        imgDel.setOnClickListener(delRowItemView);

    }
    // event click delete item row step
    private View.OnClickListener delRowItemView = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RelativeLayout re = (RelativeLayout) v.getParent();
            LinearLayout parent = (LinearLayout) re.getParent();
            stepLayout.removeView(parent);
            if(listImg.size() > 0) {
                listImg.remove(listImg.size() - 1);
            }
        }
    };
    private View.OnClickListener addImageStep = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            takePictureFromGallery();
            // intent choose img
            takePictureFromGallery(1);
            //Log.d("A", "123123213");
        }
    };

    // set onClick cho tung cai item row
    public void handlerImgStep() {
        for(int i = 0; i< stepLayout.getChildCount(); i++) {
            LinearLayout layout = (LinearLayout) stepLayout.getChildAt(i);
            addImgStep = (ImageView) layout.getChildAt(2);
            addImgStep.setOnClickListener(addImageStep);
        }
    }
    //------------------------------------------------------------------------------//

    // Xu li khi nhan nut luu
    public void postData() {
        inflater = LayoutInflater.from(context).inflate(R.layout.item_row_step, null);
        String title = ed_title.getText().toString();
        String desc = ed_desc.getText().toString();
        String time = ed_time.getText().toString();
        String ing = ed_ing.getText().toString();
        String json = shp.getString("DataUser", "");
        Gson gson = new Gson();
        UserModel dataObj = gson.fromJson(json, UserModel.class);
        int idUser = dataObj.getData().get(0).getId();
        Log.d("Tag", "\nImage: "+namefile+"\nTitle: " +title+ "\nDesc: " +desc+"\nTIme: "+time+ "\nIng: "+ing+ "\nidUser: " +idUser);
        postFoodToServer(title, time, desc, ing, idUser);
        //Intent i = new Intent()
        onBackPressed();

    }
    // ------------ RETROFIT POST ----------------------//
    public void postStepToServer(int idFood[]) {
        Retrofit retrofit = APIClient.getClient();
        ReqAPI callApi = retrofit.create(ReqAPI.class);
        if(stepLayout.getChildCount() > 0) {
            for(int i = 0; i< stepLayout.getChildCount(); i++) {
                LinearLayout layout = (LinearLayout) stepLayout.getChildAt(i);
                EditText editText = (EditText) layout.getChildAt(1);
                String pathFile = addLinkforImage(listLink.get(i).getData().getDestination(), listLink.get(i).getData().getFilename());
                Log.d("TEST", "-"+ editText.getText().toString());
                String content = editText.getText().toString();
                Log.d("LIST", "- PathFile:"+listLink.get(i).getData().getFilename());
                Call<RequestAddModel> createStepFood = callApi.createStepFood(idFood[0], i+1, pathFile, content);
                createStepFood.enqueue(new Callback<RequestAddModel>() {
                    @Override
                    public void onResponse(Call<RequestAddModel> call, Response<RequestAddModel> response) {
                        Log.d("TAG", "ID STEP: "+ response.body().getId());
                        Toast.makeText(FoodAddActivity.this, response.body().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onFailure(Call<RequestAddModel> call, Throwable t) {
                        Log.d("TAG", "EROR: "+ t.getMessage());

                    }
                });

            }
//            new sendDatatoServer().execute(listImg);
        }
    }

    public void postFoodToServer(String title, String time, String desc, String ing, int idUser) {
        Retrofit retrofit = APIClient.getClient();
        ReqAPI callApi = retrofit.create(ReqAPI.class);
        final int[] idFood = new int[1];
        Call<RequestAddModel> createFood = callApi.createFood(namefile, title, time, desc, ing, idUser);
        createFood.enqueue(new Callback<RequestAddModel>() {
            @Override
            public void onResponse(Call<RequestAddModel> call, Response<RequestAddModel> response) {
                idFood[0] = response.body().getId();
                Log.d("TAG", "ID FOOD: "+ response.body().getMessage());
                Toast.makeText(context, response.body().getMessage(),Toast.LENGTH_SHORT).show();
                postStepToServer(idFood);
            }

            @Override
            public void onFailure(Call<RequestAddModel> call, Throwable t) {
                Log.d("TAG", "EROR: "+ t.getMessage());
            }
        });
    }

    public void uploadStep(String path) {
//        String path = getPathFromURI(uri);
        File file = new File(path);
        Retrofit retrofit = APIClient.getClient();
        ReqAPI callApi = retrofit.create(ReqAPI.class);
        RequestBody requestBody = RequestBody.create(file, MediaType.parse("image/*"));
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("img_step", file.getName(), requestBody);
        Call<AddFoodModel> call = callApi.postStep(filePart);
        call.enqueue(new Callback<AddFoodModel>() {
            @Override
            public void onResponse(Call<AddFoodModel> call, Response<AddFoodModel> response) {
                //Log.d("File", "Mess: "+ response.body().getMessage());
                listLink.add(response.body());
                utilUpload(listLink);

            }
            @Override
            public void onFailure(Call<AddFoodModel> call, Throwable t) {
                Log.d("File", "Error: "+ t.getMessage());
            }
        });
    }

    public void uploadImg(String path) {
//        String path = getPathFromURI(uri);
        File file = new File(path);
        Retrofit retrofit = APIClient.getClient();
        ReqAPI callApi = retrofit.create(ReqAPI.class);
        RequestBody requestBody = RequestBody.create(file, MediaType.parse("image/*"));
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("img_food", file.getName(), requestBody);
        Call<AddFoodModel> call = callApi.postImage(filePart);
        call.enqueue(new Callback<AddFoodModel>() {
            @Override
            public void onResponse(Call<AddFoodModel> call, Response<AddFoodModel> response) {
                Log.d("File", "Mess: "+ response.body().getMessage());
                namefile = addLinkforImage(response.body().getData().getDestination(),response.body().getData().getFilename());
//                Log.d("File", "Mess: "+ listLink);

            }
            @Override
            public void onFailure(Call<AddFoodModel> call, Throwable t) {
                Log.d("File", "Error: "+ t.getMessage());
            }
        });
    }
//    public class sendDatatoServer extends AsyncTask<List<String>,List<AddFoodModel>, List<AddFoodModel>> {
//
//
//        @Override
//        protected List<AddFoodModel> doInBackground(List<String>... lists) {
//            for (int i = 0; i < lists.length; i++) {
//                upload(lists[0].get(i));
//            }
//            return listLink;
//        }
//
//        @Override
//        protected void onPostExecute(List<AddFoodModel> listLink) {
//            super.onPostExecute(listLink);
//
//            Log.d("LIST", "- PathFile:"+listLink);
//        }
//    }
     //------------------------ Cac Util support --------------------//
    private void takePictureFromGallery(int i){
        Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto, i);
    }


    private String getPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Audio.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
    public void utilUpload(List<AddFoodModel> list ) {
        for (int i = 0; i < list.size(); i++) {
            Log.d("TAG", "FileName "+i+": "+ list.get(i).getData().getFilename());
//            uploadStep(list.get(i));
        }
    }

    public String addLinkforImage(String urlImage, String fileName) {
        String[] url = urlImage.split("public");
        String urlResult = "http://192.168.1.5:3000"+url[1]+"/" +fileName;
        return urlResult;
    }
    //--------------------------------------------------------------//



    // xu li event menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_food, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.del_food_temp_item:  {
                Toast.makeText(FoodAddActivity.this, "Del", Toast.LENGTH_SHORT).show();
                return true;
            }
//            case R.id.bookmark_item: {
//                // save profile changes
//                Toast.makeText(FoodDetailActivity.this, "Bookmark true", Toast.LENGTH_SHORT).show();
//                return true;
//            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}