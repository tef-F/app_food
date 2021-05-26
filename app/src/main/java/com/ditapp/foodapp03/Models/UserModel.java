package com.ditapp.foodapp03.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserModel {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<DataUser> data = null;
    @SerializedName("token")
    @Expose
    private String token;

    /**
     * No args constructor for use in serialization
     *
     */
    public UserModel() {
    }

    /**
     *
     * @param data
     * @param message
     * @param token
     */
    public UserModel(String message, List<DataUser> data, String token) {
        super();
        this.message = message;
        this.data = data;
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataUser> getData() {
        return data;
    }

    public void setData(List<DataUser> data) {
        this.data = data;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "message='" + message + '\'' +
                ", data=" + data +
                ", token='" + token + '\'' +
                '}';
    }
}
