package com.ditapp.foodapp03.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterData {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private String data;

    /**
     * No args constructor for use in serialization
     *
     */
    public RegisterData() {
    }

    /**
     *
     * @param data
     * @param message
     */
    public RegisterData(String message, String data) {
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RegisterData{" +
                "message='" + message + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
