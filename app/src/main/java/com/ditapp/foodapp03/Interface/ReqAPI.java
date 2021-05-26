package com.ditapp.foodapp03.Interface;


import com.ditapp.foodapp03.Models.AddFoodModel;
import com.ditapp.foodapp03.Models.CategoryModel;
import com.ditapp.foodapp03.Models.FoodDetailModel;
import com.ditapp.foodapp03.Models.FoodModel;
import com.ditapp.foodapp03.Models.RegisterData;
import com.ditapp.foodapp03.Models.RequestAddModel;
import com.ditapp.foodapp03.Models.SearchResultModel;
import com.ditapp.foodapp03.Models.UserModel;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ReqAPI {
    @GET("food/api/show/")
    Call<List<FoodModel>> getlistFood();

    @GET("food/api/show/{id}")
    Call<FoodDetailModel> getFoodDetail(@Path("id") int id);

    @GET("food/api/category/")
    Call<List<CategoryModel>> getListCategory();

    @POST("food/api/store")
    @FormUrlEncoded
    Call<RequestAddModel> createFood(@Field("image") String fileName, @Field("title") String title,  @Field("time") String time, @Field("description") String desc, @Field("ingredient") String ing, @Field("id_user") int id);

    @POST("step/api/store")
    @FormUrlEncoded
    Call<RequestAddModel> createStepFood(@Field("id_food") int id_food, @Field("step") int step, @Field("image") String urlImage, @Field("description") String desc);

    @POST("food/api/find")
    @FormUrlEncoded
    Call<SearchResultModel> getFoodResult(@Field("q") String q);

    @POST("/me/api/login/")
    @FormUrlEncoded
    Call<UserModel> loginCall(@Field("username") String username, @Field("password") String password);

    @POST("/me/register/")
    @FormUrlEncoded
    Call<RegisterData> registerCall(@Field("user_name") String username, @Field("password") String password, @Field("re_password") String re_password);

    @Multipart
    @POST("/food/img/upload")
    Call<AddFoodModel> postImage(@Part MultipartBody.Part img);

    @Multipart
    @POST("/food/img/step")
    Call<AddFoodModel> postStep(@Part MultipartBody.Part imgStep);


}
