package com.ditapp.foodapp03.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestAddModel {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("id")
    @Expose
    private Integer id;

    /**
     * No args constructor for use in serialization
     *
     */
    public RequestAddModel() {
    }

    /**
     *
     * @param id
     * @param message
     */
    public RequestAddModel(String message, Integer id) {
        super();
        this.message = message;
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
