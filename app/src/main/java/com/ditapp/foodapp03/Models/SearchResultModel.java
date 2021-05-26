package com.ditapp.foodapp03.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResultModel {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("result")
    @Expose
    private List<FoodModel> foodList = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public SearchResultModel() {
    }

    /**
     *
     * @param foodList
     * @param message
     */
    public SearchResultModel(String message, List<FoodModel> foodList) {
        super();
        this.message = message;
        this.foodList = foodList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<FoodModel> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<FoodModel> foodList) {
        this.foodList = foodList;
    }

    @Override
    public String toString() {
        return "SearchResultModel{" +
                "message='" + message + '\'' +
                ", foodList=" + foodList +
                '}';
    }
}
