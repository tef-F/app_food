package com.ditapp.foodapp03.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddFoodModel {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private FileDataModel data;

    /**
     * No args constructor for use in serialization
     *
     */
    public AddFoodModel() {
    }

    /**
     *
     * @param data
     * @param message
     */
    public AddFoodModel(String message, FileDataModel data) {
        super();
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public FileDataModel getData() {
        return data;
    }

    public void setData(FileDataModel data) {
        this.data = data;
    }

}
