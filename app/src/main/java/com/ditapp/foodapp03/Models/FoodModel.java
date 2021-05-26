package com.ditapp.foodapp03.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FoodModel {
    @SerializedName("id_food")
    @Expose
    private Integer idFood;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_name")
    @Expose
    private String userName;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("avt")
    @Expose
    private String avt;
    @SerializedName("role")
    @Expose
    private Integer role;

    /**
     * No args constructor for use in serialization
     *
     */
    public FoodModel() {
    }

    /**
     *
     * @param image
     * @param name
     * @param role
     * @param idFood
     * @param description
     * @param id
     * @param title
     * @param userName
     * @param avt
     */
    public FoodModel(Integer idFood, String image, String title, String description, Integer id, String userName, String name, String avt, Integer role) {
        super();
        this.idFood = idFood;
        this.image = image;
        this.title = title;
        this.description = description;
        this.id = id;
        this.userName = userName;
        this.name = name;
        this.avt = avt;
        this.role = role;
    }

    public Integer getIdFood() {
        return idFood;
    }

    public void setIdFood(Integer idFood) {
        this.idFood = idFood;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvt() {
        return avt;
    }

    public void setAvt(String avt) {
        this.avt = avt;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "FoodModel{" +
                "idFood=" + idFood +
                ", image='" + image + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", userName='" + userName + '\'' +
                ", name='" + name + '\'' +
                ", avt='" + avt + '\'' +
                ", role=" + role +
                '}';
    }
}