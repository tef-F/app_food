package com.ditapp.foodapp03.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MessageModel {

    @SerializedName("message")
    @Expose
    private String message;

    /**
     * No args constructor for use in serialization
     *
     */
    public MessageModel() {
    }

    /**
     *
     * @param message
     */
    public MessageModel(String message) {
        super();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
