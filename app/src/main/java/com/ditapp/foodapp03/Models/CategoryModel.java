package com.ditapp.foodapp03.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryModel {
    @SerializedName("id_cate")
    @Expose
    private Integer idCate;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image_cate")
    @Expose
    private String imageCate;

    /**
     * No args constructor for use in serialization
     *
     */
    public CategoryModel() {
    }

    /**
     *
     * @param idCate
     * @param name
     * @param imageCate
     */
    public CategoryModel(Integer idCate, String name, String imageCate) {
        super();
        this.idCate = idCate;
        this.name = name;
        this.imageCate = imageCate;
    }

    public Integer getIdCate() {
        return idCate;
    }

    public void setIdCate(Integer idCate) {
        this.idCate = idCate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageCate() {
        return imageCate;
    }

    public void setImageCate(String imageCate) {
        this.imageCate = imageCate;
    }

}
